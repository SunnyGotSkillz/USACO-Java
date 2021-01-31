/*
PROBLEM: Cow Tipping (USACO January 2017 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=689

This solution uses a slight greedy algorithm. Since every rectangle has to contain the upper left cow, we can keep trying to find the last cow in our grid that is tipped 
over and change it. Essentially, we are taking the biggest rectangles first, so we only have to deal with small rectangles later, resulting in a min number of uses.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class CowTipping {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowtip.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));

        int n = Integer.parseInt(in.readLine());
        int[][] cows = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = in.readLine();
            for (int j = 0; j < n; j++) {
                cows[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        int ans = 0;
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (cows[i][j] == 1) {
                    ans++;
                    for (int a = 0; a <= i; a++) {
                        for (int b = 0; b <= j; b++) {
                            if (cows[a][b] == 1) cows[a][b] = 0;
                            else cows[a][b] = 1;
                        }
                    }
                }
            }
        }

        out.println(ans);
        out.close();
    }
}