/*
PROBLEM: Breed Counting (USACO December 2015 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=572

Use prefix sums for each breed to know how many of each breed are at each point in the cow array.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class BreedCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        int[] breed1 = new int[n+1];
        int[] breed2 = new int[n+1];
        int[] breed3 = new int[n+1];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(in.readLine());
        }
        for (int i = 1; i < n+1; i++) {
            breed1[i] = breed1[i-1];
            breed2[i] = breed2[i-1];
            breed3[i] = breed3[i-1];
            if (cows[i-1] == 1) {
                breed1[i] = breed1[i-1] + 1;
            } else if (cows[i-1] == 2) {
                breed2[i] = breed2[i-1] + 1;
            } else if (cows[i-1] == 3) {
                breed3[i] = breed3[i-1] + 1;
            }
        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            out.print(breed1[b]-breed1[a-1]);
            out.print(" " + (breed2[b]-breed2[a-1]));
            out.println(" " + (breed3[b]-breed3[a-1]));
        }

        out.close();
    }
}