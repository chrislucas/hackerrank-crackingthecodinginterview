package problems;


/**
 * https://www.hackerearth.com/practice/math/combinatorics/inclusion-exclusion/tutorial/
 * Description
 *
 * In the sample problem, for a given number "N". the goal is to compute the probability
 * that randomly chosen integer from a range [1, N]. with uniform distribution is divisible
 * by at least one of integers 2, 3, 11, 13
 *
 * */

public class CountProbability {


    public static int gdc(int a, int b) {
        if(a%b!=0)
            return gdc(b, a%b);
        return b;
    }

    public static int gdc(int [] array) {
        int ans = gdc(array[0], array[1]);
        for(int i=1; i<array.length-1; i++) {
            ans = gdc(array[i], ans);
        }
        return ans;
    }

    public static int lcm(int a, int b) {
        return a*b/gdc(a, b);
    }

    public static int lcm(int [] array) {
        int ans = lcm(array[0], array[1]);
        for (int i = 1; i <array.length-1; i++) {
            ans = lcm(array[i], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] array = {2,3,11,13};
        System.out.println(lcm(array));
    }
}
