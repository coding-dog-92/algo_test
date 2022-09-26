package com.algo;

import java.util.*;

public class Test {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
//        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
//        System.out.println(maxProduct(new int[]{-1,-2,-9,-6}));
//        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
//        System.out.println(coinChange(new int[]{1,2,5}, 11));

//
//        int[][] ints = firstSmaller(new int[]{5, 3, 1, 2, 4});
//        System.out.println(Arrays.deepToString(ints));
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(5);
//        list.set(0,1);
//        list.contains(1);
//        list.remove();
//        System.out.println(list);
//        Arrays.sort(new Integer[]{1,2,3}, (a,b)->a.compareTo(b));
        System.out.println(2^3);
    }

    static String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();

        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;

            if (inStack[c]) continue;

            while (!stk.isEmpty() && stk.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }
            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
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
//        System.out.println(s);
        yi = s.indexOf('万');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('万');
            s = s.substring(yi+1);
        }
//        System.out.println(s);
        yi = s.indexOf('千');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('千');
            s = s.substring(yi+1);
        }
//        System.out.println(s);
        yi = s.indexOf('百');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('百');
            s = s.substring(yi+1);
        }
//        System.out.println(s);
        yi = s.indexOf('卅');
        if(yi != -1) {
            res += numMap.get('卅');
            s = s.substring(yi+1);
        }
//        System.out.println(s);
        yi = s.indexOf('十');
        if(yi != -1) {
            res += (convertStr2Num(s.substring(0, yi)))*numMap.get('十');
            s = s.substring(yi+1);
        }
//        System.out.println(s);

        yi = s.indexOf('零');
        if (yi > 0 && exclusiveZeroSet.contains(s.charAt(yi-1))) s=s.substring(yi+1);
