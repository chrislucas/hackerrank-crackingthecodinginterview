package problems.codeforces;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * http://codeforces.com/contest/566/problem/D
 * http://codeforces.com/contest/566
 *
 *
 * Tutorial
 * http://codeforces.com/blog/entry/19518
 * Resolver os problemas desse tutorial futuramente
 *
 * Solucao interessante
 * https://github.com/yancouto/maratona-sua-mae/blob/master/Yan/codeforces/566D.cpp
 *
 * */
public class RestructuringCompany {

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);

    private static int [] teams;
    private static int [] sz;
    private static int [] next;

    private static void init(int size) {
        teams = new int[size+1];
        sz = new int[size+1];
        next = new int[size+1];
        for(int i=1; i<size+1; i++) {
            teams[i] = i;
            sz[i] = 1;
            next[i] = i+1;
        }
    }

    private static void unionByRank(int p, int q) {
        int rootP = findWithPathCompression(p);
        int rootQ = findWithPathCompression(q);
        if(rootP == rootQ)
            return;;
        // weight compare
        if(sz[rootP] < sz[rootQ]) {
            teams[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        }
        else {
            teams[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
    }

    private static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)
            return;;
        teams[rootP] = rootQ;
    }

    private static boolean hasSameRoot(int p, int q) {
        return find(p) == find(q);
    }

    private static int findWithPathCompression(int p) {
        if(p == teams[p])
            return p;
        teams[p] = teams[teams[p]];
        return findWithPathCompression(teams[p]);
    }

    private static int find(int p) {
        if(p == teams[p])
            return p;
        return find(teams[p]);
    }

    // TLE
    private static void solver1(int type, int x, int y) {
        if(type == 1) {
            union(x, y);
        }
        else if(type == 2) {
            for (int j = x+1; j <= y;) {
                union(j-1, j);
                int tempRoot = next[j];
                next[j] = next[y];
                j = tempRoot;
            }
        }
        else {
            printWriter.printf("%s\n", hasSameRoot(x, y) ? "YES" : "NO");
        }
    }

    private static void solver2(int type, int x, int y) {

    }


    public static void main(String[] args) {

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

                /**
                 * type == tipo de operacao na estrutura union-findWithPathCompression
                 * Tipo 1 e 2 sao unioes, tipo 3 verifica se o NÓ x possui o mesmo NÓ-PAI de y
                 *
                 * Union tipo 1 - muda o no pai de teams[y] para o no pai de teams[x
                 * Union tipo 2 - muda o no pai dos teams[x+1, x+2, x+3 ... y] para o no pai de teams[x]
                 *
                 * */
                solver1(type, x, y);
            }
        } catch (Exception e) {}
    }
}
