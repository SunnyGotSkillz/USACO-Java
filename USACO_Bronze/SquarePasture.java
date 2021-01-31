/*
LANG: JAVA
TASK: Square Pasture (USACO December 2016 Bronze #1)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=663
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("square.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        int xDistance = Math.abs(Math.max(x2, x4) - Math.min(x1, x3));
        int yDistance = Math.abs(Math.max(y2, y4) - Math.min(y1, y3));
        int sideLength = Math.max(xDistance, yDistance);
        int area = sideLength*sideLength;

        out.println(area);
        out.close();
    }
}