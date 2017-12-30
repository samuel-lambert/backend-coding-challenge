// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * This class models a city as represented as the
 * included JSON data.
 */
public class City {

    @JsonProperty("name")
    private String name;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("alternate_names")
    private List<String> alternateNames;

    /**
     * Constructs an empty city object
     */
    public City() {
        // Empty constructor needed by Spring...
    }

    /**
     * Constructs a city object
     *
     * @param name      city name
     * @param state     city state or province
     * @param country   city country
     * @param latitude  city latitude
     * @param longitude city longitude
     */
    public City(String name, String state, String country, Double latitude, Double longitude) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns city name
     *
     * @return city name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets city name
     *
     * @param name city name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns alternate city names
     *
     * @return alternate city names
     */
    public List<String> getAlternateNames() {
        return alternateNames;
    }

    /**
     * Sets city alternate names
     *
     * @param alternateNames alternate city names
     */
    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    /**
     * Returns city name or province
     *
     * @return city name of province
     */
    public String getState() {
        return state;
    }

    /**
     * Sets city state or province
     *
     * @param state city state or province
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns city country
     *
     * @return city country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets city country
     *
     * @param country city country
     */
    public void setCountry(String country) {
        this.country = country;
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
     * Sets city latitude
     *
     * @param latitude city latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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
     * Sets city longitude
     *
     * @param longitude city longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        City city = (City) other;
        return Objects.equals(name, city.name) &&
                Objects.equals(state, city.state) &&
                Objects.equals(country, city.country) &&
                Objects.equals(latitude, city.latitude) &&
                Objects.equals(longitude, city.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, country, latitude, longitude);
    }

}
