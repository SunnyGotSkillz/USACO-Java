/*
PROBLEM: Where's Bessie (USACO US Open 2017 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=740
OFFICIAL SOLUTION: http://www.usaco.org/current/data/sol_where_silver_open17.html

Since unique rectangles can be defined by their upper left and lower right corners, we have to take each of these points which will be O(n4). Then for each rectangle
we see if its a PCL by first checking if it only contains 2 colors, then we start floodfill on each unvisited part to see how many connected components we can find
within the grid for each color, since we know there are only 2 colors. If there is 1 of one color and 2 or more of the other color, then this is officially a valid
PCL. The next step becomes counting the number of unique PCLs, such that we aren't counting any subset PCLs. To do this, for each PCL x , we compare it to the rest in 
every other PCL y and see if x is within y. If it is then we know that we can only count the outer one.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class WheresBessie {
    static int n;
    static char[][] grid;
    static ArrayList<PCL> p;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("where.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));

        n = Integer.parseInt(in.readLine());
        grid = new char[n][n];
        p = new ArrayList<>();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = in.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int i2 = i1; i2 < n; i2++) {
                    for (int j2 = j1; j2 < n; j2++) {
                        if (is_PCL(i1,j1,i2,j2)) {
                            p.add(new PCL(i1,j1,i2,j2));
                        }
                    }
                }
            }
        }

        int ans = p.size();
        for (int i = 0; i < p.size(); i++) {
            PCL x = p.get(i);
            for (int j = 0; j < p.size(); j++) {
                PCL y = p.get(j);
                if (i != j) {
                    if ((x.i1 >= y.i1) &&
                        (x.i2 <= y.i2) &&
                        (x.j1 >= y.j1) &&
                        (x.j2 <= y.j2)) {
                        ans--;
                        break;
                    }
                }
            }
        }

        out.println(ans);
        out.close();
    }

    public static boolean is_PCL(int i1, int j1, int i2, int j2) {
        char c1 = grid[i1][j1];
        char c2 = ' ';
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (grid[i][j] != c1 && c2 == ' ') {
                    c2 = grid[i][j];
                } else if (grid[i][j] != c1 && c2 != ' ' && grid[i][j] != c2) {
                    return false;
                }
            }
        }

        int count1 = 0;
        int count2 = 0;
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                visited[i][j] = false;
            }
        }
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (!visited[i][j]) {
                    floodfill(i, j, i1, j1, i2, j2, grid[i][j]);
                    if (grid[i][j] == c1) count1++;
                    else if (grid[i][j] == c2) count2++;
                }
            }
        }

        return (count1 == 1 && count2 > 1) || (count1 > 1 && count2 == 1);
    }

    static void floodfill(int r, int c, int i1, int j1, int i2, int j2, char color) {
        if(r < i1 || r > i2 || c < j1 || c > j2) return;
        if(grid[r][c] != color) return;
        if(visited[r][c]) return;
        visited[r][c] = true; 
        floodfill(r, c+1, i1, j1, i2, j2, color);
        floodfill(r, c-1, i1, j1, i2, j2, color);
        floodfill(r-1, c, i1, j1, i2, j2, color);
        floodfill(r+1, c, i1, j1, i2, j2, color);
    }

    public static class PCL {
        int i1;
        int j1;
        int i2;
        int j2;
        public PCL(int i1, int j1, int i2, int j2) {
            this.i1 = i1;
            this.i2 = i2;
            this.j1 = j1;
            this.j2 = j2;
        }
    }
}