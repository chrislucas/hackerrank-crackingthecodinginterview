package numbertheory;

import java.util.Random;

public class MillerRabin {

    // https://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
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


    public static int rdn(int n, int m) {
        int min = m  < n ? m : n;
        int max = min == m ? n : m;
        return random.nextInt(max - min - 1) + min;
    }

    public static int isPrimeSqrt(int q) {
        if((q&1)==0 || q < 2)
            return 0;
        int limit = (int)Math.sqrt(q);
        for(int i=3; i<=limit; i+=2) {
            if(q%i==0)
                return i;
        }
        return 0;
    }

    /**
     * http://comnuan.com/cmnn02/cmnn02008/
     *  *                  n     Primes <= n
     *  ---------------------------------
     *                 10               4
     *                100              25
     *              1,000             168
     *             10,000           1,229
     *            100,000           9,592
     *          1,000,000          78,498
     *         10,000,000         664,579
     *        100,000,000       5,761,455
     *      1,000,000,000      50,847,534
     * */
    public static void test() {
        int c = 0;
        for(int i=1; i<10000000; i++) {
            if(isPrime(i, 10)) {
                c++;
                int divisor = isPrimeSqrt(i);
                if( divisor > 0) {
                    System.out.printf("%d %d\n", i, divisor);
                }
            }
        }
        System.out.println(c);
    }

    public static void testRDN() {
        for(int i=0; i<1000; i++) {
            System.out.println(rdn(10, 30));
        }
    }


    public static void main(String[] args) {
        test();
    }

}
