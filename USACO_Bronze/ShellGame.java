/*
PROBLEM: Shell Game (USACO January 2019 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=891
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ShellGame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("shell.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shell.out")));

        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            g[i] = Integer.parseInt(st.nextToken());
        }

        int[] shells;
        int ans = -1;
        for (int s = 1; s <= 3; s++) {
            shells = new int[]{1, 2, 3};
            int correct = 0;
            for (int i = 0; i < n; i++) {
                swap(shells, a[i]-1, b[i]-1);
                if (shells[g[i]-1] == s) correct++;
            }
            ans = Math.max(ans, correct);
        }

        out.println(ans);
        out.close();
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}