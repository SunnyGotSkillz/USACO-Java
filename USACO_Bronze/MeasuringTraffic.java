/*
PROBLEM: Measuring Traffic (USACO February 2019 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=917

Simluate the process. For finding the prior range, move backwards from the latest shown "none" ramp and change the limits based on whether is ramps are on or off.
Same for the "after" range, except find the earliest "none" range and move forwards updating the limits. 
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("traffic.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("traffic.out")));

        int n = Integer.parseInt(in.readLine());
        String[] ramps = new String[n];
        int[] lower = new int[n];
        int[] upper = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            ramps[i] = st.nextToken();
            lower[i] = Integer.parseInt(st.nextToken());
            upper[i] = Integer.parseInt(st.nextToken());
        }

        int pl = 0; int pu = 0;
        int al = 0; int au = 0;

        int i = n-1;
        for (; i >= 0; i--) {
            if (ramps[i].equals("none")) {
                break;
            }
        }
        pl = lower[i];
        pu = upper[i];
        for (int j = i; j >= 0; j--) {
            if (ramps[j].equals("on")) {
                pl -= upper[j];
                pu -= lower[j];
            } else if (ramps[j].equals("off")) {
                pl += lower[j];
                pu += upper[j];
            } else {
                pl = Math.max(pl, lower[j]);
                pu = Math.min(pu, upper[j]);
            }
        }

        i = 0;
        for (; i < n; i++) {
            if (ramps[i].equals("none")) {
                break;
            }
        }
        al = lower[i];
        au = upper[i];
        for (int j = i; j < n; j++) {
            if (ramps[j].equals("on")) {
                al += lower[j];
                au += upper[j];
            } else if (ramps[j].equals("off")) {
                al -= upper[j];
                au -= lower[j];
            } else {
                al = Math.max(al, lower[j]);
                au = Math.min(au, upper[j]);
            }
        }

        if (pl < 0) pl = 0;
        if (pu < 0) pu = 0;
        if (al < 0) al = 0;
        if (au < 0) au = 0;

        out.println(pl + " " + pu);
        out.print(al + " " + au);
        out.close();
    }
}