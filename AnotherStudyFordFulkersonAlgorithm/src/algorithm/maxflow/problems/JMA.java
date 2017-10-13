package algorithm.maxflow.problems ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by r028367 on 13/10/2017.
 * https://www.hackerearth.com/pt-br/practice/algorithms/graphs/maximum-flow/practice-problems/algorithm/jadu-and-spaceship/
 */
public class JMA {

    public static class Node {
        int number;
        Set<Integer> divisors;
        Node(int n) {
            number = n;
            divisors = new HashSet<>();
            int d = 2;
            while (n > 1) {
                if(n % d == 0) {
                    divisors.add(d);
                    n /= d;
                }
                else
                    d++;
            }
        }
    }

    public static void reader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(bufferedReader.readLine(), " ");
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());
        int [] S = new int[m];
        tk = new StringTokenizer(bufferedReader.readLine(), " ");
        for(int idx=0; tk.hasMoreTokens(); idx++) {
            S[idx] = Integer.parseInt(tk.nextToken());
        }

    }

    public static void main(String[] args) {
        Node node = new Node(60);
        System.out.println(node.divisors.size());
    }
}
