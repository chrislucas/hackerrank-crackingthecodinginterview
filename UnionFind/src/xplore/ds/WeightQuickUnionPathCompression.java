package xplore.ds;

public class WeightQuickUnionPathCompression {

    public int ds[];
    public int sz[];
    public int count;
    public WeightQuickUnionPathCompression(int size) {
        ds = new int[size];
        sz = new int[size];
        for(int i=0; i<size; i++) {
            ds[i] = i;
            sz[i] = 1;
        }
        count = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int id : ds) {
            sb.append(String.format("%d ", id));
        }
        return sb.toString();
    }

    public boolean isValid(int p) {
        return p < ds.length - 1 || p > -1;
    }

    public boolean isConnected(int p, int q) throws Exception{
        if(!isValid(p) || !isValid(q))
            throw new Exception();
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) throws Exception {
        if(!isValid(p) || !isValid(q))
            throw new Exception();
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if(rootP == rootQ)
            return;
        if(sz[rootP] < sz[rootQ]) {
            ds[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        }
        else {
            ds[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
        count--;
    }

    /**
     *
     * Procura recursivamente o no pai de p e
     * executa o path compression
     * */
    public int findRoot(int p) throws Exception {
        if(!isValid(p))
            throw new Exception();
        if(p == ds[p])
            return p;
        ds[p] = ds[ds[p]];      // path compression, aproxima o no filho do no que eh pai no maior nivel
        return findRoot(ds[p]);
    }


    public void run() throws Exception {
        union(4,3);
        union(3,8);
        union(6,5);
        union(9,4);
        union(2,1);
        System.out.println(isConnected(8,9));
        System.out.println(isConnected(5,4));
        union(5,0);
        union(7,2);
        union(6,1);
        union(7,3);
    }

    public static void main(String[] args) {
        WeightQuickUnionPathCompression wqupc = new WeightQuickUnionPathCompression(10);
        try {
            wqupc.run();
        } catch (Exception e) {}
        System.out.println(wqupc);
    }
}
