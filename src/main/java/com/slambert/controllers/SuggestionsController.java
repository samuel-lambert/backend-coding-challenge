// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.controllers;

import com.slambert.ConfigurationManager;
import com.slambert.model.AutocompleteManager;
import com.slambert.model.CityResponse;
import com.slambert.model.Location;
import com.slambert.utils.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

// TODO:
// - fix transaction with latitude/longitude
// - sponsored links
// - test user location
// - code review

@RestController
public class SuggestionsController {

    private static final String SUGGESTIONS_PATH = "/suggestions";

    @Autowired
    private AutocompleteManager autocompleteManager;

    @Autowired
    private ConfigurationManager configurationManager;

    @RequestMapping(value = SUGGESTIONS_PATH, method = RequestMethod.GET)
    public Map<String, Set<CityResponse>> getSuggestions(@RequestParam String q,
                                                         @RequestParam Optional<Double> latitude,
                                                         @RequestParam Optional<Double> longitude,
                                                         HttpServletRequest request) {
        // Example query: GET /suggestions?q=Londo&latitude=43.70011&longitude=-79.4163
        // Note: this funky return type is used to map upcoming JSON response to expected format

        Double fallbackLatitude = configurationManager.getFallbackLatitude();
        Double fallbackLongitude = configurationManager.getFallbackLongitude();
        Location targetLocation = new Location(0.0, 0.0);

        // We always prioritize location specified in the URL, even if the 'retrieveUserLocation'
        // option is set.
        try {
            targetLocation = new Location(fallbackLatitude, fallbackLongitude);

            if (latitude.isPresent() && longitude.isPresent()) {
                targetLocation = new Location(latitude.get(), longitude.get());
            } else if (configurationManager.isRetrievingUserLocation()) {
                targetLocation = GeoUtils.getLocationFromIPAddress(request.getRemoteAddr());
            }
        } catch (Exception e) {
            // LOG could not retrieve, using 0.0, 0.0 instead...
        }

        // At the moment, we are taking the query string 'q' as is and only
        // comparing it with city names prefixes or city names. If a client
        // is looking up for something like "montreal, qc", we will return
        // nothing because this controller is not smart. A good way of doing
        // this would be to parse the query string and try to find state/province
        // and country hints. These hints could then contribute to the score.
        // However, adding this "intelligence" would require more data (all
        // province/states full names and all their abbreviated names and/or
        // alternate names. The lack of this data is the reason why I used a
        // simpler logic.

        // All keys in the trie have been stored in lower case
        return autocompleteManager.query(q.toLowerCase(), targetLocation);
    }

}
