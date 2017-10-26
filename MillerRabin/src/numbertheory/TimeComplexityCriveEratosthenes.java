package numbertheory;


import java.util.LinkedHashMap;
import java.util.Map;


public class TimeComplexityCriveEratosthenes {

    public static LinkedHashMap<Integer, Boolean> primes = new LinkedHashMap<>();
    public static final int LIMIT = 10000000;

    /**
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
     *
     * */

    public static int crive() {
        primes.put(0, false);
        primes.put(1, false);
        for(int i=2; i<=LIMIT; i++) {
            primes.put(i, true);
        }
        for(int i=2; i*i<=LIMIT; i++) {
            if(primes.get(i)) {
                for(int j=i; j*i<=LIMIT; j++) {
                    primes.put(j*i, false);
                }
            }
        }
        int c = 0;
        /*
        for(Map.Entry<Integer, Boolean> i : primes.entrySet()) {
            if(i.getValue())
                c++;
        }
        */
        return c;
    }

    public static boolean isPrime(int n) {
        return primes.get(n);
    }


    public static void main(String[] args) {
        System.out.println(crive());
    }

}
