// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.slambert.model.Location;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * This class contains various distance-related utilities
 */
public class GeoUtils {

    private static final Double EARTH_RADIUS = 6371.0;
    private static final String GEO_IP_DB_PATH = "geolite2-city.mmdb";

    private static DatabaseReader dbReader;

    /**
     * Returns distance in kilometers between 2 points using
     * the Haversine formula.
     *
     * @param l1 first location
     * @param l2 second location
     * @return longest possible distance on earth
     */
    public static Double calculateDistance(Location l1, Location l2) {
        Double latDistance = Math.toRadians(l2.getLatitude() - l1.getLatitude());
        Double lonDistance = Math.toRadians(l2.getLongitude() - l1.getLongitude());
        Double startLat = Math.toRadians(l1.getLatitude());
        Double endLat = Math.toRadians(l2.getLatitude());

        Double a = Math.pow(Math.sin(latDistance / 2), 2) + Math.pow(Math.sin(lonDistance / 2), 2) *
                Math.cos(startLat) * Math.cos(endLat);
        Double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS * c;
    }

    /**
     * Returns longest possible distance on earth
     *
     * @return longest possible distance on earth
     */
    public static Double getMaximumDistance() {
        Location a = new Location(-90.0, -180.0);
        Location b = new Location(90.0, 180.0);
        return calculateDistance(a, b);
    }

    /**
     * Returns approximate location (by latitude and longitude) of IP address
     *
     * @param ipAddress address ip address
     * @return approximate location of ip address
     * @throws GeoIp2Exception if given ip address is invalid
     * @throws IOException     if connection to the geoip2 database cannot be performed
     */
    public static Location getLocationFromIPAddress(String ipAddress) throws GeoIp2Exception, IOException {
        // Lazy initialization of database reader
        if (dbReader == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File database = new File(classLoader.getResource(GEO_IP_DB_PATH).getFile());
            dbReader = new DatabaseReader.Builder(database).build();
        }

        CityResponse response = dbReader.city(InetAddress.getByName(ipAddress));
        return new Location(response.getLocation().getLatitude(), response.getLocation().getLongitude());
    }

}
