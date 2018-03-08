package problems.hackerrank;


import xplore.ds.WeightQuickUnionPathCompression;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/merging-communities/problem
 *
 * DONE
 * */

public class MergingCommunities {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static class WeightedQuickUnion {
        private int [] components, sizeComponents;
        private int countComponents;

        public WeightedQuickUnion(int size) {
            components = new int [size];
            sizeComponents = new int [size];
            for (int i = 0; i < size ; i++) {
                components[i] = i;
                sizeComponents[i] = 1;
            }
            countComponents = size;
        }

        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if(rootP == rootQ)
                return;
            if(sizeComponents[rootP] < sizeComponents[rootQ]) {
                components[rootP] = rootQ;
                sizeComponents[rootQ] += sizeComponents[rootP];
            }
            else {
                components[rootQ] = rootP;
                sizeComponents[rootP] += sizeComponents[rootQ];
            }
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

        public int getSizeComponent(int i) {
            return sizeComponents[root(i)];
        }
    }

    public static class WeightedQuickUnionPathCompression {
        private int [] components, sizeComponents;
        private int countComponents;
        public WeightedQuickUnionPathCompression(int size) {
            components = new int [size];
            sizeComponents = new int [size];
            for (int i = 0; i < size ; i++) {
                components[i] = i;
                sizeComponents[i] = 1;
            }
            countComponents = size;
        }

        public void union(int p, int q) {
            int rp = root(p);
            int rq = root(q);
            if(rq == rp)
                return;
            if(sizeComponents[rp] < sizeComponents[rq]) {
                components[rp] = rq;
                sizeComponents[rq] += sizeComponents[rp];
            }
            else {
                components[rq] = rp;
                sizeComponents[rp] += sizeComponents[rq];
            }
            countComponents--;
        }

        // path compression
        private int root(int p) {
            while (components[p] != p) {
                components[p] = components[components[p]];
                p = components[p];
            }
            return p;
        }

        public boolean sameRoot(int p, int q) {
            return root(p) == root(q);
        }

        public int getSizeComponent(int i) {
            return sizeComponents[root(i)];
        }
    }

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
    }


    private static void solver() {
        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int size = Integer.parseInt(tk.nextToken());
            int queries = Integer.parseInt(tk.nextToken());
            //WeightedQuickUnion wqu = new WeightedQuickUnion(size+1);
            WeightedQuickUnionPathCompression wqupc = new WeightedQuickUnionPathCompression(size+1);
            while (queries-- > 0) {
                tk = new StringTokenizer(reader.readLine(), " ");
                String type = tk.nextToken();
                if(type.equals("Q")) {
                    int i = Integer.parseInt(tk.nextToken());
                    writer.printf("%d\n", wqupc.getSizeComponent(i));
                }
                else {
                    int p = Integer.parseInt(tk.nextToken());
                    int q = Integer.parseInt(tk.nextToken());
                    wqupc.union(p, q);
                }
            }
        } catch (IOException ioex) {}
    }

    public static void main(String[] args) {
        solver();
    }
}
