package com.br.algograph.maxflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C_Luc on 02/09/2017.
 *
 * Algoritmo para resolver problemas do tipo 'max flow min cut'
 *
 * Baseado no video: https://www.youtube.com/watch?v=FTk3IoO8rzg&t=1686s
 *
 *
 * Informacao importante e meio aleatoria
 *
 * Um digrafo residual é um diagrafo montado a partir de um outro diagrafo cujas arestas possui um valor
 * de fluxo, que poderiamos dizer que é o peso dela ou o custo de passar por ela e uma capacidade maxima
 * que tal aresta pode ter.
 *
 * Ao montar um diagrafo residual 'DR' para resolver o problema de maximazar fluxo com um corte minimo, o novo
 * grafo possui os mesmos vertices do digrafo 'D', entretanto suas arestas agora so possuem pesos. Esses pesos ou custos
 * sao adquiridos a partir do grafo original seguindo a seguinte regra
 *
 * Se 'D' tem uma aresta 'ab' com un fluxo  0 <= 'Fd'<= capacidade 'Cd' entao 'DR' tem essa mesma aresta com fluxo Cd-Fd
 *  (Fd nesse caso pode ser zero)
 *
 * Se 'D' tem uma aresta 'ab' com um fluxo 'F' > 0 entao 'DR' tera uma aresta 'ba' cujo fluxo é 'Fd'
 *  (valor do fluxo de saida do vertice no grafo 'D')
 *  Essa aresta inversa representa uma aresta de retorno. Isso nos permite retornar um fluxo que foi passado
 *  , caso percebamos que passar desfazer essa passagem maximize o nosso fluxo
 *
 * Apos montar o grafo residual devemos escolher um valor 'g' que é o menor peso das arestas desse grafo
 *
 */
public class FordFulkersonList {

    public static class Edge {
        private int to, flow, capacity;
        public Edge(int to, int flow, int capacity) {
            this.to = to;
            this.flow = flow;
            this.capacity = capacity;
        }
    }
    public static List<List<Integer>> ref;
    public static Edge[] edges;
    public static int [] visiteds;
    public static int counter = 0, timer = 1;

    public static void init(int vertices, int qEdges) {
        ref = new ArrayList<>();
        for(int i=0; i<vertices; i++)
            ref.add(new ArrayList<>());
        edges = new Edge[qEdges];
        visiteds = new int[vertices];
    }

    /**
     * https://www.youtube.com/watch?v=uqNJGF20vVc
     * Ao inves de marcar o vetor de indices visitados como false
     * podemos usar esse truque aritmetico.
     *
     * Marcamos o vertice 'i' no vetor 'visiteds' com um inteiro
     * 'k' a cada dfs que fizermos no grafo. Assim se visitarmos uma
     * aresta mais de uma vez na mesma DFS ela ja tera sido marcada e esse
     * vertice so podera ser visitado numa proxima DFS
     *
     * Esse truque é bom quando temos um problema que exige que façamos muitas dfs.
     * No java isso evita que a td momento tenhamos que iniciar um vetor
     * booleano que marca os vertices visitados, economizando tempo e memoria.
     *
     * */
    public static boolean visited(int idx) {
        return visiteds[idx] >= timer;
    }

    /**
     * adicionando aresta direta e reversa
     * dCap = capacidade caminho direto, rCap = capacidade caminho reverso
     * */
    public static void addEdge(int a, int b, int dCap, int rCap) {
        Edge ab = new Edge(b, 0, dCap);
        edges[counter] = ab;
        // o objeto ref guarda os indices do vetor edges
        // os indices pares representam aresta de ida
        // e os indices impares representam as arestas de retorno do grafo residual
        // tendo os indices fica facil pegar tanto a aresta 'ab' quanto a de retorno 'ba'
        ref.get(a).add(counter++);
        Edge ba = new Edge(a, 0, rCap);
        edges[counter] = ba;
        ref.get(b).add(counter++);
    }

