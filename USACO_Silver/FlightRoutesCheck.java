/*
PROBLEM: Flight Routes Check (CSES: Flight Routes Check)
LINK: https://cses.fi/problemset/task/1682

This problem involves using a directed unweighted graph. Go through each of the flights and use DFS to see if all other flights can be visited from that flight. If they
can't, print out the first one that doesn't work.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
        }

        boolean yes = true;
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            dfs(i);
            for (int b = 0; b < n; b++) {
                if (!visited[b]) {
                    yes = false;
                    System.out.println("NO");
                    System.out.println((i + 1) + " " + (b+1));
                    break;
                }
            }

            if (!yes) break;
        }
        if (yes) System.out.println("YES");
    }

    static void dfs(int node) {
        visited[node] = true;
        for(int next : adj[node]) { // goes through each of the current node's neighbors
            if(!visited[next]) {
                dfs(next);
            }
        }
    }
}