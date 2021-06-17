/*
PROBLEM: Hoof Papers Scissors (USACO January 2017 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=688

This solution goes to every possible mapping for 1,2,3 of hoof, papers, scissors and looks for which gives the max wins to cow1.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class HoofPapersScissors {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        int n = Integer.parseInt(in.readLine());
        int[] cow1 = new int[n];
        int[] cow2 = new int[n];
        int ans = -1;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cow1[i] = Integer.parseInt(st.nextToken());
            cow2[i] = Integer.parseInt(st.nextToken());
        }

        int wins = 0;
        int h = 1;
        int p = 2;
        int s = 3;
        for (int i = 0; i < n; i++) {
            if (cow1[i] == h && cow2[i] == s) wins++;
            else if (cow1[i] == p && cow2[i] == h) wins++;
            else if (cow1[i] == s && cow2[i] == p) wins++;
        }
        ans = Math.max(ans, wins);

        wins = 0;
        h = 1;
        p = 3;
        s = 2;
        for (int i = 0; i < n; i++) {
            if (cow1[i] == h && cow2[i] == s) wins++;
            else if (cow1[i] == p && cow2[i] == h) wins++;
            else if (cow1[i] == s && cow2[i] == p) wins++;
        }
        ans = Math.max(ans, wins);

        wins = 0;
        h = 2;
        p = 1;
        s = 3;
        for (int i = 0; i < n; i++) {
            if (cow1[i] == h && cow2[i] == s) wins++;
            else if (cow1[i] == p && cow2[i] == h) wins++;
            else if (cow1[i] == s && cow2[i] == p) wins++;
        }
        ans = Math.max(ans, wins);

        wins = 0;
        h = 2;
        p = 3;
        s = 1;
        for (int i = 0; i < n; i++) {
            if (cow1[i] == h && cow2[i] == s) wins++;
            else if (cow1[i] == p && cow2[i] == h) wins++;
            else if (cow1[i] == s && cow2[i] == p) wins++;
        }
        ans = Math.max(ans, wins);

        wins = 0;
        h = 3;
        p = 1;
        s = 2;
        for (int i = 0; i < n; i++) {
            if (cow1[i] == h && cow2[i] == s) wins++;
            else if (cow1[i] == p && cow2[i] == h) wins++;
            else if (cow1[i] == s && cow2[i] == p) wins++;
        }
        ans = Math.max(ans, wins);

        wins = 0;
        h = 3;
        p = 2;
        s = 1;
        for (int i = 0; i < n; i++) {
            if (cow1[i] == h && cow2[i] == s) wins++;
            else if (cow1[i] == p && cow2[i] == h) wins++;
            else if (cow1[i] == s && cow2[i] == p) wins++;
        }
        ans = Math.max(ans, wins);

        out.println(ans);
        out.close();
    }
}