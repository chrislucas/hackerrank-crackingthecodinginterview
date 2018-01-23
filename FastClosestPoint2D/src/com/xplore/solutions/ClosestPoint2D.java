package com.xplore.solutions;


import java.util.Arrays;
import java.util.Comparator;

public class ClosestPoint2D {

    public static class Point2D implements Comparable {
        int x, y;
        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("P(%d,%d)", x, y);
        }

        public int euclidianD(Point2D that) {
            int dx = diffX(that);
            int dy = diffY(that);;
            return (int)Math.sqrt(dx*dx-dy*dy);
        }

        public int diffX(Point2D that) {
            return that.x - this.x;
        }

        public int diffY(Point2D that) {
            return that.y - this.y;
        }

        @Override
        public int compareTo(Object o) {
            Point2D that = (Point2D) o;
            return this.x - that.x;
        }
    }

    private static final Comparator<Point2D> SORT_BY_X = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D p, Point2D q) {
            return p.x-q.x;
        }
    };

    private static final Comparator<Point2D> SORT_BY_Y = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D p, Point2D q) {
            return p.y-q.y;
        }
    };

    // {2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}
    public static  Point2D [] points = {
         new Point2D(2, 3)
        ,new Point2D(12, 30)
        ,new Point2D(40, 50)
        ,new Point2D(5, 1)
        ,new Point2D(12, 10)
        ,new Point2D(3, 4)
    };

    public static int brute(Point2D [] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 1; j < array.length ; j++) {
                int minAux = array[i].euclidianD(array[j]);
                if( minAux < min)
                    min = minAux;
            }
        }
        return min;
    }

    public static int solver(Point2D [] array, int lo, int hi) {
        if(hi <= 3)
            return brute(array);

        int mid = (hi - lo) / 2 + lo;

        int left = solver(array, 0, mid);
        int right = solver(array, mid, hi);

        int min = Math.min(left, right);

        return 0;
    }

    public static void test() {
        Point2D [] orderByX = new Point2D[points.length];
        Point2D [] orderByY = new Point2D[points.length];
        System.arraycopy(points, 0, orderByX, 0, orderByX.length);
        System.arraycopy(points, 0, orderByY, 0, orderByY.length);

        Arrays.sort(orderByX, SORT_BY_X);
        Arrays.sort(orderByY, SORT_BY_X);
    }

    public static void main(String[] args) {
        Arrays.sort(points, SORT_BY_X);
        solver(points, 0, points.length-1);
    }
}
