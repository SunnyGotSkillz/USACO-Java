/*
PROBLEM: The Great Revegetation (USACO February 2019 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=920

For this problem, create an weighted adjacency list with the nodes representing the pastures. The color array represents the two different seed packets it could
be. The edge weights are 'S' or 'D'. Run DFS through each connected component in order to make sure that component will satisfy the cow conditions. If it works
then add 1 to k. The answer will be 2^k and since the answer has to be in binary, (if the k is not 0) the final answer will be a 1 follwed by k 0s.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    static boolean[] colors;
    static int n, m;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        visited = new boolean[n];
        colors = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            String c = st.nextToken();
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(new Edge(b, c));
            adj[b].add(new Edge(a, c));
        }

        count_components();

        if (k == 0) out.println(0);
        else {
            out.print(1);
            for (int i = 0; i < k; i++) {
                out.print(0);
            }
        }

        out.close();
    }

    static boolean bipartiteDFS(int node) {
        visited[node] = true;
        for(Edge next : adj[node]) { // goes through each of the current node's neighbors
            if (!visited[next.to]) {
                // color the adjacent nodes the opposite color
                if (next.weight.equals("D")) colors[next.to] = !colors[node];
                else if (next.weight.equals("S")) colors[next.to] = colors[node];
                bipartiteDFS(next.to);
            } else {
                // not bipartite because a previously colored adjacent node has the same color as the current node
                if (next.weight.equals("D") && colors[node] == colors[next.to]) return false;
                else if (next.weight.equals("S") && colors[node] != colors[next.to]) return false;
            }
        }
        return true;
    }

    static void count_components() {
        k = 0;
        for(int i = 0; i < n; ++i)
            if (!visited[i]) {
                if (bipartiteDFS(i)) {
                    k++;
                }
            }
    }

    static class Edge {
        int to;
        String weight;
        public Edge(int to, String weight){
            this.to = to;
            this.weight = weight;
        }
    }
}