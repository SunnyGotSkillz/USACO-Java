/*
PROBLEM: Out of Place (USACO January 2018 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=785

There is just one element in the array that makes the full array not sorted. This means that there is a sequence of length k which is not sorted. It takes k-1 swaps
to fix it.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("outofplace.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));

        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[n];
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(in.readLine());
            sorted[i] = cows[i];
        }
        Arrays.sort(sorted);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (cows[i] != sorted[i]) ans++;
        }

        out.println(ans-1);
        out.close();
    }
}