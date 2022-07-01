package com.algo.company.booking;

import java.util.*;

public class LearnGreedy {

    public static void greedy() {
        Map<String, Set<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        Set<String> allAreas = new HashSet<>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        List<String> selects = new ArrayList<>();
        while (!allAreas.isEmpty()) {
            String maxKey = "";
            int curMax = 0;

            for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
                Set<String> intersectSet = new HashSet<>(entry.getValue());
                intersectSet.retainAll(allAreas);
                if (intersectSet.size()>curMax) {
                    maxKey = entry.getKey();
                    curMax = intersectSet.size();
                }
            }
            if (curMax == 0) break;
            if (curMax>0) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }

    public static void callCenter() {
        // all task
        Map<String, Integer> allTasks = new HashMap<>();
        allTasks.put("english", 21);
        allTasks.put("dutch", 14);
        allTasks.put("spanish", 7);

        Map<String, Set<String>> candidates = new HashMap<>();
        candidates.put("1", new HashSet<>(Arrays.asList("english", "dutch")));
        candidates.put("2", new HashSet<>(Arrays.asList("english", "dutch")));
        candidates.put("3", new HashSet<>(Arrays.asList("spanish", "dutch")));
        candidates.put("4", new HashSet<>(Arrays.asList("english", "dutch", "spanish")));
        candidates.put("5", new HashSet<>(Arrays.asList("dutch", "spanish")));
        candidates.put("7", new HashSet<>(Arrays.asList("english", "spanish")));

        List<String> selects = new ArrayList<>();
        Map<String, Integer> workDays = new HashMap<>();

        while (!isEmpty(allTasks)) {
            String curCan = "";
            int max = 0;
            if (candidates.isEmpty()) break;
            for (Map.Entry<String, Set<String>> entry : candidates.entrySet()) {
                int cur = 0;
                for (String skill : entry.getValue()) {
                    if (allTasks.get(skill) > 0) cur++;
                    if (cur>max) {
                        max = cur;
                        curCan = entry.getKey();
                    }
                }
            }
            if (max == 0) {break;}
            System.out.println("curCan: "+curCan);
            if (max>0) {
                // delete
                selects.add(curCan);
                int day = workDays.getOrDefault(curCan, 0);
                workDays.put(curCan, day+1);
                for (String s : candidates.get(curCan)) {
                    int curNum = allTasks.get(s);
                    if (curNum>0) {
                        allTasks.put(s, curNum-1);
                    }
                }
                if (day>=4) candidates.remove(curCan);
            }
            System.out.println("all: "+allTasks);
            System.out.println("candidates: "+candidates);
        }

        System.out.println(selects);

    }

    static boolean isEmpty(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()>0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        greedy();
    }


}
