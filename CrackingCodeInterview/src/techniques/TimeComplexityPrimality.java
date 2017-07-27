package techniques;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by C_Luc on 22/07/2017.
 *
 * https://www.hackerrank.com/challenges/ctci-big-o
 * DONE
 */
public class TimeComplexityPrimality {


    /**
     * Em tempo sqrt(n)
     * */
    public static boolean isPrime(long n) {
        boolean res = n < 2 ? false : true;
        long limit = Math.round(Math.sqrt(n));
        for(int i=2; i<=limit; i++) {
            if(n%i==0) {
                res = false;
                break;
            }
        }
        return res;
    }


    public static void testSqrtPrime() {
        for(long i=0; i<1000000; i++) {
            if(isPrime(i))
                System.out.println(i);
        }
    }

    public static void solver() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cases = Integer.parseInt(bufferedReader.readLine());
            for(;cases>0;cases--) {
                long val = Long.parseLong(bufferedReader.readLine());
                System.out.println(isPrime(val) ? "Prime" : "Not Prime");
            }
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        solver();
    }

}
