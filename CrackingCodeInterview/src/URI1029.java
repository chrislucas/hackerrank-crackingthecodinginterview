import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by C_Luc on 16/07/2017.
 *
 * https://www.urionlinejudge.com.br/judge/pt/problems/view/1029
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
        return M[0][1];
    }


    static long [] memo = new long[41];

    private static void init() {
        for(int i=1; i<40; i++) {
            memo[i] = exp(i);
        }
    }

    public static void main(String[] args) {
        init();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(bufferedReader.readLine());
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true);
            while(n>0) {
                n--;
                int val = Integer.parseInt(bufferedReader.readLine());
                pw.printf("fib(%d) = %d calls = %d\n", val, memo[val] * 2 - 2, memo[val]);
            }
        } catch (Exception e) {

        }
    }

}
