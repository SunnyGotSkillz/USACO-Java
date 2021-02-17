/*
PROBLEM: Mootube (USACO January 2018 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=788

This problem can be solved using DSU or DFS with an undirected, weighted graph. We do DFS on each question. We never want to traverse an edge with edge weight 
strictly less than k, so we ignore those edges. We can then count how many other vertices match our relevancy constraints. We do this by having a global static 
variable called ans and resetting and printing its value after every DFS for each of FJ's questions.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int q;
    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(in.readLine());
            int pi = Integer.parseInt(st.nextToken()) - 1;
            int qi = Integer.parseInt(st.nextToken()) - 1;
            int ri = Integer.parseInt(st.nextToken());
            adj[pi].add(new Edge(qi, ri));
            adj[qi].add(new Edge(pi, ri));
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;
            dfs(v, k);
            out.println(ans);
            Arrays.fill(visited, false);
            ans = 0;
        }

        out.close();
    }

    public static void dfs(int node, int k) {
        visited[node] = true;
        for(Edge next : adj[node]) {
            if(!visited[next.to] && next.r >= k) {
                dfs(next.to, k);
            }
        }
    }

    public static class Edge {
        int to;
        int r;

        public Edge(int to, int r){
            this.to = to;
            this.r = r;
        }
    }
}
