package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by C_Luc on 05/08/2017.
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid
 *
 */
public class DFS {


    static int result [][];
    static boolean visited [][];


    public static final int [] pathI = {-1,-1,-1,0,0,1,1,1};
    public static final int [] pathJ = {-1,0,1,-1,1,-1,0,1};

    public static class Cell {
        private int i, j;
        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static final List<Cell> path = new ArrayList<>();
    static  {
        for(int idx=0; idx<pathI.length; idx++) {
            path.add(new Cell(pathI[idx], pathJ[idx]));
        }
    }

    public static boolean isReachable(int i, int j, int n, int m) {
        if(i > -1 && i < n && j > -1 && j < m)
            return true;
        return false;
    }

    public static List<Cell> getReachableCells(int i, int j, int n, int m, int grid[][]) {
        List<Cell> p = new ArrayList<>();
        for(Cell cell : path) {
            int pi =cell.i + i, pj = cell.j + j;
            if(isReachable(pi, pj, n, m) && grid[pi][pj] == 1 && ! wasVisited(pi, pj)) {
                p.add(new Cell(pi, pj));
            }
        }
        return p;
    }

    public static boolean wasVisited(int i, int j) {
        return visited[i][j];
    }

    public static int dfs(int i, int j, int n, int m, int grid[][], int acc) {
        if(grid[i][j] == 1 && ! wasVisited(i, j)) {
            visited[i][j] = true;
            List<Cell> pp = getReachableCells(i, j, n, m, grid);
            for(Cell cell : pp) {
                int ci = cell.i, cj = cell.j;
                acc = dfs(ci, cj, n, m, grid, acc);
            }
            acc += 1;
        }
        return acc;
    }

    public static void reader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(bufferedReader.readLine());
            int m = Integer.parseInt(bufferedReader.readLine());
            int [][] grid = new int[n][m];
            visited = new boolean[n][m];
            for(int i=0; i<n; i++) {
                StringTokenizer tk = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j=0; tk.hasMoreElements(); j++) {
                    grid[i][j] = Integer.parseInt(tk.nextToken());
                }
            }
            int max = 0;
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    int c = dfs(i, j, n, m, grid, 0);
                    max = c > max ? c : max;
                }
            }
            System.out.println(max);

        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        reader();
    }
}
