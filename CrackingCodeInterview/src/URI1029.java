import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by C_Luc on 16/07/2017.
 *
 * https://www.urionlinejudge.com.br/judge/pt/problems/view/1029
 * DONE
 */
public class URI1029 {


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
        long M [][] = {{1,0}, {0, 1}};
        while(n>0) {
            if((n & 1) != 0) {
                multiply(M, A);
            }
            multiply(A, A);
            n >>= 1;
        }
        return M[0][0];
    }


    static long [] memo = new long[42];
    static long [][] temp = new long[42][2];
    private static void init() {
        for(int i=1; i<41; i++) {
            memo[i] = exp(i);
        }
    }

    private static void init2() {
        /**
         * Tabela dinamica
         * [i][0] numero de fibonacci
         * [i][1] chamadas recursivas
         * long [][] temp = new long[42][2];
         * */
        temp[1][0] = 1;     // fib
        temp[1][1] = 0;     // chamadas recursivas
        for(int i=2; i<41; i++) {
            temp[i][0] = exp(i-1);
            temp[i][1] = temp[i-1][1] + temp[i-2][1] + 2;
        }
        return;
    }

    private static void solver() {
        //init();
        init2();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(bufferedReader.readLine());
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true);
            while(n>0) {
                n--;
                int val = Integer.parseInt(bufferedReader.readLine());
                //aswer1(pw, val);
                aswer2(pw, val);
            }
        } catch (Exception e) {}
    }

    private static void aswer1(PrintWriter pw, int val) {
        pw.printf("fib(%d) = %d calls = %d\n", val, memo[val] * 2 - 2, memo[val-1]);
    }

    private static void aswer2(PrintWriter pw, int val) {
        pw.printf("fib(%d) = %d calls = %d\n", val, temp[val][1], temp[val][0]);
    }

    public static void main(String[] args) {
        solver();
    }
}
