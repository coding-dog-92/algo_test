package com.review_test.booking;

import com.algo.company.booking.ChainOfBookings;

import java.util.*;

public class FindChainOfBookings {

    /**
     * steps:
     * 1. start from the earliest checkin, find bookings that checkout equals current checkout
     * 2. traversal bookings until no bookings
     * 3. record path
     * @param bookings
     * @return
     */
    public Map<Integer, List<List<Integer>>> findBookings(Booking[] bookings) {
        /**
         * convert to adjacent list
         *  user->graph
         *  checkin->[booking1, booking2]
         * {
         *     1->{100:[1001], 101: [1003]},
         *     2->{104: [1002]},
         *     3->{104: [1004], 105: [1005]},
         *     4->{106: [1006], 108: [1007, 1008], 110: [1009], 109:[1010]}
         * }
         */
        Map<Integer, Map<Integer, List<Booking>>> user2GraphMap = new HashMap<>();
        Map<Integer, Integer> earliestCheckinMap = new HashMap<>();
        for (Booking booking : bookings) {
            if (!user2GraphMap.containsKey(booking.user)) {
                user2GraphMap.put(booking.user, new HashMap<>());
            }
            Map<Integer, List<Booking>> graph = user2GraphMap.get(booking.user);
            if (!graph.containsKey(booking.checkin)) graph.put(booking.checkin, new ArrayList<>());
            graph.get(booking.checkin).add(booking);
            int min = earliestCheckinMap.getOrDefault(booking.user, Integer.MAX_VALUE);
            if (booking.checkin < min) earliestCheckinMap.put(booking.user, booking.checkin);
        }
        System.out.println(user2GraphMap);
        System.out.println(earliestCheckinMap);

        // dfs
        Map<Integer, List<List<Integer>>> res = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<Booking>>> entry : user2GraphMap.entrySet()) {
            int user = entry.getKey();
            Map<Integer, List<Booking>> graph = entry.getValue();
            List<List<Integer>> userRes = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            dfs(graph, earliestCheckinMap.get(user), userRes, path);
            if (userRes.size()>0) res.put(user, userRes);
        }
        return res;
    }

    void dfs(Map<Integer, List<Booking>> graph, int checkin, List<List<Integer>> userRes, List<Integer> path) {
        if (!graph.containsKey(checkin)) {
            if (path.size()>1) userRes.add(new ArrayList<>(path));
            return;
        }
        List<Booking> list = graph.get(checkin);
        for (Booking booking : list) {
            path.add(booking.resId);
            dfs(graph, booking.checkout, userRes, path);
            path.remove(path.size()-1);
        }
    }


    public static void main(String[] args) {
        Booking[] bookings = new Booking[]{
                new FindChainOfBookings.Booking(1, 1001, 100, 101),
                new FindChainOfBookings.Booking(2, 1002, 104, 105),
                new FindChainOfBookings.Booking(1, 1003, 101, 103),
                new FindChainOfBookings.Booking(3, 1004, 104, 105),
                new FindChainOfBookings.Booking(3, 1005, 105, 107),
                new FindChainOfBookings.Booking(4, 1006, 106, 108),
                new FindChainOfBookings.Booking(4, 1007, 108, 110),
                new FindChainOfBookings.Booking(4, 1008, 108, 109),
                new FindChainOfBookings.Booking(4, 1009, 110, 112),
                new FindChainOfBookings.Booking(4, 1010, 109, 113)
        };
        Map<Integer, List<List<Integer>>> res = new FindChainOfBookings().findBookings(bookings);
        System.out.println(res);
    }




    static class Booking {
        int user;
        int resId;
        int checkin;
        int checkout;

        public Booking(int user, int resId, int checkin, int checkout) {
            this.user = user;
            this.resId = resId;
            this.checkin = checkin;
            this.checkout = checkout;
        }

        @Override
        public String toString() {
            return "Booking{" +
                    "user=" + user +
                    ", resId=" + resId +
                    ", checkin=" + checkin +
                    ", checkout=" + checkout +
                    '}';
        }
    }
}
