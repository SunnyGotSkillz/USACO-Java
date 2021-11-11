/*
PROBLEM: Alphastar Silver Advanced A Class 9 Problem 1
LINK: https://lms.alphastar.academy/mod/quiz/attempt.php?attempt=287461&cmid=31890&scrollpos=1223

The two major parts of this problem are Coordinate Compression and Floodfill. 

Since the coordinates can range from 0...10^6, this would be too much to store in an array,therefore we must compress the coordinates. These involves keeping the original
coordinates in arrays and mapping those original values to our new, compressed values. To do coordinate compression, keep track of all of the points and sort it first
by X and replace with even numbers (0,2,4...), then sort it by Y and replace with even numbers (0,2,4...). We use maps to avoid overlapping points. 

After we have the compressed coordinates, prepare the character grid by putting '.' for empty space, 'S' for students, '*' for walls. Then we can run a BFS floodfill
and count the greatest number of students in one connected component.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class SchoolYard {
    static int n,s;
    static Pair[] all;
    static int[] x1, x2, y1, y2, xs, ys;
    static TreeMap<Integer, Integer> xmap, ymap;
    static char[][] grid;
    static boolean[][] vis;
    static int currentSize = 0;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        all = new Pair[2*n+s];
        x1 = new int[n];
        x2 = new int[n];
        y1 = new int[n];
        y2 = new int[n];
        xs = new int[s];
        ys = new int[s];
        xmap = new TreeMap<>();
        ymap = new TreeMap<>();

        // Read in all input
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            x1[i] = Integer.parseInt(st.nextToken());
            y1[i] = Integer.parseInt(st.nextToken());
            x2[i] = Integer.parseInt(st.nextToken());
            y2[i] = Integer.parseInt(st.nextToken());
            all[i*2] = new Pair(x1[i], y1[i]);
            all[i*2+1] = new Pair(x2[i], y2[i]);
        }
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(in.readLine());
            xs[i] = Integer.parseInt(st.nextToken());
            ys[i] = Integer.parseInt(st.nextToken());
            all[2*n+i] = new Pair(xs[i], ys[i]);
        }

        // Fill up X and Y maps
        Arrays.sort(all, new XComp());
        int counter = 1;
        for (int i = 0; i < all.length; i++) {
            if (!xmap.containsKey(all[i].x)) {
                xmap.put(all[i].x, counter*2);
                counter++;
            }
        }

        Arrays.sort(all, new YComp());
        counter = 1;
        for (int i = 0; i < all.length; i++) {
            if (!ymap.containsKey(all[i].y)) {
                ymap.put(all[i].y, counter*2);
                counter++;
            }
        }

        // Prepare grid for floodfill
        grid = new char[1505][1505];
        vis = new boolean[1505][1505];
        for (int i = 0; i < grid.length; i++) { // Fill in base .s
            Arrays.fill(grid[i], '.');
        }
        for (int i = 0; i < s; i++) { // Fill in students
            grid[xmap.get(xs[i])][ymap.get(ys[i])] = 'S';
        }
        for (int i = 0; i < n; i++) { // Fill in walls using the coordinates of the lines
            int x_1 = x1[i];
            int y_1 = y1[i];
            int x_2 = x2[i];
            int y_2 = y2[i];
            if (x_1 == x_2) {
                if (ymap.get(y_1) < ymap.get(y_2)) {
                    for (int j = ymap.get(y_1); j <= ymap.get(y_2); j++) {
                        grid[xmap.get(x_1)][j] = '*';
                    }
                } else if (ymap.get(y_1) > ymap.get(y_2)) {
                    for (int j = ymap.get(y_2); j <= ymap.get(y_1); j++) {
                        grid[xmap.get(x_1)][j] = '*';
                    }
                }

            } else if (y_1 == y_2) {
                if (xmap.get(x_1) < xmap.get(x_2)) {
                    for (int j = xmap.get(x_1); j <= xmap.get(x_2); j++) {
                        grid[j][ymap.get(y_1)] = '*';
                    }
                } else if (xmap.get(x_1) > xmap.get(x_2)) {
                    for (int j = xmap.get(x_2); j <= xmap.get(x_1); j++) {
                        grid[j][ymap.get(y_1)] = '*';
                    }
                }
            }
        }

        // Use BFS to floodfill (since DFS is too slow)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!vis[i][j] && grid[i][j] != '*') {
                    currentSize = 0;
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i,j));
                    while(!q.isEmpty()) {
                        Pair v = q.poll();
                        if (grid[v.x][v.y] == 'S') currentSize++;
                        int newX = v.x+1;
                        int newY = v.y;
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length && grid[newX][newY] != '*' && !vis[newX][newY]) {
                            vis[newX][newY] = true;
                            q.add(new Pair(newX, newY));
                        }

                        newX = v.x-1;
                        newY = v.y;
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length && grid[newX][newY] != '*' && !vis[newX][newY]) {
                            vis[newX][newY] = true;
                            q.add(new Pair(newX, newY));
                        }

                        newX = v.x;
                        newY = v.y+1;
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length && grid[newX][newY] != '*' && !vis[newX][newY]) {
                            vis[newX][newY] = true;
                            q.add(new Pair(newX, newY));
                        }

                        newX = v.x;
                        newY = v.y-1;
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length && grid[newX][newY] != '*' && !vis[newX][newY]) {
                            vis[newX][newY] = true;
                            q.add(new Pair(newX, newY));
                        }
                    }

                    ans = Math.max(ans, currentSize);
                }
            }
        }

        System.out.println(ans);
    }

    public static class Pair {
        public int x;
        public int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class XComp implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return Integer.compare(a.x, b.x);
        }
    }

    static class YComp implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return Integer.compare(a.y, b.y);
        }
    }
}