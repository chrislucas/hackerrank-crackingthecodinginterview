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
        for (int i = n/2-1; i>=0 ; i--)
            minHeapRec(i, n);
        for (int i = n-1; i>=0 ; i--) {
            swap(0, i);
            minHeapRec(0, i);
        }
    }

    public void increase() {
        for (int i = n/2-1; i>=0 ; i--)
            maxHeap(i, n);
        for (int i = n-1; i>0 ; i--) {
            swap(0, i);
            maxHeap(0, i);
        }
    }

    private boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    private boolean moreThan(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    private void swap(int p, int q) {
        Comparable aux = arrayData[p];
        arrayData[p] = arrayData[q];
        arrayData[q] = aux;
    }

    private void minHeap(int index, int size) {
        int smallest = -1;
        while (true) {
            int leftChild  = 2*index+1;
            int rightChild = leftChild+1;
            smallest = leftChild < size && lessThan(arrayData[leftChild], arrayData[index]) ? leftChild : index;
            smallest = rightChild < size && lessThan(arrayData[rightChild], arrayData[smallest]) ? rightChild : smallest;
            if(smallest != index) {
                swap(index, smallest);
                index = smallest;
            }
            else
                break;
        }
    }

    public void minHeapRec(int index, int size) {
        int leftChild  = 2*index+1;
        int rightChild = leftChild+1;
        int smallest = leftChild < size && lessThan(arrayData[leftChild], arrayData[index]) ? leftChild : index;
        smallest = rightChild < size && lessThan(arrayData[rightChild], arrayData[smallest]) ? rightChild : smallest;
        if(smallest != index) {
            swap(index, smallest);
            minHeapRec(smallest, size);
        }
    }


    public void maxHeap(int index, int size) {
        int greatest;
        while (true) {
            int leftChild  = 2*index+1;
            int rightChild = leftChild+1;
            greatest = leftChild < size && moreThan(arrayData[leftChild], arrayData[index]) ? leftChild : index;
            greatest = rightChild < size && moreThan(arrayData[rightChild], arrayData[greatest]) ? rightChild : greatest;
            if(greatest != index) {
                swap(index, greatest);
                index = greatest;
            }
            else
                break;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<n; i++)
            stringBuilder.append(String.format("%s ", arrayData[i]));
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Integer [][] array = {
            {3,1,4,5,0,32,-1}
            ,{7,8,3,1,2,4,7,12,-15}
            ,{10,9,8,7,6,5,4,3,2,1}
        };
        int idx = 2;
        Heapsort heapsort = new Heapsort(array[idx]);
        heapsort.decrease();
        System.out.println(heapsort.toString());

        heapsort = new Heapsort(array[idx]);
        heapsort.increase();
        System.out.println(heapsort.toString());
    }
}
