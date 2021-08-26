/*
PROBLEM: Cross Country Skiing (USACO January 2014 Old Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=380

Do a binary search looking for the minimum answer. For each d, simulate traveling to each waypoint using floodfill.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int m;
    static int[][] grid;
    static boolean[][] visited;
    static ArrayList<Point> waypoints;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ccski.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        grid = new int[m][n];
        waypoints = new ArrayList<>();
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    waypoints.add(new Point(i, j));
                }
            }
        }
        out.println(minSearch());
        out.close();
    }

    static long minSearch() { // searches for minimum true
        int left = 0;
        int right = 1000000000;
        int ans = -1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (check(mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    static boolean check(int d) {
        visited = new boolean[m][n];
        floodfill(waypoints.get(0).r, waypoints.get(0).c, d, waypoints.get(0).r, waypoints.get(0).c);

        for (Point wp : waypoints) {
            if (!visited[wp.r][wp.c]) {
                return false;
            }
        }

        return true;
    }

    static void floodfill(int r, int c, int d, int pr, int pc) {
        if(r < 0 || r >= m || c < 0 || c >= n) return; // if outside grid
        if(Math.abs(grid[r][c]-grid[pr][pc]) > d) return; // wrong color
        if(visited[r][c]) return; // already visited this square
        visited[r][c] = true; // mark current square as visited
        floodfill(r, c+1, d, r, c);
        floodfill(r, c-1, d, r, c);
        floodfill(r-1, c, d, r, c);
        floodfill(r+1, c, d, r, c);
    }

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}