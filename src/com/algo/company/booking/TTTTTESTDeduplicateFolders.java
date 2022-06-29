package com.algo.company.booking;

import java.io.File;
import java.util.*;

public class TTTTTESTDeduplicateFolders {

    Map<String, Integer> key2Count = new HashMap<>();
    Map<File, String> node2Key = new HashMap<>();
    Map<String, List<File>> key2Path = new HashMap<>();
    public static void main(String[] args) {
        TTTTTESTDeduplicateFolders TTTTTESTDeduplicateFolders = new TTTTTESTDeduplicateFolders();
        File file = new File("D:\\booking");
        TTTTTESTDeduplicateFolders.getKey(file);
        System.out.println(TTTTTESTDeduplicateFolders.key2Count);
        for (Map.Entry<String, Integer> entry : TTTTTESTDeduplicateFolders.key2Count.entrySet()) {
            if (!"".equals(entry.getKey()) && entry.getValue()>1)
                System.out.println(TTTTTESTDeduplicateFolders.key2Path.get(entry.getKey()));
        }
//        System.out.println(deduplicateFolders.key2Path);
//        deduplicateFolders.dfs(file);
    }

    /**
     *
     */
    public String getKey(File node) {
        if (node==null) return "";
        String key = "";
        if (node.isDirectory()) {
            for (String curName : node.list()) {
                if (curName.startsWith(".")) {
                    continue;
                }
                key += getKey(new File(node, curName)) + "#" + curName;
            }
        }
        int count = key2Count.getOrDefault(key, 0);
//        if (count>0) {
//            System.out.println("duplicate dir/file: "+node.getName());
//        }
        if (!"".equals(key)) {
            if (!key2Path.containsKey(key)) key2Path.put(key, new ArrayList<>());
            key2Path.get(key).add(node);
        }
        key2Count.put(key, count+1);
        node2Key.put(node, key);
        System.out.println(node);
        System.out.println("key is: "+key);
        return key;
    }

    public void dfs(File node) {
        String key = node2Key.get(node);
        if (key != "" && key2Count.get(key)>1) System.out.println("duplicate dir/file: "+node);
        if (node.isDirectory()) {
            for (String curName : node.list()) {
                if (curName.startsWith(".")) {
                    continue;
                }
                dfs(new File(node, curName));
            }
        }
    }

}
