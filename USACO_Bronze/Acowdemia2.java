/*
PROBLEM: Acowdemia 2 (USACO US Open 2021 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1132

Basically complete search/simulation.

Search thru every pair of cows. Then for each pair, if we don't know the seniority between them, figure it out by going thru each paper and checking for alphabetical
orders.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Acowdemia2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        String[] cows = new String[n];
        String[][] papers = new String[k][n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = st.nextToken();
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                papers[i][j] = st.nextToken();
            }
        }

        char[][] ans = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) ans[i][j] = 'B';
                else ans[i][j] = '?';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int m = 0; m < k; m++) {
                    int a = 0; int b = 0;
                    String as = ""; String bs = "";
                    int acows = 0; int bcows = 0;
                    for (int l = 0; l < n; l++) {
                        if (papers[m][l].equals(cows[i]) || papers[m][l].equals(cows[j])) {
                            if (as.equals("")) {
                                as = papers[m][l];
                                a = l;
                                acows = (papers[m][l].equals(cows[i])) ? i : j;
                            } else {
                                bs = papers[m][l];
                                b = l;
                                bcows = (papers[m][l].equals(cows[i])) ? i : j;
                            }
                        }
                    }
                    if (ans[i][j] == '?') {
                        if (as.charAt(0) > bs.charAt(0)) {
                            // b is a senior to a
                            ans[bcows][acows] = '1';
                            ans[acows][bcows] = '0';
                        } else {
                            char prev = as.charAt(0);
                            boolean alpha = true;
                            for (int c = a+1; c <= b; c++) {
                                if (papers[m][c].charAt(0) >= prev) {
                                    prev = papers[m][c].charAt(0);
                                } else {
                                    alpha = false;
                                    break;
                                }
                            }
                            if (!alpha) {
                                ans[bcows][acows] = '1';
                                ans[acows][bcows] = '0';
                            }
                        }
                    }

                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
}