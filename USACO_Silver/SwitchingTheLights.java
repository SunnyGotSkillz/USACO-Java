/*
PROBLEM: Switching the Lights (USACO December 2015 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=570
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class SwitchingTheLights {
    static int n, m;
    static boolean[][] lit;
    static Switch[] switches;
    static boolean[][] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lit = new boolean[n+1][n+1];
        switches = new Switch[m];
        visited = new boolean[n+1][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            switches[i] = new Switch(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        lit[1][1] = true;

        floodfill(1, 1);

        out.println(ans);
        out.close();
    }

    static class Switch {
        int x, y, a, b;
        public Switch(int x, int y, int a, int b) {
            this.x = x;
            this.y = y;
            this.a = a;
            this.b = b;
        }
    }

    static void floodfill(int r, int c) {
        if(r <= 0 || r > n || c <= 0 || c > n) return; // if outside grid
        if(!lit[r][c]) return; // wrong color
        if(visited[r][c]) return; // already visited this square
        visited[r][c] = true; // mark current square as visited
        ans++; // increment the size for each square we visit
        for (int i = 0; i < m; i++) {
            if (switches[i].x == r && switches[i].y == c) {
                lit[switches[i].a][switches[i].b] = true;
            }
        }
        floodfill(r, c+1);
        floodfill(r, c-1);
        floodfill(r-1, c);
        floodfill(r+1, c);
    }
}
