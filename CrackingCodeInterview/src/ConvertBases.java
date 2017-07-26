/**
 * Created by C_Luc on 17/07/2017.
 *
 */

import java.math.BigInteger;
import java.util.HashMap;

import static java.lang.Math.log10;

public class ConvertBases {



    public static int log(long base, long lg) {
        return (int) Math.floor(log10(base) / log10(lg));
    }

    public static final HashMap<Integer, String> hex = new HashMap<>();
    static {
        hex.put(10, "a");
        hex.put(11, "b");
        hex.put(12, "c");
        hex.put(13, "d");
        hex.put(14, "e");
        hex.put(15, "f");
    }

    public static BigInteger convertBases(BigInteger from, BigInteger to, BigInteger number) {
        BigInteger result = BigInteger.ONE;
        if(from.compareTo(to) > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            while(number.compareTo(BigInteger.ZERO) > 0) {
                stringBuilder.append( number.mod(to) );
                number = number.divide(to);
            }
            result = new BigInteger(stringBuilder.reverse().toString());
            return result;
        }
        else {
            BigInteger mult = BigInteger.ONE, acc = BigInteger.ZERO;
            while(number.compareTo(BigInteger.ZERO) > 0) {
                BigInteger rest = number.mod(BigInteger.TEN);
                acc = acc.add(rest.multiply(mult));
                mult = mult.multiply(from);
                number = number.divide(BigInteger.TEN);
            }
            return acc;
        }
    }


    public static String convertBases(int from, int to, int number) {
        if(from > to) {
            StringBuilder stringBuilder = new StringBuilder();
            while(number > 0) {
                stringBuilder.append(number % to);
                number /= to;
            }
            if(number != 0)
                stringBuilder.append(number);
            return stringBuilder.reverse().toString();
        }
        else {
            long  mult = 1, rs = 0;
            while(number > 0) {
                long rest = number % 10;
                rs += rest * mult;
                mult *= from;
                number /= 10;
            }
            return String.valueOf(rs);
        }
    }


    public static void main(String[] args) {
        System.out.println(convertBases(new BigInteger(String.valueOf(16))
                , new BigInteger(String.valueOf(2))
                , new BigInteger(String.valueOf(16))));
    }

}
