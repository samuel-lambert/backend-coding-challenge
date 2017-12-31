// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.utils;

/**
 * This class contains various distance-related utilities
 */
public class GeoUtils {

    private static final Double EARTH_RADIUS = 6371.0;

    /**
     * Returns distance in kilometers between 2 points using
     * the Haversine formula.
     *
     * @param lat1 latitude of first point
     * @param lon1 longitude of first point
     * @param lat2 latitide of second point
     * @param lat2 longitude of second point
     * @return longest possible distance on earth
     */
    public static Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double startLat = Math.toRadians(lat1);
        Double endLat = Math.toRadians(lat2);

        Double a = Math.sin(latDistance / 2.0) * Math.sin(latDistance / 2.0) +
                Math.cos(startLat) * Math.cos(endLat) * Math.sin(lonDistance / 2.0) * Math.sin(lonDistance / 2.0);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

        return EARTH_RADIUS * c;
    }

    /**
     * Returns longest possible distance on earth
     *
     * @return longest possible distance on earth
     */
    public static Double getMaximumDistance() {
        return calculateDistance(-90.0, -180.0, 90.0, 180.0);
    }

}
