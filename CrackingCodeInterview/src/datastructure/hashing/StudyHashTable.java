package datastructure.hashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by C_Luc on 26/07/2017.
 */

public class StudyHashTable {


    static class HashingBRCharSequence<T extends CharSequence> implements CustomHashtable.Hashing<T> {
        @Override
        public int encode(T sequence) {
            int code = 1;
            String charSequence = (String) sequence;
            for(char c : charSequence.toCharArray()) {
                //code *= 31 + c;         // code * (31 + c)
                code = code * 31 + c;
            }
            return code;
        }

        @Override
        public int computeIndex(T sequence, int modulo) {
            int code  = encode(sequence);                   // encode so retornara um valor negativo se o valor de um inteiro for excedido
            return ((code < 0) ? -code : code) % modulo;
        }
    }

    static class CustomHashtable<T> {

        public interface Hashing<T> {
            public int encode(T object);
            public int computeIndex(T object, int modulo);
        }

        private List<List<T>> table;
        private Hashing<T> hashing;
        private static final int MIN_SIZE = 26;

        private int size;

        public CustomHashtable(Hashing<T> hashing) {
            this.hashing = hashing;
            this.table = new ArrayList<>();
            for(int i=0; i<MIN_SIZE; i++)
                table.add(new LinkedList<>());
        }

        public List<T> getList(T object) {
            // gerando o codigo hash para esse elemento
            int code = hashing.computeIndex(object, table.size());
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
            this.size++;
        }

        public void delete(T object) {
            if(!contains(object))
                return;
            List<T> list = getList(object);
            list.remove(object);
            this.size--;
        }

        public boolean contains(T object) {
            List<T> list = getList(object);
            return list.contains(object);
        }

        public List<T> getAll() {
            List<T> all = new ArrayList<>();
            for(int i=0; i<table.size(); i++) {
                all.addAll(table.get(i));
            }
            return all;
        }

        public List<T> list() {
            return  null;
        }

        public int size() {
            return this.size;
        }

        public void printDispersion() {
            for(List<T> list : table) {
                System.out.printf("%d [", list.size());
                for(T t : list) {
                    System.out.printf("*");
                }
                System.out.println("]");
            }
        }
    }

    public static void use1() {
        HashingBRCharSequence<String> applyHashString = new HashingBRCharSequence();
        CustomHashtable<String> customHashtable = new CustomHashtable(applyHashString);

        for(int i=0; i<4000; i++) {
            customHashtable.add(String.valueOf(i));
        }

        customHashtable.printDispersion();

        System.out.println(applyHashString.computeIndex("123", 26));
        System.out.println(applyHashString.computeIndex("312", 26));
    }

    public static void main(String[] args) {
        use1();
    }
}
