package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by r028367 on 20/07/2017.
 * https://www.hackerrank.com/challenges/ctci-ransom-note
 * DONE
 * https://dzone.com/articles/hashmap-vs-treemap-vs
 */
public class HashTableRansomNote {
    //private static Map<String, Integer> table = new HashMap<>();
    //private static Map<String, Integer> table = new LinkedHashMap<>();
    private static Map<String, Integer> table = new Hashtable<>();
    public static void solver() {
        int qWordsInTable, qWordsInRansomNote;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer tk = new StringTokenizer(bufferedReader.readLine(), " ");
            qWordsInTable = Integer.parseInt(tk.nextToken());
            qWordsInRansomNote = Integer.parseInt(tk.nextToken());
            tk = new StringTokenizer(bufferedReader.readLine(), " ");
            while(tk.hasMoreTokens()) {
                String word = tk.nextToken();
                if(table.containsKey(word)) {
                    int q = table.get(word);
                    table.put(word, q+1);
                }
                else  {
                    table.put(word, 1);
                }
            }
            boolean ctn = true;
            tk = new StringTokenizer(bufferedReader.readLine(), " ");
            while(tk.hasMoreTokens()) {
                String word = tk.nextToken();
                if(table.containsKey(word) /*&& table.get(word) > 0*/) {
                    int q = table.get(word);
                    table.put(word, --q);
                    if(q == 0)
                        table.remove(word);
                }
                else {
                    ctn = false;
                }
            }
            System.out.println(ctn ? "Yes" : "No");
        } catch (Exception e) {}
    }
    public static void main(String[] args) {
        solver();
    }
}
