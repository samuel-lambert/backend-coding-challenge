// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Component
public class AutocompleteManager {

    private static final String CITIES_DATA_FILE = "cities_canada-usa.json";
    private static final Double MAXIMUM_DISTANCE = getMaximumDistance();

    private final List<City> cities;
    private final AutocompleteTrie<City> trie;

    public AutocompleteManager() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            final File file = new File(classLoader.getResource(CITIES_DATA_FILE).getFile());
            cities = Arrays.asList(mapper.readValue(file, City[].class));
            trie = new AutocompleteTrie<>();

            // Populating trie with city names as keys
            for (final City c : cities) {
                trie.add(c.getName(), c);

                // Also adding alternate names with the same city object
                for (final String s : c.getAlternateNames()) {
                    trie.add(s, c);
                }
            }
        } catch (final Exception e) {
            throw new IOException("Unexpected error: could not parse JSON city data");
        }
    }

    private static Double getMaximumDistance() {
        return calculateDistance(-90.0, -180.0, 90.0, 180.0);
    }

    // TODO: might want to put this somewhere else...
    // ref!
    private static Double calculateDistance(final Double lat1,
                                            final Double lon1,
                                            final Double lat2,
                                            final Double lon2) {
        // Calculating distance between 2 points (km) with the Haversine formula.
        final Double earthRadius = 6371.0;

        final Double latDistance = Math.toRadians(lat2 - lat1);
        final Double lonDistance = Math.toRadians(lon2 - lon1);
        final Double startLat = Math.toRadians(lat1);
        final Double endLat = Math.toRadians(lat2);

        final Double a = Math.sin(latDistance / 2.0) * Math.sin(latDistance / 2.0) +
                Math.cos(startLat) * Math.cos(endLat) * Math.sin(lonDistance / 2.0) * Math.sin(lonDistance / 2.0);
        final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

        return earthRadius * c;

    }

    public Map<String, Set<CityResponse>> query(final String q,
                                                final Double clientLat,
                                                final Double clientLon) {
        // This funky return type is used to map JSON responses to the following format:
        //
        // {
        //  "suggestions": [
        //    {
        //      "name": "London, ON, Canada",
        //      "latitude": "42.98339",
        //      "longitude": "-81.23304",
        //      "score": 0.9
        //    },
        //    {
        //      "name": "Londontowne, MD, USA",
        //      "latitude": "38.93345",
        //      "longitude": "-76.54941",
        //      "score": 0.3
        //    }
        //  ]
        //}

        final Map<String, Set<CityResponse>> suggestions = new HashMap<>();
        final Set<CityResponse> cityResponses = new TreeSet<>();
        final Set<City> suggestedCities = trie.get(q);

        for (final City c : suggestedCities) {
            cityResponses.add(new CityResponse(c, rankByDistance(c, clientLat, clientLon)));
        }

        // TODO 29/12/2017:
        // - fix nasty bug
        // - make longitude account for more points
        // - document/improve autocompletemanager
        // - autocomplete manager unit tests

        suggestions.put("suggestions", cityResponses);

        return suggestions;
    }

    private Double rankByDistance(final City city,
                                  final Double clientLat,
                                  final Double clientLon) {
        final Double distance = calculateDistance(clientLat, clientLon, city.getLatitude(), city.getLongitude());
        return 1.0 - (distance / MAXIMUM_DISTANCE);
    }
}
