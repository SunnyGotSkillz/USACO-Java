/*
PROBLEM: Wormhole Sort (USACO January 2020 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=992
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class WormholeSort {
    static int n;
    static int m;
    static int[] cows;
    static ArrayList<Edge>[] wormholes;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        wormholes = new ArrayList[n];
        cows = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken())-1;
        }
        for (int i = 0; i < n; i++) {
            wormholes[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            wormholes[a].add(new Edge(b, w));
            wormholes[b].add(new Edge(a, w));
        }

        int ans = maxSearch();
        out.println(ans == 1000000001 ? -1 : ans);
        out.close();
    }

    static boolean check(int x) {
        dfs(0, x);
        for (int i = 0; i < visited.length; i++) {
            if ((!visited[i] || !visited[cows[i]]) && i != cows[i]) {
                return false;
            }
        }
        return true;
    }

    static void dfs(int node, int x) {
        if (visited[node]) return;
        visited[node] = true;
        for (Edge next : wormholes[node]) {
            if (next.weight >= x) {
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
            Arrays.fill(visited, false);
            if (check(mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
}