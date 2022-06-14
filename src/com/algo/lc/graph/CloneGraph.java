package com.algo.lc.graph;

import java.util.*;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    Map<Node, Node> visitedMap = new HashMap<>();


    public Node cloneGraphDFS(Node node) {
        if (node==null) {
            return null;
        }
        if (visitedMap.containsKey(node)) {
            return visitedMap.get(node);
        }
        Node copyNode = new Node(node.val, new ArrayList<>());
        visitedMap.put(node, copyNode);
        for (Node neighbor : node.neighbors) {
            copyNode.neighbors.add(cloneGraphDFS(neighbor));
        }
        return copyNode;
    }

    public Node cloneGraphBFS(Node node) {
        if (node==null) {
            return null;
        }
        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);
        visitedMap.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node pop = queue.pop();
            for (Node neighbor : pop.neighbors) {
                if (!visitedMap.containsKey(neighbor)) {
                    visitedMap.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.push(neighbor);
                }
                visitedMap.get(pop).neighbors.add(visitedMap.get(neighbor));
            }
        }
        return visitedMap.get(node);
    }
}
