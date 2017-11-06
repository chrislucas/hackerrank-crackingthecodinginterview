package xplore.ds;

public class QuickUnionPathCompression {
    public int ds[];
    public int count;
    public QuickUnionPathCompression(int size) {
        ds = new int[size];
        for(int i=0; i<size; i++) {
            ds[i] = i;
        }
        count = size;
    }

    public boolean isValid(int p) {
        return p < ds.length - 1 || p > -1;
    }

    public boolean isConnected(int p, int q) throws Exception{
        if(! isValid(p) || ! isValid(q))
            throw new Exception("Invalid Range");
        return  findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) throws Exception {
        if(! isValid(p) || ! isValid(q))
            throw new Exception("Invalid Range");
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if(rootP == rootQ)
            return;
        ds[rootP] = rootQ;
        count--;
    }

    public int findRoot(int p) throws Exception {
        if(! isValid(p))
            throw new Exception("Invalid Range");
        if(p == ds[p])
            return p;
        ds[p] = ds[ds[p]];
        return findRoot(ds[p]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int id : ds) {
            sb.append(String.format("%d ", id));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
