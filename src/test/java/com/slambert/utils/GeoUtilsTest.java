// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.utils;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GeoUtilsTest {

    @Test
    public void testCalculateDistanceZero() {
        assertTrue(GeoUtils.calculateDistance(0.0, 0.0, 0.0, 0.0) == 0.0);
    }

    @Test
    public void testCalculateDistanceMisc() {
        Double distance1 = GeoUtils.calculateDistance(0.0, 0.0, 1.0, 1.0);
        Double distance2 = GeoUtils.calculateDistance(0.0, 0.0, 2.0, 2.0);
        assertTrue(distance1 < distance2);

        Double distance3 = GeoUtils.calculateDistance(0.0, 0.0, 90.0, 180.0);
        Double distance4 = GeoUtils.getMaximumDistance();
        assertTrue(distance3 < distance4);
    }

}
