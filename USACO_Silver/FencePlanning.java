/*
PROBLEM: Fence Planning (USACO US Open 2019 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=944

Go through each connected component in the graph. Fine the min and max x and y for each component. Each time calculate the perimeter and compare it to the previous
value.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static ArrayList<Integer>[] adj;
    static int n, m;
    static int[] x;
    static int[] y;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    static int minX, maxX, minY, maxY;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        visited = new boolean[n];
        x = new int[n];
        y = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        count_components();

        out.println(ans);
        out.close();
    }

    static void dfs(int node) {
        visited[node] = true;
        for(int next : adj[node]) {
            if(!visited[next]) {
                minX = Math.min(minX, x[next]);
                maxX = Math.max(maxX, x[next]);
                minY = Math.min(minY, y[next]);
                maxY = Math.max(maxY, y[next]);
                dfs(next);
            }
        }
    }

    static void count_components() {
        for (int i = 0; i < n; ++i)
            if (!visited[i]) {
                minX = x[i];
                maxX = x[i];
                minY = y[i];
                maxY = y[i];
                dfs(i);
                int perim = (maxX - minX) + (maxY - minY);
                ans = Math.min(ans, perim*2);
            }
    }
}