package com.algo.lc.trees;

import java.util.Stack;

public class Lc1597 {
//    1597. Build Binary Expression Tree From Infix Expression

    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // solution1
    /*
2-3/(5*2)+1
     i

"3*4-2*5"

2-3/*+1

()

3

3*4+5/6
 i

parse2() + parse2() - parse2()+ parse2() + parse2()
                         +
                  -
           +
parse2()     parse2()


3*2-3/(5*2)+1
i

parse1()

+-
*\/

()

+/-:  1
*\/-: 2


1 + 2 * 3 / 4 *

          i
sign:
num :

    -
2     +
   /    1
 3   *
    5 2

sign: [+, *]
            (
              + / * /
                      )

num : [1, 2, 3



1 + 2 - 3

   +
1    -
    2  3
   -
 +   3
1 2
*/
    char[] a;
    int idx, n;

    public Node expTree1(String s) {
        a = s.toCharArray();
        idx = 0;
        n = a.length;
        return parse1();
    }

    private Node parse1() {
        Node node = parse2(); // * /

        while (idx < n && (a[idx] == '+' || a[idx] == '-')) {
            node = new Node(a[idx++], node, parse2());
        }

        return node;
    }

    private Node parse2() {
        Node node = parse0();

        while (idx < n && (a[idx] == '*' || a[idx] == '/')) {
            node = new Node(a[idx++], node, parse0());
        }

        return node;
    }

    private Node parse0() {
        if (a[idx] == '(') {
            idx++;
            Node node = parse1();
            idx++; // skip )
            return node;
        }
        return new Node(a[idx++]);
    }

    // solution2
    public Node expTree2(String s) {
        return createTree(s);
    }

    private Node createTree(String s) {
        if (s.length() == 1) return new Node(s.charAt(0));


        int idx = findRootIdx(s); //N
        while (idx == -1) {
            if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
                s = s.substring(1, s.length() - 1);
                idx = findRootIdx(s);
            } else {
                return null;
            }
        }
        Node left = createTree(s.substring(0, idx));
        Node right = createTree(s.substring(idx + 1));

        return new Node(s.charAt(idx), left, right);
    }

    private int findRootIdx(String s) {
        int open = 0;
        int close = 0;
        int idx = -1;
        for (int i = 0; i < s.length(); i++) {
            if (open == close &&
                    (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                idx = i;
            }
            if (s.charAt(i) == '(') open++;
            if (s.charAt(i) == ')') close++;
        }
        if (idx != -1) return idx;
        open = 0;
        close = 0;
        for (int i = 0; i < s.length(); i++) {
            if (open == close &&
                    (s.charAt(i) == '/' || s.charAt(i) == '*')) {
                idx = i;
            }
            if (s.charAt(i) == '(') open++;
            if (s.charAt(i) == ')') close++;
        }
        return idx;
    }


    public Node expTree3(String s) {
        // we put () around whole string because we only stop at '('
        s = '(' + s + ')';
        Stack<Node> stack = new Stack<Node>();
        for (char c : s.toCharArray()) {
            // check [#3](https://leetcode.com/problems/longest-substring-without-repeating-characters) and #4
            if (!Character.isDigit(c) && c != '(') {
                // right is the digit on the right side of previous opeator
                Node right = stack.pop();
                // if it's '*' or '/', we need to check if the previous operator
                // in stack if also '*' or '/', because e.g. 2 + 3 * 5, we have to
                // calcualte the 3 * 5 first
                if (c == '*' || c == '/') {
                    if (stack.peek().val == '*' || stack.peek().val == '/') {
                        Node operator = stack.pop();
                        Node left = stack.pop();
                        operator.left = left;
                        operator.right = right;
                        right = operator;
                    }
                }
                // if it's '+' or '-' or ')', we need to go all the way back
                // until we see '(', cuz e.g. (2 + 3 * 5 + 4), for the 2nd '+',
                // after we done with 3 * 5, we still have to do 2 + 15
                else {
                    while (stack.peek().val != '(') {
                        Node operator = stack.pop();
                        Node left = stack.pop();
                        operator.left = left;
                        operator.right = right;
                        right = operator;
                    }
                    // pop out the '(' once we process ')'
                    if (c == ')') {
                        stack.pop();
                    }
                }
                stack.push(right);
            }
            // we never insert ')' into stack
            if (c != ')') {
                stack.push(new Node(c));
            }
        }
        return stack.pop();
    }
}
