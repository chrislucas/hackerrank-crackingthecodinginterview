package problems;

import java.io.*;

/**
 * https://www.hackerrank.com/contests/w36/challenges/acid-naming
 * */

public class AcidNaming {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);


    public static String test(String str) {
        if(str.length() > 6 && str.substring(0, 5).equals("hydro")
                && str.substring(str.length()-2, str.length()).equals("ic")) {
            return "non-metal acid";
        }
        else if(str.length() > 1 && str.substring(str.length()-2, str.length()).equals("ic")) {
            return "polyatomic acid";
        }
        else {
            return "not an acid";
        }
    }

    private static void test() {
        String str = "hydro teste abc ic";
        String str2 = "ic";
        System.out.printf("%s %s", str.substring(0, 5), str2.substring(str2.length()-2, str2.length()));
    }

    public static void main(String[] args) {
        try {
            int cases = Integer.parseInt(reader.readLine());
            while (cases > 0) {
                writer.printf("%s\n", test(reader.readLine()));
                cases--;
            }
        } catch (IOException ioex) { }
    }
}
