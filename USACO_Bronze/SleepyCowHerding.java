/*
PROBLEM: Sleepy Cow Herding (USACO February 2019 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=915

SOLUTION: http://www.usaco.org/current/data/sol_herding_bronze_feb19.html
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("herding.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int[] cows = new int[3];
        cows[0] = Integer.parseInt(st.nextToken());
        cows[1] = Integer.parseInt(st.nextToken());
        cows[2] = Integer.parseInt(st.nextToken());
        Arrays.sort(cows);

        int a = cows[0];
        int b = cows[1];
        int c = cows[2];

        int min;
        int max = Math.max(b-a, c-b) - 1;
        if (c - a == 2) min = 0;
        else if (b==a+2 || c==b+2) min = 1;
        else min = 2;

        out.println(min);
        out.println(max);
        out.close();
    }
}