package com.algo.company.amz;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PacketArrChannel {

    public static void main(String[] args) {
        System.out.println(maximumQuality(Arrays.asList(4,3,1,2,6,5), 3));
    }
    /**
     * stress-test quality of the servers' channels
     * 给一个packets数组arr和channel k, 要求每个channel里面必须至少有一个数组里面的元素，每个元素只能在一个channel里面。要求算出所有channel中位数之和的最大值。
     * 比如arr = [4，3，1，2，6，5] k = 3 最大的就是
     * channel 1 = [1, 2, 3, 4]
     * channel 2 = [5]
     * channel 3 = [6]
     * 最大中位数之和为 (2 + 3) /2 + 5 + 6 = 14 注意有小数的2.5的话就取相邻的大的整数3.
     *
     * sort first
     * start from last, assign 1 packet to 1 channel, till channels-1
     * [1,2,3,4,5,6]
     */
    public static long maximumQuality(List<Integer> packets, int channels){
        long res = 0;
        Collections.sort(packets);
        int n = packets.size();
        for (int i = n-1;i>n-channels;i--) {
            res += packets.get(i);
        }
        // 0~n-channels
        int len = n-channels+1;
        // odd
        if ((len&1)==1) {
            res += packets.get(len / 2);
        } else {
            res += (packets.get(len/2) + packets.get(len/2-1)+1)/2;
        }
        return res;
    }
}
