package problems;


import java.io.*;
import java.util.*;

/**
 *
 * */

public class ChargingTheBatteries {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    static class Points2D {
        int x, y, line;
        public Points2D(int x, int y, int line) {
            this.x = x;
            this.y = y;
            this.line = line;
        }

        public int absManhatanDistance(Points2D that) {
            return Math.abs(x-that.x) + Math.abs(y-that.y);
        }
    }

    static Comparator<Points2D>  sortByLineNumber = new Comparator<Points2D>() {
        @Override
        public int compare(Points2D a, Points2D b) {
            return a.line - b.line;
        }
    };

    public static int minValueSubsetK(int [] set, int k) {
        int min = set[0];
        for(int idx=0; idx<k; idx++)
            min = set[idx] < min ? set[idx] : min;
        int current = min;
        for(int idx=k; idx<set.length; idx++) {
            current = current + set[idx] - set[idx-k];
            min = current < min ? current : min;
        }
        return min;
    }

    public static void s1(int n, int m, int k) throws IOException {
        StringTokenizer tk;
        int set [] = new int[m];
        // sockets
        Points2D [] points2D = new Points2D[m];
        for (int i = 0; i < m; i++) {
            tk = new StringTokenizer(reader.readLine(), " ");
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());
            /**
             * Condicionais
             *
             * Quadro no sentido anti-horario
             * na linha 1  y == 0 e 0 <= x <= n
             * na linha 2  0 <= y <= n e x == n
             * na linha 3  y == n e x >= 0
             * na linha 4  0 <= y <= n e x == 0;
             **/
            int line;
            if(x >= 0 && y == 0)
                line = 1;
            else if(x == n && y >=0)
                line = 2;
            else if(x >= 0 && y == n)
                line = 3;
            else
                line = 4;
            points2D[i] = new Points2D(x, y, line);

        }
        Arrays.sort(points2D, sortByLineNumber);
        for(int i=1; i<m; i++) {
            set[i-1] = points2D[i].absManhatanDistance(points2D[i-1]);
        }
        writer.printf("%d", minValueSubsetK(set, k));
    }

    public static void s2(int n, int m, int k) {
        Points2D [] line1 = new Points2D[m];
        Points2D [] line2 = new Points2D[m];
        Points2D [] line3 = new Points2D[m];
        Points2D [] line4 = new Points2D[m];
    }


    public static void main(String[] args) {
        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(tk.nextToken());
            int m = Integer.parseInt(tk.nextToken());
            int k = Integer.parseInt(tk.nextToken());
            s1(n, m, k);
        } catch (Exception e) {

        }
    }

}
