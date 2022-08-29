package com.review_test.booking;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * node.val+serial(node.left)+serial(node.right)
     *
     * serial(node.left)+#+node.left.val+#+serial(node.right)+#+node.right.val+#
     * if is file, str=""
     * a: ""
     * boo: #a##b#
     * buzz: #a##b#
     * baz: #a##b##boo##c#
     *
     */

    Map<String, Integer> key2Count = new HashMap<>();
    Map<String, List<File>> key2FileListMap = new HashMap<>();

    String serialize(File file) {
        if (!file.exists()) return "";
        String res = "";
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                res += serialize(child) + "#" + child.getName() + "#";
            }
        }
        int count = key2Count.getOrDefault(res, 0);
        if (!"".equals(res) && count>0) {
            // delete
        }
        key2Count.put(res, count+1);
        if (!key2FileListMap.containsKey(res)) key2FileListMap.put(res, new ArrayList<>());
        key2FileListMap.get(res).add(file);
        return res;
    }

    boolean delete(File file) {
        if (!file.exists()) return true;
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                delete(child);
            }
        }
        return file.delete();
    }

    public static void main(String[] args) {
        DeduplicateFolders folders = new DeduplicateFolders();
        File file = new File("D:\\booking");
        folders.serialize(file);
        for (Map.Entry<String, List<File>> entry : folders.key2FileListMap.entrySet()) {
            System.out.println("key= "+entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
