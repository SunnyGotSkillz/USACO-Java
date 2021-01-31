/*
PROBLEM: Even More Odd Photos (USACO January 2021 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1084


*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        int evens = 0;
        int odds = 0;
        for (int i = 0; i < n; i++) {
            if (cows[i] % 2 == 0) {
                evens++;
            } else {
                odds++;
            }
        }

        int ans = 0;

        if (odds == evens) {
            ans = n;
        } else if (odds > evens) {
            boolean even = true;
            while (odds > 1) {
                if (even) {
                    if (evens > 0) {
                        evens--;
                    } else {
                        odds -= 2;
                    }
                } else {
                    odds--;
                }
                ans++;
                even = !even;
            }

            if (odds == 1 && even) ans--;
            else if (odds == 1) ans++;
        } else if (evens > odds) {
            boolean even = true;
            while (odds != 0) {
                if (even) {
                    if (evens > 0) {
                        evens--;
                    } else {
                        odds -= 2;
                    }
                } else {
                    odds--;
                }
                ans++;
                even = !even;
            }

            if (evens >= 1 && even) ans++;
        }


        System.out.println(ans);
    }
}