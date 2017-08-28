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

    public static void  extRec(long a, long b, long [] res) {
        if( a % b == 0) {
            res[0] = b;
            res[1] = 0;
            res[2] = 1;
            return;
        }
        extRec(b, a%b, res);
        long u = res[1], v = res[2];
        res[1] = v;
        res[2] = u - (a/b) * v;
    }

    public static void test1() {
        long [] res = new long[3];
        extRec(126, 31, res);
        System.out.printf("%d %d %d\n", res[0], res[1], res[2]);
        extRec(408, 126, res);
        System.out.printf("%d %d %d\n", res[0], res[1], res[2]);
    }

    public static void main(String[] args) {
        test1();
    }

}
