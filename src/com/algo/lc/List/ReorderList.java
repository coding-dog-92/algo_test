package com.algo.lc.List;

public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while (fast!=null&&fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse
        ListNode pre = null, cur = slow, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        ListNode l1 = head, l2 = pre;
        while (l2.next != null) {
            next = l1.next;
            l1.next = l2;
            l1 = next;

            next = l2.next;
            l2.next = l1;
            l2 = next;
        }
    }
}
