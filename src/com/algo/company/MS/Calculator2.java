package com.algo.company.MS;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator2 {
    private int index = 0;
    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return cal(ch);
    }
    // 利用栈把乘除法先做了，然后放到栈里的只有加减法，最后求和就行
    // 利用递归处理括号
    private int cal(char[] ch){
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        for(; index < ch.length; index++){
            char c = ch[index];
            if(Character.isDigit(c)){
                num = num*10 + (c-'0');
            }
            if(c == '('){
                index++;//index指针指到下一个字符
                num = cal(ch);
            }
            //当遇到了新的运算符，就要对上一个运算符sign和累计的数字num作运算
            //空格直接无视，i继续前进
            //遇到字符串末尾，肯定是要结算的
            if(!Character.isDigit(c)&& c != ' ' || index == ch.length-1){
                int pre = 0;
                switch(sign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                sign = c;
                num = 0;//计数归位
            }
            if(c == ')') break;//阶段，后面开始计算局部结果，返回
        }

        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
