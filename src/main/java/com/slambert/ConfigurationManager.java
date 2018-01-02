// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert;

import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager {

    // Should the service try to use the IP address of the client to improve accuracy of
    // the score if the latitude and/or longitude are not included in the URL?
    private final Boolean retrieveUserLocation = false;

    // What would be the default latitude used to calculate scores if it was not included
    // in the URL and 'retrieve_user_location' is not enabled?
    private final Double fallbackLatitude = 45.5017;

    // What would be the default longitude used to calculate scores if it was not included
    // in the URL and 'retrieve_user_location' is not enabled?
    private final Double fallbackLongitude = -73.5673;

    public Boolean isRetrievingUserLocation() {
        return retrieveUserLocation;
    }

    public Double getFallbackLatitude() {
        return fallbackLatitude;
    }

    public Double getFallbackLongitude() {
        return fallbackLongitude;
    }

}
