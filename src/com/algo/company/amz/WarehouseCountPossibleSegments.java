package com.algo.company.amz;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class WarehouseCountPossibleSegments {
    /**
     * k=3
     * weights=[1,3,6]
     * output = 5
     * (0,0)->max(weight[0])-min(weight[0]) = max(1)-min(1)=0
     * (0,1)->max(1,3)-min(1,3) = 3-1=2
     * (0,2)->max(1,3,6)-min(1,3,6) = 6-1=5>3  error
     * (1,1)...
     * (1,2)...
     * (2,2)...
     */
    public static int countPossibleSegments(int k, int[] weights) {
        int n = weights.length;
        Deque<Integer> min = new LinkedList<>(); // incr monotonic queue
        Deque<Integer> max = new LinkedList<>(); // decr monotonic queue

        int j = 0, count = 0;
        for (int i = 0; i < n; i++) {
            while (!min.isEmpty() && weights[min.peekLast()] >= weights[i]) min.removeLast();
            min.add(i);
            while (!max.isEmpty() && weights[max.peekLast()] <= weights[i]) max.removeLast();
            max.add(i);

            // move the j to ensure the max - min > k
            while (weights[max.peekFirst()] - weights[min.peekFirst()] > k) {
                if (max.peekFirst() <= j) max.removeFirst();
                if (min.peekFirst() <= j) min.removeFirst();
                j++;
            }
            // if segment[j,i] is valid, then segment[j+1,i] to segment[i,i] are valid.
            // therefore, just sum it up
            count += i - j + 1;
        }
        return count;
    }

    public int countPossibleSegments1(int[] weights, int k) {
        int n = weights.length;
        Deque<Integer> incrQueue = new ArrayDeque<>();
        Deque<Integer> decrQueue = new ArrayDeque<>();
        int count = 0;
        for (int right = 0, left = 0; right < n; right++) {
            while (!incrQueue.isEmpty() && weights[right]<weights[incrQueue.getLast()]) {
                incrQueue.pollLast();
            }
            while (!decrQueue.isEmpty() && weights[right]>weights[decrQueue.getLast()]) {
                decrQueue.pollLast();
            }
            incrQueue.offer(right);
            decrQueue.offer(right);
            while (weights[decrQueue.getFirst()] - weights[incrQueue.getFirst()] > k) {
                if (decrQueue.getFirst()<=left) {
                    decrQueue.pollFirst();
                }
                if (incrQueue.getFirst()<=left) {
                    incrQueue.pollFirst();
                }
                left++;
            }
            count += right-left+1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPossibleSegments(3, new int[]{1,3,6}));
    }
}
