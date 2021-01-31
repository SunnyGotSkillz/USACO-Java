/*
PROBLEM: Painting The Barn (USACO February 2019 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=919
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[1000][1001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            for (int j = a; j < c; j++) {
                dp[j][b]++;
                dp[j][d]--;
            }
        }

        int ans = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (dp[i][j] == k) ans++;
                dp[i][j+1] += dp[i][j];
            }
        }

        out.println(ans);
        out.close();
    }
}