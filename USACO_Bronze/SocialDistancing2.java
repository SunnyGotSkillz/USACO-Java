/*
PROBLEM: Social Distancing 2 (USACO US Open 2020 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1036
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("socdist2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist2.out")));

        int n = Integer.parseInt(in.readLine());
        int[][] cows = new int[n][2];
        int[] distances = new int[n-1];
        int sick = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            if (cows[i][1] == 1) sick++;
        }

        Arrays.sort(cows, Comparator.comparingDouble(a -> a[0]));
        for (int i = 1; i < n; i++) {
            distances[i-1] = cows[i][0] - cows[i-1][0];
        }

        int maxR = Integer.MAX_VALUE;
        if (n > 1) {
            if (cows[0][1] == 0 && cows[1][1] == 1) {
                maxR = Math.min(maxR, distances[0]);
            }
            if (cows[n-1][1] == 0 && cows[n-2][1] == 1) {
                maxR = Math.min(maxR, distances[n-2]);
            }
        }
        for (int i = 1; i < n-1; i++) {
            if (cows[i][1] == 0) {
                if (cows[i-1][1] == 1) maxR = Math.min(maxR, distances[i-1]);
                if (cows[i+1][1] == 1) maxR = Math.min(maxR, distances[i]);
            }
        }

        int ans = sick;
        for (int r = 1; r < maxR; r++) {
            int initial = 0;
            boolean streak = false;
            for (int i = 0; i < n; i++) {
                if (cows[i][1] == 1) {
                    streak = true;
                }
                if (streak && i != n-1 && (distances[i] > r || cows[i][1] == 0)) {
                    streak = false;
                    initial++;
                }
            }
            if (streak) initial++;
            ans = Math.min(ans, initial);
        }

        out.println(ans);
        out.close();
    }
}