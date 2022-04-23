package com.test.dp;

public class CatchThief {

    public static void main(String[] args) {
        System.out.println(new CatchThief().numOfCatch(new char[]{'T','T','P','P','P','P'}, 2));
    }

    /**
     * [P,T,T,P,T]
     * k=1,res=2
     * [T,T,P,P,T,P]
     * k=2,res=3
     *[P,T,T,T,T,T,P,P,T]
     * k=1
     */
    public int numOfCatch(char[] arr, int k) {
        int posP = 0, posT = 0;
        int maxCount = 0;
        while (posP<arr.length && posT<arr.length) {
            while (posP<arr.length && arr[posP] != 'P') {
                posP++;
            }
            while (posT<arr.length && arr[posT] != 'T') {
                posT++;
            }
            if (posP>=arr.length || posT>=arr.length)
                break;
            if (Math.abs(posP-posT)<=k) {
                maxCount++;
                posP++;
                posT++;
            } else if(posP<posT) {
                posP++;
            } else {
                posT++;
            }
        }
        return maxCount;
    }
}
