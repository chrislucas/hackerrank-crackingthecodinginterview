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

    private Node root;
    private Node add(Node root, Point2D p) {
        return root;
    }

    public  void test() {
        Point2D [] points2D = {
             new Point2D(3, 6)
            ,new Point2D(17, 15)
            ,new Point2D(13, 15)
            ,new Point2D(6, 12)
            ,new Point2D(9, 1)
            ,new Point2D(2, 7)
            ,new Point2D(10, 19)
        };

        for(Point2D p: points2D)
            add(root, p);

    }


    public static void main(String[] args) {

    }

}
