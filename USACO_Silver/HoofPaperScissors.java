/*
PROBLEM: Hoof Paper Scissors (USACO January 2017 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=691

Similar to Breed Counting silver problem. Use prefix sums to store the count of each move at every point. Loop thru every "pivot" index and find where theres a max
number of wins for Bessie.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        int n = Integer.parseInt(in.readLine());
        int[] moves = new int[n];
        int[][] prefix = new int[n][3];
        for (int i = 0; i < n; i++) {
            String m = in.readLine();
            if (m.equals("H")) moves[i] = 0;
            else if (m.equals("P")) moves[i] = 1;
            else moves[i] = 2;
        }
        prefix[0][moves[0]]++;
        for (int i = 1; i < n; i++) {
            prefix[i][0] = prefix[i-1][0];
            prefix[i][1] = prefix[i-1][1];
            prefix[i][2] = prefix[i-1][2];
            prefix[i][moves[i]]++;
        }

        int ans = -1;
        for (int i = 0; i < n; i++) {
            int currentWins = 0;
            currentWins += Math.max(prefix[i][0], Math.max(prefix[i][1], prefix[i][2]));
            int numH = prefix[n-1][0]-prefix[i][0];
            int numP = prefix[n-1][1]-prefix[i][1];
            int numS = prefix[n-1][2]-prefix[i][2];
            currentWins += Math.max(numH, Math.max(numP, numS));
            ans = Math.max(ans, currentWins);
        }

        out.println(ans);
        out.close();
    }
}