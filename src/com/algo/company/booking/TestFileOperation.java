package com.algo.company.booking;

import java.io.File;
import java.security.MessageDigest;
import java.util.Arrays;

public class TestFileOperation {

    public static void main(String[] args) {
        String root = "./";
        File dir = new File(root);
        if (!dir.exists()) {
            System.out.println("dir not exist!");
            return;
        }
        if (!dir.isDirectory()) {
            System.out.println("dir not exist!");
            return;
        }
        if (dir.isDirectory()) {
            System.out.println(Arrays.toString(dir.list()));
            for (String s : dir.list()) {
                System.out.println(s);
            }
        }
    }

    public static boolean compare(File a, File b) {
        if (a.isFile() && !b.isFile()) return false;
        if (a.isDirectory() && !b.isDirectory()) return false;
        if (a.isFile() && b.isFile()) return a.getName().equals(b.getName());
        //
        String[] aList = a.list();
        String[] bList = b.list();
        if (aList == null && bList == null) return true;
        if (aList == null || bList == null) return false;
        if (aList.length != bList.length) return false;
        for (int i = 0; i < aList.length; i++) {
            if (!compare(new File(aList[i]), new File(bList[i]))) return false;
        }
        return true;
    }


    /**
     * getInfo(left)+"#"+node.val+"#"+getInfo(right)
     * a#boo#b               a#buzz#b
     * getInfo(left)+"#"+lef.val+"#"+getInfo(right)+"#"+right.val...
     * #a##b                 #a##b
     * @param node
     * @return
     */
    public static String getInfo(File node) {
        return "";
    }
}
