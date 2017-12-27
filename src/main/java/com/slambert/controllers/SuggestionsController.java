// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/suggestions")
public class SuggestionsController {

    @RequestMapping(method = RequestMethod.GET)
    public String getSuggestions(@RequestParam final Map<String, String> queryParameters) {
        // Example query: GET /suggestions?q=Londo&latitude=43.70011&longitude=-79.4163

        // TODO
        return "To be implemented.";
    }

}
