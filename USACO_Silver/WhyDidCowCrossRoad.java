/*
PROBLEM: Why Did the Cow Cross the Road (USACO February 2017 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=714

To solve this, sort the chickens in increasing order and sort the cows based on end times. This is greedy because we always want to try the earliest chicken
with the cow. We go through both sorted arrays and pair animals up.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] t = new int[c];
        cow[] cows = new cow[n];
        for (int i = 0; i < c; i++) {
            t[i] = Integer.parseInt(in.readLine());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            cows[i] = new cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(t);
        Arrays.sort(cows);

        int ans = 0;
        boolean[] visited = new boolean[c];
        for (cow moo : cows) {
            for (int i = 0; i < c; i++) {
                if (!visited[i] && t[i] >= moo.a && t[i] <= moo.b) {
                    visited[i] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < c; i++) {
            if (visited[i]) ans++;
        }

        out.println(ans);
        out.close();
    }

    static class cow implements Comparable<cow> {
        int a;
        int b;
        public cow(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(cow o) {
            if (b == o.b) {
                return Integer.compare(a, o.a);
            }
            return Integer.compare(b, o.b);
        }
    }
}
