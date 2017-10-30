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
     * Ideia furada
     * */
    public static boolean checkBST(Node root) {
        boolean test = true;
        if(root == null) {
            return test;
        }
        int rootData = root.data;
        if(root.left != null) {
            int ldata = root.left.data;
            test = checkBST(root.left) && (ldata < rootData);
        }
        if(root.right != null) {
            int rdata = root.right.data;
            test = checkBST(root.right) && (rdata > rootData);
        }
        return test;
    }

    private static boolean checkBSTV1(Node root) {
        int [] minmax = { Integer.MAX_VALUE, Integer.MIN_VALUE};
        getMinMax(root, minmax);
        return isBST(root, minmax[0], minmax[1]);
    }

    private static boolean checkBSTV2(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBST(Node root, int min, int max) {
        if(root == null)
            return true;
        if(root.data >= max || root.data <= min)
            return false;
        boolean a = isBST(root.left, min, root.data);
        boolean b = isBST(root.right, root.data, max);
        return a & b;
    }

    private static boolean checkBSTV3(Node root) {
        if(root == null)
            return  true;

        boolean a = checkBSTV3(root.left);
        boolean b = checkBSTV3(root.right);

        return a & b;
    }

    private static void getMinMax(Node root, int data []) {
        if(root!=null) {
            if(root.left != null && data[0] > root.left.data)
                data[0] = root.left.data;
            getMinMax(root.left, data);
            if(root.right != null && data[1] < root.right.data)
                data[1] = root.right.data;
            getMinMax(root.right, data);
        }
    }

    private static boolean inOrderChecker(Node current, Node ancestor) {
        if(current != null) {
            if(!inOrderChecker(current.left, current))
                return false;
            if(ancestor != null && current.data <= ancestor.data) {
                return false;
            }
            return inOrderChecker(current.right, current);
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
        root                = new Node(3);
        root.left           = new Node(2);
        root.right          = new Node(6);
        root.left.left      = new Node(1);
        root.left.right     = new Node(4);
        root.right.left     = new Node(5);
        root.right.right    = new Node(7);
    }


    public static void main(String[] args) {
        test();
        System.out.println(checkBSTV2(root));
        System.out.println(inOrderChecker(root, null));
    }



}
