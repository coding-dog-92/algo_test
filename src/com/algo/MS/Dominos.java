package com.algo.MS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dominos {

    /**
     * A game of dominos consists of 28 domino tiles. Between 0 and 6 dots appear at each
     * end of every tile. Tiles can be reversed during the game, so the tile showing
     * "2-3"
     * can
     * be played as "3-2".
     * You are given a list of N unique domino tiles. Your task is to find any domino tile not on
     * the list and return it in the format "X-Y", where X and Y are digits representing the
     * number of dots on each end of the tile. Note that because domino tiles can be reversed,
     * tiles "2-3" and "3-2" are treated as the same tile.
     * Write a function:
     * func Solution(A []string) string
     * that, given an array A of N strings representing unique domino tiles, returns a string
     * representing any tile which is not in the array A. Tiles in A are given in the format
     * described above. You can assume that there will always exist a tile not listed in A
     * Examples:
     * 1. Given A = ["O-0", "O-1", "2-3", "2-0"], one of the possible outputs is "0-3". Note that
     * "1-0" is not a valid answer, as "0-1" represents the same tile.
     * 2. Given A = ["O-0", "1-1", "2-2", "3-3" "4-4', "5-5" "6-6'), one of the possible outputs is
     * "2-4"
     */
    public static String missingTile(List<String> list) {
        Set<String> existSet = new HashSet<>();
        for (String s : list) {
            existSet.add(s);
            existSet.add(s.charAt(2)+"-"+s.charAt(0));
        }
        System.out.println(existSet);
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                String key = i+"-"+j;
                if (!existSet.contains(key)) {
                    return key;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(missingTile(Arrays.asList("0-0", "0-1", "2-3", "2-0")));
    }
}
