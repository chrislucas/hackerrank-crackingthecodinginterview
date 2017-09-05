import java.util.*;

/**
 * Created by r028367 on 05/09/2017.
 */
public class InterseccaoBuscaBinaria {

    Comparator<String> comparatorString = new Comparator<String>() {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    };

    static Comparator<Integer> comparatorInteger = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return a-b;
        }
    };

    public static <T extends Comparable> T binarySearch(List<T> list, T target) {
        T element = null;
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            T current = list.get(mid);
            if(current.compareTo(target) == 0) {
                element = current;
                break;
            }
            else {
                boolean lessThan = current.compareTo(target) < 0;
                if(lessThan) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return element;
    }

    public static <T extends Comparable>  List<T> addElementIsNotBothSets(List<T> al, List<T> bl) {
        List<T> copy = new ArrayList<>(bl);
        Collections.sort(bl);
        for(T element : al) {
            T rs = binarySearch(bl, element);
            if(rs != null) {
                Iterator<T> iterator = copy.iterator();
                while (iterator.hasNext()) {
                    T t = iterator.next();
                    if(t.compareTo(rs) == 0) {
                        iterator.remove();
                        // se for garantido que nao ha elementos repetidos podemos quebrar o loop
                        break;
                    }
                }
            }
            else {
                copy.add(element);
            }
        }
        return copy;
    }

    /**
     * Elementos da lista 'a' que estao na lista 'b'
     *
     * */
    public static <T extends Comparable>  List<T> interssectBinarySearch(List<T> al, List<T> bl) {
        List<T> list = new ArrayList<T>();
        /*
        if(bl.get(0) instanceof Integer)
            bl.sort((Comparator<? super T>) comparatorInteger);
        */
        Collections.sort(bl);

        for(T element : al) {
            T rs = binarySearch(bl, element);
            if(rs != null)
                list.add(rs);
        }
        return list;
    }

    public static <T extends Comparable> boolean interssectEquals(List<T> al, List<T> bl) {
        List<T> cl = interssectBinarySearch(al, bl);
        return /*cl.size() == al.size() &&*/ cl.size() == bl.size();
    }

    public static <T> ArrayList<T> toList(T [] array) {
        ArrayList<T> a = new ArrayList<T>();
        for(T t : array)
            a.add(t);
        return a;
    }

    public static void testIntersectBSearchInteger() {
        Integer [] a = {4,3,2,1,-10};
        ArrayList<Integer> al = toList(a);
        Integer [] b = {7,-10,12,15,32,1,5,7,9};
        ArrayList<Integer> bl = toList(b);
        testInterssectBinarySearch(al, bl);
    }

    public static void testIntersectBSearchString() {
        String [] a = {
             "Marta"
            ,"Adelaide"
            ,"Maria"
            ,"Priscila"
        };
        List<String> al = toList(a);
        String [][] b = {
            {
                "Marta", "Maria", "Adelaide", "Lorena", "Joana", "Juliana", "Priscila"
            }
            ,{
                "Marta", "Maria", "Adelaide", "Priscila"
            }
        };
        List<String> bl = toList(b[1]);
        testInterssectBinarySearch(al, bl);
    }

    public static void testRemoveIntersectString() {
        String [] a = {
             "Marta"
            ,"Adelaide"
            ,"Maria"
            ,"Priscila"
            ,"Camila"
            ,"Natalia"
        };
        List<String> al = toList(a);
        String [][] b = {
             {"Marta","Maria","Adelaide","Priscila","Tamara"}
            ,{}
        };
        List<String> bl = toList(b[1]);
        testRemoveInterssect(al, bl);
    }

    public static <T extends Comparable> void testInterssectBinarySearch(List<T> al, List<T> bl) {
        List<T> list = interssectBinarySearch(al, bl);
        for(T i: list) {
            System.out.printf("%s ", i.toString());
        }
        System.out.printf("\n%s\n", list.size() == bl.size() ? "As listas sao iguaus" : "As listas não sãos iguais");
    }

    public static  <T extends Comparable> void testRemoveInterssect(List<T> al, List<T> bl) {
        List<T> list = addElementIsNotBothSets(al, bl);
        for(T i: list) {
            System.out.printf("%s ", i.toString());
        }
        System.out.printf("\n%s\n", list.size() == bl.size() ? "As listas sao iguaus" : "As listas não sãos iguais");
    }

    public static <T extends Comparable> boolean equalsArray(List<T> al, List<T> bl) {
        List<T> list = interssectBinarySearch(al, bl);
        return list.size() == bl.size();
    }



    public static void main(String[] args) {
        testRemoveIntersectString();
    }
}
