package com.algo.company;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DeduplicateFolders {

    Map<String, Integer> key2Count = new HashMap<>();
    Map<String, String> node2Key = new HashMap<>();
    public static void main(String[] args) {
        DeduplicateFolders deduplicateFolders = new DeduplicateFolders();
        File file = new File("/Users/ago/Desktop/test");
        deduplicateFolders.getKey(file);
        System.out.println(deduplicateFolders.key2Count);
        deduplicateFolders.dfs(file);
    }

    /**
     *
     */
    public String getKey(File node) {
        if (node==null) return "";
        String key = "";
        if (node.isFile()) {
            key = node.getName();
        } else {
            for (String curName : node.list()) {
                if (curName.startsWith(".")) {
                    continue;
                }
                key += getKey(new File(node, curName)) + "#";
            }
        }
        int count = key2Count.getOrDefault(key, 0);
//        if (count>0) {
//            System.out.println("duplicate dir/file: "+node.getName());
//        }
        key2Count.put(key, count+1);
        node2Key.put(node.getName(), key);
        System.out.println(node);
        System.out.println("key is: "+key);
        return key;
    }

    public void dfs(File node) {
        String key = node2Key.get(node.getName());
        if (key2Count.get(key)>1) System.out.println("duplicate dir/file: "+node.getName());
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
