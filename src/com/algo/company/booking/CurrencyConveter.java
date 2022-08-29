package com.algo.company.booking;

import java.util.*;

public class CurrencyConveter {

    List<Rate> res = new ArrayList<>();

    /**
     *
     */
    public List<Rate> convert(List<Rate> rateList, String from, String to) {
        Map<String, List<Rate>> adjacentList = new HashMap<>();
        for (Rate rate : rateList) {
            if (!adjacentList.containsKey(rate.from)) adjacentList.put(rate.from, new ArrayList<>());
            adjacentList.get(rate.from).add(rate);
        }
        System.out.println(adjacentList);
        Set<String> visited = new HashSet<>();
        List<Rate> path = new ArrayList<>();
        dfs(adjacentList, from, to, path, visited);
        System.out.println(res);
        return res;
    }

    boolean dfs(Map<String, List<Rate>> adjacentList, String cur, String target, List<Rate> path, Set<String> visited) {
        System.out.println(cur);

        if (cur.equals(target)) {
            System.out.println("eqqqqq....");
            res = path;
            return true;
        }
        if (!adjacentList.containsKey(cur)) {
            System.out.println("cannot find...");
            return false;
        }
        for (Rate rate : adjacentList.get(cur)) {
            String key = rate.from+"-"+rate.to;
            if (!visited.contains(key)) {
                System.out.println(key);
                visited.add(key);
                path.add(rate);
                boolean r = dfs(adjacentList, rate.to, target, path, visited);
                if (r) return true;
                path.remove(path.size()-1);
                visited.remove(key);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CurrencyConveter currencyConveter = new CurrencyConveter();
        List<Rate> list = Arrays.asList(
                new Rate("AUD", "USD", "0.5"),
                new Rate("USD", "EUR", "0.5"),
                new Rate("EUR", "GBP", "0.5"),
                new Rate("GBP", "EUR", "0.5"),
                new Rate("EUR", "USD", "0.5"),
                new Rate("USD", "AUD", "0.5")
                );
        currencyConveter.convert(list, "AUD", "xxx");
    }

    static class Rate {
        String from;
        String to;
        String rate;

        public Rate(String from, String to, String rate) {
            this.from = from;
            this.to = to;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "Rate{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", rate='" + rate + '\'' +
                    '}';
        }
    }
}
