import java.util.HashMap;

public class StairCaseProblem {


    public static int solver(int n, HashMap<Integer, Integer> mem) {
        if(n < 0) {
            return 0;
        }
        int a = solver(n-1, mem);
        int b = solver(n-2, mem);
        int c = solver(n-3, mem);
        if(mem.get(n) == null)
            mem.put(n, a+b+c);
        return mem.get(n);
    }


    public static void main(String[] args) {
        HashMap<Integer, Integer> mem = new HashMap<>();
        mem.put(0, 1);
        mem.put(1, 1);
        mem.put(2, 2);
        mem.put(3, 4);
        System.out.println(solver(7, mem));

    }

}
