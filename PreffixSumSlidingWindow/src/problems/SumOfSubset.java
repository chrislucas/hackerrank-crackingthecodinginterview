package problems;

public class SumOfSubset {


    public static int maxSumOfSubsetK(int [] set, int k) {
        int limit = set.length;
        if(k > limit)
            return -1;
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
            ,{100, 200, 300,400}
            ,{1, 4, 2, 10, 23, 3, 1, 0, 20}
            ,{4, 2, 10, 23}
        };
        System.out.println(maxSumOfSubsetK(set[2], 4));
    }




    public static void main(String[] args) {
        test1();
    }

}
