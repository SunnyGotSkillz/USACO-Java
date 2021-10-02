/*
PROBLEM: Moocryption (USACO US Open 2015 Bronze #1)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=545

Try every pair for 'M' and 'O' and test the encryption that gives the max number of XYY formatted words (MOO)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Moocryption {
    static int n;
    static int m;
    static char[][] original;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moocrypt.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocrypt.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        original = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = in.readLine();
            for (int j = 0; j < m; j++) {
                original[i][j] = line.charAt(j);
            }
        }

        int ans = -1;
        for (int a = 65; a <= 90; a++) {
            if (a != 77) {
                for (int b = 65; b <= 90; b++) {
                    if (b != a && b != 79) {
                        char ac = (char) a; char bc = (char) b;
                        int count = 0;

                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                if (get(i,j) == ac) {
                                    if (get(i-1, j) == bc && get(i-2, j) == bc) count++;
                                    if (get(i+1, j) == bc && get(i+2, j) == bc) count++;
                                    if (get(i, j-1) == bc && get(i, j-2) == bc) count++;
                                    if (get(i, j+1) == bc && get(i, j+2) == bc) count++;
                                    if (get(i+1, j+1) == bc && get(i+2, j+2) == bc) count++;
                                    if (get(i-1, j-1) == bc && get(i-2, j-2) == bc) count++;
                                    if (get(i+1, j-1) == bc && get(i+2, j-2) == bc) count++;
                                    if (get(i-1, j+1) == bc && get(i-2, j+2) == bc) count++;
                                }
                            }
                        }

                        ans = Math.max(ans, count);
                    }
                }
            }
        }

        out.println(ans);
        out.close();
    }

    public static char get(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return '_';
        }
        return original[r][c];
    }
}