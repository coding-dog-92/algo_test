package com;

import com.algo.TreeNode;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        TreeNode root = Utils.constructTree(new Integer[]{1, 2, 3, null, null, 4, 5, 6, 7});
//        Utils.show(root);
//        ArrayList<Integer> resultList = new ArrayList<>();
//        ViewOfBinaryTree viewOfBinaryTree = new ViewOfBinaryTree();
//        viewOfBinaryTree.leftView(root.left, 1, resultList);
//        Collections.reverse(resultList);
//        viewOfBinaryTree.maxLevel = 0;
//        viewOfBinaryTree.rightView(root, 1, resultList);
//        System.out.println(resultList);

//        System.out.println(sumSubArray(new int[]{2,3,2}));
        System.out.println(1e9);
    }

    static String add(String a, String b) {
//        if(a.length() < b.length()) return add(b, a);
        int m = a.length();
        int n = b.length();
        StringBuilder sb = new StringBuilder();
        int carry = 0, i=m-1, j=n-1;
        while(i>=0 || j>=0) {
            int num1 = i>=0? a.charAt(i)-'0' : 0;
            int num2 = j>=0? b.charAt(j)-'0' : 0;
            int curSum = num1+num2+carry;
            carry = curSum / 10;
            sb.append(curSum%10);
            i--;
            j--;
        }
        if(carry>0) sb.append(carry);
        return sb.reverse().toString();
    }

    /**
     * [2,3,2,1]
     * s[i] = s[i-1]+i*
     *       1
     *     2,1
     *   3,2,1
     * 2,3,2,1
     * ------------
     *2*1*len+3*2*(len-1)+...+1*len*1
     */
    public static int sumSubArray(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i]*(n-i)*(i+1);
        }
        return sum;
    }
}


class ViewOfBinaryTree {
    int maxLevel = 0;//the first element has been printed of this level
    public void leftView (TreeNode root, int currentLevel, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (maxLevel < currentLevel) {
            list.add(root.val);
            maxLevel = currentLevel;
        }
        leftView(root.left, currentLevel+1, list);
    }

    public void rightView (TreeNode root, int currentLevel, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (maxLevel < currentLevel) {
            list.add(root.val);
            maxLevel = currentLevel;
        }
        rightView(root.right, currentLevel+1, list);
    }
}