package datastructure;

import java.util.Comparator;

public class MinHeap {

    public static class Data implements Comparable<Data>  {
        public int priority;
        public Data(int priority) {
            this.priority = priority;
        }
        @Override
        public int compareTo(Data p) { return priority - p.priority; }
        @Override
        public String toString() {
            return String.format("Prioridade %d", priority);
        }
    }

    public MinHeap() {
        arrayData =  new Data[2];
        controlSize = 0;
    }

    public MinHeap(int capacity) {
        arrayData = new Data[capacity];
        controlSize = 0;
    }

    public class ComparatorPLessThanQ implements Comparator<Data> {
        @Override
        public int compare(Data p, Data q) {
            return p.priority - q.priority;
        }
    }

    private Data [] arrayData;
    private int controlSize, position;

    public boolean lessThan(Data p, Data q) {
        return p.priority < q.priority;
    }

    public boolean moreThan(Data p, Data q) {
        return ! lessThan(p, q);
    }

    public void build(Data [] array) {
        int n = array.length;
        arrayData = new Data[n];
        controlSize = n;
        for (int i = 0; i < n ; i++)
            arrayData[i] = array[i];
        for (int i = n/2-1; i>=0 ; i--)
            minHeapIt(i);
    }

    public void swap(int p, int q) {
        Data aux = arrayData[p];
        arrayData[p] = arrayData[q];
        arrayData[q] = aux;
    }

    /**
     * Funcao que mantem as propriedades do MinHeap, verifica se o no pai do heap
     * eh menor que os seus filhos da esquerda e direita, senao for troca-os de posicao
     * */
    public void minHeapRec(int index) {
        int leftChild  = left(index);
        int rightChild = leftChild++; //right(index);
        int smallest = leftChild < controlSize && lessThan(arrayData[leftChild], arrayData[index]) ? leftChild : index;
        smallest = rightChild < controlSize && lessThan(arrayData[rightChild], arrayData[index]) ? rightChild : smallest;
        if(smallest != index) {
            swap(smallest, index);
            minHeapRec(smallest);
        }
    }

    public void minHeapIt(int index) {
        int smallest = -1;
        while (index != smallest) {
            int leftChild  = left(index);
            int rightChild = leftChild++;//right(index);
            smallest = leftChild < controlSize && lessThan(arrayData[leftChild], arrayData[index]) ? leftChild : index;
            smallest = rightChild < controlSize && lessThan(arrayData[rightChild], arrayData[index]) ? rightChild : smallest;
            if(smallest != index) {
                swap(smallest, index);
            }
        }
    }

    public int parent(int index) {return (index-1)/2;}
    public int left(int index) {return index*2+1;}
    public int right(int index) {return index*2+2;}

    public void resize(int capacity) {
        Data [] newData =  new Data[capacity];
        for (int i = 0; i <controlSize ; i++)
            newData[i] = arrayData[i];
        arrayData = newData;
    }

    public void add(Data data) {
        if(controlSize == arrayData.length)
            resize(controlSize * 2);
        arrayData[controlSize] = data;
        fix();
        controlSize++;
    }
    /**
     * Se o no pai for maior que um dos nos filhos, troque de posicao
     * */
    public void fix() {
        for (int k=controlSize-1; k>0; k=parent(k)) {
            int p = parent(k);    // indice do no pai
            int q = k;
            if(moreThan(arrayData[p], arrayData[q]))
                swap(p, q);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<controlSize; i++)
            stringBuilder.append(String.format("%d ", arrayData[i].priority));
        return stringBuilder.toString();
    }

    public void remove() {

    }

    public static void s1() {
        Data [] array  =  {
             new Data(10)
            ,new Data(9)
            ,new Data(8)
            ,new Data(7)
            ,new Data(6)
            ,new Data(5)
            ,new Data(4)
            ,new Data(3)
            ,new Data(2)
            ,new Data(1)
        };
        test(array);
    }

    public static void s2() {
        // 3  2  1  7  8  4  10  16  12
        MinHeap minHeap = new MinHeap();
        Data [] array  =  {
             new Data(3)
            ,new Data(2)
            ,new Data(1)
            ,new Data(7)
            ,new Data(8)
            ,new Data(4)
            ,new Data(10)
            ,new Data(16)
            ,new Data(12)
        };
        test(array);
    }

    public static void s3() {
        MinHeap minHeap = new MinHeap();
        Data [] array  =  {
                 new Data(3)
                ,new Data(2)
                ,new Data(1)
                ,new Data(7)
                ,new Data(8)
                ,new Data(4)
                ,new Data(10)
                ,new Data(16)
                ,new Data(12)
        };
        minHeap.build(array);
        System.out.println(minHeap.toString());
    }

    public static void test(Data [] array) {
        MinHeap minHeap = new MinHeap();
        for(Data d : array)
            minHeap.add(d);
        minHeap.fix();
        System.out.println(minHeap.toString());
    }

    public static void main(String[] args) {
        s2();
        s3();
    }
}
