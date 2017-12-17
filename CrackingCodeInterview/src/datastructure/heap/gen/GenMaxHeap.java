package datastructure.heap.gen;


public class GenMaxHeap<Key extends Comparable> {

    private Key [] keys;
    private int controlSize;
    private static final String [] names = {
            "Adriana"
            ,"Juliana"
            ,"Andreia"
            ,"Luana"
            ,"Amara"
            ,"Alessandra"
    };

    public GenMaxHeap() {
        keys = (Key []) new Comparable[2];
        controlSize = 0;
    }

    public GenMaxHeap(int capacity) {
        keys = (Key []) new Comparable[capacity];
        controlSize = 0;
    }

    public GenMaxHeap(Key [] sample) {
        keys = (Key []) new Comparable[sample.length];
        build(sample);
    }

    public void build(Key []  sample) {
        controlSize = sample.length;
        for (int i = 0; i<controlSize ; i++)
            keys[i] = sample[i];
        for (int i = (controlSize-1)/2; i>=0 ; i--)
            fixTopDown(i);
    }

    public boolean lessThan(int p, int q) {
        return keys[p].compareTo(keys[q]) < 0;
    }

    private int parent(int index) {
        return (index-1)/2;
    }

    public void swap(int p, int q) {
        Key aux = keys[p];
        keys[p] = keys[q];
        keys[q] = aux;
    }

    public void add(Key key) {
        if(controlSize == keys.length)
            resize(controlSize * 2);
        keys[controlSize++] = key;
        fixBottomUp();
    }

    public void resize(int capacity) {
        Key [] data = (Key []) new Comparable[capacity];
        for (int i = 0; i < controlSize; i++)
            data[i] = keys[i];
        keys = data;
    }

    public void fixBottomUp() {
        for(int k=controlSize-1; k>0; k=parent(k)) {
            int p = parent(k);
            if(lessThan(p, k))
                swap(p, k);
        }
    }

    public void fixTopDown(int k) {
        while (2*k+1<controlSize) {
            int l = 2*k+1;
            if(l < controlSize && lessThan(l, l+1))
                l++;
            if(lessThan(l, k))
                break;
            swap(l, k);
            k = l;
        }
    }

    public Key remove() {
        if(controlSize == 0)
            return null;
        Key max = keys[0];
        swap(0, controlSize--);
        fixTopDown(0);
        keys[controlSize+1] = null;
        if(controlSize > 0 && controlSize == (keys.length)/4)
            resize(controlSize/2);
        return max;
    }

    public static  void test() {
        GenMaxHeap<Comparable<String>> gen = new GenMaxHeap<>();
        for(String name : names)
            gen.add(name);
        for(int i=0; i<gen.controlSize; i++)
            System.out.printf("%s ", gen.keys[i]);
        System.out.println("");
    }

    public static void test2() {
        GenMaxHeap<Comparable<String>> gen = new GenMaxHeap<>(names);
        for(int i=0; i<gen.controlSize; i++)
            System.out.printf("%s ", gen.keys[i]);
        System.out.println("");
    }

    public static void test3() {
        GenMaxHeap<Comparable<Integer>> gen = new GenMaxHeap<>();
        Integer [] ints = {3,2,7,1,8,4,10,16,12};
        for(int i : ints)
            gen.add(i);
        for(int i=0; i<gen.controlSize; i++)
            System.out.printf("%s ", gen.keys[i]);
        System.out.println("");
    }

    public static void main(String[] args) {
        //test();
        test3();
    }
}
