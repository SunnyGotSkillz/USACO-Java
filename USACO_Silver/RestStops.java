/*
PROBLEM: Rest Stops (USACO February 2018 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=810

The greedy algorithm in this problem is to prioritze right rest stops with the most tastiness. We can go iterate backwards to check the max rightmost rest stops, because
if there is a rest stop that has more tastiness further to the right, then it is optimal to stop there instead.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class RestStops {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int rf = Integer.parseInt(st.nextToken());
        int rb = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] rightMax = new boolean[n];
        int max = 0;
        for (int i = n-1; i >= 0; i--) {
            if (c[i] > max) {
                max = c[i];
                rightMax[i] = true;
            }
        }

        int prevX = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (rightMax[i]) {
                ans += (long) c[i]*(((x[i]-prevX) * (long) rf) - ((x[i]-prevX) * (long) rb));
                prevX = x[i];
            }
        }

        out.println(ans);
        out.close();
    }
}