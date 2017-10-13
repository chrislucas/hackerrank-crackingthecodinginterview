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
            // (vertice == from) -> vertice de inicial
            return (vertice == from) ? flow : cap - flow;
        }

        public void addResidualFlow(int vertex, int delta) {
            flow +=  vertex == from ? -delta : delta;
        }

        public int nextNode(int vertice) {
            // (vertice == from) -> aresta de inicial
            return (vertice == from) ? to : from;
        }

        @Override
        public String toString() {
            return String.format("From %d To %d Cap %d Flow %d"
                    , from, to, cap, flow);
        }
    }

    private static ArrayList<ArrayList<Edge>> ref;
    private static Edge[] edgesTo;
    private static int E = 0, V;
    private static int [] seen;
    private static int timer = 0;

    public static void init(int vertices) {
        V = vertices;
        ref = new ArrayList<>();
        for(int i=0; i<vertices; i++)
            ref.add(new ArrayList<>());
        seen = new int[V];
    }

    public static void add(Edge e) {
        ref.get(e.from).add(e);
        ref.get(e.to).add(e);
    }

    public static int dfs(int s, int t, int f) {
        return 0;
    }

    public static void main(String[] args) {

    }
}
