package com.algo.lc.List;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode dummyNode = new ListNode();
        ListNode p = list1, q = list2, cur = dummyNode;
        while (p != null && q != null) {
            if (p.val<q.val) {
                cur.next = p;
                p = p.next;
            } else {
                cur.next = q;
                q = q.next;
            }
            cur = cur.next;
        }
        while (p != null) {
            cur.next = p;
            p = p.next;
            cur = cur.next;
        }
        while (q != null) {
            cur.next = q;
            q = q.next;
            cur = cur.next;
        }
        return dummyNode.next;
    }
}
