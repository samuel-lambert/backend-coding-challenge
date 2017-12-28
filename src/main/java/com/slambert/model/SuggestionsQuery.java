// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsQuery {

    private final List<City> suggestions = new ArrayList<>();

    public SuggestionsQuery(final String q, final Double latitude, final Double longitude) {
        // TODO
    }

    public List<City> getSuggestions() {
        return suggestions;
    }

}
