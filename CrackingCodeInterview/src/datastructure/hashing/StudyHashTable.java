package datastructure.hashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by C_Luc on 26/07/2017.
 */

public class StudyHashTable {

    static class MHashtable<T> {

        public interface Hashing<T> {
            public int fnDisperse(T object);
        }

        private List<List<T>> table;
        private Hashing<T> hashing;
        private static final int MIN_SIZE = 26;

        public MHashtable(Hashing<T> hashing) {
            this.table = new ArrayList<>();
            for(int i=0; i<MIN_SIZE; i++)
                table.add(new LinkedList<>());
        }

        public void add(T object) {

        }

        public void delete(T object) {

        }

        public boolean contains(T object) {
            return false;
        }

        public List<T> list() {
            return  null;
        }

        public int size() {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
