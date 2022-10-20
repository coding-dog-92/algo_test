package com.algo.company.flexport;

import java.util.*;

public class Sentence {
    /**
     * 【第一问】
     * 给一个句子 text = "this is a sentence it is not a good one and it is also bad"
     * 给一个数字 n = 5
     * 输出一个单词长度为 n 的句子
     * . check 1point3acres for more.
     * 要求：. ----
     * 输出的第一个单词在 text 里随机选择
     * 第 k + 1 个单词是 text 里所有第 k 个单词的后一个单词的随机选择（比如第 k 个单词是 is，那么第 k + 1 个单词就是 [a, not, also] 里随机选择一个）
     * . 1point3acres.com
     * 实现方案：
     * 定义一个字典 / 哈希表 / Set，key 是当前单词，value 是当前单词的后一个单词的选项数组。
     * 注意边界条件，末尾 bad 的下一个单词是句首的 this。
     * . .и
     * 【第二问】
     * 给一个句子 text = "this is a sentence it is not a good one and it is also bad".
     * 给一个数字 n = 5
     * 给一个数字 m = 2.1point3acres
     * 输出一个单词长度为 n 的句子
     * 要求：
     * 输出的前 m 个单词在 text 里随机选择一个连续的单词组合. check 1point3acres for more.
     * 下一个单词是当前输出的末尾 m 个单词在 text 里后一个单词的随机选择（比如现在输出的末尾是 it is，那么下一个单词就是 [not, also] 里随机选择一个）
     */
    /**
     * 注意题目只是让输出一条合法的字符串就行，如果是要输出所有合法的字符串，那么需要dfs回溯
     * 最后一个元素的下一个元素是第一个?
     * 重复元素random概率是不是提高，是就用list，否则用set
     */

    public static String printRandom(String text, int n) {
        if (text==null||text.length()==0) return "";

        String[] arr = text.split(" ");
        int len = arr.length;
        Map<String, List<String>> map = buildGraph(arr);
        Random random = new Random();
        int start = random.nextInt(len);
        List<String> res = new ArrayList<>();
        res.add(arr[start]);
        String key = arr[start];
        while (res.size()<n) {
            List<String> list = map.get(key);
            if (list==null || list.size()==0) {
                System.out.println("error");
                break;
            }
            int idx = random.nextInt(list.size());
            res.add(list.get(idx));
            key = list.get(idx);
        }
        return String.join(" ", res);
    }

    public static Map<String, List<String>> buildGraph(String[] arr) {
        Map<String, List<String>> res = new HashMap<>();
        int len = arr.length;
        for(int r = 0;r<len;r++) {
            String key = arr[r];
            if(!res.containsKey(key)) res.put(key, new ArrayList<>());
            res.get(key).add(arr[(r+1)%len]);
        }
        return res;
    }

    public static String printRandom(String text, int n, int m) {
        // todo coner cases;
        if (text==null||text.length()==0||m>n) return "";
        String[] arr = text.split(" ");
        Map<String, List<String>> map = buildGraph(arr, m);
        List<String> res = new ArrayList<>(), path = new LinkedList<>();
        // 生成第一个位置
        int len = arr.length;
        Random random = new Random();
        int start = random.nextInt(len);
//        System.out.println(start);
        while (path.size()<m) {
            res.add(arr[start%len]);
            path.add(arr[start%len]);
            start++;
        }
        while (res.size()<n) {
            if (path.size()>m) path.remove(0);
            if (path.size()==m) {
                String key = String.join(" ", path);
                List<String> list = map.get(key);
                if (list==null || list.size()==0) {
                    System.out.println("error");
                    break;
                }
                int idx = random.nextInt(list.size());
                res.add(list.get(idx));
                path.add(list.get(idx));
            }
        }
//        System.out.println(map);
        return String.join(" ", res);
    }

    public static Map<String, List<String>> buildGraph(String[] arr, int m) {
        Map<String, List<String>> res = new HashMap<>();
        List<String> path = new LinkedList<>();
        int len = arr.length;
        for(int r = 0;r<len+m-1;r++) {
            path.add(arr[r%len]);
//            System.out.println(path);
            if(path.size()>m) path.remove(0);
            if(path.size()==m) {
                String key = String.join(" ", path);
//                System.out.println(key);
                if(!res.containsKey(key)) res.put(key, new ArrayList<>());
                res.get(key).add(arr[(r+1)%len]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "this is a sentence it is not a good one and it is also not a bad it is just a sentence";
//        for (int i = 0; i < 10; i++) {
//            String res = printRandom(s, 33);
//            System.out.println(res);
//        }

//        long start = System.currentTimeMillis();
//        for (int i=0;i<100000;i++) {
//            List<String> result = generateRandom(s, 5, 2);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
////        System.out.println(String.join(" ", result));
        for (int i=0;i<10;i++) {
            String res = printRandom(s, 6, 6);
            System.out.println(res);
        }
//        end = System.currentTimeMillis();
//        System.out.println(end-start);
    }

    public static List<String> generateRandom(String input, int n, int m) {
        String[] array = input.split(" ");
        Map<String, List<Integer>> map = populateMap(array, m);
//        System.out.println(map);

        int index = new Random().nextInt(array.length);
        List<String> result = new ArrayList<>();
        result.addAll(getKey(array,index,m));
        while(result.size() != n) {
//            System.out.println("Reulst: "+ Arrays.toString(result.toArray()));
            List<String> key = new ArrayList<>();
            for (int i = result.size() - m; i < result.size(); i++) {
                key.add(result.get(i));
            }

            List<Integer> nextIndexs = map.get(String.join(" ", key));
            index = nextIndexs.get(new Random().nextInt(nextIndexs.size()));
            result.add(array[index]);
        }

        return result;
    }

    public static Map<String, List<Integer>> populateMap(String[] strings, int m) {
        Map<String, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String key = String.join(" ",getKey(strings, i, m));
//            System.out.println(key);
            int nextIndex = (i+m)%strings.length;
            if(!result.containsKey(key)) {
                result.put(key, new ArrayList<>());
            }
            result.get(key).add(nextIndex);
        }
        return result;
    }

    public static List<String> getKey(String[] strings,int currentIndex, int m) {
        List<String> result = new ArrayList<>();
        int index = currentIndex;
        while(result.size() != m) {
            result.add(strings[index%strings.length]);
            index++;
        }
        return result;
    }

}
