package com.algo.company.flexport;

public class ValidIp {
    public String validIPAddress(String queryIP) {
        if(queryIP.length()==0) return "Neither";
        if(check4(queryIP)) return "IPv4";
        if(check6(queryIP)) return "IPv6";
        return "Neither";
    }

    boolean check4(String ip) {
        if(ip.charAt(ip.length()-1)=='.') return false;
        String[] arr = ip.split("\\.");
        if(arr.length != 4) return false;
        for(String s : arr) {
            if(s.length()<1||s.length()>3) return false;
            for(int i=0;i<s.length();i++) {
                if(!Character.isDigit(s.charAt(i))) return false;
                if(i>0&&s.charAt(0)=='0') return false;
            }
            int num = Integer.parseInt(s);
            if(num<0||num>255) return false;
        }
        return true;
    }

    boolean check6(String ip) {
        if(ip.charAt(ip.length()-1)==':') return false;
        String[] arr = ip.split(":");
        if(arr.length != 8) return false;
        for(String s : arr) {
            if(s.length()<1||s.length()>4) return false;
            for(int i=0;i<s.length();i++) {
                if(!checkChar(s.charAt(i))) return false;
            }
        }
        return true;
    }

    boolean checkChar(char ch) {
        if((ch>='0'&&ch<='9') || (ch>='a'&&ch<='f') || (ch>='A'&&ch<='F')) return true;
        return false;
    }
}
