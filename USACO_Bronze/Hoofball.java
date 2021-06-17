/*
PROBLEM: Hoofball (USACO February 2018 Bronze #2)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=808

Sort the cows and find their distances between each other. Then use another array to store where each cow's target cow (which cow will this cow throw the ball to). If
a cow never receives a ball, that is an extra ball FJ has to give. A special case is also where 2 adjacent cows pass to each other but are never given the ball by
any external cow. We have to check if this pair is fully isolated, and if so, FJ has to give one of them a ball.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Hoofball {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("hoofball.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hoofball.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] cows = new int[n];
        int[] distances = new int[n-1];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        for (int i = 1; i < n; i++) {
            distances[i-1] = cows[i] - cows[i-1];
        }

        int[] to = new int[n];
        to[0] = cows[1];
        to[n-1] = cows[n-2];
        for (int i = 1; i < n-1; i++) {
            if (distances[i] < distances[i-1]) {
                to[i] = cows[i+1];
            } else if (distances[i] >= distances[i-1]) {
                to[i] = cows[i-1];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (cows[i] == to[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) ans++;
        }

        int a = 0;
        int b = 1;
        while (b < n) {
            int cowa = cows[a];
            int cowb = cows[b];
            if (to[a] == cowb && to[b] == cowa) {
                boolean found = false;
                for (int i = 0; i < n; i++) {
                    if (cows[i] != cowa && cows[i] != cowb && (to[i] == cowa || to[i] == cowb)) {
                        found = true;
                        break;
                    }
                }
                if (!found) ans++;
            }
            a++; b++;
        }

        out.println(ans);
        out.close();
    }
}