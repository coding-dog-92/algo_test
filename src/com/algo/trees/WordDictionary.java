package com.algo.trees;

import java.util.HashSet;
import java.util.Set;

public class WordDictionary {
    private WordDictionary[] children;
    private boolean isEnd;
    public WordDictionary() {
        children = new WordDictionary[26];
        isEnd = false;
    }

    public void addWord(String word) {
        WordDictionary current = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c-'a';
            if (current.children[index] == null) {
                current.children[index] = new WordDictionary();
            }
            current = current.children[index];
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(this, word, 0);
    }

    private boolean dfs(WordDictionary current, String word, int start) {
        if (start==word.length()) {
            return current.isEnd;
        }
        char c = word.charAt(start);
        if (c != '.') {
            WordDictionary dictionary = current.children[c - 'a'];
            return dictionary != null && dfs(dictionary, word, start+1);
        }
        for (int i = 0; i < 26; i++) {
            WordDictionary dictionary = current.children[i];
            if (dictionary != null && dfs(dictionary, word, start+1)) {
                return true;
            }
        }
        return false;
    }
}
