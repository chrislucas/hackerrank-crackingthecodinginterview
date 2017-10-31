public class KDimensionalTree {


    public class Point2D {
        private int x,y;
        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Node {
        private Point2D p;
        private Node left, right;
        public Node(Point2D p) {
            this.p = p;
        }
    }


    public Node root;


    public static void main(String[] args) {

    }

}
