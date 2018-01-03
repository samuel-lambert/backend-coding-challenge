// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;

public class CityTrieTest {

    private CityTrie trie;
    private City a = new City("she", "o", "o", 0.0, 0.0);
    private City b = new City("sells", "o", "o", 0.0, 0.0);
    private City c = new City("sea", "o", "o", 0.0, 0.0);
    private City d = new City("shells", "o", "o", 0.0, 0.0);
    private City e = new City("by", "o", "o", 0.0, 0.0);
    private City f = new City("the", "o", "o", 0.0, 0.0);
    private City g = new City("sea", "o", "o", 0.0, 0.0);
    private City h = new City("shore", "o", "o", 0.0, 0.0);

    @Before
    public void setUp() {
        trie = new CityTrie();
    }

    @Test
    public void testEmptyTrie() {
        assertEquals(trie.get("hello").size(), 0);
    }

    @Test
    public void testSimpleTrie() {
        trie.add("hello", a);

        assertEquals(trie.get("hello").size(), 1);
        assertEquals(trie.get("helloo").size(), 0);
        assertEquals(trie.get("h").size(), 1);
    }

    @Test
    public void testComplexTrie() {
        trie.add("she", a);
        trie.add("sells", b);
        trie.add("sea", c);
        trie.add("shells", d);
        trie.add("by", e);
        trie.add("the", f);
        trie.add("sea", g); // duplicate
        trie.add("shore", h);

        Set<City> result1 = trie.get("by");
        assertEquals(result1.size(), 1);
        assertThat(result1, hasItem(e));

        Set<City> result2 = trie.get("the");
        assertEquals(result2.size(), 1);
        assertThat(result2, hasItem(f));

        Set<City> result3 = trie.get("she");
        assertEquals(result3.size(), 2);
        assertThat(result3, hasItems(a, d));

        Set<City> result4 = trie.get("s");
        assertEquals(result4.size(), 5);
        assertThat(result4, hasItems(a, b, d, g, h));
    }

    @Test
    public void testGetCity() {
        trie.add("she", a);
        trie.add("shells", b);

        City result = trie.get("she", "o", "o");
        assertTrue(result.getName().equals("she"));
    }
}
