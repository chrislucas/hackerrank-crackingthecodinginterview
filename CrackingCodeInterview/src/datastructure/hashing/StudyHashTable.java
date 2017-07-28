package datastructure.hashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by C_Luc on 26/07/2017.
 */

public class StudyHashTable {

    static class Hashingimpl1<T> implements CustomHashtable.Hashing<T> {
        @Override
        public int encode(T object) {
            return 0;
        }
    }

    static class CustomHashtable<T> {

        public interface Hashing<T> {
            public int encode(T object);
        }

        private List<List<T>> table;
        private Hashing<T> hashing;
        private static final int MIN_SIZE = 26;

        public CustomHashtable(Hashing<T> hashing) {
            this.table = new ArrayList<>();
            for(int i=0; i<MIN_SIZE; i++)
                table.add(new LinkedList<>());
        }

        public List<T> getList(T object) {
            // gerando o codigo hash para esse elemento
            int code = hashing.encode(object);
            // pegando na tabela usando o codigo do elemento que se quer adicionar
            List<T> list = table.get(code);
            return list;
        }

        public void add(T object) {
            if(contains(object))
                return;
            List<T> list = getList(object);
            // adicionar o elemento a lista certa
            list.add(object);
        }

        public void delete(T object) {
            if(!contains(object))
                return;
            List<T> list = getList(object);
            list.remove(object);
        }

        public boolean contains(T object) {
            List<T> list = getList(object);
            return list.contains(object);
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
