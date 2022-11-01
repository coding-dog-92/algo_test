package com.algo.company.MS;

public class CopyList {
    public static Node copyDoubleList(Node head) {
        if(head==null) return null;
        Node cur = head, pre = null;
        Node dummy = new Node(-1);
        Node copyPre = dummy;
        while (cur!=null) {
            Node copyCur = new Node(cur.val);
            copyCur.pre = copyPre;
            copyPre.next = copyCur;

            cur = cur.next;
            copyPre = copyCur;
        }
        dummy.next.pre = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        head.next = n2;
        n2.pre = head;
        n2.next = n3;
        n3.pre = n2;
        n3.next=n4;
        n4.pre=n3;
        Node cur = head;
        while (cur!=null) {
            System.out.println(cur);
            cur = cur.next;
        }
        System.out.println("++++++");
        cur = copyDoubleList(head);
        while (cur!=null) {
            System.out.println(cur);
            cur = cur.next;
        }
//        System.out.println(copyDoubleList(head));
    }

    static class Node{
        int val;
        Node pre, next;
        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node{val=").append(val).append(", pre=").append(pre==null? "" : pre.val).append(", next=").append(next==null? "":next.val).append("}");
            return sb.toString();
    }
}
}
