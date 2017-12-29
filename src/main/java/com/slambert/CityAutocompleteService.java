// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert;

import com.slambert.model.AutocompleteManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CityAutocompleteService {

    public static void main(final String[] args) {
        try {
            final AutocompleteManager autocompleteManager = new AutocompleteManager();
        } catch (final IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        SpringApplication.run(CityAutocompleteService.class, args);
    }

}
