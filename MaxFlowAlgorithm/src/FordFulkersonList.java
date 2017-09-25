import java.util.ArrayList;

/**
 * Created by r028367 on 15/09/2017.
 */
public class FordFulkersonList {

    public class Edge {
        public int to, flow, capacity;

        public Edge(int to, int flow, int capacity) {
            this.to = to;
            this.flow = flow;
            this.capacity = capacity;
        }
    }

    public static ArrayList<ArrayList<Edge>> g;
    public static Edge [] edges;
    public static int [] seen;


    public static void addEdge(int from, Edge edge) {
        g.get(from).add(edge);
    }

}
