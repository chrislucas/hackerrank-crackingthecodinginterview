package datastructure;

public class Heapsort {

    private Comparable [] arrayData;
    private int n;
    public Heapsort(Comparable[] array) {
        this.arrayData = array;
        this.n = array.length;
    }

    /**
     * Min heap tem a propriedade de manter o primeiro indice com o menor valor.
     * Vamos aproveitar isso para ordenar de forma descrente
     * */
    public void decrease() {
        for (int i = n/2-1; i>-1 ; i--)
            minHeapIt(i, n);
        int sz = n-1;
        while (sz > 0) {
            swap(0, sz);
            minHeapIt(1, sz);
            sz--;
        }
    }

    public void increase() {

    }

    public boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public void swap(int p, int q) {
        Comparable aux = arrayData[p];
        arrayData[p] = arrayData[q];
        arrayData[q] = aux;
    }

    public void minHeapIt(int index, int size) {
        int smallest = -1;
        while (index != smallest) {
            int leftChild  = 2*index+1;
            int rightChild = leftChild++;
            smallest = leftChild < size && lessThan(arrayData[leftChild], arrayData[index]) ? leftChild : index;
            smallest = rightChild < size && lessThan(arrayData[rightChild], arrayData[index]) ? rightChild : smallest;
            if(smallest != index)
                swap(smallest, index);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<n; i++)
            stringBuilder.append(String.format("%s ", arrayData[i]));
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Integer [] array = {3,1,4,5,0,32,-1};
        Heapsort heapsort = new Heapsort(array);
        heapsort.decrease();
        System.out.println(heapsort.toString());
    }
}
