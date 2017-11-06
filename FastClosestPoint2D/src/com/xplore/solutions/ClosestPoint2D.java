package com.xplore.solutions;


import java.util.Arrays;

public class ClosestPoint2D {

    public class Point2D implements Comparable {
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

    public class Pointf2D {
        double x, y;
        public Pointf2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("P(%f,%f)", x, y);
        }

        public double euclidianD(Point2D that) {
            double diffX = that.x - this.x;
            double diffY = that.y - this.y;
            return Math.sqrt(diffX*diffX+diffY*diffY);
        }
    }

    public  Point2D [] getArrayV1() {
        // {2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}
        Point2D [] points = {
             new Point2D(2, 3)
            ,new Point2D(12, 30)
            ,new Point2D(40, 50)
            ,new Point2D(5, 1)
            ,new Point2D(12, 10)
            ,new Point2D(3, 4)
        };
        return points;
    }

    public Point2D solver(Point2D [] points) {
        int mid = points.length/2;

        return null;
    }

    public static void main(String[] args) {
        ClosestPoint2D instance = new ClosestPoint2D();
        Point2D [] array = instance.getArrayV1();
        Arrays.sort(array)
        instance.solver(array);
    }
}
