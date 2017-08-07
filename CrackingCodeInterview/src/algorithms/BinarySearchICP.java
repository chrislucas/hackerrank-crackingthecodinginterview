package algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by r028367 on 02/08/2017.
 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor
 */
public class BinarySearchICP {

    public static class IceCream{
        int id, cost;
        public IceCream(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    public static Comparator<IceCream> comparator = new Comparator<IceCream>() {
        @Override
        public int compare(IceCream ic1, IceCream ic2) {
            return ic1.cost - ic2.cost;
        }
    };


    public static int binarySearch(int cost, int linf, int lsup, IceCream [] iceCreams) {
        while(linf<=lsup) {
            int mid = (lsup-linf)/2 + linf;
            if(iceCreams[mid].cost == cost) {
                return iceCreams[mid].id;
            }
            else if(iceCreams[mid].cost > cost) {
                lsup = mid-1;
            }
            else {
                linf = mid+1;
            }
        }
        return -1;
    }

    public static void solver1(IceCream [] iceCreams, int m) {
        Arrays.sort(iceCreams, comparator);
        int [] ans = new int[2];
        int linf = 0, lsup = iceCreams.length - 1;
        for(int i=0; i<iceCreams.length; i++) {
            int ithCost    = iceCreams[i].cost;
                int complement = m - ithCost;
            // como os valoes de custo sao ordenados do menor para o maior
            // quando estiver procurando pelo valor que somado ao ith custo chegue ao valor m
            // a partir do ith custo nao vou ter valores menores do que ithCost, no minimo
            // iguais ou maiores. Logo o valor de completmento deve ser maior ou igual
            // exemplo m=24 i = 0 ith Cost = 12. complement  = 12
            // so valores iguais a 12 resolvem o problema
            // se m = 23 i=0 ith Cost = 13 o complemento = 11, o problema que eh que pela ordenacao
            // nao teremos valores para os elementos ith+1 menores que 13
            if(complement >= ithCost) {
                int idx = binarySearch(complement, i+1, lsup, iceCreams);
                if(idx > -1) {
                    int min = iceCreams[i].id < idx ? iceCreams[i].id : idx;
                    int max = iceCreams[i].id < idx ? idx: iceCreams[i].id ;
                    System.out.printf("%d %d\n", min, max);
                    break;
                }
            }
        }
    }

    /**
     * Two sums
     *
     * Usando implementacao de mapas do java, podemos chegar a resultados inesperados
     * pelo fato de nao conseguirmos guardar valores de chave repetidos
     *
     *
     * */
    public static void solver2(IceCream [] iceCreams, int m) {
        Arrays.sort(iceCreams, comparator);
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for(IceCream iceCream : iceCreams) {
            table.put(iceCream.cost, iceCream.id);
        }

        for(int idx=0; idx<iceCreams.length; idx++) {
            int complement = m - iceCreams[idx].cost;
            if(table.containsKey(complement)) {
                int id = table.get(complement);
                System.out.printf("%d %d\n", Math.min(iceCreams[idx].id, id), Math.max(iceCreams[idx].id, id));
                break;
            }
        }
    }

    public static void solver3(IceCream [] iceCreams, int m) {
        Arrays.sort(iceCreams, comparator);
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for(int idx=0; idx<iceCreams.length; idx++) {
            int complement = m - iceCreams[idx].cost;
            if(table.containsKey(complement)) {
                int id = table.get(complement);
                System.out.printf("%d %d\n", Math.min(iceCreams[idx].id, id), Math.max(iceCreams[idx].id, id));
                break;
            }
            table.put(iceCreams[idx].cost, iceCreams[idx].id);
        }
    }

    public static void solver4(IceCream [] iceCreams, int m) {
        /**
         * Se a chave ja existir, adicione mais um item a lista
         * Entrada interessante
         * 8
         * 8
         * 2 1 9 4 4 56 90 3
         * */
        Map<Integer, List<Integer>> multimap = new HashMap<>();
        Arrays.sort(iceCreams, comparator);
        for(int idx=0; idx<iceCreams.length; idx++) {
            if(multimap.containsKey(iceCreams[idx].cost)) {
                List<Integer> list = multimap.get(iceCreams[idx].cost);
                list.add(iceCreams[idx].id);
                multimap.put(iceCreams[idx].cost, list);
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(iceCreams[idx].id);
                multimap.put(iceCreams[idx].cost, list);
            }
        }

        for(int idx=0; idx<iceCreams.length; idx++) {
            int complement = m - iceCreams[idx].cost;
            int curId = iceCreams[idx].id;
            if(multimap.containsKey(complement)) {
                List<Integer> list = multimap.get(complement);
                for(int id : list) {
                    if(id == curId)
                        continue;
                    System.out.printf("%d %d\n", Math.min(iceCreams[idx].id, id), Math.max(iceCreams[idx].id, id));
                    break;
                }
                break;
            }
        }
    }

    public static void reader() {
        IceCream [] iceCreams;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for(;t>0;t--) {
                int m = Integer.parseInt(reader.readLine());
                int n = Integer.parseInt(reader.readLine());
                StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                iceCreams = new IceCream[n];
                for(int idx=0; idx<n; idx++) {
                    iceCreams[idx] = new IceCream(idx+1, Integer.parseInt(tk.nextToken()));
                }
                solver4(iceCreams, m);
            }
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        reader();
    }

}