//        System.out.println(s);
        if(s.length()>0) {
            long tmp = 0;
            for (int k=0;k<s.length();k++) {
                tmp  = tmp*10+numMap.get(s.charAt(k));
            }
            res += tmp;
        }
        return res;
    }


    private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }


    public boolean isValid(String code) {
        int n = code.length();
        Deque<String> tags = new ArrayDeque<String>();

        int i = 0;
        while (i < n) {
            if (code.charAt(i) == '<') {
                if (i == n - 1) {
                    return false;
                }
                if (code.charAt(i + 1) == '/') {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }
                    String tagname = code.substring(i + 2, j);
                    if (tags.isEmpty() || !tags.peek().equals(tagname)) {
                        return false;
                    }
                    tags.pop();
                    i = j + 1;
                    if (tags.isEmpty() && i != n) {
                        return false;
                    }
                } else if (code.charAt(i + 1) == '!') {
                    if (tags.isEmpty()) {
                        return false;
                    }
                    if (i + 9 > n) {
                        return false;
                    }
                    String cdata = code.substring(i + 2, i + 9);
                    if (!"[CDATA[".equals(cdata)) {
                        return false;
                    }
                    int j = code.indexOf("]]>", i);
                    if (j < 0) {
                        return false;
                    }
                    i = j + 3;
                } else {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }
                    String tagname = code.substring(i + 1, j);
                    if (tagname.length() < 1 || tagname.length() > 9) {
                        return false;
                    }
                    for (int k = 0; k < tagname.length(); ++k) {
                        if (!Character.isUpperCase(tagname.charAt(k))) {
                            return false;
                        }
                    }
                    tags.push(tagname);
                    i = j + 1;
                }
            } else {
                if (tags.isEmpty()) {
                    return false;
                }
                ++i;
            }
        }

        return tags.isEmpty();
    }


    int i=0;
    public int calculate(String s) {
        // num是求字符串中连续的数字，sign是指当前位置的符号是正还是负
        int num = 0;

        char sign = '+';

        Stack<Integer> stack = new Stack<>();

        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            //如果有左括号，进行递归计算括号里面的值
            if (c == '(') {
                i++;//递归进入去左括号的下一个字符
                num = calculate(s);
            }

            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                System.out.println(i);
                switch (sign) {

                    case '+':
                        stack.push(num);
                        break;

                    case '-':
                        stack.push(-num);
                        break;

                    case '*':
                        stack.push(stack.pop() * num);
                        break;

                    case '/':
                        stack.push(stack.pop() / num);
                        break;

                }
                sign = c;
                num = 0;
                if(sign == ')') break;
            }
            System.out.println(stack);
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static int compress(char[] chars) {
        int n = chars.length;
        int left = 0, right = 0;
        while (right<n) {
            char cur = chars[right];
            while (chars[right]==cur) right++;
            int num = right-left;
            chars[left] = cur;
            left++;
            if (num>1) {
                String numStr = String.valueOf(num);
                for (int i=0;i<numStr.length();i++) {
                    chars[left++] = numStr.charAt(i);
                }
            }
        }
        return left;
    }

    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length, ans = 1, leftMax = arr[0]; // leftMax: 前缀最大值
        int[] rightMin = new int[n]; // 后缀最小值数组
        rightMin[n-1] = arr[n-1];
        for (int i = n-2; i >= 0; i--) rightMin[i] = Math.min(arr[i], rightMin[i+1]);
        System.out.println(Arrays.toString(rightMin));
        for (int i = 1; i < n; i++) {
            System.out.println(leftMax);
            if (leftMax <= rightMin[i]) ans++; // i 位置“守得住”，可以增加分块
            leftMax = Math.max(leftMax, arr[i]);
        }
        return ans;
    }

    public static int[][] firstSmaller(int[] nums) {
        int[] leftSmaller = new int[nums.length];
        int[] rightSmaller = new int[nums.length];
        Arrays.fill(leftSmaller, -1);
        Arrays.fill(rightSmaller, -1);
        Deque<Integer> queue = new LinkedList<>(); // increasing queue
        for (int i = 0; i < nums.length; i++) {    // left to right
            while (!queue.isEmpty() && nums[queue.peekLast()] >= nums[i]) {
                rightSmaller[queue.pollLast()] = nums[i];
            }
            if (!queue.isEmpty()) {
                leftSmaller[i] = nums[queue.peekLast()];
            }
            queue.offerLast(i);
        }

        return new int[][]{leftSmaller, rightSmaller};
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];
        leftProduct[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }
        int right = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            leftProduct[i] = leftProduct[i] * right;
            right = right * nums[i];
        }
        return  leftProduct;
    }

    // [-2,1,-3,4,-1,2,1,-5,4]
    /**
     * dp; index~[0,nums.length-1]
     * dp[i] maxSum from 0 to i
     * dp[i] = max(dp[i-1]+nums[i], nums[i])
     * [5,4,-1,7,8]
     */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = nums[0];
        for (int i=1;i<nums.length;i++) {
            System.out.println(nums[i]);
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            sum = Math.max(sum, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return sum;
    }

    public static int maxSubArray1(int[] nums) {
        int pre = nums[0];
        int sum = nums[0];
        for (int i=1;i<nums.length;i++) {
            pre = Math.max(nums[i], nums[i] + pre);
            sum = Math.max(sum, pre);
        }
        return sum;
    }


    // [-4,-3,-2]
    /**
     * dp[i] = max(dp[i]*nums[i], nums[i])
     * -1,-2,-9,-6
     */
    public static int maxProduct(int[] nums) {
        int preMax = nums[0], preMin = nums[0], maxProduct = nums[0];
        for (int i=1;i<nums.length;i++) {
            int tmpMax = preMax, tmpMin = preMin;
            preMax = Math.max(Math.max(tmpMax*nums[i], nums[i]), tmpMin*nums[i]);
            preMin = Math.min(Math.min(tmpMax*nums[i], nums[i]),  tmpMin*nums[i]);
            maxProduct = Math.max(maxProduct, preMax);
        }
        return maxProduct;
    }

    public static int maxProduct1(int[] nums) {
        int[] maxArr = new int[nums.length];
        maxArr[0] = nums[0];
        int[] minArr = new int[nums.length];
        minArr[0] = nums[0];

        int sum = nums[0];
        for (int i=1;i<nums.length;i++) {
            maxArr[i] = Math.max(Math.max(nums[i], nums[i] * maxArr[i-1]), nums[i] * minArr[i-1]);
            minArr[i] = Math.min(Math.min(nums[i], nums[i] * maxArr[i-1]), nums[i] * minArr[i-1]);
            sum = Math.max(sum, maxArr[i]);
        }
//        System.out.println(Arrays.toString(maxArr));
//        System.out.println(Arrays.toString(minArr));

        return sum;
    }


    /**
     * [4,5,6,7,8,0,1,2]
     * [0,1,2,4,5,6,7,8]
     * [3,4,5,1,2]
     * [2,1]
     * all the elements left to point > first element
     * all the elements right to point < first element
     */
    public static int findMin(int[] nums) {
        if (nums[0] < nums[nums.length-1]) {
            return  nums[0];
        }
        int low=0, high=nums.length-1;
        while (low<high) {
            int mid = (low+high)/2;
            if (nums[mid]>=nums[0]) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return nums[low];

    }


    /**
     *
     *
     *
     * ==========================================================================
     *
     *
     *
     */


    /**
     * 根据id查询对应配置
     *
     * @param nums
     * @param target
     * @return
     * [4,5,6,7,0,1,2], target = 0
     * [0,1,2,4,5,6,7]
     * if nums[pivot]<target then right
     * else if nums[pivot]>target and target>nums[low] left
     * else right
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0]==target? 0 : -1;
        }
        int low = 0, high = nums.length-1;
        while (low<=high) {
            int mid = low+(high-low)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid]>=nums[low]) {
                if (target>=nums[low] && target<nums[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else {
                if (target>nums[mid] && target<=nums[high]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }
        return -1;
    }




    /*
      [-1,0,1,2,-1,-4]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums.length<3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++) {
            if (i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int target = -nums[i];
            int left = i+1, right = nums.length-1;
            while (left<right) {
                if (nums[left]+nums[right] == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left<right && nums[left]==nums[left-1]) {
                        left++;
                    }
                    while (left<right && nums[right]==nums[right+1]) {
                        right--;
                    }
                } else if (nums[left]+nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }


    /**
     * [2,7,11,15], target = 9
     * [1,2]
     * -1,-1,1,1,1,1,1,1
     * -2
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers.length < 2) {
            return res;
        }
        int left = 0, right = numbers.length-1;
        while (left<right) {
            if (numbers[left]+numbers[right]==target) {
                return new int[]{left+1, right+1};
            } else if (numbers[left]+numbers[right]>target) {
                right--;
                while (left<right && numbers[right]==numbers[right+1]) {
                    right--;
                }
            } else {
                left++;
                while (left<right && numbers[left]==numbers[left-1]) {
                    left++;
                }
            }
        }
        return res;
    }


    /**
     * [1,8,6,2,5,4,8,3,7]
     * 49
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int res = 0, left=0, right=height.length-1;
        while (left<right) {
            int tmpArea = (right-left)*Math.min(height[left], height[right]);
            if (tmpArea>res) {
                res = tmpArea;
            }
            if (height[left]<height[right]) {
                left ++;
            } else {
                right--;
            }
        }
        return res;
    }

    /**
     * coins = [1,2,5], amount = 11
     * 3
     * F(i) = minF(i-c)+1
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i>=c) {
                    dp[i] = Math.min(dp[i], dp[i-c]+1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        if (dp[amount] == amount+1) {
            return -1;
        }
        return dp[amount];
    }


    /**
     * [10,9,2,5,3,7,101,18]
     * 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * dp[i] = max(dp[j])+1, 0<=j<i when nums[i]>num[j]
     *
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            if (dp[i]>len) {
                len = dp[i];
            }
        }
        return len;
    }


    /**
     * text1 = "abcde", text2 = "ace"
     * 3
     * The longest common subsequence is "ace" and its length is 3.
     * dp[i] = dp[j]+1, 0<=j<i when text2[i]=text1[j]
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        String longStr = text1, shortStr = text2;
        int res = 0;
        if (text2.length()>text1.length()) {
            longStr = text2;
            shortStr = text1;
        }
        int[] dp = new int[shortStr.length()];
        Arrays.fill(dp, 0);
        for (int i = 0; i < longStr.length(); i++) {
            if (longStr.charAt(i) == shortStr.charAt(0)) {
                dp[0] = 1;
                break;
            }
        }
        for (int i = 0; i < shortStr.length(); i++) {
            for (int j = 0; j < longStr.length(); j++) {
                if (shortStr.charAt(i) == longStr.charAt(i)) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            if (dp[i]>res) {
                res = dp[i];
            }
        }
        return res;
    }
}
