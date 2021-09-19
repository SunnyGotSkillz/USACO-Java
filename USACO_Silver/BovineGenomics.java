/*
PROBLEM: Bovine Genomics (USACO US Open 2017 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=739#

Convert the ACGT into 0123. We will try every combination of three indices to see if it works. To test a combination, we use an array with 64 elements (since there are
64 possible combinations) and determine which ones correspond to the combinations taken by the spotty cows. Then we check if the plain cows correspond to any of these.
If they do, then this combination does not work, otherwise it does work.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class BovineGenomics {
    static int n;
    static int m;
    static String[] spotty;
    static String[] plain;
    static int[][] s;
    static int[][] p;
    static boolean[] taken;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        spotty = new String[n];
        plain = new String[n];
        s = new int[n][m];
        p = new int[n][m];
        taken = new boolean[64];
        for (int i = 0; i < n; i++) {
            spotty[i] = in.readLine();
        }
        for (int i = 0; i < n; i++) {
            plain[i] = in.readLine();
        }
        for (int i = 0; i < n; i++) {
            String c = spotty[i];
            for (int j = 0; j < m; j++) {
                if (c.charAt(j) == 'A') s[i][j] = 0;
                if (c.charAt(j) == 'C') s[i][j] = 1;
                if (c.charAt(j) == 'G') s[i][j] = 2;
                if (c.charAt(j) == 'T') s[i][j] = 3;
            }
        }
        for (int i = 0; i < n; i++) {
            String c = plain[i];
            for (int j = 0; j < m; j++) {
                if (c.charAt(j) == 'A') p[i][j] = 0;
                if (c.charAt(j) == 'C') p[i][j] = 1;
                if (c.charAt(j) == 'G') p[i][j] = 2;
                if (c.charAt(j) == 'T') p[i][j] = 3;
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                for (int k = j+1; k < m; k++) {
                    if (test_location(i, j, k)) ans++;
                }
            }
        }

        out.println(ans);
        out.close();
    }

    public static boolean test_location(int a, int b, int c) {
        boolean works = true;
        for (int i = 0; i < n; i++) {
            taken[16*s[i][a] + 4*s[i][b] + s[i][c]] = true;
        }
        for (int i = 0; i < n; i++) {
            if (taken[16*p[i][a] + 4*p[i][b] + p[i][c]]) works = false;
        }
        for (int i = 0; i < n; i++) {
            taken[16*s[i][a] + 4*s[i][b] + s[i][c]] = false;
        }

        return works;
    }
}