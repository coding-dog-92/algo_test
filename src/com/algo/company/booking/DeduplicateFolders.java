package com.algo.company.booking;

import java.io.File;
import java.util.*;

public class DeduplicateFolders {
    /**
     * https://leetcode.com/discuss/interview-experience/1698865/bookingcom-amsterdam-software-developer-first-round-2022-reject
     * Question: We noticed that after a few years in production our file storage started taking too space. Luckily for us, we identified a high percent of identical files and folders in the storage and decided to compact it. As a solution we decided to find all identical folder structures, deduplicate and create symbolic links to the only copy.
     * We follow the next principle: 2 folders are identical if they contain an identical set of files and subfolders. 2 files are identical if they have the same name.
     *
     *
     * \
     * |—— bar
     * |    |—— baz
     * |    |  |—— boo
     * |    |  |      |—— a
     * |    |  |      |—— b
     * |    |  |—— c
     * |    |—— fizz
     * |    |  |—— buzz
     * |               |—— a
     * |               |—— b
     * |—— foo
     * |    |—— boo
     *      |        |—— a
     *      |        |—— b
     *      |——c
     * In the example above folder bar/baz/boo is identical to bar/fizz/buzz and foo/boo; also bar/baz is identical to foo. (and few other combinations like boo and buzz are also identical).
     */


    /**
     *                                  \
     *                          /              \
     *                        bar              foo
     *                      /   \             /   \
     *                    baz    fizz        boo   c
     *                  /   \     /          /  \
     *                boo    c   buzz       a    b
     *               /  \       /  \
     *              a    b    a     b
     *  node.val+"#"+serial(node.left)+"#"+serial(node.right)
     *  serial(node.left)+"#"+node.left.val+"#"+serial(node.right)+"#"+node.right.val+"#"
     *  a: ""
     *  boo: #a##b#
     *  buzz: #a##b#
     *  baz: #a##b#boo##c
     *
     *  serial(dir.child1)+"#"+dir.child1.name+serial(dir.child2)+"#"+dir.child2.name+....
     */

    Map<String, Integer> key2CountMap = new HashMap<>();
    Map<String, List<File>> key2FileListMap = new HashMap<>();

    String serialize(File dir) {
        if (!dir.exists()) return "";
        String key = "";
        if (dir.isDirectory()) {
            for(File child : dir.listFiles()) {
                key += serialize(child) + "#" + child.getName() + "#";
            }
        }
        int count = key2CountMap.getOrDefault(key, 0);
        if (!"".equals(key) && count>0) {
            System.out.println("find duplicate: " + dir);
            boolean delete = deleteDir(dir);
            if (!delete) {
                System.out.println("delete failed...");
            }
        }
        key2CountMap.put(key, count+1);
        if (!key2FileListMap.containsKey(key)) {
            key2FileListMap.put(key, new ArrayList<>());
        }
        key2FileListMap.get(key).add(dir);
        return key;
    }

    boolean deleteDir(File file) {
        if (!file.exists()) return true;
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteDir(child);
            }
        }
        if (file.delete()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DeduplicateFolders deduplicateFolders = new DeduplicateFolders();
        File file = new File("D:\\booking");
        deduplicateFolders.serialize(file);
//        System.out.println(deduplicateFolders.key2CountMap);
        for (Map.Entry<String, Integer> entry : deduplicateFolders.key2CountMap.entrySet()) {
            if (!"".equals(entry.getKey()) && entry.getValue()>1) {
                System.out.println(deduplicateFolders.key2FileListMap.get(entry.getKey()));
            }
        }
    }
}
