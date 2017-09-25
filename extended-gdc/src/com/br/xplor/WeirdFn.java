package com.br.xplor;

/**
 * Created by r028367 on 31/08/2017.
 * https://www.hackerrank.com/challenges/a-weird-function?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=3-day-campaign
 */
public class WeirdFn {

    private static long gdc(long a, long b) {
        return (a%b==0) ?  b : gdc(b, a%b);
    }

    private static long fn(long l, long r) {
        long acc = 0;
        for (long i = l; i <=r ; i++) {
            for (long j = 1; j <=i ; j++) {
                long acc2 = 0;
                if(j*(j+1) == 2*i) {
                    for (int k = 1; k <=i ; k++) {
                        if(gdc(k, i) == 1)
                            acc2++;
                    }
                }
                acc += acc2;
            }
        }
        return acc;
    }

    private static void testFn() {
        System.out.println(fn(1, 1000));
    }

    public static void main(String[] args) {
        testFn();
    }

}
