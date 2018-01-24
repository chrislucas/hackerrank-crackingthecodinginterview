package com.xplore.solutions;

public class Point2f {
    double x, y;
    public Point2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("P(%f,%f)", x, y);
    }

    public double euclidianD(Point2f that) {
        double diffX = that.x - this.x;
        double diffY = that.y - this.y;
        return Math.sqrt(diffX*diffX+diffY*diffY);
    }
}
