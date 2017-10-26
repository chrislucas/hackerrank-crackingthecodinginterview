package sol;


/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/problem
 * */
public class TestBST {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {this.data = data;}
        Node() {}

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    /**
     * Ideia
     * */
    public static boolean checkBST(Node root) {
        if(root == null) {
            return true;
        }
        int dc = root.data;
        if(root.left != null) {
            int dl = root.left.data;
            return checkBST(root.left) &&  (dl < dc);
        }
        else if(root.right != null) {
            int dr = root.right.data;
            return checkBST(root.right) && (dr > dc);
        }
        return true;
    }

    private static boolean preorderChecker(Node root) {
        if(root != null) {
            preorder(root.left);
            preorder(root.right);
        }
        return true;
    }


    public static Node root;

    public static void add(int data) {
       root = add(root, data);
    }

    public static Node add(Node node, int data) {
        if(node == null) {
            node = new Node();
            node.data = data;
            return node;
        }
        else {
            if(node.data > data)
                node.left = add(node.left, data);
            else
                node.right = add(node.right, data);
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
            preorder(root.left);
            preorder(root.right);
        }
    }


    public static void test() {
        add(8);
        add(6);
        add(10);
        add(4);
        add(7);
        add(9);
        add(11);
    }

    public static void test2() {
        root            = new Node(8);
        root.left       = new Node(10);
        root.right      = new Node(6);
        root.left.left  = new Node(11);
        root.right.right = new Node(3);
    }

    public static void test3() {
        root            = new Node(4);
        root.left       = new Node(2);
        root.right      = new Node(5);
        root.left.left  = new Node(3);
        root.right.right = new Node(1);
    }

    public static void test4() {
        root             = new Node(4);
        root.left        = new Node(2);
        root.right       = new Node(7);
        root.left.left   = new Node(1);
        root.left.right = new Node(3);
        root.left.left.left= new Node(0);
        root.right.left = new Node(6);
        root.right.right = new Node(8);
    }


    public static void main(String[] args) {
        test4();
        System.out.println(checkBST(root));
    }



}
