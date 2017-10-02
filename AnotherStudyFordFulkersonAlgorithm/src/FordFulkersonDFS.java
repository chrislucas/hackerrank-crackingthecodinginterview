import java.util.ArrayList;

import static java.lang.Math.*;


/**
 * Created by r028367 on 26/09/2017.
 */
public class FordFulkersonDFS {


    public static class Edge {
        public int from, to, cap, flow;
        public Edge(int from, int to, int cap, int flow) {
            this.from   = from;
            this.to     = to;
            this.cap    = cap;
            this.flow   = flow;
        }

        public Edge(int from, int to, int cap) {
            this(from, to, cap, 0);
        }

        public int residualCapacity(int vertice) {
            return (vertice == from) ? flow : cap - flow; // (vertice == from) -> vertice inicial
        }

        public void addResidualFlowTo(int vertex, int delta) {
            flow +=  vertex == from ? -delta : delta;
        }

        public int other(int vertice) {
            return (vertice == from) ? to : from; // (vertice == from) -> aresta de inicial
        }

        @Override
        public String toString() {
            return String.format("From %d To %d Cap %d Flow %d", from, to, cap, flow);
        }
    }

    private static ArrayList<ArrayList<Integer>> ref;
    private static Edge[] edgesTo;
    private static int E = 0, V;
    private static int [] seen;
    private static int timer = 1;

    public static void init(int v, int e) {
        ref = new ArrayList<>();
        V = v;
        for(int i=0; i<v; i++) {
            ref.add(new ArrayList<>());
        }
        seen = new int[v];
        edgesTo = new Edge[e];
    }


    public static int dfs(int s, int t, int f) {
        if(s == t)
            return f;
        seen[s] = timer;
        for(int i : ref.get(s)) {
            int u = edgesTo[i].other(i);
            int diff = edgesTo[i].residualCapacity(u);
            if(seen[u] < timer && diff > 0) {
                seen[u] = timer;
                int flow = dfs(u, t, min(f, diff));
                if(flow > 0) {
                    int o = edgesTo[i].other(i);
                    edgesTo[o].addResidualFlowTo(o, flow);
                    return flow;
                }
            }
        }
        return 0;
    }

    public static void addEdge(int from, int to, int cap) {
        Edge ab = new Edge(from, to, cap, 0);
        edgesTo[E] = ab;
        ref.get(from).add(E++);
        Edge ba = new Edge(to, from, 0,0);
        edgesTo[E] = ba;
        ref.get(to).add(E++);
    }


    /**
     * Com a mesma estrutura tentar implementar o algoritmo usando uma DFS
     * Talvez de para atualizar as arestas do grafo residual usando a pilha
     * de recursao, um metodo diferente de como eh feito na BFS
     *
     * */

    public static void main(String[] args) {
        init(6, 20);
        test1(0, 5);
    }

    public static int test1(int s, int t) {
        // http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
        addEdge(0,1, 16);
        addEdge(0,2, 13);
        addEdge(1,2, 10);
        addEdge(1,3, 12);
        addEdge(2,1, 4);
        addEdge(2,4, 14);
        addEdge(3,2, 9);
        addEdge(3,5, 20);
        addEdge(4,3, 7);
        addEdge(4,5, 4);
        int rs      = -1;
        int inf     = Integer.MAX_VALUE;
        int flow    = 0;
        do {
            rs = dfs(s, t, inf);
            flow += rs;
            timer++;
        } while (rs != 0);
        return flow;
    }

}
