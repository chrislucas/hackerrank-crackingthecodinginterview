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
        for(int i=0; i<list.size(); i++)
            distances[i] = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int nodeStart = queue.poll();
            visiteds[nodeStart] = true;
            ArrayList<Edge> edges = list.get(nodeStart);
            for(Edge edge : edges) {
                if(!visiteds[edge.to]) {
                    queue.add(edge.to);
                    int cost = edge.weight + 6;
                    if(distances[edge.to] > cost)
                        distances[edge.to] = cost;
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
            StringTokenizer tk  = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(tk.nextToken());
            int m = Integer.parseInt(tk.nextToken());
            // consultas
            for(;queries>0;queries--) {
                // arestas
                init(n+1);
                for(int idx=m; idx>0;idx--) {
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

                for(int i=1; i<=n; i++) {
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
