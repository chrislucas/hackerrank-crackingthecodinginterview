package numbertheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TimeComplexitySqrt {

    public static boolean isPrimeSqrt(int q) {
        if(((q&1)==0 && q > 2) || q < 2)
            return false;
        int limit = (int)Math.sqrt(q);
        for(int i=3; i<=limit; i+=2) {
            if(q%i==0)
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
                        , isPrimeSqrt(Integer.parseInt(reader.readLine())) ? "Prime" : "Not prime");
            }
        } catch (Exception e) {}
    }
}
