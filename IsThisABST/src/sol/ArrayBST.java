package sol;


/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/forum
 * */
public class ArrayBST {

    public static class Node<Comparable> {
        Comparable data;
        Node left, right;

        public Node(Comparable data) {
            this.data   = data;
            this.left   = null;
            this.right  = null;
        }

        @Override
        public String toString() {
            return String.format(data.toString());
        }


    }

    public Node<Comparable> sortedArrayToBST(Comparable [] data, int s, int e) {
        if(s >= e) {
            return null;
        }
        int mid = (e-s)/2+s;
        Node node   = new Node(data[mid]);
        node.left   = sortedArrayToBST(data, s, mid-1);
        node.right  = sortedArrayToBST(data, mid+1, e);
        return node;
    }

    public void print(Node node) {
        System.out.println(node);
    }

    public void preorder(Node root) {
        if(root != null) {
            print(root);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        Comparable [] array = {10,20,30,40,50,60,70};
        ArrayBST inst = new ArrayBST();
        Node node = inst.sortedArrayToBST(array, 0, array.length);
        inst.print(node);
    }


}
