package com.algo.heap;

import java.util.*;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (!countMap.containsKey(num)) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, countMap.get(num) + 1);
            }
        }

        Queue<int[]> queue = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] to = new TopKFrequentElements().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(to));
    }
}
