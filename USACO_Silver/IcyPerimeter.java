/*
PROBLEM: Icy Perimeter (USACO January 2019 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=895
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static char[][] grid;
    static int n;
    static boolean[][] visited;
    static int currentArea, currentPerimeter = 0;
    static int area, perimeter = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));

        n = Integer.parseInt(in.readLine());
        grid = new char[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = in.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    currentArea = 0;
                    currentPerimeter = 0;
                    floodfill(i, j, false);
                }

                if (currentArea > area) {
                    area = currentArea;
                    perimeter = currentPerimeter;
                } else if (currentArea == area) {
                    if (currentPerimeter < perimeter) {
                        perimeter = currentPerimeter;
                    }
                }
            }
        }

        out.println(area + " " + perimeter);
        out.close();
    }

    static void floodfill(int r, int c, boolean b) {
        if(r < 0 || r >= n || c < 0 || c >= n) {
            currentPerimeter++;
            return; // if outside grid
        }
        if(grid[r][c] != '#') {
            if (b) currentPerimeter++;
            return; // wrong color
        }
        if(visited[r][c]) return; // already visited this square
        visited[r][c] = true; // mark current square as visited
        currentArea++; // increment the size for each square we visit
        floodfill(r, c+1, true);
        floodfill(r, c-1, true);
        floodfill(r-1, c, true);
        floodfill(r+1, c, true);
    }
}