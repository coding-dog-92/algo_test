package com.algo.company.booking;

import com.Main;

import java.util.*;

public class MergeIntervalRooms {
    /**
     * https://leetcode.com/discuss/interview-question/2346458/Booking.com-or-Fintech-Unit-or-London-or-SWE-or-Phone-Call(2nd-round)
     * **Foreword: **
     * As a first round there was a Hackerank test, to implement a basic REST API using JAVA SpringBoot, would be useful to familirize yourself with best practices.
     * Then 15 mins HR call .
     *
     * Interview
     * It was 90 mins of coding challenge , there were 2 interviewers and 1 shadow interviewer.
     * the task was very similar to MergeIntervals;
     *
     * The price of hotel rooms varies from day to day , to save on the request size and storage, when the same price for the same room applies to multiple consecutive days , we store that information in a single entry, with startDate and EndDate defining the date range.
     *
     * class Price {
     *     int roomId;
     *     double value;
     *     int startDay;
     *     int endDay;
     * }
     * dates are in epoch format , example of sequential and non-sequatial dates:
     *
     * [1,2][3,4] - sequential have to be merged as [1,4]
     * [2,2][1,1] - sequential have to be merged as [1,2]
     * [1,4][3,5] - not sequential , overlap will not hapen
     * [1,4][6,7] - not sequential , overlap will not hapen
     * [1,1][1,1] - not sequential , overlap will not hapen
     * input
     *
     * [
     *    {
     *       "roomId":1,
     *       "value":2.0,
     *       "startDate":1,
     *       "endDate":2
     *    },
     *    {
     *       "roomId":1,
     *       "value":2.0,
     *       "startDate":3,
     *       "endDate":4
     *    },
     *    {
     *       "roomId":1,
     *       "value":5.0,
     *       "startDate":1,
     *       "endDate":2
     *    }
     * ]
     * output
     *
     * [
     *    {
     *       "roomId":1,
     *       "value":2.0,
     *       "startDate":1,
     *       "endDate":4
     *    },
     *    {
     *       "roomId":1,
     *       "value":5.0,
     *       "startDate":1,
     *       "endDate":2
     *    }
     * ]
     * No need to execute the code, you have to implement your approach then interviewer will give u test cases and ask you to be a compiler, then you have to measure the complexity of your code.
     */

    List<Price> mergePrice(List<Price> priceList) {
        if (priceList.size()<2) return priceList;
        // classify
        Map<String, List<Price>> map = new HashMap<>();
        for (Price price : priceList) {
            String key = price.roomId+"#"+price.value;
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(price);
        }
        List<Price> res = new ArrayList<>();
        for (List<Price> list : map.values()) {
            res.addAll(merge(list));
        }
        return res;
    }

    List<Price> merge(List<Price> priceList) {
        if (priceList.size()<2) return priceList;
        priceList.sort((a,b)->a.startDay-b.startDay);
        Price pre = null;
        List<Price> res = new ArrayList<>();
        for (Price price : priceList) {
            if (pre == null) {
                pre = price;
            } else {
                if (price.startDay == pre.endDay+1) {
                    pre.endDay = price.endDay;
                } else {
                    res.add(pre);
                    pre = price;
                }
            }
        }
        res.add(pre);
        return res;
    }

    public static void main(String[] args) {
        List<Price> list = Arrays.asList(
                new Price(1, 2.0, 1, 2),
                new Price(1, 2.0, 3, 4),
                new Price(1, 5.0, 1, 2)
//                new Price(1, 2.0, 7, 9)
        );
        System.out.println(new MergeIntervalRooms().mergePrice(list));
    }


    static class Price {
        int roomId;
        double value;
        int startDay;
        int endDay;

        public Price(int roomId, double value, int startDay, int endDay) {
            this.roomId = roomId;
            this.value = value;
            this.startDay = startDay;
            this.endDay = endDay;
        }

        @Override
        public String toString() {
            return "Price{" +
                    "roomId=" + roomId +
                    ", value=" + value +
                    ", startDay=" + startDay +
                    ", endDay=" + endDay +
                    '}';
        }
    }
}
