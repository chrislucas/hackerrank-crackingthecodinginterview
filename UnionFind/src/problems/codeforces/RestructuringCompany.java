package problems.codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * http://codeforces.com/contest/566/problem/D
 * http://codeforces.com/contest/566
 * */
public class RestructuringCompany {


    public static int [] teams;
    public static int [] sz;
    public static int [] next;

    public static void init(int size) {
        teams = new int[size+1];
        sz = new int[size+1];
        next = new int[size+1];
        for(int i=1; i<size+1; i++) {
            teams[i] = i;
            sz[i] = 1;
            next[i] = i+1;
        }
    }

    public static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)
            return;;
        teams[rootP] = rootQ;
                /*
        // weight compare
        if(sz[rootP] < sz[rootQ]) {
            teams[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        }
        else {
            teams[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
        */
    }

    public static boolean hasSameRoot(int p, int q) {
        return find(p) == find(q);
    }

    public static int find(int p) {
        if(p == teams[p])
            return p;
        teams[p] = teams[teams[p]];     // path compression
        return find(teams[p]);
    }


    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
        try {
            StringTokenizer tk = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(tk.nextToken());
            init(n);
            int q = Integer.parseInt(tk.nextToken());
            while(q-- > 0) {
                tk = new StringTokenizer(bufferedReader.readLine(), " ");
                int type = Integer.parseInt(tk.nextToken());
                int x = Integer.parseInt(tk.nextToken());
                int y = Integer.parseInt(tk.nextToken());
                if(type == 1) {
                    union(x, y);
                }
                else if(type == 2) {
                    int i=x+1;
                    while(i<=y) {
                        union(i, x);
                        int tempRoot = next[i];
                        next[i] = next[y];
                        i = tempRoot;
                    }
                }
                else {
                    printWriter.printf("%s\n", hasSameRoot(x, y) ? "YES" : "NO");
                }
            }
        } catch (Exception e) {}
    }
}
