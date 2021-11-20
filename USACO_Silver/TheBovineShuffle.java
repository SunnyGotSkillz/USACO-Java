/*
PROBLEM: The Bovine Shuffle (USACO December 2017 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=764

Create an array that will count how many cows moved into each position during each shuffle. If it is 0, that means no cows move in during that shuffle, and we have to check
the positions where cows from empty positions are supposed to come from.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class TheBovineShuffle {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] cows = new int[n];
        int[] vis = new int[n]; // how many cows move into each position during each shuffle
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken())-1;
            vis[cows[i]]++;
        }

        int ans = n;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                ans--;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            vis[cows[v]]--;
            if (vis[cows[v]] == 0) {
                ans--;
                q.add(cows[v]);
            }
        }

        out.println(ans);
        out.close();
    }
}