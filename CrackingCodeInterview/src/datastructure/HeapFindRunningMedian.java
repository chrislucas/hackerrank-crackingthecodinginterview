package datastructure;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 *
 * */
public class HeapFindRunningMedian {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static int [] array;
    public static int controlSize = 0;

    public static int left(int index) {
        return index * 2 + 1;
    }

    public static int right(int index) {
        return index * 2 + 2;
    }

    public static void insert(int data) {

        controlSize++;
    }

    public static void fixMinHeap() {

    }


    public static void main(String[] args) {

    }
}
