package problems;


import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/contests/101hack51/challenges/charging-the-batteries?utm_source=101hack52-reminder-1&utm_medium=email&utm_campaign=101hack52
 * */

public class ChargingTheBatteries {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    static class Points2D {
        long x, y, line;
        public Points2D(long x, long y, long line) {
            this.x = x;
            this.y = y;
            this.line = line;
        }

        @Override
        public String toString() {
            return String.format("(%d %d) Linha %d", x, y, line);
        }

        public long absManhattanDistance(Points2D that) {
            return Math.abs(x-that.x) + Math.abs(y-that.y);
        }
    }

    static Comparator<Points2D> sortAntiClockwise = new Comparator<Points2D>() {
        @Override
        public int compare(Points2D a, Points2D b) {
            return (int)(a.line - b.line);
        }
    };

    static Comparator<Points2D> sortClockwise = new Comparator<Points2D>() {
        @Override
        public int compare(Points2D a, Points2D b) {
            return (int)(b.line - a.line);
        }
    };

    static Comparator<Points2D> sortCoordAntiClockwise = new Comparator<Points2D>() {
        @Override
        public int compare(Points2D a, Points2D b) {
            if( (a.line - b.line) < 0 )
                return -1;
            else if( (a.line - b.line) > 0)
                return 1;
            return (int)((a.x - b.x) + (a.y - b.y));
        }
    };

    public static long minValueSubsetK(long [] setAntiClockwise, long []  setClockwise, int k) {
        long min1 = 0, min2 = 0;
        for(int idx=0; idx<k; idx++) {
            min1 += setAntiClockwise[idx];
            min2 += setClockwise[idx];
        }
        long current1 = min1, current2 = min2;
        for(int idx=k; idx<setAntiClockwise.length; idx++) {
            current1 = current1 + setAntiClockwise[idx] - setAntiClockwise[idx-k];
            min1 = current1 < min1 ? current1 : min1;
            current2 = current2 + setClockwise[idx] - setClockwise[idx-k];
            min2 = current2 < min2 ? current2 : min2;
        }
        return min1 < min2 ? min1 : min2;
    }

    public static void s1(int n, int m, int k) throws IOException {
        StringTokenizer tk;
        long set [] = new long[m], set2 [] = new long[m];
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
        Arrays.sort(points2D, sortCoordAntiClockwise);
        // antihorario
        for(int i=1; i<m; i++)
            set[i-1] = points2D[i].absManhattanDistance(points2D[i-1]);
        set[m-1] = points2D[m-1].absManhattanDistance(points2D[0]);
        // horario
        for(int i=m-1; i>0; i--)
            set2[m-i-1] = points2D[i].absManhattanDistance(points2D[i-1]);
        set2[m-1] = points2D[0].absManhattanDistance(points2D[m-1]);
        writer.printf("%d", minValueSubsetK(set, set2,k-1));
    }

    public static void s2(int n, int m, int k) throws IOException {
        // linhas do quadrado
        Points2D [] line1 = new Points2D[m];
        Points2D [] line2 = new Points2D[m];
        Points2D [] line3 = new Points2D[m];
        Points2D [] line4 = new Points2D[m];
        int o = 0, p = 0, q = 0, r = 0;
        StringTokenizer tk;
        long set [] = new long[m], set2 [] = new long[m];
        // sockets
        for (int i = 0; i < m; i++) {
            tk = new StringTokenizer(reader.readLine(), " ");
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());
            if(x >= 0 && y == 0)
                line1[o++] = new Points2D(x, y, 1);
            else if(x == n && y >=0)
                line2[p++] = new Points2D(x, y, 2);
            else if(x >= 0 && y == n)
                line3[q++] = new Points2D(x, y, 3);
            else
                line4[r++] = new Points2D(x, y, 4);
        }
        /**
         * Percorrer da linha 1 a 4
         * */
        int indexSet = 0;
        // anti-clockwise
        for (int idx = 1; idx < o; idx++)
            set[indexSet++] = line1[idx].absManhattanDistance(line1[idx-1]);
        set[indexSet++] = line1[o-1].absManhattanDistance(line2[0]);

        for (int idx = 1; idx < p; idx++)
            set[indexSet++] = line2[idx].absManhattanDistance(line2[idx-1]);
        set[indexSet++] = line2[p-1].absManhattanDistance(line3[0]);

        for (int idx = 1; idx < q; idx++)
            set[indexSet++] = line3[idx].absManhattanDistance(line3[idx-1]);
        set[indexSet++] = line3[q-1].absManhattanDistance(line4[0]);

        for (int idx = 1; idx < r; idx++)
            set[indexSet++] = line4[idx].absManhattanDistance(line4[idx-1]);
        set[indexSet] = line4[r-1].absManhattanDistance(line1[0]);

        // clockwise
        indexSet = 0;
        set2[indexSet++] = line4[r-1].absManhattanDistance(line1[0]);
        for (int idx = r-1; idx > 0; idx--)
            set2[indexSet++] = line4[idx].absManhattanDistance(line4[idx-1]);

        writer.printf("%d", minValueSubsetK(set, set2,k-1));
    }

    public static void s3(int n, int m, int k) {}

    public static void main(String[] args) {
        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(tk.nextToken());
            int m = Integer.parseInt(tk.nextToken());
            int k = Integer.parseInt(tk.nextToken());
            s1(n, m, k);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
