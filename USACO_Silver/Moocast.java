/*
PROBLEM: Moocast (USACO December 2016 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=668

Create a directed graph based on whether a cow can communicate with another cow. run DFS on each cow to see how many cows a particular cow can communicate with. Take 
the max number out of all of these.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Moocast {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int numVisited = 0;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));

        int n = Integer.parseInt(in.readLine());
        int[] x = new int[n];
        int[] y = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[n];

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (distance(x[i], y[i], x[j], y[j]) <= p[i]) {
                    adj[i].add(j);
                }
                if (distance(x[i], y[i], x[j], y[j]) <= p[j]) {
                    adj[j].add(i);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            numVisited = 0;
            Arrays.fill(visited, false);
            dfs(i);
            ans = Math.max(ans, numVisited);
        }


        out.println(ans);
        out.close();
    }

    public static void dfs(int node) {
        visited[node] = true;
        numVisited++;
        for(int next : adj[node]) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));
    }
}