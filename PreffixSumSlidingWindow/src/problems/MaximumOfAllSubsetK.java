package problems;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumOfAllSubsetK {


    /**
     * dado um array e um valor k, ache para cada subset de tamanho k
     * o valor maximo
     *
     * Exemplo
     * {1, 2, 3, 1, 4, 5, 2, 3, 6} k = 3
     * 3 3 4 5 5 5 6
     * */


    public int [] maxSubsetSum(int [] array, int k) {
        int rs [] = new int[array.length];
        if(k > array.length) {
            return rs;
        }
        Deque<Integer> Q = new LinkedList<>();
        return rs;
    }

    public static void main(String[] args) {

    }

}
