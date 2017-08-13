package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by C_Luc on 07/08/2017.
 *
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach
 */
public class BFS {

    public static final int INFITITY = Integer.MAX_VALUE;

    public static class Edge {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static ArrayList<ArrayList<Edge>> list;

    private static void init(int nodes) {
        list = new ArrayList<>();
        for(int i=0; i<nodes; i++)
            list.add(new ArrayList<>());
    }

    public static void addEdge(Edge edge) {
        list.get(edge.from).add(edge);
    }

    public static int [] shortestReach(int start) {
        int distances [] = new int[list.size()];
        boolean visiteds [] = new boolean[list.size()];
        for(int i=0; i<list.size(); i++) {
            distances[i] = i == start ? 0 : INFITITY;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int nodeStart = queue.poll();
            visiteds[nodeStart] = true;
            ArrayList<Edge> edges = list.get(nodeStart);
            for(Edge edge : edges) {
                if(!visiteds[edge.to]) {
                    int cost = distances[nodeStart] + edge.weight;
                    if(distances[edge.to] > cost) {
                        distances[edge.to] = cost;
                        queue.add(edge.to);
                    }
                }
            }
        }

        for( int i=1; i<visiteds.length; i++) {
            if(!visiteds[i])
                distances[i] = -1;
        }

        return distances;
    }

    public static void reader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int queries = Integer.parseInt(bufferedReader.readLine());
            // consultas
            for(;queries>0;queries--) {
                StringTokenizer tk  = new StringTokenizer(bufferedReader.readLine(), " ");
                int nodes = Integer.parseInt(tk.nextToken());
                int edges = Integer.parseInt(tk.nextToken());
                // arestas
                init(nodes+1);
                for(int idx=edges; idx>0;idx--) {
                    tk  = new StringTokenizer(bufferedReader.readLine(), " ");
                    int from = Integer.parseInt(tk.nextToken());
                    int to = Integer.parseInt(tk.nextToken());
                    Edge a = new Edge(from, to, 6);
                    Edge b = new Edge(to, from, 6);
                    addEdge(a);
                    addEdge(b);
                }
                int start = Integer.parseInt(bufferedReader.readLine());
                int distances  [] = shortestReach(start);
                for(int i=1; i<=nodes; i++) {
                    if(i == start)
                        continue;
                    System.out.printf("%d ", distances[i]);
                }
                System.out.println("\n");
            }
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        reader();
    }
}
