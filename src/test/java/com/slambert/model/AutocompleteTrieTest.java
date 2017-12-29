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

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AutocompleteTrieTest {

    private AutocompleteTrie<Integer> trie;

    @Before
    public void setUp() {
        trie = new AutocompleteTrie<>();
    }

    @Test
    public void testEmptyTrie() {
        assertEquals(trie.get("hello").size(), 0);
    }

    @Test
    public void testSimpleTrie() {
        trie.add("hello", 0);

        assertEquals(trie.get("hello").size(), 1);
        assertEquals(trie.get("help").size(), 0);
        assertEquals(trie.get("he").size(), 1);
    }

    @Test
    public void testComplexTrie() {
        trie.add("she", 0);
        trie.add("sells", 1);
        trie.add("sea", 2);
        trie.add("shells", 3);
        trie.add("by", 4);
        trie.add("the", 5);
        trie.add("sea", 6);
        trie.add("shore", 7);

        final Set<Integer> result1 = trie.get("by");
        assertEquals(result1.size(), 1);
        assertThat(result1, hasItems(4));

        final Set<Integer> result2 = trie.get("the");
        assertEquals(result2.size(), 1);
        assertThat(result2, hasItems(5));

        final Set<Integer> result3 = trie.get("she");
        assertEquals(result3.size(), 2);
        assertThat(result3, hasItems(0, 3));

        final Set<Integer> result4 = trie.get("s");
        assertEquals(result4.size(), 6);
        assertThat(result4, hasItems(0, 1, 2, 3, 6, 7));
    }

}
