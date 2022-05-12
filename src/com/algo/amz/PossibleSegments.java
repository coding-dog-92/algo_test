package com.algo.amz;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class PossibleSegments {
    /**
     *[1,3,6]
     * 3
     * 5
     */
    public int countPossibleSegments(int[] weights, int k) {
        int n = weights.length;
        Deque<Integer> incrQueue = new ArrayDeque<>();
        Deque<Integer> decrQueue = new ArrayDeque<>();
        int count = 0;
        for (int right = 0, left = 0; right < n; right++) {
            while (!incrQueue.isEmpty() && weights[right]<incrQueue.getLast()) {
                incrQueue.pollLast();
            }
            while (!decrQueue.isEmpty() && weights[right]>decrQueue.getLast()) {
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

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n-k+1];
        for (int i = 0, j=0; i < n; i++) {
            // check first
            while (!deque.isEmpty() && deque.getFirst()<i-k+1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[i]>nums[deque.getLast()]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i-k+1>=0) {
                res[j++] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    /**
     * 1,3,6
     * 3
     * 5
     * j->left, i->right
     */
    public int getNumberOfSegments(int k, int[] weights) {
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
            // [0,1,2]
            // therefore, just sum it up
            count += i - j + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new PossibleSegments().countPossibleSegments(new int[]{1,3,6},3));
    }
}
