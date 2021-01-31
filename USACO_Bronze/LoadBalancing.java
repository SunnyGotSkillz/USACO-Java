/*
PROBLEM: Load Balancing (USACO February 2016 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=617

The partial solutino would be to complete search over all possible values of a and b, then find m based on how many would fit in each region. The full solution requires
realizing that the only a and b values we have to consider are the ones where there is a cow at a-1 or b-1. This is because if there is not a cow, then the number
of cows in each region would not change.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int a = x[i] + 1;
            for (int j = 0; j < n; j++) {
                int b = y[j] + 1;
                int q1 = 0; int q2 = 0; int q3 = 0; int q4 = 0;
                for (int k = 0; k < n; k++) {
                    int xc = x[k]; int yc = y[k];
                    if (xc < a && yc < b) q3++;
                    else if (xc < a && yc > b) q2++;
                    else if (xc > a && yc < b) q4++;
                    else if (xc > a && yc > b) q1++;
                }
                int poss = Math.max(q1, Math.max(q2, Math.max(q3, q4)));
                m = Math.min(m, poss);
            }
        }

        out.println(m);
        out.close();
    }
}