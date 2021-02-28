/*
PROBLEM: Why Did The Cow Cross the Road II (USACO February 2017 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=715

Create a prefix sum array for the number of damaged signals at any point. Then take each set of contiguous k signals starting from the beginning and see how
many damaged signals would have to be fixed each time. The minimum fixes is the answer.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] road = new int[n];
        int[] prefixSum  = new int[n+1];
        for (int i = 0; i < b; i++) {
            int j = Integer.parseInt(in.readLine());
            road[j-1] = 1;
        }

        prefixSum[0] = 0;
        for (int i = 1; i <= n; ++i) {
            prefixSum[i] = prefixSum[i-1] + road[i-1];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n - (k-1); i++) {
            int num = prefixSum[i + (k-1)] - prefixSum[i-1];
            ans = Math.min(ans, num);
        }

        out.println(ans);
        out.close();
    }
}