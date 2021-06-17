/*
PROBLEM: Modern Art (USACO US Open 2017 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=737
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ModernArt {
    static int n;
    static int[][] art;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("art.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));

        n = Integer.parseInt(in.readLine());
        art = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            for (int j = 0; j < n; j++) {
                art[i][j] = s.charAt(j) - '0'; // char to int
            }
        }

        int ans = 0;
        for (int i = 1; i <= 9; i++) {
            if (appears(i)) {
                boolean first = true;
                for (int j = 1; j <= 9; j++) {
                    if (i!=j && appears(j) && overlap(i, j)) {
                        first = false;
                    }
                }
                if (first) ans++;
            }
        }

        out.println(ans);
        out.close();
    }

    static boolean appears(int c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (art[i][j] == c) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean overlap(int c1, int c2) { // does c1 appear on top of c2
        int top = n; int left = n; int bottom = 0; int right = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (art[i][j] == c2) {
                    top = Math.min(i, top);
                    bottom = Math.max(i, bottom);
                    left = Math.min(j, left);
                    right = Math.max(j, right);
                }
            }
        }

        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                if (art[i][j] == c1) return true;
            }
        }
        return false;
    }
}