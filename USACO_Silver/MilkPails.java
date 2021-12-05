/*
PROBLEM: Milk Pails (USACO February 2016 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=620

Create an array that represents all the possible states of the buckets. For each step, we must look at all the possible previous states. There are six operations with 
the buckets: empty X, fill X, empty Y, fill Y, move X to Y, move Y to X. At the end we find the best possible state for the buckets.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class MilkPails {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pails.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] possible = new boolean[x+1][y+1];
        possible[0][0] = true;
        for (int c = 0; c < k; c++) {
            boolean[][] next = new boolean[x+1][y+1];
            for (int i = 0; i < possible.length; i++) {
                for (int j = 0; j < possible[i].length; j++) {
                    if (!possible[i][j]) continue;
                    next[0][j] = true;
                    next[x][j] = true;
                    next[i][0] = true;
                    next[i][y] = true;
                    int moved = Math.min(i, y-j);
                    next[i-moved][j+moved] = true;
                    moved = Math.min(j, x-i);
                    next[i+moved][j-moved] = true;
                }
            }
            possible = next;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < possible.length; i++) {
            for (int j = 0; j < possible[i].length; j++) {
                if (possible[i][j]) {
                    ans = Math.min(ans, Math.abs(m-(i+j)));
                }
            }
        }

        out.println(ans);
        out.close();
    }
}