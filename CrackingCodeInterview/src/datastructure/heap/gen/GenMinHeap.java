package datastructure.heap.gen;

public class GenMinHeap<Key extends Comparable> {

    private Key [] array;
    private int controlSize;

    public GenMinHeap() {
        array = (Key []) new Object[1];
        controlSize = 0;
    }

    public GenMinHeap(int capacity) {
        array = (Key []) new Object[capacity];
        controlSize = 0;
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
        array[controlSize] = key;
        bottomUp(controlSize);
        controlSize++;
    }

    public void topDown(int k) {
        // 2*k+1  no da esquerda do indice k
        while(2*k+1 <= controlSize) {
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

    public void bottomUp(int controlSize) {
        for(int k=controlSize/2; moreThan(k, parent(k)); k=parent(k))
            swap(k, parent(k));
    }


    public void resize(int q) {
        Key [] newArray =(Key[]) new Object[q];
        for(int i=0; i<controlSize; i++)
            newArray[i] = array[i];
        array = newArray;
    }




    public static  void test() {
        String [] names = {
            "Adriana"
            ,"Juliana"
            ,"Andreia"
            ,"Luana"
            ,"Amara"
            ,"Antonia"
        };

        GenMinHeap<String> genMinHeap = new GenMinHeap<>();
        for(String name : names) {
            genMinHeap.add(name);
        }
    }

    public static void main(String[] args) {

    }

}
