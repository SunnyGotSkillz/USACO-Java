/*
PROBLEM: Moocast (USACO December 2016 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=668

First, find which cows can talk to which cows. I used a Cow class and array to achieve this. Then create an adjacency list that will represent a directed
graph. Run DFS on the graph for each cow. We have to reset the values in the visited array because this is not running DFS on each connected component. We
want to traverse the graph starting at each cow no matter what. After each traversal we store the max number cows that were reached.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static ArrayList<Integer>[] adj;
    static boolean[] vis;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));

        int n = Integer.parseInt(in.readLine());
        Cow[] cows = new Cow[n];
        adj = new ArrayList[n];
        vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                double dist = distance(cows[i], cows[j]);
                if (dist <= cows[i].r) {
                    adj[i].add(j);
                }
                if (dist <= cows[j].r) {
                    adj[j].add(i);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            Arrays.fill(vis, false);
            max = Math.max(max, dfs(i));
        }

        out.println(max);
        out.close();
    }

    static double distance(Cow a, Cow b) {
        return Math.sqrt(((a.x-b.x)*(a.x-b.x)) + ((a.y-b.y)*(a.y-b.y)));
    }

    static class Cow {
        int x;
        int y;
        int r;
        public Cow(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    static int dfs(int node) {
        if(vis[node]) {
            return 0;
        }
        int visCount = 1;
        vis[node] = true;
        for(int next : adj[node]) { // goes through each of the current node's neighbors
            if(!vis[next]) {
                visCount += dfs(next);
                //dfs(next);
            }
        }

        return visCount;
    }
}
