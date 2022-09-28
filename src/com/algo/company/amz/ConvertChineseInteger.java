package com.algo.company.amz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConvertChineseInteger {

    /**
     * 数字转中文
     *
     */
    public static final int YI = 100_000_000;
    public static final int WAN = 10_000;
    public static final int QIAN = 1000;
    public static final int BAI = 100;
    public static final int SHI = 10;

    public static String[] num2WordArr = {"", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    public static String number2Chinese(int num) {
        if(num==0) return "零";
        StringBuilder sb = new StringBuilder();
        boolean except = false;
        int yi = num/YI;
        if(yi>0) {
            except = true;
            sb.append(number2Chinese(yi)).append("亿");
            num%=YI;
        }
        int wan = num/WAN;
        if(wan>0) {
            except = true;
            sb.append(number2Chinese(wan)).append("万");
            num%=WAN;
        }
        int qian = num/QIAN;
        if(qian>0) {
            except = true;
            sb.append(number2Chinese(qian)).append("千");
            num%=QIAN;
        }
        int bai = num/BAI;
        if(bai>0) {
            except = true;
            sb.append(number2Chinese(bai)).append("百");
            num%=BAI;
        }
        int shi = num/SHI;
        if(shi>0) {
            sb.append(number2Chinese(shi)).append("十");
            num%=SHI;
        }
        if(num>0) {
            if(except&&shi==0) sb.append("零");
            sb.append(num2WordArr[num]);
        }
        return sb.toString();
    }




    static Map<Character, Long> numMap  = new HashMap<>();
    static Set<Character> exclusiveZeroSet = new HashSet<>();
    static {
        numMap.put('零', 0L);
        numMap.put('一', 1L);
        numMap.put('二', 2L);
        numMap.put('三', 3L);
        numMap.put('四', 4L);
        numMap.put('五', 5L);
        numMap.put('六', 6L);
        numMap.put('七', 7L);
        numMap.put('八', 8L);
        numMap.put('九', 9L);
        numMap.put('十', 10L);
        numMap.put('廿', 20L);
        numMap.put('卅', 30L);
        numMap.put('百', 100L);
        numMap.put('千', 1000L);
        numMap.put('万', 10_000L);
        numMap.put('亿', 100_000_000L);
        exclusiveZeroSet.add('亿');
        exclusiveZeroSet.add('万');
        exclusiveZeroSet.add('千');
        exclusiveZeroSet.add('百');
        exclusiveZeroSet.add('卅');
        exclusiveZeroSet.add('廿');
        exclusiveZeroSet.add('十');
    }
    static long convertStr2Num(String s) {
        long res = 0;
        int yi = s.indexOf('亿');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('亿');
            s = s.substring(yi+1);
        }
        yi = s.indexOf('万');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('万');
            s = s.substring(yi+1);
        }
        yi = s.indexOf('千');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('千');
            s = s.substring(yi+1);
        }
        yi = s.indexOf('百');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('百');
            s = s.substring(yi+1);
        }
        yi = s.indexOf('卅');
        if(yi != -1) {
            res += numMap.get('卅');
            s = s.substring(yi+1);
        }
        yi = s.indexOf('十');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('十');
            s = s.substring(yi+1);
        }
        yi = s.indexOf('零');
        // 亿 万..百后面的零都跳过
        if (yi > 0 && exclusiveZeroSet.contains(s.charAt(yi-1)))
            s=s.substring(yi+1);
        // 兼容一二三四这种写法
        if(s.length()>0) {
            long tmp = 0;
            for (int k=0;k<s.length();k++) {
                tmp  = tmp*10+numMap.get(s.charAt(k));
            }
            res += tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(number2Chinese(12345006));
    }
}
