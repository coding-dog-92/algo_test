package com.algo.company.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChainOfBookings {
    /**
     * We have the following class that describes a booking:
     *
     * Booking {
     *    int user_id;
     *    int res_id;
     *    int checkin;
     *    int checkout;
     * }
     * Now, having the following bookings list:
     *
     * bookings:[
     *     {user = 1, res_id = 1001, checkin = 100, checkout 101 },
     *     {user = 2, res_id = 1002, checkin = 104, checkout 105 },
     *     {user = 1, res_id = 1003, checkin = 101, checkout 103 },
     *     {user = 3, res_id = 1004, checkin = 104, checkout 105},
     *     {user = 3, res_id = 1005, checkin = 105, checkout 107},
     *     {user = 4, res_id = 1006, checkin = 106, checkout 108},
     *    {user = 4, res_id = 1007, checkin = 108, checkout 110},
     *    {user = 4, res_id = 1008, checkin = 108, checkout 109},
     *    {user = 4, res_id = 1009, checkin = 110, checkout 112},
     *    {user = 4, res_id = 1010, checkin = 109, checkout 113},
     * ];
     * we name chain a sequence of two bookings or more.
     * Example.
     * For user 1 we have the following chain: 100-101-103 which means the user
     * had to make two reservations, 1001 and 1003. The task is to retrieve all the bookings per user.
     * Example: { 1: {{1001, 1003}}, 3: {{1004, 1005}}, 4 : {{1006, 1007, 1009}, {1006, 1008, 1009}} }
     * find all the chains in the list.
     *
     * public class Solution {
     *      public Map<Integer, List<List<Integer>> findBookings(int[][] bookings) {
     * 	         return Collections.emptyList();
     *     }
     * }
     *
     * 1:
     * {user = 1, res_id = 1001, checkin = 100, checkout 101 },
     * {user = 1, res_id = 1003, checkin = 101, checkout 103 },
     *
     * 2:
     * {user = 2, res_id = 1002, checkin = 104, checkout 105 },
     *
     * 3:
     * {user = 3, res_id = 1004, checkin = 104, checkout 105},
     * {user = 3, res_id = 1005, checkin = 105, checkout 107},
     *
     * 4:
     * {user = 4, res_id = 1006, checkin = 106, checkout 108},
     * {user = 4, res_id = 1007, checkin = 108, checkout 110},
     * {user = 4, res_id = 1009, checkin = 110, checkout 112},
     *
     * {user = 4, res_id = 1008, checkin = 108, checkout 109},
     * {user = 4, res_id = 1010, checkin = 109, checkout 113},
     */
    public Map<Integer, List<List<Integer>>> findChainOfBookings(Booking[] bookings) {
        // build map
        // {user: {checkin: booking}}
        Map<Integer, Map<Integer, List<Booking>>> user2BookingsMap = new HashMap<>();
        // {user: earliest checkin}
        Map<Integer, Integer> user2EarliestCheckinMap = new HashMap<>();
        for (Booking booking : bookings) {
            if (!user2BookingsMap.containsKey(booking.user_id)) {
                user2BookingsMap.put(booking.user_id, new HashMap<>());
            }
            if (!user2BookingsMap.get(booking.user_id).containsKey(booking.checkin)) {
                user2BookingsMap.get(booking.user_id).put(booking.checkin, new ArrayList<>());
            }
            user2BookingsMap.get(booking.user_id).get(booking.checkin).add(booking);
            int earliest = user2EarliestCheckinMap.getOrDefault(booking.user_id, Integer.MAX_VALUE);
            if (booking.checkin < earliest) user2EarliestCheckinMap.put(booking.user_id, booking.checkin);
        }
        System.out.println(user2BookingsMap);
        // dfs
        Map<Integer, List<List<Integer>>> res = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<Booking>>> entry : user2BookingsMap.entrySet()) {
            List<List<Integer>> userRes = new ArrayList<>();
            dfs(entry.getValue(), user2EarliestCheckinMap.get(entry.getKey()), new ArrayList<>(), userRes);
            if (userRes.size()>0) res.put(entry.getKey(), userRes);
        }
        return res;
    }

    public void dfs(Map<Integer, List<Booking>> graph, int checkin, List<Integer> path, List<List<Integer>> userRes) {
        if (!graph.containsKey(checkin)) {
            if (path.size()>1)
                userRes.add(new ArrayList<>(path));
            return;
        }
        List<Booking> neighbors = graph.get(checkin);
        for (Booking neighbor : neighbors) {
            path.add(neighbor.res_id);
            dfs(graph, neighbor.checkout, path, userRes);
            path.remove(path.size()-1);
        }
    }

    static class Booking {
        int user_id;
        int res_id;
        int checkin;
        int checkout;

        public Booking(int user_id, int res_id, int checkin, int checkout) {
            this.user_id = user_id;
            this.res_id = res_id;
            this.checkin = checkin;
            this.checkout = checkout;
        }

        @Override
        public String toString() {
            return "Booking{" +
                    "user_id=" + user_id +
                    ", res_id=" + res_id +
                    ", checkin=" + checkin +
                    ", checkout=" + checkout +
                    '}';
        }
    }

    public static void main(String[] args) {
        Booking[] bookings = new Booking[]{
                new Booking(1, 1001, 100, 101),
                new Booking(2, 1002, 104, 105),
                new Booking(1, 1003, 101, 103),
                new Booking(3, 1004, 104, 105),
                new Booking(3, 1005, 105, 107),
                new Booking(4, 1006, 106, 108),
                new Booking(4, 1007, 108, 110),
                new Booking(4, 1008, 108, 109),
                new Booking(4, 1009, 110, 112),
                new Booking(4, 1010, 109, 113)
        };
        Map<Integer, List<List<Integer>>> res = new ChainOfBookings().findChainOfBookings(bookings);
        System.out.println(res);
    }
}
