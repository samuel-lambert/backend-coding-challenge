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

@Component
public class AutocompleteManager {

    private static final String CITIES_DATA_FILE = "cities_canada-usa.json";

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

    public Map<String, Set<City>> query(final String q, final Double latitude, final Double longitude) {
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

        final Map<String, Set<City>> result = new HashMap<>();
        final Set<City> suggestedCities = trie.get(q);

        // TODO 29/12/2017:
        // - fill the list with suggestions and scores with good format
        // - fix nasty bug
        // - mock impl of autocompletemanager (distance score)
        // - document/improve autocompletemanager
        // - autocomplete manager unit tests

        result.put("suggestions", suggestedCities);

        return result;
    }

}
