package datastructure.heap.problems;


import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 *
 * */
public class HeapFindRunningMedian {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static double [] array;
    public static int controlSize = 0;

    /**
     * Indice do meio do heap
     * */
    public static int getIndex() {
        return (controlSize & 1) == 0 ? controlSize / 2 - 1 : controlSize / 2;
    }

    /**
     * Elemento do meio do heap ou a media dos elementos do meio (No caso do heap ter tamanho parç)
     * */
    public static double getMedian() {
        int root = getIndex();
        if( (controlSize & 1) == 0  ) {
            double l = array[root + 1];
            double r = array[root + 2];
            return (l+r)/2;
        }
        return array[root];
    }

    public static int left(int index) {
        return index * 2 + 1;
    }
    public static int right(int index) {
        return index * 2 + 2;
    }
    public static int parent(int index) { return index / 2 ; }
    public static boolean moreThan(int p, int q) {
        return array[p] > array[q];
    }

    public static void swap(int p, int q) {
        double a = array[p];
        array[p] = array[q];
        array[q] = a;
    }

    public static void insert(double data) {
        array[controlSize] = data;
        minHeap();
        controlSize++;
        double median = getMedian();
        writer.printf("%.1f", median);
    }

    // minHeap
    public static void minHeap() {
        for (int i = controlSize; i>0 ; i=parent(i)) {
            int p = parent(i);
            if(moreThan(p, i))
                swap(p, i);
        }
    }


    public static void solver() {
        try {
            int size = Integer.parseInt(reader.readLine().trim());
            array = new double[size];
            StringTokenizer tk;
            for(int i=0; i<size; i++) {
                tk = new StringTokenizer(reader.readLine(), " ");
                double data = Double.parseDouble(tk.nextToken());
                insert(data);
            }

        } catch (IOException e) {}
    }



    public static void main(String[] args) {

    }
}
