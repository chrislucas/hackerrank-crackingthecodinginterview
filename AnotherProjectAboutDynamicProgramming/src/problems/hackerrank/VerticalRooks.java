package problems.hackerrank;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/vertical-rooks/problem
 * */

public class VerticalRooks {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);


    public static void solver() throws IOException {

        int cases = Integer.parseInt(reader.readLine());

        while(cases > 0) {
            int sizeTable = Integer.parseInt(reader.readLine());
            int [] playerA = new int[sizeTable];
            int [] playerB = new int[sizeTable];
            for(int i=0; i<sizeTable; i++)
                playerA[i] = Integer.parseInt(reader.readLine());


            for(int i=0; i<sizeTable; i++)
                playerB[i] = Integer.parseInt(reader.readLine());
        }




    }

    public static void main(String[] args) {

    }

}
