package com.br.algograph.maxflow.hackerearth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C_Luc on 10/09/2017.
 */
public class FordFulkersonAlgorithm {


    public static class Edge {
        int to, flow, capacity;
        public Edge(int to, int flow, int capacity) {
            this.to = to;
            this.flow = flow;
            this.capacity = capacity;
        }
    }

    public static int [] seen;      // vertices visitados
    public static int timer = 0;    //

    public static List<List<Edge>> graph;

    public static void initializer(int vertices) {
        graph = new ArrayList<>();
        for(int i=0; i<vertices; i++) {
            graph.add(new ArrayList<>());
        }
        seen = new int[vertices];
    }

    /**
     * implementacao de uma dfs
     * */
    public static int find(int s, int t, int flow) {
        if(s==t) {
            return flow;
        }
        for(Edge e : graph.get(s)) {

        }

        return 0;
    }

    /**
     * Adiciona aresta de u-v e v-u iniciando com fluxo 0
     * */
    public static void addEdge(int u, int v, int capacity) {
        Edge uv = new Edge(v, 0, capacity);

        Edge vu = new Edge(u, 0, capacity);
    }


}
