package datastructure.heap.gen;

public class GenMinHeap<Key extends Comparable> {

    private Key [] array;
    private int controlSize;

    public GenMinHeap() {
        array = (Key []) new Comparable[2];
        controlSize = 0;
    }

    public GenMinHeap(int capacity) {
        array = (Key []) new Comparable[capacity];
        controlSize = 0;
    }

    public GenMinHeap(Key [] sample) {
        build(sample);
    }

    public void build(Key [] sample) {
        int n = sample.length;
        array = (Key []) new Comparable[n];
        controlSize = sample.length;
        for (int i = 0; i < n; i++)
            array[i] = sample[i];
        for (int i = (n-1)/2; i >= 0 ; i--)
            topDown(i);
    }

    public boolean moreThan(int p, int q) {
        return array[p].compareTo(array[q]) > 0;
    }

    public int  parent(int k) {
        return (k-1)/2;
    }

    public void swap(int p, int q) {
        Key aux = array[p];
        array[p] = array[q];
        array[q] = aux;
    }

    public void add(Key key) {
        if(controlSize == array.length)
            resize(controlSize * 2);
        array[controlSize++] = key;
        bottomUp();
    }

    public boolean isEmpty() {
        return controlSize == 0;
    }

    /**
     * Abordagem top down: partindo do no raiz verificamos se tem algum no filho maior que o raiz
     * */
    public void topDown(int k) {
        // 2*k+1  no da esquerda do indice k
        while(2*k+1 < controlSize) {
            int l = 2*k+1;
            // se o o lado esquerdo tiver um elemento maior que o direito
            if(l < controlSize && moreThan(l, l+1))
                l++;    // pegue o indice do lado direito para testar com o no raiz e reorganizar o heap
            // se o no raiz for menor que um de seus filhos o heap esta certo
            if(!moreThan(k, l))
                break;
            swap(k, l);
            k = l;
        }
    }

    /**
     * Bottom up: Partimos de um no raiz e vamos subindo atraves dos no raizes no heap
     * verificando se a estrutura respeita a condicao de min heap
     * */
    public void bottomUp() {
        for(int k=controlSize-1; k>0; k=parent(k)) {
            int p = parent(k);
            if(moreThan(p, k))
                swap(k, p);
        }

    }

    public void resize(int q) {
        Key [] newArray =(Key[]) new Comparable[q];
        for(int i=0; i<controlSize; i++)
            newArray[i] = array[i];
        array = newArray;
    }

    /**
     * remover o menor item do heap
     * */
    public Key remove() {
        if (isEmpty())
            return null;
        Key mKey = array[0];
        swap(0, controlSize--);
        topDown(0);
        array[controlSize+1] = null;
        if(controlSize > 0 && controlSize == (array.length)/4 )
            resize((array.length)/2);
        return mKey;
    }


    public static  void test() {
        String [] names = {
            "Adriana"
            ,"Juliana"
            ,"Andreia"
            ,"Luana"
            ,"Amara"
            ,"Alessandra"
        };
        GenMinHeap<Comparable<String>> genMinHeap = new GenMinHeap<>();
        for(String name : names)
            genMinHeap.add(name);
        for(int i=0; i<genMinHeap.controlSize; i++)
            System.out.printf("%s ", genMinHeap.array[i]);
        System.out.println("");
    }

    public static void test2() {
        GenMinHeap<Comparable<Integer>> gen = new GenMinHeap<>();
        Integer [] ints = {3,2,7,1,8,4,10,16,12};
        for(int i : ints)
            gen.add(i);
        for(int i=0; i<gen.controlSize; i++)
            System.out.printf("%s ", gen.array[i]);
        System.out.println("");
    }

    public static void test3() {
        Integer [] ints = {3,2,1,7,8,4,10,16,12};
        GenMinHeap<Comparable<Integer>> gen = new GenMinHeap<>(ints);
        for(int i=0; i<gen.controlSize; i++)
            System.out.printf("%d ", gen.array[i]);
        System.out.println("");
    }

    public static void main(String[] args) {
        test();
        //test2();
        //test3();
    }

}
