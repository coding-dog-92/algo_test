package com.algo.company.amz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BestCombos {


    /**
     * https://leetcode.com/discuss/interview-question/1895396/amazon-sde-new-grad-oa-k-most-popular-&#8205;&#8205;&#8204;&#8205;&#8205;&#8205;&#8205;&#8204;&#8205;&#8204;&#8204;&#8205;&#8205;&#8205;&#8205;&#8205;&#8204;&#8204;&#8205;&#8204;combos
     * 3 5 -2
     * 2 3 5
     * 8 6 3 5 1 -2 3 0
     *
     */
    public int[] bestCombos(int[] popularity, int k) {
        int max = 0;
        PriorityQueue<Integer> posQueue = new PriorityQueue<>(), negQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int p : popularity) {
            if (p > 0) {
                max += p;
                posQueue.offer(p);
                if(posQueue.size()>k) posQueue.poll();
            } else {
                negQueue.offer(p);
                if (negQueue.size()>k) negQueue.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (posQueue.isEmpty() && negQueue.isEmpty()) break;
            if (posQueue.isEmpty()) {
                res[i] = max - negQueue.poll();
            } else if (negQueue.isEmpty()) {
            }
        }
        return new int[0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n,k;

        String []nk=br.readLine().split("\\s+");
        n=Integer.valueOf(nk[0]);
        k=Integer.valueOf(nk[1]);

        int[] nums= Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();


        System.out.println(Arrays.toString(nums));

        PriorityQueue<int []> pq=new PriorityQueue<>((a,b)->b[0]-a[0]);

        int[] absArray=Arrays.stream(nums).map(i->Math.abs(i)).toArray();


        Arrays.sort(absArray);

        System.out.println(Arrays.toString(absArray));

        int maxSum=0;
        for(int num:nums)
            if(num>0)
                maxSum+=num;

        pq.add(new int[]{maxSum-absArray[0],0});

        List<Integer> ans=new ArrayList<>();
        ans.add(maxSum);


        while(ans.size()<k)
        {
            int[] cur =pq.poll();
            System.out.println(Arrays.toString(cur));
            int curSum=cur[0];
            int i=cur[1];
            ans.add(curSum);

            if(i+1<n)
            {
                pq.add(new int[]{curSum+absArray[i]-absArray[i+1],i+1});
                pq.add(new int[]{curSum-absArray[i+1],i+1});
            }

        }

        System.out.println(ans);


    }
}


