// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: 27/12/2017
// - City data class
// - Move data into resource folder
// - Mock Suggestions class
// - AutocompleteTrie impl
// - AutocompleteTrie tests

@SpringBootApplication
public class CityAutocompleteService {

    public static void main(final String[] args) {
        SpringApplication.run(CityAutocompleteService.class, args);
    }

}
