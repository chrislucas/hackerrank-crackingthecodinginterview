import java.util.ArrayList;
import java.util.List;

/**
 * Created by r028367 on 14/08/2017.
 */
public class SparseM2 {

    public static class Data<T> {
        T value;
        int column, row;
        public Data(T value, int row, int column) {
            this.value  = value;
            this.row    = row;
            this.column = column;
        }

        @Override
        public String toString() {
            return String.format("[%d %d] %s", row, column, value);
        }
    }

    private static List<List<Data>> sparseMatrix;

    static final int mat[][] =
    {
          {0 , 0 , 3 , 0 , 4 }
        , {0 , 0 , 5 , 7 , 0 }
        , {0 , 0 , 0 , 0 , 0 }
        , {0 , 2 , 6 , 0 , 0 }
    };

    static void init() {
        sparseMatrix = new ArrayList<>();
        for(int i=0; i<mat.length; i++) {
            sparseMatrix.add(new ArrayList<Data>());
        }
    }


    public static <T> void add(int i, Data<T> data) {
        sparseMatrix.get(i).add(data);
    }


    static void convert() {
        for(int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[i].length; j++) {
                if(mat[i][j] != 0) {
                    Data<Integer> data = new Data<>(mat[i][j], i, j);
                    add(i, data);
                }
            }
        }
        dumb();
    }

    static void dumb() {
        for(List<Data> list : sparseMatrix) {
            for(Data data : list) {
                System.out.printf("%s ", data);
            }
            if(list.size() > 0)
                System.out.println("");
        }
    }

    public static void main(String[] args) {
        init();
        convert();
    }


}
