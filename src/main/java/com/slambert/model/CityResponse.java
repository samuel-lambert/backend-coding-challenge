// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

public class CityResponse implements Comparable<CityResponse> {
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Double score;

    public CityResponse(final City city, final Double score) {
        // Formatting of the city name as follow: "Montréal, QC, CA"
        this.name = city.getName().substring(0, 1).toUpperCase() +
                city.getName().substring(1) +
                ", " +
                city.getState().toUpperCase() +
                ", " +
                city.getCountry().toUpperCase();

        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public int compareTo(final CityResponse rhs) {
        // Reverting lhs/rhs because these objects
        // need to be sorted in reverse order.
        if (score == rhs.score) {
            return rhs.name.compareTo(name);
        }

        return rhs.score.compareTo(score);
    }

}
