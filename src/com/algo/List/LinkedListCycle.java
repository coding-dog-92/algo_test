package com.algo.List;

public class LinkedListCycle {

    /**
     * two pointers
     * length of cycle: keep intersection, slow continuely iterate through until meet intersection
     * enter: two pointers from start, fast go cycle-length first, then two pointers start going,  when they meet, the node is enter
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head==null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (slow != null && fast != null) {
            if (slow==fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next != null? fast.next.next :null;
        }
        return false;
    }
}
