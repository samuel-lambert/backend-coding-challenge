// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("file:application.properties")
public class ConfigurationManager {

    private Boolean retrieveUserLocation;
    private Double fallbackLatitude;
    private Double fallbackLongitude;

    public Boolean isRetrievingUserLocation() {
        return retrieveUserLocation;
    }

    public void setRetrieveUserLocation(Boolean retrieveUserLocation) {
        this.retrieveUserLocation = retrieveUserLocation;
    }

    public Double getFallbackLatitude() {
        return fallbackLatitude;
    }

    public void setFallbackLatitude(Double fallbackLatitude) {
        this.fallbackLatitude = fallbackLatitude;
    }

    public Double getFallbackLongitude() {
        return fallbackLongitude;
    }

    public void setFallbackLongitude(Double fallbackLongitude) {
        this.fallbackLongitude = fallbackLongitude;
    }

}
