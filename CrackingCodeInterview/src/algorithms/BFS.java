package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by C_Luc on 07/08/2017.
 *
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach
 *
 * Override equal e hashcode
 * https://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
 * https://stackoverflow.com/questions/12787947/overriding-object-equals-vs-overloading-it
 * Whenever a.equals(b), then a.hashCode() must be same as b.hashCode().
 * do contrario havera uma quebra de contrato com o metodo equals
 * DONE
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

        @Override
        public String toString() {
            return String.format("from %d to %d", from, to);
        }

        @Override
        public boolean equals(Object o) {
            Edge that = (Edge) o;
            return this.from == that.from && this.to == that.to;
        }

        @Override
        public int hashCode() {
            return from + to;
        }
    }

    private static List<Set<Edge>> list;

    private static void init(int nodes) {
        list = new ArrayList<>();
        for(int i=0; i<nodes; i++)
            list.add(new LinkedHashSet<Edge>());
    }

    public static void addEdge(Edge edge) {
        list.get(edge.from).add(edge);
    }

    public static int [] shortestReach(int start) {
        int distances [] = new int[list.size()];
        //boolean v [] = new boolean[list.size()];
        for(int i=0; i<list.size(); i++) {
            distances[i] = i == start ? 0 : INFITITY;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int nodeStart = queue.poll();
            //if(!v[nodeStart]) { v[nodeStart] = true; }
            Set<Edge> edges = list.get(nodeStart);
            for(Edge edge : edges) {
                int cost = distances[nodeStart] + edge.weight;
                if(distances[edge.to] > cost) {
                    distances[edge.to] = cost;
                    queue.offer(edge.to);
                }
            }
        }

        for( int i=1; i<distances.length; i++) {
            if(distances[i] == INFITITY)
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
                System.out.println("");
            }
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        reader();
    }
}
