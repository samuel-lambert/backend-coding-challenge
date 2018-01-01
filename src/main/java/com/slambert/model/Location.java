// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

public class Location {

    private final Double latitude;
    private final Double longitude;

    /**
     * Constructs a location object
     *
     * @throws IllegalArgumentException if latitude is not in the [-90.0, 90.0] range
     *                                  or longitude is not in the [-180.0, 180.0] range
     */
    public Location(Double latitude, Double longitude) {
        if (latitude < -90.0 || latitude > 90.0 || longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Invalid location");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns latitude
     *
     * @return latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Returns longitude
     *
     * @return longitude
     */
    public Double getLongitude() {
        return longitude;
    }
}