    /**
     * arestas diretas de indices pares e reversas impares.
     * Usando bitwise XOR podemos pegar a aresta direta
     * e se quisermos pegamos a reversa vinculada a ela.
     * Exemplo
     * idx = 10. idx ^ 1 == 11
     * idx = 3. idx ^ 3 == 2
     * */
    public static Edge getEdge(int idx, boolean direct) {
        return direct ? edges[idx] : edges[idx ^ 1];
    }

    /**
     *
     * DFS que parte de um vertice 's' e tenta alcancar um vertice 't'
     * */

    public static int dfs(int currentVertice, int finalVertice, int currentFlow) {
        // fim da N-esima dfs
        if(currentVertice == finalVertice)
            return currentFlow;
        visiteds[currentVertice] = timer;           // vertice visitado na iteracao com valor em 'timer'
        for(int i : ref.get(currentVertice)) {
            int curr    = edges[i].to;
            // diferenca entre o fluxo passado nessa aresta e sua capacidade maxima,
            // para saber se ela nao esta saturada
            int diff    = edges[i].capacity - edges[i].flow;
            /**
             *
             * Se o vertice alcançavel ainda nao foi visto e se a aresta
             * que me permite chegar a tal vertice não esta saturada(posso passar fluxo)
             * entao faco a dfs
             * */
            if( visiteds[ curr ] < timer && diff > 0) {
                visiteds[ curr ] = timer;
                /**
                 * A aresta de valor minimo de um caminho nos diz qual é o valor maximo
                 * de fluxo que podemos passar por todas as arestas para realizar um percurso
                 * do vertice S ao vertice T (final)
                 * */
                int min     = Math.min(currentFlow, diff);
                int flow    = dfs(curr, finalVertice, min);
                /**
                 * Se flow > 0 significa que existe um caminho
                 * entre vertices 'a' e 'b' cuja aresta ainda permite
                 * que seu fluxo seja maximizado
                 * */
                if(flow > 0) {
                    edges[i].flow += flow;
                    edges[i^1].flow -= flow;
                    return flow;
                }
            }
        }
        return 0;   // nao tem mais arestas q podem ser maximizadas
    }

    /**
     * Fluxo maximo num grafo com um caminho entre vertices 's' e 't'
     * */
    public static int maxFlow(int s, int t) {
        int flow    = 0, rs = -1;
        int inf     = Integer.MAX_VALUE;
        do {
            // a DFS retorna o quanto de fluxo ainda da para passar pela aresta
            // se rs == 0, atingimos a capacidade maxima passada por uma aresta
            rs = dfs(s, t, inf);
            flow += rs;
            timer++;
        } while (rs != 0);
        return flow;
    }

    public static void run() {
        test3();
    }

    public static void main(String[] args) {
        run();
    }

    public static void test1() {
        init(6, 20);
        addEdge(0,1, 16, 0);
        addEdge(0,2, 13, 0);
        addEdge(1,2, 10, 0);
        addEdge(1,3, 12, 0);
        addEdge(2,1, 4, 0);
        addEdge(2,4, 14, 0);
        addEdge(3,2, 9, 0);
        addEdge(3,5, 20, 0);
        addEdge(4,3, 7, 0);
        addEdge(4,5, 4, 0);
        int start = 0, end = 5;
        int mf = maxFlow(start, end);
        System.out.println(mf);
    }

    private static void test2() {
        init(5, 14);
        addEdge(0,2,100,0);
        addEdge(0,3,50,0);
        addEdge(2,1,50,0);
        addEdge(2,3,50,0);
        addEdge(2,4,50,0);
        addEdge(3,4,100,0);
        addEdge(4,1,125,0);
        int start = 0, end = 1;
        int mf = maxFlow(start, end);
        System.out.println(mf);
    }

    private static void test3() {
        init(8,26);
        addEdge(0,1,13,0);
        addEdge(0,2,10,0);
        addEdge(0,3,10,0);
        addEdge(1,4,24,0);
        addEdge(2,3,15,0);
        addEdge(2,6,7,0);
        addEdge(3,6,15,0);
        addEdge(4,5,1,0);
        addEdge(4,7,9,0);
        addEdge(5,6,6,0);
        addEdge(5,7,13,0);
        addEdge(6,7,16,0);
        int start = 0, end = 7;
        int mf = maxFlow(start, end);
        System.out.println(mf);
    }


}
