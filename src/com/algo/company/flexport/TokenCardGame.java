package com.algo.company.flexport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TokenCardGame {
    static class Panel{
        Map<String, Card> cardMap;
        public Panel() {
            cardMap = new HashMap<>();
        }
        public boolean addCard(Card card) {
            cardMap.put(card.cardId, card);
            return true;
        }
        public boolean canPurchase(String cardId, Player player) {
            if (!cardMap.containsKey(cardId)) return false;
            Card card = cardMap.get(cardId);
            if (card.owner!=null) return false;
            int oweNum = 0;
            for (Map.Entry<Color, Integer> entry : card.priceMap.entrySet()) {
                Color color = entry.getKey();
                // int price = entry.getValue();
                int price = calculatePrice(color, entry.getValue(), player);
//                if(!player.currencyMap.containsKey(color) || price>player.currencyMap.get(color)) return false;
                int curOwnNum = player.currencyMap.getOrDefault(color, 0);
                if (price>curOwnNum) oweNum+=price-curOwnNum;
            }
//            return true;
            return player.currencyMap.getOrDefault(Color.GOLD, 0) >= oweNum;
        }
        public void purchase(String cardId, Player player) {
            if (!canPurchase(cardId, player)) return;
            Card card = cardMap.get(cardId);
            int oweNum = 0;
            for (Map.Entry<Color, Integer> entry : card.priceMap.entrySet()) {
                Color color = entry.getKey();
                // int price = entry.getValue();
                int price = calculatePrice(color, entry.getValue(), player);
                int cur = player.currencyMap.get(color);
//                player.currencyMap.put(color, cur-price);
                if (cur>=price) {
                    player.currencyMap.put(color, cur-price);
                } else {
                    oweNum+=price-cur;
                }
            }
            if (oweNum>0) {
                int cur = player.currencyMap.get(Color.GOLD);
                player.currencyMap.put(Color.GOLD, cur-oweNum);
            }
            card.setOwner(player);
            player.addCard(card);
        }
        private int calculatePrice(Color color, int oriPrice, Player player) {
            Set<String> set = player.ownCardMap.get(color);
            int ownNum = set==null ? 0 : set.size();
            return Math.max(0, oriPrice-ownNum);
        }
        public void printMap() {
            System.out.println(cardMap);
        }
    }

    static class Card{
        String cardId;
        Color color;
        Player owner;
        Map<Color,Integer> priceMap;
        public Card(String cardId, Color color, Map<Color, Integer> priceMap) {
            this.cardId = cardId;
            this.color = color;
            this.priceMap = priceMap;
        }
        public void setOwner(Player owner) {
            this.owner = owner;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "cardId='" + cardId + '\'' +
                    ", color=" + color +
                    ", owner=" + owner +
                    ", priceMap=" + priceMap +
                    '}';
        }
    }

    static class Player{
        String playerId;
        Map<Color, Set<String>> ownCardMap;
        Map<Color, Integer> currencyMap;
        public Player(String playerId, Map<Color, Integer> currencyMap) {
            this.playerId = playerId;
            ownCardMap = new HashMap<>();
            this.currencyMap = currencyMap;
        }
        public void addCard(Card card) {
            if (!ownCardMap.containsKey(card.color)) ownCardMap.put(card.color, new HashSet<>());
            ownCardMap.get(card.color).add(card.cardId);
        }
        @Override
        public String toString() {
            return "Player{" +
                    "playerId='" + playerId + '\'' +
                    ", ownCardMap=" + ownCardMap +
                    ", currencyMap=" + currencyMap +
                    '}';
        }
    }

    enum Color{
        RED,
        BLACK,
        BLUE,
        GREEN,
        WHITE,

        GOLD
    }

    public static void main(String[] args) {
        Panel panel = new Panel();
        Card card1 = new Card("1", Color.RED, new HashMap<Color, Integer>() {{
            put(Color.WHITE, 2);
            put(Color.BLACK, 1);
            put(Color.BLUE, 4);
        }});
        panel.addCard(card1);
        Card card2 = new Card("2", Color.WHITE, new HashMap<Color, Integer>() {{
            put(Color.RED, 2);
            put(Color.BLACK, 1);
            put(Color.BLUE, 2);
        }});
        panel.addCard(card2);
        Player playerA = new Player("playerA", new HashMap<Color, Integer>() {{
            put(Color.WHITE, 4);
            put(Color.BLACK, 2);
            put(Color.BLUE, 6);
            put(Color.RED, 0);
            put(Color.GOLD, 1);
        }});
        boolean canPurchase = panel.canPurchase(card1.cardId, playerA);
        System.out.println(canPurchase);
        panel.printMap();
        System.out.println(playerA);
        panel.purchase(card1.cardId, playerA);
        System.out.println("=========");
        panel.printMap();
        System.out.println(playerA);
        System.out.println("*******************");
        canPurchase = panel.canPurchase(card2.cardId, playerA);
        System.out.println(canPurchase);
        panel.printMap();
        System.out.println(playerA);
        panel.purchase(card2.cardId, playerA);
        System.out.println("=========");
        panel.printMap();
        System.out.println(playerA);
    }
}
