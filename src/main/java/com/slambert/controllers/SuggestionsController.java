// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.controllers;

import com.slambert.model.SuggestionsQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SuggestionsController {

    private static final String SUGGESTIONS_PATH = "/suggestions";

    @RequestMapping(value = SUGGESTIONS_PATH, method = RequestMethod.GET)
    public SuggestionsQuery getSuggestions(@RequestParam final String q,
                                           @RequestParam final Optional<Double> latitude,
                                           @RequestParam final Optional<Double> longitude) {
        // Example query: GET /suggestions?q=Londo&latitude=43.70011&longitude=-79.4163

        // TODO: retrieve default latitude/longitude from configuration file.
        final Double sanitizedLatitude = latitude.isPresent() ? latitude.get() : 0.0;
        final Double sanitizedLongitude = longitude.isPresent() ? longitude.get() : 0.0;
        return new SuggestionsQuery(q, sanitizedLatitude, sanitizedLongitude);
    }

}
