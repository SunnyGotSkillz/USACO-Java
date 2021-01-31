/*
PROBLEM: The Bovine Shuffle (USACO December 2017 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=760

Reverse the process and simulate the cows going back three shuffles in their dance.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        int[] one = new int[n];
        int[] two = new int[n];
        int[] three = new int[n];
        for (int i = 0; i < n; i++) {
            one[i] = cows[a[i]-1];
        }
        for (int i = 0; i < n; i++) {
            two[i] = one[a[i]-1];
        }
        for (int i = 0; i < n; i++) {
            three[i] = two[a[i]-1];
        }

        for (int i : three) {
            out.println(i);
        }

        out.close();
    }
}