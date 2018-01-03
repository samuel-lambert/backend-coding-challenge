// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import org.apache.commons.lang3.text.WordUtils;

/**
 * This class is an adapter to a City object with the only
 * purpose of being displayed as a JSON response.
 */
public class CityResponse implements Comparable<CityResponse> {
    private String name;
    private Double latitude;
    private Double longitude;
    private Double score;

    /**
     * Constructs a CityResponse object
     *
     * @param city  city object to adapt
     * @param score score assigned to this city
     */
    public CityResponse(City city, Double score) {
        // Formatting city name as follow: "Montréal, QC, CA"
        this.name = WordUtils.capitalizeFully(city.getName(), ' ', '-') +
                ", " + city.getState().toUpperCase() +
                ", " + city.getCountry().toUpperCase();

        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
        this.score = score;
    }

    /**
     * Returns formatted city name
     *
     * @return formatted city name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns city latitude
     *
     * @return city latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Returns city longitude
     *
     * @return city longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Returns city score
     *
     * @return city score
     */
    public Double getScore() {
        return score;
    }

    @Override
    public int compareTo(CityResponse rhs) {
        if (name.equals(rhs.name)) {
            return 0;
        }

        // Reverting lhs/rhs because these objects
        // need to be sorted in reverse order.
        if (score == rhs.score) {
            return rhs.name.compareTo(name);
        }

        return rhs.score.compareTo(score);
    }

}
