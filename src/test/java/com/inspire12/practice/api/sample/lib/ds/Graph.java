package com.inspire12.practice.api.sample.lib.ds;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<List<Edge>> graph = new ArrayList<>();

    public Graph(int nodeCount) {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        Edge newEdge = new Edge(from, to, weight);
        graph.get(from).add(newEdge);
    }
}
