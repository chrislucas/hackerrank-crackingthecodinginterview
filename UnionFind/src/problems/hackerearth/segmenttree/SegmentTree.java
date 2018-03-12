package problems.hackerearth.segmenttree;

/**
 * Estudar essa solucao
 * https://github.com/yancouto/maratona-sua-mae/blob/master/Yan/codeforces/566D.cpp
 *
 * Tutorial
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * */

public class SegmentTree {

    private static int struct [];

    /**
     * Construindo uma segment tree a partir de um array
     * */
    private static void build(int [] array, int size) {
        int x = (int) Math.round(Math.log(10)/Math.log(2));
    }

    public static int getLeft(int idx) {
        return 2*idx+1;
    }

    public static int getRight(int idx) {
        return 2*idx+2;
    }

    public static int getParent(int idx) {
        return idx < 1 ? idx : (idx-1)/2;
    }



    public static void main(String[] args) {

    }
}
