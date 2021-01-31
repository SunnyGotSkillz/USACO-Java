/*
PROBLEM: Mootube (USACO January 2018 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=788

This problem can be solved using DSU or DFS with an undirected, weighted graph. We do DFS on each question. We never want to traverse an edge with edge weight 
strictly less than k, so we ignore those edges. We can then count how many other vertices we have visited.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static ArrayList<Edge>[] adj;
    static int n, q;
    static boolean[] visited;
    static int previous = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken());
            adj[a].add(new Edge(b, r));
            adj[b].add(new Edge(a, r));
        }

        for (int i = 1; i <= q; i++) {
            Arrays.fill(visited, false);
            previous = Integer.MAX_VALUE;
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int ans = dfs(v-1, k);
            out.println(ans-1);
        }

        out.close();
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int dfs(int node, int k) {
        int visCount = 1;
        visited[node] = true;
        for (Edge next : adj[node]) { // goes through each of the current node's neighbors
            int temp = Math.min(previous, next.weight);
            if (!visited[next.to] && temp >= k) {
                previous = temp;
                visCount += dfs(next.to, k);
            }
        }
        
        return visCount;
    }
}
