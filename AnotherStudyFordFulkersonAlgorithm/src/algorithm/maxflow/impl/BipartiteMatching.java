package algorithm.maxflow.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by r028367 on 13/10/2017.
 * http://www.cdn.geeksforgeeks.org/bipartite-graph/
 *
 * Um grafo bipartido é um grafo cujos vertices podem ser dividos em 2 conjuntos
 * U e V tal que toda aresta existente do grafo conecta um vertice do conjunto
 * U a 1 vertice do conjunto V e nao vertices conectados dentro do mesmo conjunto
 *
 * Esse problema pode ser pensado como o problema de coloracao de vertices de um grafo
 * usando 2 cores. 1 aresta nao pode ter 2 vertices coloridos pela mesma cor.
 *
 * Por exemplo:
 * Se uma aresta S conecta os vertices 2-3(R-B), uma aresta T conecta os vertices
 * 3-4(B-R) e uma aresta U conecta os vertices 4-2(R-R) o grafo nao é bipartido
 * pois temos 2 vertices com a mesma coloracao
 *
 */
public class BipartiteMatching {


    enum COLOR {
        UNDEFINED(-1), RED(0), BLUE(1);
        private int color;
        COLOR(int color) {
            this.color = color;
        }
        public int getColor() {
            return color;
        }
        public static COLOR getColor(int i) {
            return i == -1 ? UNDEFINED : i == 1 ? BLUE : RED;
        }
    }

    private static List<List<Integer>> ref;
    private static COLOR [] colorVertice;
    private static int vertices;

    static void init(int v) {
        vertices = v;
        ref = new ArrayList<>();
        for(int i=0; i<v; i++)
            ref.add(new ArrayList<>());
        colorVertice = new COLOR[v];
    }

    static void add(int i, int j) {
        ref.get(i).add(j);
    }

    public static boolean isBipartite(int src) {
        colorVertice[src] = COLOR.BLUE;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for(int v : ref.get(u)) {
                if(colorVertice[v].equals(COLOR.UNDEFINED)) {
                    colorVertice[v] = COLOR.getColor(1 - colorVertice[u].getColor());
                    queue.add(v);
                }
                // vertices com a mesma 'cor' indicam que
                // existe uma aresta
                else if(colorVertice[v] == colorVertice[u]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBipartite() {
        for(int idx=0; idx<vertices; idx++)
            colorVertice[idx] = COLOR.UNDEFINED;
        for(int idx=0; idx<vertices; idx++) {
            if(colorVertice[idx].equals(COLOR.UNDEFINED)) {
                if(!isBipartite(idx))
                    return false;
            }
        }
        return true;
    }

    public static void run1() {
        init(4);
        add(0, 1);
        add(0, 3);
        add(1, 0);
        add(1, 2);
        add(2, 1);
        add(2, 3);
        add(3, 0);
        add(3, 2);
        //add(3, 1);
        System.out.println(isBipartite());
    }

    public static void run2() {
        init(5);
        add(0, 1);
        add(1, 0);
        add(2, 3);
        add(2, 1);
        add(3, 2);
        //add(4, 2);
        add(4, 3);
        //add(3, 1);
        System.out.println(isBipartite());
    }

    public static void main(String[] args) {
        run2();
    }
}
