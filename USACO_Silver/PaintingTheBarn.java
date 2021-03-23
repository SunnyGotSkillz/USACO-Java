/*
PROBLEM: Painting The Barn (USACO February 2019 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=919

The naive approach to this problem would be to simulate painting all the rectangles one at the time. This is too slow, so we need to use 2D prefix sums. Start with
a barn array. We will keep this 1-indexed. Mark the intervals of where each rectangle starts (add 1) and where each rectangle ends (minus 1). Compute a prefix sum of this.
Then go through the prefix sum array and count the number of times a square with k coats appears.

The reason this works is because of the way we are marking the intervals. When the prefix sum is calculated it uses a running sum, meaning that when we enter the interval
of a rectangle, we will stay with the same number of coats until the end of the interval, where we subtract one, since we don't want any more extra coats.

Similar apprach to Old Bronze Haybale Stacking
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class PaintingTheBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] barn = new int[1001][1001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            barn[x1][y1]++;
            barn[x2][y2]++;
            barn[x1][y2]--;
            barn[x2][y1]--;
        }

        int[][] sum = new int[1002][1002];
        fill2DPrefixSum(barn, sum);

        int ans = 0;
        for (int i = 0; i < 1002; i++) {
            for (int j = 0; j < 1002; j++) {
                if (sum[i][j] == k) ans++;
            }
        }

        out.println(ans);
        out.close();
    }

    public static void fill2DPrefixSum(int[][] a, int[][] psa) {
        int R = a.length;
        int C = a[0].length;

        for (int i = 0; i < psa.length; i++) psa[0][i] = 0;
        
        for (int i = 1; i < psa.length; i++) psa[i][0] = 0;

        for (int i = 1; i <= C; i++) psa[1][i] = psa[1][i - 1] + a[0][i-1];
        for (int i = 1; i <= R; i++) psa[i][1] = psa[i - 1][1] + a[i-1][0];

        for (int i = 2; i <= R; i++) {
            for (int j = 2; j <= C; j++) {
                psa[i][j] = psa[i - 1][j] + psa[i][j - 1] - psa[i - 1][j - 1] + a[i-1][j-1];
            }
        }
    }
}