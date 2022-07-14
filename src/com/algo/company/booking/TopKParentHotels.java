package com.algo.company.booking;

import javafx.util.Pair;
import jdk.nashorn.internal.ir.IfNode;

import java.util.*;

public class TopKParentHotels {

    /**
     * https://leetcode.com/discuss/interview-question/1580539/Booking.com-or-Stage%3A-OA-(Virtual)-or-Top-k-count-of-hotels
     * Given a list of hotelId, parentHotelId and a score retrieve the top k root parentHotelIds with highest scores:
     *
     * [{0, 1, 10}, {1, 2, 20}, {3, 4, 10}, {7, 8, 5}] K = 2
     *
     * Result: [[2, 30], [4,10]]
     */

    public List<List<Integer>> parentHotels(List<int[]> hotels, int k) {
        // hotelId->[hotelId, parentHotelId, score]
        Map<Integer, int[]> hotel2ParentMap = new HashMap<>();
        for (int[] hotel : hotels) {
            hotel2ParentMap.put(hotel[0], hotel);
        }
        // calculate parentHotels' score
        Map<Integer, Integer> parentHotel2ScoreMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int[] hotel : hotels) {
            dfs(hotel[0], 0, hotel2ParentMap, parentHotel2ScoreMap, visited);
        }
        System.out.println(parentHotel2ScoreMap);
        // top k
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (a,b)->a[1]-b[1]);
        for (Map.Entry<Integer, Integer> entry : parentHotel2ScoreMap.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
            if (queue.size()>k) queue.poll();
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] hotelInfo = queue.poll();
            res.add(Arrays.asList(hotelInfo[0], hotelInfo[1]));
        }
        return res;
    }

    public void dfs(int hotelId, int curSum, Map<Integer, int[]> hotel2ParentMap, Map<Integer, Integer> parentHotel2ScoreMap, Set<Integer> visited) {
        if (!hotel2ParentMap.containsKey(hotelId)) {
            int currentScore = parentHotel2ScoreMap.getOrDefault(hotelId, 0);
            parentHotel2ScoreMap.put(hotelId, currentScore+curSum);
            return;
        }
        int[] parentInfo = hotel2ParentMap.get(hotelId);
        if (!visited.contains(hotelId)) {
            curSum+=parentInfo[2];
            visited.add(hotelId);
        }
        dfs(parentInfo[1], curSum, hotel2ParentMap, parentHotel2ScoreMap, visited);
    }


























    public List<int[]> topKDFS(List<int[]> hotels, int k, int n) {
        // map of child_hotel=(parent_hotel, rating)
        Map<Integer, Pair<Integer, Integer>> parent = new HashMap<>();
        for (int[] hotel: hotels) {
            parent.put(hotel[0], new Pair<>(hotel[1], hotel[2]));
        }
        Map<Integer, Integer> total = new HashMap<>();
        for (int[] hotel: hotels) {
            dfs(hotel[0], 0, parent, total);
        }
        System.out.println(total);
        List<int[]> ans = new ArrayList<>();
        Queue<Integer> pq = new PriorityQueue<>((h1, h2) -> total.get(h1) - total.get(h2));
        for (Map.Entry<Integer, Integer> e: total.entrySet()) {
            pq.offer(e.getKey());
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            int hotel = pq.poll();
            ans.add(new int[]{hotel, total.get(hotel)});
        }
        return ans;
    }

    private void dfs(int hotel, int rating, Map<Integer, Pair<Integer, Integer>> parent, Map<Integer, Integer> total) {
        if (!parent.containsKey(hotel)) {
            total.put(hotel, total.getOrDefault(hotel, 0) + rating);
            return;
        }
        Pair<Integer, Integer> pair = parent.get(hotel);
        dfs(pair.getKey(), rating + pair.getValue(), parent, total);
        parent.put(hotel, new Pair<>(pair.getKey(), 0));
    }

    public static void main(String[] args) {
        List<List<Integer>> res2 = new TopKParentHotels().parentHotels(
                Arrays.asList(
                        new int[]{0, 1, 10},
                        new int[]{1, 2, 20},
                        new int[]{3, 4, 10},
                        new int[]{7, 8, 5}
                ),
                2
        );
        System.out.println(res2);

//        for (int[] val: res2) {
//            System.out.println(Arrays.toString(val));
//        }
    }
}
