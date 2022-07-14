package com.algo.company.MS;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII {
    List<Integer> numsList = new ArrayList<>();
    List<Integer> depthList = new ArrayList<>();
    List<Integer> weightList = new ArrayList<>();

    int maxDepth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            getDepth(nestedInteger, 1);
        }
        for (Integer integer : depthList) {
            if (integer>maxDepth) maxDepth = integer;
        }
        for (Integer integer : depthList) {
            weightList.add(maxDepth-integer+1);
        }
        System.out.println(numsList);
        System.out.println(depthList);
        System.out.println(weightList);
        int res = 0;
        for (int i = 0; i < numsList.size(); i++) {
            res += numsList.get(i) * weightList.get(i);
        }
        return res;
    }

    void getDepth(NestedInteger nestedInteger, int depth) {
        if (nestedInteger.isInteger()) {
            numsList.add(nestedInteger.getInteger());
            depthList.add(depth);
        }
        for (NestedInteger integer : nestedInteger.getList()) {
            getDepth(integer, depth+1);
        }
    }
}


interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}