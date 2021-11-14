/*
PROBLEM: Field Reduction (USACO US Open 2016 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=642

Since we would only take extreme X and Y coordinates as borders for our rectangles, we can loop through choosing each of the minX, maxX, minY, maxY. There are 4 possibilites
on each border (because the worst case is taking away 3 cows from just one border). We have our 4 extreme values calculated in 4^4 (256). Now that we have our 256 
possible rectangles, we have to check if they are valid by making sure the number of cows outside of the rectangle is <=3. Then, the answer is the minimum area of all
valid rectangles.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));

        int n = Integer.parseInt(in.readLine());
        Pair[] cowsX = new Pair[n];
        Pair[] cowsY = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cowsX[i] = new Pair(x, y);
            cowsY[i] = new Pair(x, y);
        }
        Arrays.sort(cowsX, new XComp());
        Arrays.sort(cowsY, new YComp());

        int ans = Integer.MAX_VALUE;
        for (int a = n-4; a < n; a++) { // max X coordinate
            int maxX = cowsX[a].x;
            for (int b = 0; b <= 3; b++) { // min X coordinate
                int minX = cowsX[b].x;
                for (int c = n-4; c < n; c++) { // max Y coordinate
                    int maxY = cowsY[c].y;
                    for (int d = 0; d <= 3; d++) { // min Y coordinate
                        int minY = cowsY[d].y;
                        int outsideCounter = 0; // how many cows outside of rectangle
                        for (int i = 0; i < n; i++) {
                            if (!(cowsX[i].x <= maxX && cowsX[i].x >= minX && cowsX[i].y <= maxY && cowsX[i].y >= minY)) {
                                outsideCounter++;
                            }
                        }
                        if (outsideCounter <= 3) {
                            ans = Math.min(ans, (maxX-minX)*(maxY-minY));
                        }
                    }
                }
            }
        }

        out.println(ans);
        out.close();
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

    public static class Pair {
        public int x;
        public int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}