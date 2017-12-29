// Coveo Backend Coding Challenge
// Design an API endpoint that provides auto-complete suggestions for large cities.
//
// Copyright (c) 2017 Samuel Lambert
//
// This software may be modified and distributed under the terms
// of the MIT license. See the LICENSE file for details.

package com.slambert.model;

import java.util.HashSet;
import java.util.Set;

/**
 * This class implements a ternary search trie that can return all
 * elements in the sub-tries of a key rather than just the element
 * associated with the key.
 * <p>
 * Inspired by the Ternary Search Trie proposed by Sedgewick & Wayne
 * Reference: Algorithms 4th edition, page 747.s
 */
public class AutocompleteTrie<T> {

    private Node root;

    /**
     * Searches the trie according to the query and returns a list
     * of all keys compatible with the query string.
     *
     * @param query string containing the search query to perform
     * @return set of all elements compatible with the query string
     */
    public Set<T> get(final String query) {
        // Retrieving the node corresponding of the last query string
        // character then recursively get all elements of the sub-tries.
        final Node node = get(query, 0, root);

        // Using an intermediate set to avoid duplication
        final Set<T> results = new HashSet<>();
        findElements(node, results);

        return results;
    }

    private Node get(final String query, final Integer index, Node node) {
        if (node == null) {
            return null;
        }

        final Character c = query.charAt(index);

        if (c < node.c) {
            return get(query, index, node.left);
        } else if (c > node.c) {
            return get(query, index, node.right);
        } else if (index < query.length() - 1) {
            return get(query, index + 1, node.middle);
        }

        return node;
    }

    private void findElements(final Node node, final Set<T> results) {
        if (node == null) {
            return;
        }

        if (!node.elements.isEmpty()) {
            results.addAll(node.elements);
        }

        findElements(node.middle, results);

        if (node.middle != null) {
            findElements(node.middle.left, results);
            findElements(node.middle.right, results);
        }
    }

    /**
     * Inserts the specified key in the trie and associate the
     * given element with this key.
     * Note: the trie is case-insensitive.
     *
     * @param key     key to be added in the autocomplete trie
     * @param element element to be associated with the key
     */
    public void add(final String key, final T element) {
        // The keys are stored in lower case because this trie
        // does not support case-sensitiveness by design.
        root = add(key.toLowerCase(), element, 0, root);
    }

    private Node add(final String key, final T element, final Integer index, Node node) {
        final Character c = key.charAt(index);

        if (node == null) {
            node = new Node();
            node.c = c;
        }

        if (c < node.c) {
            node.left = add(key, element, index, node.left);
        } else if (c > node.c) {
            node.right = add(key, element, index, node.right);
        } else if (index < key.length() - 1) {
            node.middle = add(key, element, index + 1, node.middle);
        } else {
            node.elements.add(element);
        }

        return node;
    }

    private class Node {
        public Character c;                 // Character
        public Node left;                   // Left sub-trie
        public Node middle;                 // Middle sub-trie
        public Node right;                  // Right sub-trie
        Set<T> elements = new HashSet<>();  // Values associated with the string
    }

}
