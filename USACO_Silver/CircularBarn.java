/*
PROBLEM: Circular Barn (USACO February 2016 Silver #1)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=618

Since the constraints are small enough, try each possible starting point and choose the one that requires the minimum energy. Starting at each start point, we move
each cow to the closest door it needs to be in.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class CircularBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        int n = Integer.parseInt(in.readLine());
        int[] rooms = new int[n];
        int cowTotal = 0;
        for (int i = 0; i < n; i++) {
            rooms[i] = Integer.parseInt(in.readLine());
            cowTotal += rooms[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int distance = 0;
            int tempTotal = cowTotal;
            for (int j = 0; j < n; j++) {
                int index = (i + j) % n;
                tempTotal -= rooms[index];
                distance += tempTotal;
            }
            ans = Math.min(ans, distance);
        }

        out.println(ans);
        out.close();
    }
}