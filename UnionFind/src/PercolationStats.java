public class PercolationStats {

    private int n, trials;

    public PercolationStats(int n, int trials) {
        if(n < 1 || trials < 1)
            throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
    }

    public double mean() {
        return 0.0;
    }

    public double stddev() {
        return 0.0;
    }

    public double condidenceLo() {
        return 0.0;
    }

    public double confidenceHi() {
        return 0.0;
    }

    public static void main(String[] args) {

    }

}
