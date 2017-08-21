/**
 * Created by r028367 on 14/08/2017.
 */
public class SparseM1 {

    static final int mat[][] =
    {
          {0 , 0 , 3 , 0 , 4 }
        , {0 , 0 , 5 , 7 , 0 }
        , {0 , 0 , 0 , 0 , 0 }
        , {0 , 2 , 6 , 0 , 0 }
    };

    public static int nonZeros() {
        int counter = 0;
        for(int i=0; i<mat.length; i++)
            for (int j=0; j<mat[i].length; j++)
                if(mat[i][j] != 0)
                    counter++;
        return counter;
    }

    private static void dump(int [][] mat) {
        for(int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[i].length; j++) {
                System.out.printf("%d ", mat[i][j]);
            }
            System.out.println("");
        }
    }

    public static int [][] sparseMatrixV1() {
        int [][] compact = new int[3][nonZeros()];
        int k = 0;
        for(int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[i].length; j++) {
                if(mat[i][j] != 0) {
                    compact[0][k] = i;
                    compact[1][k] = j;
                    compact[2][k] = mat[i][j];
                    k++;
                }
            }
        }
        return compact;
    }


    public static void main(String[] args) {
        dump(sparseMatrixV1());
    }

}
