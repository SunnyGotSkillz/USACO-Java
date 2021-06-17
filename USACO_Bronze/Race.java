/*
PROBLEM: Race (USACO January 2020 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=989

SOLUTION: http://www.usaco.org/current/data/sol_race_bronze_jan20.html
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Race {
    static BufferedReader in;
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new FileReader("race.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            out.println(solve(k));
        }

        out.close();
    }

    public static int solve(int dist) throws IOException {
        int minSpeed = Integer.parseInt(in.readLine());
        int lhstravel = 0;
        int rhstravel = 0;
        int time = 0;
        for (int currSpeed = 1;; currSpeed++) {
            lhstravel += currSpeed;
            time++;
            if (lhstravel + rhstravel >= dist) return time;
            if (currSpeed >= minSpeed) {
                rhstravel += currSpeed;
                time++;
                if (lhstravel + rhstravel >= dist) return time;
            }
        }
    }
}