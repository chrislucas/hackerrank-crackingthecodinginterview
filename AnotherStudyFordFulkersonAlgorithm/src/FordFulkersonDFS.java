import java.util.ArrayList;

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

    private static ArrayList<ArrayList<FordFulkersonAlgorithm.Edge>> ref;
    private static FordFulkersonAlgorithm.Edge[] edgesTo;
    private static int E = 0, V;
    private static int [] seen;
    private static int timer = 0;


    /**
     * Com a mesma estrutura tentar implementar o algoritmo usando uma DFS
     * Talvez de para atualizar as arestas do grafo residual usando a pilha
     * de recursao, um metodo diferente de como eh feito na BFS
     *
     * */

    public static void main(String[] args) {

    }

}
