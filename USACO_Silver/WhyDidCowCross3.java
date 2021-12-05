/*
PROBLEM: Why Did the Cow Cross the Road III (USACO February 2017 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=716

Store the roads as a 2D array where each point contains a set representing adjacent points that contain a road. For each pair of cows, run a floodfill. Don't continue
if there is a road present. At the end, check if we were able to visit our destination cow without crossing any roads. If not then add one to the answer.

Hash code and equals functions on the Point class are because we use a set and we have to determine whether two Point objects are equal.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class WhyDidCowCross3 {
    static int n;
    static boolean[][] visited;
    static Set<Point>[][] farm;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        farm = new Set[n][n];
        Point[] cows = new Point[k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                farm[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;
            farm[x1][y1].add(new Point(x2, y2));
            farm[x2][y2].add(new Point(x1, y1));
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            cows[i] = new Point(x, y);
        }

        int ans = 0;
        for (int i = 0; i < k; i++) {
            for (int j = i+1; j < k; j++) {
                visited = new boolean[n][n];
                floodfill(cows[i].x, cows[i].y, cows[i].x, cows[i].y, cows[j].x, cows[j].y);
                if (!visited[cows[j].x][cows[j].y]) ans++;
            }
        }

        out.println(ans);
        out.close();
    }

    static void floodfill(int currR, int currC, int prevR, int prevC, int endR, int endC) {
        if (currR < 0 || currR >= n || currC < 0 || currC >= n) return;
        if (visited[endR][endC]) return;
        if (farm[currR][currC].contains(new Point(prevR, prevC))) return;
        if (visited[currR][currC]) return;
        visited[currR][currC] = true;
        if (currR == endR && currC == endC) return;
        floodfill(currR, currC+1, currR, currC, endR, endC);
        floodfill(currR, currC-1, currR, currC, endR, endC);
        floodfill(currR+1, currC, currR, currC, endR, endC);
        floodfill(currR-1, currC, currR, currC, endR, endC);
    }

    public static class Point {
        int x;
        int y;
        public Point(int x1, int y1) {
            x = x1;
            y = y1;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }
}