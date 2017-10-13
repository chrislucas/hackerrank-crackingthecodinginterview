import java.util.ArrayList;

/**
 * Created by C_Luc on 12/10/2017.
 */
public class FFDFS {

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
        public int getResidualCapacity(int vertice) {
            return (vertice == from) ? flow : cap - flow;
        }
        public void addResidualFlow(int vertex, int delta) {
            flow +=  vertex == from ? delta : -delta;
        }

        @Override
        public String toString() {
            return String.format("From %d To %d Cap %d Flow %d"
                    , from, to, cap, flow);
        }
    }

    private static ArrayList<ArrayList<Integer>> ref;
    private static Edge [] path, edges;
    private static int E, V;
    private static int [] seen;
    private static int timer = 1, counter = 0;

    public static void init(int v, int e) {
        V = v;
        E = e;
        ref = new ArrayList<>();
        for(int i=0; i<v; i++)
            ref.add(new ArrayList<>());
        seen = new int[V];
        edges = new Edge[E * 2];
    }

    public static void addEdge(int from, int to, int cap) {
        edges[counter] = new Edge(from, to, cap);
        ref.get(from).add(counter++);
        edges[counter] = new Edge(to, from, 0);
        ref.get(to).add(counter++);
    }

    public static int dfs(int s, int t, int f) {
        if(s == t)
            return f;
        seen[s] = timer;
        for(int idx : ref.get(s)) {
            int u = edges[idx].to;
            int c = edges[idx].getResidualCapacity(u);
            if(seen[u] < timer && c > 0) {
                seen[u] = timer;
                int flow = dfs(u, t, Math.min(f, c));
                if(flow > 0) {
                    edges[idx].addResidualFlow(edges[idx].from, flow);
                    edges[idx^1].addResidualFlow(edges[idx^1].to, flow);
                    return flow;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(test1());
    }

    public static int test1() {
        init(6, 20);
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
        return run(0,5);
    }

    public static int test2() {
        init(8, 13);
        // https://www.youtube.com/watch?v=xC2tYIZvmgc
        addEdge(0,1,13);
        addEdge(0,2,10);
        addEdge(0,3,10);
        addEdge(1,4,24);
        addEdge(2,1,5);
        addEdge(2,3,15);
        addEdge(2,6,7);
        addEdge(3,6,15);
        addEdge(4,5,1);
        addEdge(4,7,9);
        addEdge(5,6,6);
        addEdge(5,7,13);
        addEdge(6,7,16);
        return run(0,7);
    }

    public static int run(int s, int t) {
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
