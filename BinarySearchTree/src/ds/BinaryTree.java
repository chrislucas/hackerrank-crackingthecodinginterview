package ds;

/**
 * Created by r028367 on 10/08/2017.
 */
public class BinaryTree {

    public static class Node implements Comparable<Node> {
        Comparable data;
        Node left, right;
        public Node(Comparable data) {
            this.data   = data;
            this.left   = null;
            this.right  = null;
        }

        @Override
        public int compareTo(Node that) {
            return data.compareTo(that.data);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static Node root;
    public static long size = 0;

    public static void add(Comparable data) {
        root = addRec(data, root);
        size++;
    }

    private static Node addRec(Comparable data, Node node) {
        if(node == null) {
            node = new Node(data);
            return node;
        }
        else {
            if( data.compareTo(root.data) < 0) {
                node.left = addRec(data, node.left);
            }
            else {
                node.right = addRec(data, node.right);
            }
        }
        return node;
    }

    public static void inorder() {inorder(root);}
    public static void postorder() {postorder(root);}
    public static void preorder() {preorder(root);}

    private static void inorder(Node root) {
        if(root != null) {
            inorder(root.left);
            System.out.println(root);
            inorder(root.right);
        }
    }
    private static void postorder(Node root) {
        if(root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root);

        }
    }
    private static void preorder(Node root) {
        if(root != null) {
            System.out.println(root);
            postorder(root.left);
            postorder(root.right);
        }

    }


    public static void main(String[] args) {
        BinaryTree.add(1);
        BinaryTree.add(8);
        BinaryTree.add(10);
        BinaryTree.add(7);
        BinaryTree.add(5);
        BinaryTree.add(4);
        BinaryTree.add(13);
        BinaryTree.add(2);

        BinaryTree.inorder();
        BinaryTree.postorder();
    }
}
