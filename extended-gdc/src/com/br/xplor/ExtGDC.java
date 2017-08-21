package com.br.xplor;

/**
 * Created by r028367 on 17/08/2017.
 */
public class ExtGDC {

    public static void  extended(long a, long b, long [] res) {

    }

    public static void main(String[] args) {
        long [] res = new long[3];
        extended(126, 31, res);
        System.out.printf("%d %d %d\n", res[0], res[1], res[2]);
    }

}
