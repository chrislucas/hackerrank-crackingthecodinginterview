package com.xplore.solutions;


import java.util.Arrays;
import java.util.Comparator;

public class ClosestPoint2D {

    public static class Point2d implements Comparable {
        int x, y;
        public Point2d(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("P(%d,%d)", x, y);
        }

        public double euclidianDistance(Point2d that) {
            int dx = diffX(that);
            int dy = diffY(that);;
            return Math.sqrt(dx*dx+dy*dy);
        }

        public int diffX(Point2d that) {
            return that.x - this.x;
        }

        public int diffY(Point2d that) {
            return that.y - this.y;
        }

        @Override
        public int compareTo(Object o) {
            Point2d that = (Point2d) o;
            return this.x - that.x;
        }
    }

    private static final Comparator<Point2d> SORT_BY_X = Comparator.comparingInt(p -> p.x);
    private static final Comparator<Point2d> SORT_BY_Y = new Comparator<Point2d>() {
        @Override
        public int compare(Point2d p, Point2d q) {
            return p.y - q.y;
        }
    };

    public static double brute(Point2d[] array, int lo, int hi) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            for (int j = i+1; j<hi ; j++) {
                double minAux = array[i].euclidianDistance(array[j]);
                if( minAux < min)
                    min = minAux;
            }
        }
        return min;
    }

    /**
     * n log(n)
     * */
    private static double solver(Point2d[] orderByX, Point2d[] aux,  int lo, int hi) {
        if(hi <= 3)
            return brute(orderByX, lo, hi);
        int mid = hi/2;
        double left = solver(orderByX, aux, 0, mid);
        double right = solver(orderByX, aux,   mid+1, hi - mid);
        double currentMin = Math.min(left, right);
        int k = 0;
        for (int i = 0; i < hi ; i++) {
            if(Math.abs(orderByX[i].x - orderByX[mid].x) < currentMin)
                aux[k++] = orderByX[i];
        }
        if(k > 2)
            Arrays.sort(aux, 0, k, SORT_BY_Y);
        else if(k == 2) {
            if(aux[0].y > aux[1].y) {
                Point2d a = aux[0];
                aux[0] = aux[1];
                aux[1] = a;
            }
        }
        for (int i = 0; i < k ; i++) {
            for (int j = i+1; j < k && (aux[j].y - aux[i].y) < currentMin  ; j++) {
                double distance = aux[j].euclidianDistance(aux[i]);
                if(distance < currentMin) {
                    currentMin = distance;
                }
            }
        }
        return currentMin;
    }

    private static Point2d [] solver2(Point2d [] points, Point2d [] aux, int len) {
        if(len <= 3) {
            /**
             * algoritmo para descobrir o par mais proximo (3 comparacoes)
             * */
            double distance = Double.POSITIVE_INFINITY;
            Point2d [] answer = new Point2d[2];
            for(int i=0; i<len; i++) {
                for (int j = i+1; j < len ; j++) {
                    double minDitance = points[i].euclidianDistance(points[j]);
                    if(minDitance < distance) {
                        distance = minDitance;
                        answer[0] = points[i];
                        answer[1] = points[j];
                    }
                }
            }
            return answer;
        }
        int mid = len/2;
        Point2d [] pA = solver2(points, aux, mid);
        Point2d [] pB = solver2(points, aux, len-mid);
        double distanceA = pA[0].euclidianDistance(pA[1]);
        double distanceB = pB[0].euclidianDistance(pB[1]);
        double minDistance;
        Point2d [] pQ = new Point2d[2];
        if(distanceA < distanceB) {
            pQ[0] = pA[0];
            pQ[1] = pA[1];
            minDistance = distanceA;
        }
        else {
            pQ[0] = pB[0];
            pQ[1] = pB[1];
            minDistance = distanceB;
        }
        int k = 0;
        for(int i=0; i<len; i++) {
            if(Math.abs(points[i].x - points[mid].x) < minDistance) {
                aux[k++] = points[i];
            }
        }
        if(k > 2) {
            Arrays.sort(aux, 0, k, SORT_BY_Y);
        }
        else if(k == 2) {
            if(aux[0].y > aux[1].y) {
                Point2d a = aux[0];
                aux[0] = aux[1];
                aux[1] = a;
            }
        }
        for (int i = 0; i < k ; i++) {
            for (int j = i+1; j < k && aux[j].diffY(aux[i]) < minDistance; j++) {
                double distance = aux[j].euclidianDistance(aux[i]);
                if(distance < minDistance) {
                    minDistance = distance;
                    pQ[0] = aux[i];
                    pQ[1] = aux[j];
                }
            }
        }
        return pQ;
    }

    private static Point2d[][] matrix = {
        {
             new Point2d(2, 3)
            ,new Point2d(12, 30)
            ,new Point2d(40, 50)
            ,new Point2d(5, 1)
            ,new Point2d(12, 10)
            ,new Point2d(3, 4)
        }
        ,{
             new Point2d(1, 1)
            ,new Point2d(0, 1)
            ,new Point2d(0, 0)
            ,new Point2d(2, 3)
            ,new Point2d(12, 30)
            ,new Point2d(40, 50)
            ,new Point2d(5, 1)
            ,new Point2d(12, 10)
            ,new Point2d(3, 4)
        }
        ,{
             new Point2d(0, 3)
            ,new Point2d(3, 4)
            ,new Point2d(4, 2)
            ,new Point2d(-2, -1)
            ,new Point2d(1, -1)
        }
        ,{
             new Point2d(0, 3)
            ,new Point2d(3, 4)
            ,new Point2d(4, 2)
            ,new Point2d(-2, 1)
            ,new Point2d(1, -1)
        }
        ,{
             new Point2d(0, 2)
            ,new Point2d(6, 67)
            ,new Point2d(43, 71)
            ,new Point2d(39, 107)
            ,new Point2d(189, 140)
        }
    };

    public static void test() {
        int idx = 4;
        Point2d[] points = new Point2d[matrix[idx].length];
        System.arraycopy(matrix[idx], 0, points, 0, points.length);
        Arrays.sort(points, SORT_BY_X);

        double min = solver(points, new Point2d[points.length], 0, points.length);
        System.out.println(min);

        Point2d [] pQ = solver2(points, new Point2d[points.length], points.length);
        min = pQ[0].euclidianDistance(pQ[1]);
        System.out.printf("%s - %s =  distance %f", pQ[0], pQ[1], min);
    }

    public static void main(String[] args) {
        test();
    }
}
