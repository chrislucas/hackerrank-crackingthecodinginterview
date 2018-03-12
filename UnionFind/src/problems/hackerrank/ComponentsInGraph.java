package problems.hackerrank;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * https://www.hackerrank.com/challenges/components-in-graph/problem
 * DONE
 * */

public class ComponentsInGraph {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);


    private static class QuickUnion {
        private int [] components, sizeComponents;
        private int countComponents;

        public QuickUnion(int size) {
            components = new int [size];
            sizeComponents = new int [size];
            for (int i = 0; i < size ; i++) {
                components[i] = i;
                sizeComponents[i] = 1;
            }
            countComponents = size;
        }

        public int getSizeComponent(int i) {
            return sizeComponents[root(i)];
        }

        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if(rootP == rootQ)
                return;
            components[rootP] = rootQ;
            sizeComponents[rootQ] += sizeComponents[rootP];
            countComponents--;
        }

        private int root(int p) {
            while (components[p] != p)
                p = components[p];
            return p;
        }

        public boolean sameRoot(int p, int q) {
            return root(p) == root(q);
        }


        public int [] minMaxComponents() {
            int [] minmax = new int[2];
            minmax[0] = Integer.MAX_VALUE;
            minmax[1] = Integer.MIN_VALUE;
            for(int i=1; i<components.length; i++) {
                if(i == components[i]) {
                    int s = getSizeComponent(i);
                    if(s > 1) {
                        if(minmax[0] > s)
                            minmax[0] = s;
                        if(minmax[1] < s)
                            minmax[1] = s;
                    }
                }
            }
            return minmax;
        }
    }


    private static void solver() {
        try {
            int cases = Integer.parseInt(reader.readLine());
            QuickUnion quickUnion = new QuickUnion(2*cases+1);
            StringTokenizer tk;
            while (cases-- > 0) {
                tk = new StringTokenizer(reader.readLine(), " ");
                int p = Integer.parseInt(tk.nextToken());
                int q = Integer.parseInt(tk.nextToken());
                quickUnion.union(p, q);
            }
            int ans [] = quickUnion.minMaxComponents();
            writer.printf("%d %d\n", ans[0], ans[1]);

        } catch (IOException ioex) {}
    }

    public static void main(String[] args) {
        solver();
    }
}
