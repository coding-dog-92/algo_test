package com.algo.company.tesla.vo;

import java.util.*;

public class ArithemeticEquation {
    /**
     * Given series of arithematic equations as a string:
     * A = B + C
     * B = C + D + 5
     * C = 4
     * D = 6
     * The equations only use addition and positive numbers and separated by newline.
     * Print value of each variable:
     * A = 19
     * B = 15
     * C = 4
     * D = 6
     *
     * notice:
     * 1. 未知数是单个字符还是字符串
     * 2.输入的等式一定是最简的吗？未知数前是否存在系数？例如 A=2B+C 或者 3A=B+4C
     * 3.支持的运算有哪些类型，是否支持括号，例如 A=(B+C)*2
     * 4.等式里是否存在相同变量，例如A=B+B
     * 5.等式左右两边有相同的变量不合法，例如A=A
     */
    Map<String, Integer> inDegreeMap = new HashMap<>();
    // 注意这里用list存储依赖当前变量的有哪些变量，这样如果碰到A=B+B的情况，存的是A:[B,B]，处理完A之后会对B的入度多次处理，保证正确性
    Map<String, List<String>> nextMap = new HashMap<>();
    // 存储当前节点依赖哪些节点，这样计算的时候把里面节点的值加起来就行
    Map<String, List<String>> depNodeMap = new HashMap<>();
    Map<String, Integer> valueMap = new HashMap<>();
    // v1.0 只考虑+的情况！
    List<String> solveEquation(List<String> equationList) {
        System.out.println(equationList);
        List<String> res = new ArrayList<>();
        if (!buildGraphAndCalIndegrees(equationList)) return res;
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : inDegreeMap.entrySet()) {
            if(entry.getValue()==0) queue.offer(entry.getKey());
        }
        System.out.println("======================start======================");
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            // 对于每个入度为0的节点，把它依赖的所有的变量加起来就行
            int oldValue = valueMap.getOrDefault(cur, 0);
            List<String> curDepList = depNodeMap.get(cur);
            if(curDepList!=null) {
                for (String depName : curDepList) {
                    oldValue+=valueMap.get(depName);
                }
            }
            valueMap.put(cur, oldValue);
            // 当前节点计算完成之后，更新那些依赖cur的节点
            List<String> nextList = nextMap.get(cur);
            if(nextList!=null) {
                for (String next : nextList) {
                    int nextIndegree = inDegreeMap.get(next);
                    inDegreeMap.put(next, nextIndegree-1);
                    if (nextIndegree-1==0) queue.offer(next);
                }
            }
        }
        // 校验当前是不是所有节点的入度都是0，否则说明无解
        for (Map.Entry<String, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue()!=0) return res;
        }
        for (Map.Entry<String, Integer> entry : valueMap.entrySet()) {
            res.add(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("======================end======================");
        return res;
    }

    boolean buildGraphAndCalIndegrees(List<String> equationList) {
        for (String equation : equationList) {
            String[] equationArr = equation.split("=");
            // 等号分为两部分
            if(equationArr.length!=2) return false;
            // 等号右边至少有一个数
            if (equationArr[1]==null||equationArr[1].length()==0) return false;
            String left = equationArr[0], right = equationArr[1];
            String[] rightArr = right.split("\\+");
            // 如果等号右边只有一个数字，那么相当于left变量被求出来了
            int inDegree = 0;
            if(rightArr.length==1 && Character.isDigit(rightArr[0].trim().charAt(0))) {
                int num = Integer.parseInt(rightArr[0].trim());
                valueMap.put(left, num);
            } else {
                // B + C + D + 5
                for (String variable : rightArr) {
                    variable = variable.trim();
                    // 等号右边有变量和左边相同，说明不合法
                    if (variable.equals(left)) return false;
                    // 当前是数字，那么先加到这个变量的值上去
                    if (Character.isDigit(variable.charAt(0))) {
                        int num = Integer.parseInt(variable);
                        valueMap.put(left, valueMap.getOrDefault(left, 0)+num);
                    } else {
                        // 是个变量，那么left要依赖这个变量
                        inDegree++;
                        if (!depNodeMap.containsKey(left)) depNodeMap.put(left, new ArrayList<>());
                        depNodeMap.get(left).add(variable);
                        if(!nextMap.containsKey(variable)) nextMap.put(variable, new ArrayList<>());
                        nextMap.get(variable).add(left);
                    }
                }
            }
            inDegreeMap.put(left, inDegreeMap.getOrDefault(left, 0)+inDegree);
        }
        return true;
    }

    public static void main(String[] args) {
        ArithemeticEquation equation = new ArithemeticEquation();
//        List<String> list = Arrays.asList("A=B+C","B=C+D+5","C=4","D=6");
//        List<String> list = Arrays.asList("A=C","B=E","C=D+4","D=6");
        List<String> list = Arrays.asList("A=C+1","B=C+C","C=D+4","D=6");

        System.out.println(equation.solveEquation(list));
    }




//
//    Map<String, Node> graph = new HashMap<>();
//    void buildGraphAndCalIndegrees1(List<String> euqationList) {
//        for (String equation : euqationList) {
//            String[] euqationArr = equation.split(":");
//            String left = euqationArr[0], right = euqationArr[1];
//            String[] rightArr = right.split("\\+");
//            Node node = graph.getOrDefault(left, new Node(left));
//            // 如果等号右边只有一个数字，那么相当于left变量被求出来了
//            if(rightArr.length==1 && Character.isDigit(rightArr[0].charAt(0))) {
//                int num = Integer.parseInt(rightArr[1]);
//                node.val = num;
//                node.status = true;
//            } else {
//                for (String variable : rightArr) {
//                    variable = variable.trim();
//                    int inDegree = 0;
//
//                    if () {
//
//                    } else {
//
//                    }
//                }
//            }
//        }
//    }
//
//    static class Node{
//        int val, inDegrees;
//        String name;
//        boolean status;
//        List<String> dependencyList;
//
//        public Node(String name) {
//            this.name = name;
//            this.dependencyList = new ArrayList<>();
//        }
//    }
}
