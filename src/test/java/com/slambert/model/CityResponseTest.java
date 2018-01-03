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

    private City a = new City("a", "b", "c", 0.0, 0.0);
    private City b = new City("b", "b", "c", 0.0, 0.0);
    private City c = new City("aa", "bb", "cc", 0.0, 0.0);

    @Test
    public void testComparableEqual() {
        CityResponse ar = new CityResponse(a, 0.0);
        CityResponse br = new CityResponse(b, 0.0);
        assertTrue(ar.compareTo(br) == 0);
    }

    @Test
    public void testComparableNotEqual() {
        CityResponse ar = new CityResponse(a, 0.1);
        CityResponse br = new CityResponse(b, 0.0);
        assertFalse(ar.compareTo(br) == 0);
    }

    @Test
    public void testComparableLess() {
        CityResponse ar = new CityResponse(a, 0.0);
        CityResponse br = new CityResponse(b, 1.0);
        assertTrue(ar.compareTo(br) > 0);
    }


    @Test
    public void testComparableGreater() {
        CityResponse ar = new CityResponse(a, 1.0);
        CityResponse br = new CityResponse(b, 0.0);
        assertTrue(ar.compareTo(br) < 0);
    }

    @Test
    public void testNameFormat() {
        CityResponse ar = new CityResponse(b, 0.0);
        CityResponse cr = new CityResponse(c, 0.0);
        assertTrue(ar.getName().equals("B, B, C"));
        assertTrue(cr.getName().equals("Aa, BB, CC"));
    }

}
