package numbertheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * https://www.hackerrank.com/challenges/ctci-big-o/problem
 * http://comnuan.com/cmnn02/cmnn02008/
 * */
public class TimeComplexityMillerRabin {

    public static Random random = new Random();

    private static long expmod(long a, long b, long m) {
        long x=1, y=a;
        while(b>0) {
            if((b & 1) == 1)
                x=((x%m)*(y%m))%m;
            y=((y%m)*(y%m))%m;
            b >>= 1;
        }
        return x % m;
    }

    private static boolean isPrime(long p, long it) {
        if(p < 2 || (p!=2 && (p&1)==0))
            return  false;

        long s = p-1;
        while((s&1) == 0)
            s >>= 1;

        for(int i=0; i<it; i++) {
            long r = random.nextInt(((int)p-2)+1)+1;
            long t = s;
            long m = expmod(r, t, p);
            while(t!=p-1 && m != 1 && m != p-1) {
                m = ((m%p)*(m%p))%p;
                t <<= 1;
            }
            if(m!=p-1 && (t&1)==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
        try {
            int cases =  Integer.parseInt(reader.readLine());
            while(cases-->0) {
                writer.printf("%s\n"
                        , isPrime(Integer.parseInt(reader.readLine()), 100) ? "Prime" : "Not prime");
            }
        } catch (Exception e) {}
    }
}
