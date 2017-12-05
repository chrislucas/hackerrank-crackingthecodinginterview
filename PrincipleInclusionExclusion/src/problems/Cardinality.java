package problems;

public class Cardinality {
    private static void generateSubsets(int n) {
        int limit = 1 << n;
        for(int i=0; i<limit; i++) {
            for (int j=0; j<n ; j++) {
                System.out.printf("%d", (i&(1<<j))>0 ? 1 : 0);
            }
            System.out.println();
        }
    }

    public static void generateSubsets(int [] n) {
        int limit = 1 << n.length;
        for(int i=1; i<limit; i++) {
            for (int j=0; j<n.length ; j++) {
                if((i&(1<<j))>0 )
                    System.out.printf("%d ", n[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //generateSubsets(3);
        int [] array = {1,2,13};
        generateSubsets(array);
    }
}
