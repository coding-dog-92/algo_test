package com.algo.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length == 0) {
            return null;
        }
        int n = lists.length;
        ListNode res = new ListNode();
        ListNode current = res;
        while (true) {
            ListNode minNode = null;
            int minIdx = -1;
            for (int i = 0; i < n; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode==null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minIdx = i;
                }
            }
            if (minIdx == -1) {
                break;
            }
            current.next = minNode;
            current = current.next;
            lists[minIdx] = lists[minIdx].next;
        }
        return res.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists==null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> queue = new PriorityQueue<>((n1, n2)->n1.val-n2.val);
        ListNode res = new ListNode();
        ListNode current = res;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            current.next = node;
            current = current.next;
            if (node.next != null) {
                queue.offer(node.next);
            }

        }
        return res.next;
    }

}
