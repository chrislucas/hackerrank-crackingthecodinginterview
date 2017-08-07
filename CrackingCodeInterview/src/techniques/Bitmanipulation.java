package techniques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by r028367 on 03/08/2017.
 * https://www.hackerrank.com/challenges/ctci-lonely-integer
 */
public class Bitmanipulation {

    public static int solver2(int [] array) {
        int acc = 0;
        for(int i : array) {
            acc ^= i;
        }
        return acc;
    }

    public static int solver1(int [] array) {
        int x = -1;
        for(int i = 0; i < array.length; i++) {
            x = array[i];
            boolean f = false;
            for(int j=0; j<array.length; j++) {
                if(i == j)
                    continue;
                if( array[j] == x) {
                    f = true;
                    break;
                }
            }
            if(!f) {
                break;
            }
            x = -1;
        }
        return x;
    }

    public static void reader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(bufferedReader.readLine());
            int [] array = new int[n];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for(int idx=0; stringTokenizer.hasMoreTokens(); idx++)
                array[idx] = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(solver2(array));
        } catch (IOException ioex) {}
    }

    public static void main(String[] args) {
        reader();
    }
}
