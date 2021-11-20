/*
PROBLEM: Subsequences Summing to Sevens (USACO January 2016 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=595
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class SubsequencesSummingSevens {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("div7.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));

        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[n+1];
        int[] prefix = new int[n+1]; // prefix sum array of cows
        cows[0] = 0;
        prefix[0] = 0;
        for (int i = 1; i < n+1; i++) {
            cows[i] = Integer.parseInt(in.readLine());
        }
        for (int i = 1; i < n+1; i++) {
            // if two prefixes have the same remainder then their subarray sum will be equal to 7
            prefix[i] = (prefix[i-1] + cows[i]) % 7; // remainder of each prefix
        }
        // cumulative frequency arrays for the min and max indexes of each remainder in the prefix array
        int[] min = new int[7];
        Arrays.fill(min, n);
        int[] max = new int[7];
        Arrays.fill(max, -1);
        for (int i = 0; i < prefix.length; i++) {
            min[prefix[i]] = Math.min(min[prefix[i]], i);
            max[prefix[i]] = Math.max(min[prefix[i]], i);
        }

        int ans = -1;
        for (int i = 0; i < 7; i++) {
            ans = Math.max(ans, max[i] - min[i]); // answer is the max difference between two indexes of the same remainder
        }
        
        out.println(ans);
        out.close();
    }
}