package datastructure;

import java.util.Comparator;

public class MinHeap {

    public class Data  {
        public int priority;
        public Data(int priority) {
            this.priority = priority;
        }
        @Override
        public String toString() {
            return String.format("Prioridade %d", priority);
        }
    }

    public class ComparatorPLessThanQ implements Comparator<Data> {
        @Override
        public int compare(Data p, Data q) {
            return p.priority - q.priority;
        }
    }

    public boolean less(Data p, Data q) {
        return p.priority - q.priority < 0;
    }

    public Data [] arrayData;
    public int size, position;

    public void maxHeapifY(Data newData) {

    }


    public static void main(String[] args) {

    }
}
