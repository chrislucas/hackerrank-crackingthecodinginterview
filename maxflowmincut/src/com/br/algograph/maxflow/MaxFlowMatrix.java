package com.br.algograph.maxflow;

/**
 * Created by C_Luc on 03/09/2017.
 *
 * https://pt.wikipedia.org/wiki/Algoritmo_de_Ford-Fulkerson
 * http://www.geeksforgeeks.org/max-flow-problem-introduction/
 *
 * Dado um grafo G direcionado cuja as arestas possuem pesos, que são
 * considerados valores de um fluxo e dados 2 nos nesse grafp, 's' e 't'
 * que são respectivamente nó inicial e final de um caminho, achar um caminho
 * cujo valor de fluxo é máximo.
 *
 * Um grafo G de uma rede de fluxo possui as seguintes propriedades
 *
 * Para cada aresta a pertencente a esse grafo, a um valor associado a ela
 * indicando sua capacidade
 *
 * Existe um nó 's' pertencente ao grafo que denota o inicio do fluxo
 *
 * Existe um nó 't' pertencente ao grafo que denota o fim do fluxo
 *
 * Nao há nenhum aresta que incide em 's', apenas arestas que saem
 *
 * Nao há nenhum aresta que saia em 't', apenas arestas que entram
 *
 * O valor de um fluxo maximo e dado pelo somatorio da capacidade das arestas
 * que saem de 's' e chegam a 't' num grafo G
 *
 */
public class MaxFlowMatrix {

    /**
     * Grafo residual
     *
     * A ideia principal para resolver o problema de fluxo maximo stá vinculada
     * a 2 operações: Adicionar mais fluxo a uma aresta caso ela nao tenha
     * atingido sua capacidade máxima ou diminuir o fluxo de uma resta quando sua
     * capacidade é positiva ( 0 <= 'capacidaded <= max Para a aresta em questao')
     *
     * Para realizaar essa operacao definimos um grafo auxiliar (chama de grafo residual)
     * Gr
     *
     * O conjunto de aresta em Gr e o mesmo que em G
     *
     * Ao percorrer as arestas de G, cada aresta possui um fluxo f()
     * e uma capacidade 'c', o que quer dizer que pela aresta passa um
     * fluxo determinado pela funcao f cuja capacidade maixa eh c
     *
     * Para cada aresta 'a' que possui f(a) < ca, adicionamos uma aresta
     * ar (que segue de 's' ate 't' 'para frente') em Gr com f(ar) = 0 (fluxo zero) e car = ca - f(a) (capacidade da
     * aresta 'ar' eh ca - f(a) ou seja o restante que falta para atingir a capacidade maxima)
     * essa aresta ar esta saindo (empurrando para frente )do vertice no grafo Gr
     *
     *
     * Para cada aresta 'a' que possui f(a) > 0 (aresta com alguma quantidade
     * positiva de fluxo, o que nos permite voltar fluxo para o no) adicionados
     * uma aresta a'r (que segue de 't' ate 's' 'para tras')
     * em Gr com a direcap inversa de 'a' com fluxo f(ar) = 0
     * e capacidade car = f(a)
     *
     * Obs: cada aresta em G pode gerar no maximo 2 arestas em Gr. A aresta de saida
     * denota o fluxo que ainda pode ser passado e a aresta de volta denota o fluxo
     * existente (que ja foi passado), que pode retornar para o no anterior para otimizar
     * o resultado do problema.
     * */

    public static class Edge {
        public int to, maxCapacity, currentCapacity;

        public Edge(int to, int maxCapacity, int currentCapacity) {
            this.to = to;
            this.maxCapacity = maxCapacity;
            this.currentCapacity = currentCapacity;
        }
    }

    public static Edge [][] graph, rGraph;
    public static int [] visited, parents;

    public static boolean bfs(int nodeStart, int nodeEnd) {

        return false;
    }

    public static void addEdge(int a, Edge e) {
        graph[a][e.to] = e;
        rGraph[a][e.to] = e;
    }

    public static void initializeG(int vertices) {
        graph   = new Edge[vertices][vertices];
        rGraph  = new Edge[vertices][vertices];
        visited = new int[vertices];
        parents = new int[vertices];
    }

    public static void start(int nodeStart, int nodeEnd) {
        initializeG(5);
        addEdge(0, new Edge(1,16,0));
        addEdge(0, new Edge(2,13,0));
        addEdge(1, new Edge(2,10,0));
        addEdge(1, new Edge(3,12,0));
        addEdge(2, new Edge(1,4,0));
        addEdge(2, new Edge(4,14,0));
        addEdge(3, new Edge(2,9,0));
        addEdge(3, new Edge(5,20,0));
        addEdge(4, new Edge(3,7,0));
        addEdge(4, new Edge(5,4,0));
        int maxFlow = 0;
        while(bfs(nodeStart, nodeEnd)) {
            int pathFlow = Integer.MAX_VALUE;
            for(int v=nodeEnd; v!=nodeStart; v=parents[v]) {
                int u = parents[v];
                pathFlow = pathFlow < rGraph[u][v].maxCapacity ? pathFlow : rGraph[u][v].maxCapacity;
            }


        }
    }

    public static void main(String[] args) {
        start(0,5);
    }

}
