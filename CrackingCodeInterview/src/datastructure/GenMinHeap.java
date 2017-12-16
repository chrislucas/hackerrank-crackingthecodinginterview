package datastructure;

public class GenMinHeap<Key> {

    private Key [] array;
    private int controlSize;

    public class Data {
        Key value;
    }

    public GenMinHeap() {
        array = (Key []) new Object[1];
        controlSize = 0;
    }

    public GenMinHeap(int capacity) {
        array = (Key []) new Object[capacity];
        controlSize = 0;
    }

    public static void main(String[] args) {

    }

}
