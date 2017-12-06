package problems;

import sun.plugin.com.event.COMEventListener;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ChargingTheBatteries {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);


    public static int absManhatanDisntance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    static class Points2D {
        int x, y, line;
        public Points2D(int x, int y, int line) {
            this.x = x;
            this.y = y;
            this.line = line;
        }
    }

    Comparator<Points2D> comparator = new Comparator<Points2D>() {
        @Override
        public int compare(Points2D a, Points2D b) {
            return a.line - b.line;
        }
    };

    public static int solver(int [] set, int k) {
        int max = set[0];
        for(int idx=0; idx<k; idx++)
            max = set[idx] > max ? set[idx] : max;
        int current = max;
        for(int idx=k; idx<set.length; idx++) {
            current = current + set[idx] - set[idx-k];
            max = current > max ? current : max;
        }

        return max;
    }

    public static void s1(int n, int m, int k) throws IOException {
        StringTokenizer tk;
        int set [] = new int[m];
        Points2D [] line1 = new Points2D[m];
        Points2D [] line2 = new Points2D[m];
        Points2D [] line3 = new Points2D[m];
        Points2D [] line4 = new Points2D[m];

        // sockets
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
            Points2D p = new Points2D(x, y, line);
        }
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
