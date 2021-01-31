/*
PROBLEM: Grass Planting (USACO January 2019 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=894

Solution: http://www.usaco.org/current/data/sol_planting_silver_jan19.html

We know that this problem uses a tree since every field can reach every other field and there are n - 1 paths. We can find the degree of the tree D and the answer 
will be D+1.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] types;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));

        n = Integer.parseInt(in.readLine());
        m = n - 1;
        adj = new ArrayList[n];
        visited = new boolean[n];
        types = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        // Find the degree (max children for each node) of the tree
        int d = -1; 
        for (int i = 0; i < n; i++) {
            if (adj[i].size() > d) {
                d = adj[i].size();
            }
        }
=
        out.println(d+1);
        out.close();
    }
}