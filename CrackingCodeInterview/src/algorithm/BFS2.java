package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by C_Luc on 12/08/2017.
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem
 *
 * Solucao usando a estrutura da site hackerank
 * DONE
 */
public class BFS2 {


    public static final int INFITITY = Integer.MAX_VALUE;

    public static class Graph {
        static List<List<Integer>> list;
        static int size;

        public Graph(int size) {
            this.size = size;
            list = new ArrayList<>();
            for(int i=0; i<size; i++) {
                list.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to) {
            list.get(from).add(to);
        }

        public int[] shortestReach(int startId) {
            int distance [] = new int[size];
            for(int i=0; i<size; i++)
                distance[i] = i == startId ? 0 : INFITITY;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startId);
            while (!queue.isEmpty()) {
                int from = queue.poll();
                for(Integer to : list.get(from) ) {
                    int cost = distance[from] + 6;      // todas as arestas pesam 6
                    if(distance[to] > cost) {
                        distance[to] = cost;
                        queue.offer(to);
                    }
                }
            }
            for(int i=0; i<distance.length; i++)
                distance[i] = distance[i] == INFITITY ? -1 : distance[i];
            return distance;
        }
    }

    private static void readerData() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int queries = Integer.parseInt(bufferedReader.readLine());
            for(;queries>0;queries--) {
                StringTokenizer tk  = new StringTokenizer(bufferedReader.readLine(), " ");
                int nodes = Integer.parseInt(tk.nextToken());
                int edges = Integer.parseInt(tk.nextToken());
                Graph graph = new Graph(nodes + 1);
                for(;edges>0;edges--) {
                    tk          = new StringTokenizer(bufferedReader.readLine(), " ");
                    int from    = Integer.parseInt(tk.nextToken());
                    int to      = Integer.parseInt(tk.nextToken());
                    graph.addEdge(from, to);
                    graph.addEdge(to, from);
                }
                int start = Integer.parseInt(bufferedReader.readLine());
                int result [] = graph.shortestReach(start);
                for(int i=1; i<=nodes; i++) {
                    if(i == start)
                        continue;
                    System.out.printf("%d ", result[i]);
                }
                System.out.println("");
            }
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        readerData();
    }
}
