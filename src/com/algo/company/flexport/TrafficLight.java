package com.algo.company.flexport;

public class TrafficLight {
    /**
     * https://leetcode.com/discuss/interview-question/1143290/Build-a-program-to-simulate-traffic-flowing-through-the-city
     *
     */
    // 0代表普通路，1代表信号灯，假设进入起点的时候是绿灯，之后每分钟变一次，那么可以根据当前时间奇偶数判断红绿灯
    int[] road;
    int car = 0;

    public TrafficLight(int[] road) {
        this.road = road;
    }

    public int costTime() {
        int cost = 0;
        for(int i=1;i<road.length;i++) {
            cost++;
            if (road[i]!=0&&!isGreen(cost)) cost++;
        }
        return cost;
    }

    private boolean isGreen(int cost) {
//        return true;
        return (cost&1)==0;
    }

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight(new int[]{0,1,1,0});
        System.out.println(trafficLight.costTime());
    }
}
