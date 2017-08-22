package com.br.xplor;

/**
 * Created by r028367 on 17/08/2017.
 */
public class ExtGDC {

    /**
     * Equacoes diofantinas
     * ax+by=c ->   ax + by = gcd(a, b)
     * x e y sao os coeficientes de Bézout's identity
     * */

    public static void  extended(long a, long b, long [] res) {
        if( a % b == 0) {
            return;
        }
        extended(b, a%b, res);
        res[0] = b;
    }

    public static void test1() {
        long [] res = new long[3];
        long a = 126, b = 31;
        res[0] = b;
        res[1] = 0;
        res[2] = 1;
        extended(a, b, res);
        System.out.printf("%d %d %d\n", res[0], res[1], res[2]);
    }

    public static void main(String[] args) {

    }

}
