import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 * */

public class PercolationStats {

    private int sizeMatrix, trials;
    private Percolation percolation;
    private double [] fract;

    public PercolationStats(int sizeMatrix, int trials) {
        if(sizeMatrix < 1 || trials < 1)
            throw new IllegalArgumentException();
        this.sizeMatrix = sizeMatrix;
        this.trials = trials;
        this.fract  = new double[trials];
        for (int i = 0; i <trials ; i++) {
            percolation = new Percolation(sizeMatrix);
            int acc = 0;
            while(!percolation.percolates()) {
                int k = StdRandom.uniform(1, sizeMatrix+1);
                int l = StdRandom.uniform(1, sizeMatrix+1);
                if(!percolation.isOpen(k, l)) {
                    percolation.open(k,l);
                    acc++;
                }
            }
            fract[i] = (double) acc /(sizeMatrix*sizeMatrix);
        }
    }

    public double mean() {
        return StdStats.mean(fract);
    }

    public double stddev() {
        return StdStats.stddev(fract);
    }

    public double condidenceLo() {
        return mean() -  (1.96 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return  mean() +  (1.96 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats pStats = new PercolationStats(10, 1000);
        StdOut.printf("Mean: %f\n", pStats.mean());
        StdOut.printf("Standard Deviation: %f\n", pStats.stddev());
        StdOut.printf("Lo: %f\n", pStats.condidenceLo());
        StdOut.printf("Hi: %f\n", pStats.confidenceHi());
    }

}
