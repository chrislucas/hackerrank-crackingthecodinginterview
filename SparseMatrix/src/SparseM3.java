import java.util.*;

/**
 * Created by r028367 on 14/08/2017.
 */
public class SparseM3 {

    public static final int mat[][] =
    {
            {0 , 0 , 3 , 0 , 4 }
        , {0 , 0 , 5 , 7 , 0 }
        , {0 , 0 , 0 , 0 , 0 }
        , {0 , 2 , 6 , 0 , 0 }
    };

    // com o linkedhashmap a ordem de insercao eh mantida
    static Map<Map.Entry<Integer, Integer>, Integer> sparse = new LinkedHashMap<>();

    public static class Pair<K, V> implements Map.Entry<K, V> {
        private K key;
        private V val;

        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return val;
        }

        @Override
        public V setValue(V value) {
            this.val = value;
            return val;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", key, val);
        }
    }

    static void add(Map.Entry<Integer, Integer>  pair, int val) {
        sparse.put(pair, val);
    }

    static void convert() {
        for(int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[i].length; j++) {
                if(mat[i][j] != 0) {
                    Map.Entry<Integer, Integer> pair = new Pair<>(i, j);
                    add(pair, mat[i][j]);
                }
            }
        }
        dumb();
    }

    private static void dumb() {
        for(Map.Entry<Map.Entry<Integer, Integer>, Integer> pair: sparse.entrySet()) {
           Pair<Integer, Integer> pos = (Pair<Integer, Integer>) pair.getKey();
           Integer val = pair.getValue();
           System.out.printf("%s %s", pos, val);
        }
    }


    public static void main(String[] args) {
        convert();
    }

}
