/*
PROBLEM: Rest Stops (USACO February 2018 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=810

The greedy algorithm in this problem is to prioritze rest stops with the most tastiness. Store the positiions of the right most tasty stops in an array.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int rf = Integer.parseInt(st.nextToken());
        int rb = Integer.parseInt(st.nextToken());

        int[] x = new int[100000];
        int[] c = new int[100000];
        boolean[] isMax = new boolean[100000];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        int mx = 0;
        for(int i=N-1;i>=0;i--)
            if(c[i] > mx) {
                isMax[i] = true;
                mx = c[i];
            }

        long ans = 0;
        long tf = 0;
        long tb = 0;
        int lastx = 0;
        for(int i=0;i<N;i++) if(isMax[i]) {
            tf += (x[i] - lastx)*((long)rf);
            tb += (x[i] - lastx)*((long)rb);
            ans += (tf - tb)*((long)c[i]);
            tb = tf;
            lastx = x[i];
        }

        out.println(ans);
        out.close();
    }
}