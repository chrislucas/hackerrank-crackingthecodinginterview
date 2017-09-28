import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by r028367 on 25/09/2017.
 */
public class FordFulkersonAlgorithm {

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
        E++;
    }

    /**
     * Vamos aplicar bfs's no grafo, partindo de um no S, inicialmente
     * s e o no origem do problema, conforme as bfs's vao sendo executados
     * esse no vai mudando, ate alcançar o no T que nao muda. Esse processo
     * so terminara quando nao ouver mais como aumentar o fluxo que se pode passar
     * nas arestas do grafo
     * */
    public static boolean hasAugmentingPath(int s, int t) {
        edgesTo = new Edge[V];  // arestas adicionadas durante a BFS entre o no S e T
        seen[s] = ++timer;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty() && seen[t] < timer) {
            int u = queue.poll();
            for(Edge edge : ref.get(u)) {
                int v = edge.other(u);
                if(seen[v] < timer && edge.residualCapacity(v) > 0) {
                    edgesTo[v] = edge;
                    seen[v] = timer;
                    queue.add(v);
                }
            }
        }
        // se fizemos u uma bfs e nao foi possivel chegar em t, quer dizer que os caminhos
        // que vao de s ate t estao saturados
        return seen[t] == timer;
    }

    public static void main(String[] args) {
        System.out.println(test1(0, 5));
    }

    private static int test1(int s, int t) {
        // http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
        init(6);
        add(new Edge(0,1, 16));
        add(new Edge(0,2, 13));
        add(new Edge(1,2, 10));
        add(new Edge(1,3, 12));
        add(new Edge(2,1, 4));
        add(new Edge(2,4, 14));
        add(new Edge(3,2, 9));
        add(new Edge(3,5, 20));
        add(new Edge(4,3, 7));
        add(new Edge(4,5, 4));
        int maxFlow = 0;
        while (hasAugmentingPath(s, t)) {
            // atualizar as aresta residuais
            int INF = Integer.MAX_VALUE;
            for(int v = t; v!=s; v = edgesTo[v].other(v)){
                INF = Math.min(INF, edgesTo[v].residualCapacity(v));
            }
            for(int v = t; v!=s; v = edgesTo[v].other(v)) {
                // atualiza o fluxo das arestas adicionadas durante a BFS, para o FLUXO maximo enquanto der para realizar BFS's
                edgesTo[v].addResidualFlowTo(v, INF);
            }
            maxFlow += INF;
            timer++;
        }
        return maxFlow;
    }
}
