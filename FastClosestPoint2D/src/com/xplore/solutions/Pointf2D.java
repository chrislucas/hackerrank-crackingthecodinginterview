package com.xplore.solutions;

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

    public double euclidianD(ClosestPoint2D.Point2D that) {
        double diffX = that.x - this.x;
        double diffY = that.y - this.y;
        return Math.sqrt(diffX*diffX+diffY*diffY);
    }
}
