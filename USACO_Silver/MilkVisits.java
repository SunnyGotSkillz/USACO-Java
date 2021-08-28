/*
PROBLEM: Milk Visits (USACO January 2019 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=968

Create an adjacency list for the farms. We split the farms into connected components where all the farms have the same breeds. If we know which component every farm
is in, for each query, we can figure out whether or not the friend will be satisfied.
1. If they are in the same connected component and the breeds match, then 1
2. If they are not the same component, then 1 because everything is connected (in a tree) and we know we are crossing every breed
3. Else, 0
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main3 {
    static int n;
    static int m;
    static char[] breeds;
    static ArrayList<Integer>[] adj;
    static char[] ans;
    static int numCC = 1;
    static int[] cc; // connected component # of each farm

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        breeds = new char[n];
        ans = new char[m];
        cc = new int[n];
        String breedString = in.readLine();
        for (int i = 0; i < n; i++) {
            breeds[i] = breedString.charAt(i);
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            dfs(i);
            numCC++;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            char c = st.nextToken().charAt(0);

            if (cc[a] == cc[b] && breeds[a] == c) {
                ans[i] = '1';
            } else if (cc[a] != cc[b]) {
                ans[i] = '1';
            } else {
                ans[i] = '0';
            }
        }

        for (char c : ans) out.print(c);
        out.close();
    }

    public static void dfs(int node) {
        if (cc[node] > 0) return;
        cc[node] = numCC;
        for(int next : adj[node]) {
            if(breeds[next] == breeds[node]) {
                dfs(next);
            }
        }
    }
}