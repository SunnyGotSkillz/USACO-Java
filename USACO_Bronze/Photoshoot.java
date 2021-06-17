/*
PROBLEM: Photoshoot (USACO January 2020 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=988

For each i from 1 to N, try setting a[1]=i. Then we can determine the rest of the elements of a by setting a[i] = b[i-1] − a[i-1]. If this indeed produces a valid 
permutation (all elements of a are in [1,N] and none repeat), then return the result. This runs in O(N2) time.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Photoshoot {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("photo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] b = new int[n-1];
        int[] a = new int[n];
        boolean[] chosen = new boolean[n];
        for (int i = 0; i < n-1; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        for (int f = 1; f <= n; f++) {
            a[0] = f;
            for(int i = 1; i < n; i++) a[i] = b[i-1] - a[i-1];
            boolean bad = false;
            for (int i = 0; i < n; i++) {
                if (a[i] <= 0 || a[i] > n) {
                    bad = true;
                    break;
                }
                if (chosen[a[i]-1]) {
                    bad = true;
                    break;
                }
                chosen[a[i]-1] = true;
            }

            chosen = new boolean[n];
            if (!bad) break;
        }

        for (int j : a) {
            if (j == a[n-1]) out.print(j);
            else out.print(j + " ");
        }

        out.close();
    }
}