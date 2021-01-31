/*
PROBLEM: Codeforces Contest 1216 Problem C - White Sheet
LINK: https://codeforces.com/contest/1216/problem/C
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x1 = Integer.parseInt(st.nextToken())*2;
        int y1 = Integer.parseInt(st.nextToken())*2;
        int x2 = Integer.parseInt(st.nextToken())*2;
        int y2 = Integer.parseInt(st.nextToken())*2;

        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken())*2;
        int y3 = Integer.parseInt(st.nextToken())*2;
        int x4 = Integer.parseInt(st.nextToken())*2;
        int y4 = Integer.parseInt(st.nextToken())*2;

        st = new StringTokenizer(br.readLine());
        int x5 = Integer.parseInt(st.nextToken())*2;
        int y5 = Integer.parseInt(st.nextToken())*2;
        int x6 = Integer.parseInt(st.nextToken())*2;
        int y6 = Integer.parseInt(st.nextToken())*2;

        boolean found = false;
        for (int x = x1; x <= x2; x++) {
            if (!(inside(x, y1, x3, y3, x4, y4) || inside(x, y1, x5, y5, x6, y6))) {
                found = true;
                break;
            }

            if (!(inside(x, y2, x3, y3, x4, y4) || inside(x, y2, x5, y5, x6, y6))) {
                found = true;
                break;
            }
        }

        if (!found) {
            for (int y = y1; y <= y2; y++) {
                if (!(inside(x1, y, x3, y3, x4, y4) || inside(x1, y, x5, y5, x6, y6))) {
                    found = true;
                    break;
                }

                if (!(inside(x2, y, x3, y3, x4, y4) || inside(x2, y, x5, y5, x6, y6))) {
                    found = true;
                    break;
                }
            }
        }

        if (found) System.out.println("YES");
        else System.out.println("NO");

    }

    public static boolean inside(int x, int y, int x1, int y1, int x2, int y2) {
        return (x >= x1) && (x <= x2) && (y <= y2) && (y >= y1);
    }
}