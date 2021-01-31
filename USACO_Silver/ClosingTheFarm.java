/*
PROBLEM: Closing the Farm (USACO US Open 2016 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=644

This problem can be solved using DSU or simulation with a graph and DFS. Use an adjacency list to represent the barns and the roads as a graph. Keep track of the barns
that are closed, and everytime you close a barn, start DFS to count the number of barns in a connected component at the first open barn.  If the number of barns
connected to that first open barn is equal to the number of open barns, then we know the open barns are fully connected.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int m;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static boolean[] closed;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        closed = new boolean[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        int counter = n;
        for (int i = 0; i < counter; i++) {
            Arrays.fill(visited, false);
            if (i != 0) {
                closed[Integer.parseInt(in.readLine()) - 1] = true;
                n--;
            }

            int firstOpen = -1;
            for (int j = 0; j < closed.length; j++) { // finds first open barn
                if (!closed[j]) {
                    firstOpen = j;
                    break;
                }
            }

            if (dfs(firstOpen) == n) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        out.close();
    }

    static int dfs(int node) {
        if (closed[node]) { // if the current barn is closed, then return 0 because we cannot go anywhere
            return 0;
        }
        int visCount = 1;
        visited[node] = true;
        for(int next : adj[node]) { // goes through each of the current node's neighbors
            if(!visited[next] && !closed[next]) { 
                visCount += dfs(next);
            }
        }

        return visCount;
    }
}