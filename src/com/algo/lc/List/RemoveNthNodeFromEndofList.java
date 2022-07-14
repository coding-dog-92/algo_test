package com.algo.lc.List;

public class RemoveNthNodeFromEndofList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head, pre = null;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        if (pre == null) {
            head = head.next;
        } else {
            pre.next = slow.next;
        }
        return head;
    }
}
