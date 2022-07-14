package com.algo.lc.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeandDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        String spe = Character.toString((char)258);
        if (strs.size()==0) {
            return spe;
        }
        String delimiter = Character.toString((char)257);
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
            sb.append(delimiter);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String spe = Character.toString((char)258);
        if (s.equals(spe)) {
            return new ArrayList<>();
        }
        String delimiter = Character.toString((char)257);
        return Arrays.asList(s.split(delimiter, -1));
    }

    public static void main(String[] args) {
        EncodeandDecodeStrings encodeandDecodeStrings = new EncodeandDecodeStrings();
        String encode = encodeandDecodeStrings.encode(Arrays.asList("\"\"", "\"\""));
        System.out.println(encode);
        List<String> decode = encodeandDecodeStrings.decode(encode);
        System.out.println(decode);
    }
}
