/*
PROBLEM: Moocast (USACO December 2016 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=668

First, find which cows can talk to which cows using the distance formula between 2 coordinates and the power of the walkie-talkies. Then create an adjacency list 
that will represent a directed graph. Run DFS on the graph for each cow. We have to reset the values in the visited array because this is not running DFS on each 
connected component. We want to traverse the graph starting at each cow no matter what. After each traversal we store the max number cows that were reached.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int[] x;
    static int[] y;
    static int[] p;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));

        n = Integer.parseInt(in.readLine());
        x = new int[n];
        y = new int[n];
        p = new int[n];
        adj = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            adj[i] = new ArrayList<>();
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double dist = distance(x[i], x[j], y[i], y[j]);
                if (p[i] >= dist) {
                    adj[i].add(j);
                }
                if (p[j] >= dist) {
                    adj[j].add(i);
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i));
            Arrays.fill(visited, false);
        }

        out.println(ans);
        out.close();
    }

    public static double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
    }

    public static int dfs(int node) {
        int visCount = 1;
        visited[node] = true;
        for(int next : adj[node]) { // goes through each of the current node's neighbors
            if(!visited[next]) {
                visCount += dfs(next);
            }
        }

        return visCount;
    }
}
