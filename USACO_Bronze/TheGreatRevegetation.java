/*
PROBLEM: The Great Revegetation (USACO February 2019 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=916

Create a graph where nodes represent pastures and edges represent the pastures that must be different from each other. Since the solution must be the lowest possible
number, we want to assign smaller seed buckets to earlier numbered pastures. We go through all the pastures from 1 to N and assign the lowest possible seed bucket
to it based on what its neighbor has.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main2 {
    static int n;
    static int m;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] colors;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        visited = new boolean[n];
        colors = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(adj[i]);
        }

        for (int i = 0; i < n; i++) {
            boolean one = true;
            boolean two = true;
            boolean three = true;
            boolean four = true;
            for (int next : adj[i]) {
                if (colors[next] > 0) {
                    if (colors[next] == 1) one = false;
                    else if (colors[next] == 2) two = false;
                    else if (colors[next] == 3) three = false;
                    else if (colors[next] == 4) four = false;
                }
            }

            if (one) colors[i] = 1;
            else if (two) colors[i] = 2;
            else if (three) colors[i] = 3;
            else if (four) colors[i] = 4;
        }

        for (int i = 0; i < n; i++) {
            out.print(colors[i]);
        }

        out.close();
    }
}