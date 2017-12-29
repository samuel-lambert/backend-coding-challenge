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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AutocompleteManager {

    private static final String CITIES_DATA_FILE = "cities_canada-usa.json";
    private List<City> cities;
    private AutocompleteTrie<City> trie;

    public AutocompleteManager() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            final File file = new File(classLoader.getResource(CITIES_DATA_FILE).getFile());
            cities = Arrays.asList(mapper.readValue(file, City[].class));
            trie = new AutocompleteTrie<>();
        } catch (final Exception e) {
            throw new IOException("Could not parse JSON city data");
        }
    }

    public List<City> query(final String q, final Double latitude, final Double longitude) {
        // TODO: need to return JSON data according to expectations!
        return new ArrayList<>();
    }

}
