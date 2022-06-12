package com.algo.amz;

public class CompareVersionNumbers {
    public static int compareVersion(String version1, String version2) {
        String[] vArr1 = version1.split("\\.");
        String[] vArr2 = version2.split("\\.");
        String[] longArr, shortArr;
        boolean flag = true;
        if (vArr1.length>vArr2.length) {
            longArr = vArr1;
            shortArr = vArr2;
        } else {
            longArr = vArr2;
            shortArr = vArr1;
            flag = false;
        }

        for (int i = 0; i < longArr.length; i++) {
            int longVal = Integer.parseInt(longArr[i]);
            int shortVal = 0;
            if (i < shortArr.length) {
                shortVal = Integer.parseInt(shortArr[i]);
            }
            if (longVal > shortVal) {
                if (flag) return 1;
                else return -1;
            }
            if (longVal < shortVal) {
                if (flag) return -1;
                else return 1;
            }
        }
        return 0;
    }

    public static int convertInt(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("1.01", "1.001"));
    }
}
