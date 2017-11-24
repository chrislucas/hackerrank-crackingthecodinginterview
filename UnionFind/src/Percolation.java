public class Percolation {

    private int openSites = 0;
    private boolean sites [];
    private int ds [];          // nós
    private int sz [];          // pesos
    private int qSites;

    /**
     * Como transformar o par i,j de uma matriz bidimensional num valor k
     * para uma matriz unidimensional
     * k = i*n+j onde n é o numero de colunas que a matriz bidimensional possui
     * */

    public Percolation(int n) {
        qSites      = n;
        int limit   = (n+2)*(n+2);
        sites       = new boolean[limit+1];
        ds          = new int[limit+1];
        sz          = new int[limit+1];
        for(int i = 0; i<limit; i++) {
            ds[i] = i;
            sz[i] = 1;
        }
        // os 2 indices abaixo sao auxiliares
        //sites[0] = true;
        //sites[szMatrix-1] = true;
    }

    private int getIndex(int r, int c) {
        return r * qSites + c;
    }

    public void open(int row, int col) {
        if(row < 1 || row > qSites || col < 1 || col > qSites) {
            throw new IllegalArgumentException();
        }
        if(!isOpen(row, col)) {
            int idx = getIndex(row, col);
            sites[idx] = true;
        }
    }

    public boolean isOpen(int row, int col) {
        if(row < 1 || row > qSites || col < 1 || col > qSites) {
            throw new IllegalArgumentException();
        }
        int idx = getIndex(row, col);
        return sites[idx];
    }

    public boolean isFull(int row, int col) {
        if(row < 1 || row > qSites || col < 1 || col > qSites) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    private int root(int p) {
        if(p == ds[p])
            return p;
        return root(ds[p]);
    }

    public boolean percolates() {


        return root(ds[0]) == root(ds[qSites]);
    }

    public static void main(String[] args) { }
}
