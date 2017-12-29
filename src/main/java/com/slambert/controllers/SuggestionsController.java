// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.controllers;

import com.slambert.model.AutocompleteManager;
import com.slambert.model.CityResponse;
import com.slambert.model.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
public class SuggestionsController {

    private static final String SUGGESTIONS_PATH = "/suggestions";

    @Autowired
    private AutocompleteManager autocompleteManager;

    @Autowired
    private ConfigurationManager configurationManager;

    @RequestMapping(value = SUGGESTIONS_PATH, method = RequestMethod.GET)
    public Map<String, Set<CityResponse>> getSuggestions(@RequestParam final String q,
                                                         @RequestParam final Optional<Double> latitude,
                                                         @RequestParam final Optional<Double> longitude) {
        // Example query: GET /suggestions?q=Londo&latitude=43.70011&longitude=-79.4163
        // Note: this funky return type is used to map JSON response to expected format

        final Double sanitizedLatitude =
                latitude.isPresent() ? latitude.get() : configurationManager.getFallbackLatitude();
        final Double sanitizedLongitude =
                longitude.isPresent() ? longitude.get() : configurationManager.getFallbackLongitude();

        // All keys in the trie have been stored in lower case
        return autocompleteManager.query(q.toLowerCase(), sanitizedLatitude, sanitizedLongitude);
    }

}
