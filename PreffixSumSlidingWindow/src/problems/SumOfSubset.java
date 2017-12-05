package problems;

public class SumOfSubset {


    public static int maxSumOfSubsetK(int [] set, int k) {
        int limit = set.length;
        int acc = 0;
        for (int i=0; i<k ; i++) {
            acc += set[i];
        }
        int partial = acc;
        for (int i = k; i < limit ; i++) {
            partial = partial + set[i] - set[i-k];
            acc = Math.max(acc, partial);

        }
        return acc;
    }

    public static void test1() {
        int [][] set = {
            {5 , 2 , -1 , 0 , 3}
        };
        System.out.println(maxSumOfSubsetK(set[0], 3));
    }




    public static void main(String[] args) {
        test1();
    }

}
