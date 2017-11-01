package com.xplore.solutions;



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
            int diffX = that.x - this.x;
            int diffY = that.y - this.y;
            return (int)Math.sqrt(diffX*diffX-diffY*diffY);
        }

        @Override
        public int compareTo(Object o) {
            Point2D that = (Point2D) o;
            return that.x - this.y;
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

    public static void main(String[] args) {

    }
}
