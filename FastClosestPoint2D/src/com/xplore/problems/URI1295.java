package com.xplore.problems;

import com.xplore.solutions.ClosestPoint2D;

import java.io.*;
import java.text.DecimalFormat;
import java.util.StringTokenizer;


/**
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1295
 * */

public class URI1295 {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
    private static final DecimalFormat formatter = new DecimalFormat("#.####");

    private static class Point2f {
        double x, y;
        public Point2f(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public double distance(Point2f that) {
            double diffX = that.x - this.x;
            double diffY = that.y - this.y;
            return Math.sqrt(diffX*diffX+diffY*diffY);
        }
    }


    public static double minDistance(Point2f [] array, int limit) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < limit-1 ; i++) {
            for (int j = i+1; j < limit ; j++) {
                double distance = array[j].distance(array[i]);
                if(distance < min)
                    min = distance;
            }
        }
        return min;
    }

    public static double solver(Point2f [] array, int len) {
        if(len < 4) {
            return minDistance(array, len);
        }

        int mid = len / 2;
        double minLeft = solver(array, mid);
        double minRight =solver(array, len - mid);
        double min = minLeft < minRight ? minLeft : minRight;


        return min;
    }

    public static void main(String[] args) {
        try {
            int in = 0;
            while ( (in = Integer.parseInt(reader.readLine())) != 0 ) {
                Point2f [] points = new Point2f[in];
                for (int i = 0; i <in ; i++) {
                    StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                    double a = Double.parseDouble(tk.nextToken());
                    double b = Double.parseDouble(tk.nextToken());
                    points[i] = new Point2f(a, b);
                }
                double answer = solver(points, in);
                writer.printf("%s", answer == Double.POSITIVE_INFINITY
                        ? "INFINITY" : formatter.format(answer));
            }
        } catch (IOException ioex) {}
    }
}
