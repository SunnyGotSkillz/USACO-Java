/*
PROBLEM: Circular Barn (USACO February 2016 Silver #1)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=618

Since the constraints are small enough, try each possible starting point and choose the one that requires the minimum energy. Starting at each start point, we move
each cow to the closest door it needs to be in.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(in.readLine());
        }

        int res = Integer.MAX_VALUE;

        // Try each rotation.
        for (int i=0; i<n; i++) {

            // Copy in this rotation to make life easy.
            int[] rot = new int[n];
            for (int j=0; j<n; j++)
                rot[j] = cows[(i+j)%n];

            // See if this rotation is possible.
            int total = 0;
            boolean flag = true;
            for (int j=n-1; j>=0; j--) {
                total += rot[j];
                if (total > n-j) {
                    flag = false;
                    break;
                }
            }

            // Can't do this.
            if (!flag) continue;

            // Find last 0 slot, initially.
            int cost = 0;
            int last = n-1;
            while (last >= 0 && rot[last] != 0) last--;

            // Now go backwards.
            for (int j=last-1; j>=0; j--) {

                // As long as this room has cows to donate.
                while (rot[j] > 0) {

                    if (last == -1 || last < j) break;

                    // Move a cow from j to last.
                    rot[j]--;
                    rot[last]++;
                    cost = cost + (last-j)*(last-j);

                    // Update last.
                    while (last >= 0 && rot[last] != 0) last--;
                }
            }

            // See if this is better or not.
            res = Math.min(res, cost);
        }

        out.println(res);
        out.close();
    }
}