// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.utils;

import com.slambert.model.Location;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GeoUtilsTest {

    @Test
    public void testCalculateDistanceZero() {
        Location a = new Location(0.0, 0.0);
        assertTrue(GeoUtils.calculateDistance(a, a) == 0.0);
    }

    @Test
    public void testCalculateDistanceMisc() {
        Location a = new Location(0.0, 0.0);
        Location b = new Location(1.0, 1.0);
        Location c = new Location(2.0, 2.0);
        Double distance1 = GeoUtils.calculateDistance(a, b);
        Double distance2 = GeoUtils.calculateDistance(a, c);
        assertTrue(distance1 < distance2);

        Location d = new Location(0.0, 0.0);
        Location e = new Location(90.0, 180.0);
        Double distance3 = GeoUtils.calculateDistance(d, e);
        Double distance4 = GeoUtils.MAXIMUM_DISTANCE;
        assertTrue(distance3 < distance4);
    }

}
