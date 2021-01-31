/*
PROBLEM: My Cow Ate My Homework (USACO December 2017 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=691

Create a sum array and minimum array by going backwards in the order of k. Then find the maximum possible average by going thru all possible values of k. Finally 
find the values of k that makes that maximum average.
*/


import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("homework.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));

        int n = Integer.parseInt(in.readLine());
        int[] homework = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            homework[i] = Integer.parseInt(st.nextToken());
        }

        long[] sum = new long[n];
        int[] min = new int[n];
        sum[n-1] = homework[n-1];
        min[n-1] = homework[n-1];
        for (int k = n-2; k >= 1; k--) {
            sum[k] += (homework[k] + sum[k+1]);
            min[k] = Math.min(min[k+1], homework[k]);
        }

        double maxA = -1;
        for (int k = 1; k <= n-2; k++) {
            long currSum = sum[k];
            int currMin = min[k];
            double currA = (double) (currSum - currMin) / (n-k-1);
            maxA = Math.max(maxA, currA);
        }

        for (int k = 1; k <= n-2; k++) {
            long currSum = sum[k];
            int currMin = min[k];
            double currA = (double) (currSum - currMin) / (n-k-1);
            if (currA == maxA) {
                out.println(k);
            }
        }

        out.close();
    }
}