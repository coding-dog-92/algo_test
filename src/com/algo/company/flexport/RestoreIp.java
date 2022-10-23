package com.algo.company.flexport;

import java.util.ArrayList;
import java.util.List;

public class RestoreIp {

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length()<4 || s.length()>12) return res;
        dfs(s, 0, 0, new ArrayList<>());
        return res;
    }

    void dfs(String s, int idx, int seg, List<String> path) {
        if (seg==4) {
            if (idx==s.length()) {
                res.add(String.join(".", path));
            }
            return;
        }
        // 字符串到头了还没拼出4段来
        if (idx==s.length()) return;
        // 剩余长度太短或太长
        if (s.length()-idx<4-seg || s.length()-idx>3*(4-seg)) return;
        // 不能有前导0，因此当前字符串为0说明这一段只有一个0
        if (s.charAt(idx)=='0') {
            path.add("0");
            dfs(s, idx+1, seg+1, path);
            path.remove(path.size()-1);
            return;
        }
        int num = 0;
        for (int k=idx;k<s.length();k++) {
            num = num*10+(s.charAt(k)-'0');
            if (num>255) break;
            path.add(String.valueOf(num));
            dfs(s, k+1, seg+1, path);
            path.remove(path.size()-1);
        }
    }
}
