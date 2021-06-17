/*
PROBLEM: Taming the Herd (USACO February 2018 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=809
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class TamingTheHerd {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("taming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] logs = new int[n];
        int[] breakouts = new int[n];
        for (int i = 0; i < n; i++) {
            logs[i] = Integer.parseInt(st.nextToken());
        }

        int min = 0;
        int max = 0;
        boolean possible = true;
        for (int i = 0; i < n; i++) {
            if (i == 0) breakouts[0] = 1;
            else if (logs[i] == 0) {
                breakouts[i] = 1;
            } else if (logs[i] > 0) {
                for (int j = i; j >= i-logs[i]; j--) {
                    if (j == i-logs[i]) {
                        if (logs[j] > 0) {
                            possible = false;
                        } else {
                            breakouts[j] = 1;
                        }
                    } else {
                        breakouts[j] = 0;
                    }
                }
            } else {
                breakouts[i] = 2;
            }
        }

        if (possible) {
            for (int i = 0; i < n; i++) {
                if (breakouts[i] == 1) {
                    min++; max++;
                } else if (breakouts[i] == 2) {
                    max++;
                }
            }
            out.println(min + " " + max);
        } else {
            out.println(-1);
        }

        out.close();
    }
}