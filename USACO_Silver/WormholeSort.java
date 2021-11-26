/*
PROBLEM: Wormhole Sort (USACO January 2020 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=992

Use DFS (connected components) and binary search. We will create a weighted, undirected graph that will connect cows at all positions if there is a wormhole that 
connects them. We will check if the cows are intially sorted (we don't need wormholes in this case). If they are not, then we will run a maximum binary search and run
DFS on each potential answer in the check function. In the DFS, we have to make sure we are only considering wormholes with valid widths. After each DFS call, we check
the visited array to see if we have visited each cow and the position that cow is suppposed to be in for every cow. If we have not, then this answer does not work.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main2 {
    static int n;
    static int m;
    static int[] cows;
    static boolean[] visited;
    static ArrayList<Wormhole>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cows = new int[n];
        visited = new boolean[n];
        boolean sorted = true;
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken())-1;
            if (i > 0) {
                if (cows[i] != cows[i-1]+1) {
                    sorted = false;
                }
            }
        }
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Wormhole>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new Wormhole(b, w));
            adj[b].add(new Wormhole(a, w));
        }

        int ans = -1;
        if (!sorted) {
            ans = maxSearch();
        }

        out.println(ans);
        out.close();
    }

    static boolean check(int x) {
        Arrays.fill(visited, false);
        dfs(0, x);
        for (int i = 0; i < n; i++) {
            if ((!visited[i] || !visited[cows[i]]) && i != cows[i]) {
                return false;
            }
        }
        return true;
    }

    static void dfs(int node, int x) {
        visited[node] = true;
        for(Wormhole next : adj[node]) { // goes through each of the current node's neighbors
            if(!visited[next.to] && next.width >= x) {
                dfs(next.to, x);
            }
        }
    }

    static int maxSearch() { // searches for maximum true
        int left = 0;
        int right = 1000000001;
        int ans = -1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (check(mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static class Wormhole {
        int to;
        int width;
        public Wormhole(int to, int width) {
            this.to = to;
            this.width = width;
        }
    }
}