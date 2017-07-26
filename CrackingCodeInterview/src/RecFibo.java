/**
 * Created by C_Luc on 16/07/2017.
 * https://www.hackerrank.com/challenges/ctci-fibonacci-numbers
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

import static  java.lang.Math.log10;

public class RecFibo {

    static int count = 0;

    public static long fbrec(long n) {
        count++;
        return n == 0 || n == 1 ? 1 : fbrec(n-1) + fbrec(n-2);
    }

    // http://vulms.vu.edu.pk/Courses/CS201/Downloads/p60-robertson.pdf;

    public static double log(double base, double arg) {
        return log10(base) / log10(arg);
    }

    private static void multiply(long [][] fibA, long [][] M) {
        long p1 = fibA[0][0] * M[0][0] + fibA[0][1] * M[1][0];
        long p2 = fibA[0][0] * M[0][1] + fibA[0][1] * M[1][1];
        long p3 = fibA[1][0] * M[0][0] + fibA[1][1] * M[1][0];
        long p4 = fibA[1][0] * M[0][1] + fibA[1][1] * M[1][1];
        fibA[0][0] = p1;
        fibA[0][1] = p2;
        fibA[1][0] = p3;
        fibA[1][1] = p4;
        return;
    }

    public static long exp(long n) {
        long A [][] = {{1,1}, {1, 0}};
        long M [][] = {{1,1}, {1, 0}};
        Stack<Long> stack = new Stack<>();
        while(n>1) {
            stack.push(n);
            n = (n >> 1);
        }
        while(!stack.empty()) {
            n = stack.pop();
            multiply(A, A);
            if((n & 1) != 0) {
                multiply(A, M);
            }
        }
        return A[0][0];
    }

    public static long exp2(long n) {
        long A [][] = {{1,1}, {1, 0}};
        long M [][] = {{1,0}, {0, 1}};
        while(n>0) {
            if((n & 1) != 0) {
                multiply(M, A);
            }
            multiply(A, A);
            n >>= 1;
        }
        return M[0][1];
    }

    public static long exp(int n, long [][] A) {
        if(n == 0 || n == 1)
            return A[0][0];
        exp(n/2, A);
        multiply(A, A);
        if((n & 1) != 0) {
            long M [][] = {{1,1}, {1, 0}};
            multiply(A, M);
        }
        return A[0][0];
    }

    public static void test1() {
        for (int i = 1; i < 40; i++) {
            count = 0;
            long A [][] = {{1,1}, {1, 0}};
            /**
             * How many calls 2 * F(n) - 1
             * */
            System.out.printf("fib(%d): %d calls: %d\nfib(%d): %d\nfib(%d): %d\n"
                    , i, fbrec(i), count, i, exp(i), i, exp(i, A));
        }
    }


    public static void test2() {
        for (int i = 0; i < 40 ; i++) {
            System.out.printf("fib(%d): %d\n", i, exp2(i));
        }
    }

    static long [] memo;

    public static void init() {
        memo = new long [41];
        memo[0] = 0;
        memo[1] = 1;
        for(int i=2; i<41; i++) {
            memo[i] = exp2(i);
        }
        return;
    }

    public static void solver() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        init();
        try {
            int val = Integer.parseInt(bufferedReader.readLine());
            System.out.println(memo[val]);
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
       solver();
    }
}
