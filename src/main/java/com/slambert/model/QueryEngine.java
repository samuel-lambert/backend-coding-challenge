// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slambert.utils.GeoUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class contains everything needed to query the autocomplete
 * data and obtain "scored" results according to client's location.
 */
@Component
public class QueryEngine {

    private static final String CITIES_DATA_FILE = "cities_canada-usa.json";
    private static final String SPONSORED_TOKENS_FILE = "sponsored_tokens.json";

    private CityTrie trie;
    private List<City> cities;
    private Map<String, City> sponsoredCities;

    /**
     * Constructs the autocomplete trie with the data obtained in the
     * included JSON file.
     */
    public QueryEngine() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            InputStream is = classLoader.getResourceAsStream(CITIES_DATA_FILE);
            cities = Arrays.asList(mapper.readValue(is, City[].class));
            trie = new CityTrie();

            // Populating trie with city names as keys
            for (City c : cities) {
                trie.add(c.getName(), c);

                // Also adding alternate names with the same city object
                for (String s : c.getAlternateNames()) {
                    trie.add(s, c);
                }
            }

            // Also loading sponsored city data
            is = classLoader.getResourceAsStream(SPONSORED_TOKENS_FILE);
            sponsoredCities = mapper.readValue(is, new TypeReference<Map<String, City>>() {});
        } catch (Exception e) {
            throw new IOException("Unexpected error: could not parse JSON city data");
        }
    }

    /**
     * Returns sorted city suggestions according to the query string. Scores are added to
     * each city according to its distance between itself and client location. Scores can
     * also be set to 1.0 (maximum score possible) if the city is found to be "sponsored".
     *
     * @param q              query string sent by the client
     * @param clientLocation location of the client
     * @return city suggestions according to the query string
     */
    public Map<String, Set<CityResponse>> query(String q, Location clientLocation) {
        // This funky return type is used to map JSON responses to the expected format...

        Map<String, Set<CityResponse>> suggestions = new HashMap<>();
        Set<CityResponse> cityResponses = new TreeSet<>();
        Set<City> suggestedCities = trie.get(q);

        // If query parameter matches exactly a sponsored city token, we add and prioritize that city
        City mock = sponsoredCities.get(q);
        if (mock != null) {
            City sponsoredCity = trie.get(mock.getName(), mock.getState(), mock.getCountry());
            if (sponsoredCity != null) {
                cityResponses.add(new CityResponse(sponsoredCity, 1.0));
            }
        }

        // Adding regular suggestions
        for (City c : suggestedCities) {
            cityResponses.add(new CityResponse(c, rankByDistance(c, clientLocation)));
        }

        suggestions.put("suggestions", cityResponses);
        return suggestions;
    }

    private Double rankByDistance(City city, Location clientLocation) {
        Location cityLocation = new Location(city.getLatitude(), city.getLongitude());
        Double distance = GeoUtils.calculateDistance(clientLocation, cityLocation);

        // Following division normalizes distance so it is between interval [0, 1]
        return 1.0 - (distance / GeoUtils.MAXIMUM_DISTANCE);
    }

}
