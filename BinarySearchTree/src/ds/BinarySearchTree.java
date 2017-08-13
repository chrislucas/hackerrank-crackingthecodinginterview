package ds;

import java.util.Comparator;

/**
 * Created by r028367 on 10/08/2017.
 */
public class BinarySearchTree {

    public class No implements Comparable<No> {
        Comparable data;
        No left, right;

        public No(Comparable data, No left, No right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(No that) {
            return 0;
        }
    }

    public static void main(String[] args) {

    }

}
