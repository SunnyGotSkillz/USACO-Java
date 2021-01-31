/*
PROBLEM: Breed Counting (USACO December 2015 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=572

Use prefix sums to know how many of each breed are at each point in the cow array.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        int[][] breeds = new int[n][3];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(in.readLine());
        }
        breeds[0][cows[0]-1]++;
        for (int i = 1; i < n; i++) {
            breeds[i][0] = breeds[i-1][0];
            breeds[i][1] = breeds[i-1][1];
            breeds[i][2] = breeds[i-1][2];
            breeds[i][cows[i]-1]++;
        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == 0) {
                out.println(breeds[b][0] + " " + breeds[b][1] + " " + breeds[b][2]);
            } else {
                int breed1 = breeds[b][0] - breeds[a-1][0];
                int breed2 = breeds[b][1] - breeds[a-1][1];
                int breed3 = breeds[b][2] - breeds[a-1][2];
                out.println(breed1 + " " + breed2 + " " + breed3);
            }

        }

        out.close();
    }
}