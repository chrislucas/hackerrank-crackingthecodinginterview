package problems.hackerearth.segmenttree;

/**
 * Estudar essa solucao
 * https://github.com/yancouto/maratona-sua-mae/blob/master/Yan/codeforces/566D.cpp
 *
 * Tutorial
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * */

public class SegmentTree {

    private static int segmentTree [];

    // troca de base Math.log10(logt) / Math.log10(base);
    private static  double log(long logt, long base) {
        return Math.log(logt) / Math.log(base);
    }

    /**
     * Construindo uma segment tree a partir de um array
     * */
    private static void init(int [] array, int size) {
        // tamanho da Arvores
        int x = (int) Math.round(Math.log(size)/Math.log(2));
        segmentTree = new int[2*(1<<x)-1];
    }

    private static void build(int [] arr, int startTree, int endTree, int currIndex) {

    }

    private static int getLeft(int idx) {
        return 2*idx+1;
    }

    private static int getRight(int idx) {
        return 2*idx+2;
    }

    private static int getMiddle(int s, int e) {
        return (e - s) / 2 + s;
    }

    private static int getParent(int idx) {
        return idx < 1 ? idx : (idx-1)/2;
    }

    private static int rangeSum(int startTree, int endTree, int startQuery, int endQuery, int currIndex) {
        // dentro dos limites da segment Tree
        if(startQuery <= startTree && endQuery >= endTree)
            return segmentTree[currIndex];

        // fora dos limites da segment Tree
        if(startQuery > endTree || endQuery < startQuery)
            return 0;

        int middle = (endTree - startTree) / 2 + startTree;
        int left = rangeSum(startTree, middle, startQuery, endQuery, 2 * currIndex + 1)
        int right = rangeSum(middle + 1, endTree, startQuery, endQuery, 2 *currIndex + 2);
        return left + right;
    }

    private static void rangeUpdate(int startTree, int endTree
            , int idxElementToUp, int valueToAdd, int currIndex) {
        // fora dos limites da segment tree
        if(idxElementToUp < startTree || idxElementToUp > endTree)
            return;


    }



    public static void main(String[] args) {
        System.out.println(log(125, 5));
    }
}
