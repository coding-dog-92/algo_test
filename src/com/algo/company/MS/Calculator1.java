package com.algo.company.MS;

public class Calculator1 {
    // 递归处理
    int i=0;
    public int calculate(String s) {
        int sign = 1, sum = 0, n = s.length();

        while(i<n) {
            if(s.charAt(i)==' ') {
                i++;
                continue;
            }
            int num=0;
            while(i<n&&Character.isDigit(s.charAt(i))) {
                num = num*10+(s.charAt(i)-'0');
                i++;
            }
            if(i<n&&s.charAt(i)=='(') {
                i++;
                num = calculate(s);
            }
            sum += sign*num;
            if(i<n&&s.charAt(i)==')') {
                i++;
                return sum;
            }
            if(i<n&&s.charAt(i)=='+') {
                sign = 1;
                i++;
            }
            else if(i<n&&s.charAt(i)=='-') {
                sign = -1;
                i++;
            }
        }
        return sum;
    }


    // 直接使用栈模拟，栈里面存储当前所处位置的正负号
    // 1.碰到+，那么和之前的sign一致
    // 2.碰到-，反转之前的sign
    // 3.碰到(，入栈当前sign，即括号中的所有元素的值最后都依赖当前sign
    // 4.碰到)，出栈sign
    // 5.数字则直接处理
    // public int calculate(String s) {
    //     Deque<Integer> ops = new LinkedList<Integer>();
    //     ops.push(1);
    //     int sign = 1;

    //     int ret = 0;
    //     int n = s.length();
    //     int i = 0;
    //     while (i < n) {
    //         if (s.charAt(i) == ' ') {
    //             i++;
    //         } else if (s.charAt(i) == '+') {
    //             sign = ops.peek();
    //             i++;
    //         } else if (s.charAt(i) == '-') {
    //             sign = -ops.peek();
    //             i++;
    //         } else if (s.charAt(i) == '(') {
    //             ops.push(sign);
    //             i++;
    //         } else if (s.charAt(i) == ')') {
    //             ops.pop();
    //             i++;
    //         } else {
    //             long num = 0;
    //             while (i < n && Character.isDigit(s.charAt(i))) {
    //                 num = num * 10 + s.charAt(i) - '0';
    //                 i++;
    //             }
    //             ret += sign * num;
    //         }
    //     }
    //     return ret;
    // }
}
