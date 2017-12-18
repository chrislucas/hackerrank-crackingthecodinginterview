package datastructure.heap.problems;


import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 *
 * */
public class HeapFindRunningMedian {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static double [] array, copy;
    public static int controlSize = 0;
    public static NumberFormat numberFormat;

    static {
        numberFormat = NumberFormat.getPercentInstance(Locale.ENGLISH);
        if(numberFormat instanceof DecimalFormat) {
            ((DecimalFormat) numberFormat).applyPattern("#.0");
        }
    }

    /**
     * Elemento do meio do heap ou a media dos elementos do meio (No caso do heap ter tamanho parç)
     * */
    public static double getMedian(double [] array) {
        int root = controlSize/2;
        if(controlSize == 1) {
            return array[root];
        }
        else if(controlSize == 2) {
            return (array[0] + array[1]) / 2.0D;
        }
        else if( controlSize > 2 && (controlSize & 1) == 0 ) {
            double l = array[root-1];
            double r = array[root];
            return (r-l)/2.0D+l;
        }
        return array[root];
    }
/*
    public static int left(int index) {
        return index * 2 + 1;
    }
    public static int right(int index) {
        return index * 2 + 2;
    }
*/
    public static int parent(int index) { return index / 2 ; }
    public static boolean moreThan(int p, int q) {
        return array[p] > array[q];
    }

    public static void swap(double [] data, int p, int q) {
        double a = data[p];
        data[p] = data[q];
        data[q] = a;
    }

    public static void insert(double data) {
        array[controlSize++] = data;
        System.arraycopy(array, 0, copy, 0, controlSize);
        minHeap(copy, controlSize, 0);
        double median = getMedian(copy);
        writer.printf("%s\n", numberFormat.format(median));
    }

    public static void sort() {
        for(int i=(controlSize-1)/2; i>=0; i--)
            maxHeap(array, controlSize, i);
        for(int i=controlSize-1; i>0; i--) {
            swap(array, 0, i);
            maxHeap(array, i, 0);
        }
    }

    /**
     * Matem a propriedade de min heap
     * */
    public static void minHeapBottomUp() {
        for (int i=controlSize-1; i>0 ; i=parent(i)) {
            int p = parent(i);
            if(moreThan(p, i))
                swap(array, p, i);
        }
    }

    /**
     * Ajuda a ordenar o array de forma crescente
     * */
    public static void maxHeapTopDown(double array [], int k) {
        while (2*k+1<controlSize) {
            int l = 2*k+1;
            if(l < controlSize && array[l] < array[l+1])
                l++;
            if(array[k] > array[l])
                break;
            swap(array, k, l);
            k = l;
        }
    }

    public static void maxHeap(double array [], int size, int k) {
        int largest = k;
        int l = 2*k+1;
        int r = l+1;
        if(l < size && array[l] > array[k])
            largest = l;
        if(r < size && array[r] > array[largest])
            largest = r;
        if(largest != k) {
            swap(array, largest, k);
            maxHeap(array, size, largest);
        }
    }

    public static void minHeap(double array [], int size, int k) {
        int smallest = k;
        int l = 2*k+1;
        int r = l+1;
        if(l < size && array[l] < array[k])
            smallest = l;
        if(r < size && array[r] < array[smallest])
            smallest = r;
        if(smallest != k) {
            swap(array, smallest, k);
            minHeap(array, size, smallest);
        }
    }

    public static void solver() {
        try {
            int size = Integer.parseInt(reader.readLine().trim());
            array = new double[size];
            copy = new double[size];
            StringTokenizer tk;
            for(int i=0; i<size; i++) {
                tk = new StringTokenizer(reader.readLine(), " ");
                double data = Double.parseDouble(tk.nextToken());
                insert(data);
            }

        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        solver();
    }
}
