package algorithm.maxflow.problems.URI;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by r028367 on 13/10/2017.
 * https://www.urionlinejudge.com.br/judge/pt/problems/view/1362
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1986
 *
 * Estudar algoritmo de bipartite matching
 * https://www.urionlinejudge.com.br/judge/pt/search?for=problems&q=bipartido
 * https://pt.wikipedia.org/wiki/Grafo_bipartido
 */
public class URI1362 {

    static Hashtable<String, Integer> table = new Hashtable<>();

    static {
        table.put("XXL", 0);
        table.put("XL", 1);
        table.put("L", 2);
        table.put("M", 3);
        table.put("S", 4);
        table.put("XS", 5);
    }

    public static class Edge {
        public int from, to, cap, flow;
        public Edge(int from, int to, int cap, int flow) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.flow = flow;
        }
    }

    public static Edge [] edges;
    public static List<List<Integer>> ref;
    public static int time = 1;
    public static int [] seen;

    public static int vFlow(int s, int t, int f) {
        return 0;
    }

    public static void init(int vertices) {
        ref = new ArrayList<>();
        for(int i=0; i<vertices; i++)
            ref.add(new ArrayList<>());
        seen = new int[vertices];
    }

    public static void add(int from, int to, int cap) {
        Edge ab = new Edge(from, to, cap, 0);
        Edge ba = new Edge(from, to, cap, 0);
    }

    public static void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cases = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer tk = new StringTokenizer(bufferedReader.readLine());
            int tShirts = Integer.parseInt(tk.nextToken());
            int volunteers = Integer.parseInt(tk.nextToken());
            init(volunteers);
            while(cases-->0) {
                tk = new StringTokenizer(bufferedReader.readLine());
                int ta = table.get(tk.nextToken());
                int tb = table.get(tk.nextToken());
            }
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        run();
    }

}
