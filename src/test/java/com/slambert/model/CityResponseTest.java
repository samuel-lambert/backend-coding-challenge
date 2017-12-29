// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CityResponseTest {

    private final City a = new City("a", "b", "c", 0.0, 0.0);
    private final City b = new City("a", "b", "c", 0.0, 0.0);
    private final City c = new City("aa", "bb", "cc", 0.0, 0.0);

    @Test
    public void testComparableEqual() {
        final CityResponse ar = new CityResponse(a, 0.0);
        final CityResponse br = new CityResponse(b, 0.0);
        assertTrue(ar.compareTo(br) == 0);
    }

    @Test
    public void testComparableNotEqual() {
        final CityResponse ar = new CityResponse(a, 0.1);
        final CityResponse br = new CityResponse(b, 0.0);
        assertFalse(ar.compareTo(br) == 0);
    }

    @Test
    public void testComparableLess() {
        final CityResponse ar = new CityResponse(a, 0.0);
        final CityResponse br = new CityResponse(b, 1.0);
        assertTrue(ar.compareTo(br) < 0);
    }


    @Test
    public void testComparableGreater() {
        final CityResponse ar = new CityResponse(a, 1.0);
        final CityResponse br = new CityResponse(b, 0.0);
        assertTrue(ar.compareTo(br) > 0);
    }

    @Test
    public void testNameFormat() {
        final CityResponse ar = new CityResponse(b, 0.0);
        final CityResponse cr = new CityResponse(c, 0.0);
        assertTrue(ar.getName().equals("A, B, C"));
        assertTrue(cr.getName().equals("Aa, BB, CC"));
    }

}
