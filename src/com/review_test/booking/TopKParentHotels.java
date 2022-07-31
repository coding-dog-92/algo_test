package com.review_test.booking;

import javafx.util.Pair;

import java.util.*;

public class TopKParentHotels {

    /**
     * Given a list of hotelId, parentHotelId and a score retrieve the top k root parentHotelIds with highest scores:
     *
     * [{0, 1, 10}, {1, 2, 20}, {3, 4, 10}, {7, 8, 5}] K = 2
     *
     * Result: [[2, 30], [4,10]]
     *
     * The solution I gave was to create an Object to store scores and the ids, then use a Map<CustomObject, CustomObject> from parent to child. Then traverse the tree and propagate the sum of scores up to the root note.
     *
     *  10 20
     * 0->1->2
     *
     *  10
     * 3->4
     *
     *  5
     * 3->8
     */
    Map<Integer, Integer> parent2Score = new HashMap<>();


    public List<int[]> topK(List<int[]> hotelList, int k) {
        Map<Integer, List<int[]>> adjacentList = new HashMap<>();
        for (int[] ints : hotelList) {
            int hotelId = ints[0];
//            int parentId = ints[1];
//            int score = ints[2];
            if (!adjacentList.containsKey(hotelId)) adjacentList.put(hotelId, new ArrayList<>());
            adjacentList.get(hotelId).add(ints);
        }
        System.out.println(adjacentList);
        //
        Set<String> visited = new HashSet<>();
        for (int[] ints : hotelList) {
            int hotelId = ints[0];
            int parentId = ints[1];
            int score = ints[2];
            dfs(adjacentList, hotelId, 0, visited);
        }
        System.out.println(parent2Score);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for (Map.Entry<Integer, Integer> entry : parent2Score.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
            if (queue.size()>k) queue.poll();
        }
        List<int[]> res = new ArrayList<>();
        while (!queue.isEmpty()) res.add(queue.poll());
        return res;
    }

     void dfs(Map<Integer, List<int[]>> adjacentList, int hotelId, int sum, Set<String> visited) {
        if (!adjacentList.containsKey(hotelId)) {
            int curSum = parent2Score.getOrDefault(hotelId, 0);
            parent2Score.put(hotelId, curSum+sum);
            return;
        }
         for (int[] ints : adjacentList.get(hotelId)) {
             int id = ints[0];
             int parentId = ints[1];
             int score = 0;
             String key = id+"-"+parentId;
//             System.out.println("key= "+key+", sum= "+sum);
             if (!visited.contains(key)) {
                 score = ints[2];
                 visited.add(key);
             }
             dfs(adjacentList, parentId, sum+score, visited);
         }
    }

    public static void main(String[] args) {
        TopKParentHotels topKParentHotels = new TopKParentHotels();
        List<int[]> list = topKParentHotels.topK(
                Arrays.asList(
                        new int[]{0, 1, 10},
//                        new int[]{0,3,5},
//                        new int[]{3,7,5},
                        new int[]{1, 2, 20},
                        new int[]{3, 4, 10},
//                        new int[]{4,2,5},
                        new int[]{7, 8, 5}
                ),
                2
        );
        for (int[] ints : list) {
            System.out.println(Arrays.toString(ints));
        }
    }


}
