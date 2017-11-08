public class Percolation {

    private int openSites = 0;
    private boolean sites [];
    private int ds [];          // nós
    private int sz [];          // pesos
    private int qSites;
    private static final int [][] directions = {
         {0,-1}     // left
        ,{-1,0}    // top
        ,{0,1}     // right
        ,{1,0}     // bottom
    };

    /**
     * Como transformar o par i,j de uma matriz bidimensional num valor k
     * para uma matriz unidimensional
     * k = i*n+j onde n é o numero de colunas que a matriz bidimensional possui
     * */

    public Percolation(int n) {
        if(n < 0) {
            throw new IllegalArgumentException();
        }
        qSites  = n;
        int limit = n*n+2;
        sites   = new boolean[limit];
        ds      = new int[limit];
        sz      = new int[limit];
        for(int i = 0; i<limit; i++) {
            ds[i] = i;
            sz[i] = 1;
        }
        // os 2 indices abaixo sao auxiliares
        sites[0] = true;
        sites[limit-1] = true;
    }

    private int getIndex(int r, int c) {
        return (r-1) * (qSites) + c;
    }

    public void open(int row, int col) {
        if(!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        if(!isOpen(row, col)) { }
        int idx = getIndex(row, col);
        if(row == 1) {
            union(idx, ds[0]);
        }
        else if(row == qSites) {
            union(idx, ds[qSites*qSites+1]);
        }
        for(int k=0; k<directions.length; k++) {
            int i = directions[k][0] + row;
            int j = directions[k][1] + col;
            if(isValid(i, j) && isOpen(i, j)) {
                union(getIndex(i, j), idx);
            }
        }
        sites[idx] = true;
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        ds[rootP] = rootQ;
    }

    private int find(int p) {
        if(p != ds[p]) {
            ds[p] = ds[ds[p]];
            return find(ds[p]);
        }
        return p;
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public boolean isOpen(int row, int col) {
        if(!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        int idx = getIndex(row, col);
        return sites[idx];
    }


    private boolean isValid(int row, int col) {
        return row > 0 && row < qSites+1 && col > 0 && col < qSites+1;
    }

    public boolean isFull(int row, int col) {
        if(!isValid(row, col)) {
            throw new IllegalArgumentException(String.format("%d %d", row, col));
        }
        if(isOpen(row, col)) {
            return connected(ds[0], getIndex(row, col));
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return find(ds[0]) == find(ds[qSites*qSites+1]);
    }

    public static void main(String[] args) { }
}
