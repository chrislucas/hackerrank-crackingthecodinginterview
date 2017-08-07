package datastructure.hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C_Luc on 30/07/2017.
 */
public class StudyDinHashtable {



    public static class CustomHashtable<T> {
        public interface Hashing<T> {
            public int encode(T element);
            public int computaIndexTable(T element, int module);
        }

        private List<List<T>> table;
        private Hashing<T> hashing;
        private static final int MIN_SIZE = 3;


        private double weightControl;
        private int elements, lists;

        public CustomHashtable(Hashing<T> hashing) {
            this.hashing = hashing;
            table = new ArrayList<>();
            for(int i=0; i<MIN_SIZE; i++)
                table.add(new ArrayList<>());
            lists = MIN_SIZE;
        }

        // esse metodo deve ser chamado quando adicionamos um elemento a tabela
        private void updateWeightControl() {
            this.weightControl = elements / lists;
        }

        public int getElements() {
            return elements;
        }

        public void setElements(int elements) {
            this.elements = elements;
        }

        private boolean overWeight() {
            return this.weightControl >= 0.75;
        }
    }

}
