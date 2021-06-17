/*
PROBLEM: Triangles (USACO February 2020 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1011
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Triangles {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(in.readLine());
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        int maxArea = 0;
        for (int i = 0; i <= n-3; i++) {
            int ix = x[i];
            int iy = y[i];
            for (int j = i+1; j <= n-2; j++) {
                int jx = x[j];
                int jy = y[j];
                for (int k = j+1; k <= n-1; k++) {
                    boolean xAxis = false;
                    boolean yAxis = false;
                    int base = 0;
                    int height = 0;
                    int kx = x[k];
                    int ky = y[k];

                    if (iy == jy) {
                        xAxis = true;
                        base = Math.abs(jx-ix);
                    } else if (iy == ky) {
                        xAxis = true;
                        base = Math.abs(kx-ix);
                    } else if (jy == ky) {
                        xAxis = true;
                        base = Math.abs(kx-jx);
                    }

                    if (xAxis && (ix == jx)) {
                        yAxis = true;
                        height = Math.abs(jy-iy);
                    } else if (xAxis && (ix == kx)) {
                        yAxis = true;
                        height = Math.abs(ky-iy);
                    } else if (xAxis && (jx == kx)) {
                        yAxis = true;
                        height = Math.abs(ky-jy);
                    }

                    if (xAxis && yAxis) {
                        maxArea = Math.max(base*height, maxArea);
                    }
                }
            }
        }

        out.println(maxArea);
        out.close();
    }
}