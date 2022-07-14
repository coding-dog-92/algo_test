package com.algo.lc.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

    // max heap
    private Queue<Integer> left;
    // min heap
    private Queue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((a,b)->b-a);
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left.size() == right.size()) {
            if (left.isEmpty() || num<=right.peek()) {
                left.offer(num);
            } else {
                left.offer(right.poll());
                right.offer(num);
            }
        } else {
            if (num>=left.peek()) {
                right.offer(num);
            } else {
                right.offer(left.poll());
                left.offer(num);
            }
        }
    }

    public double findMedian() {
        if (left.size() != right.size()) {
            return left.peek();
        }
        return (left.peek()+right.peek())/2.0;
    }
}
