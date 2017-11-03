package problems.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/practice-problems/algorithm/city-and-flood-1/
 * */
public class CityAndFlood {
    public static int [] data;
    public static int count;
    public static int find(int p) {
        if(p == data[p]) {
            return p;
        }
        data[p] = data[data[p]];    // path compression
        return find(data[p]);
    }

    public static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)
            return;
        data[rootP] = rootQ;
        count--;
    }

    public static boolean hasSameRoot(int p, int q) {
        return find(p) == find(q);
    }

    public static void init(int size) {
        data = new int[size];
        for(int i=0; i<size; i++) {
            data[i] = i;
        }
        count = size - 1;
    }

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
    public static int parseInt(String in) {
        return Integer.parseInt(in);
    }

    public static void main(String[] args) {
        try {
            String line = reader.readLine();
            int n = 0, k = 0;
            /**
             * Gambiarra para ler dados do arquivo de teste pq os juizes nao
             * arrumam o formato do arquivo
             * */
            if(line.contains(" ")) {
                StringTokenizer tk = new StringTokenizer(line, " ");
                n = parseInt(tk.nextToken());
                k = parseInt(tk.nextToken());
            }
            else {
                n = parseInt(line);
                k = parseInt(reader.readLine());
            }
            init(n+1);
            while(k-->0) {
                StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                int p = parseInt(tk.nextToken());
                int q = parseInt(tk.nextToken());
                union(q, p);
            }
            writer.printf("%d\n", count);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
