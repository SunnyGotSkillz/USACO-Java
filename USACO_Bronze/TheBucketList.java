/*
PROBLEM: The Bucket List (USACO December 2018 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=856
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class TheBucketList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("blist.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));

        int n = Integer.parseInt(in.readLine());
        int begTime = 1001;
        int endTime = 0;
        int[] s = new int[n];
        int[] t = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int currS = Integer.parseInt(st.nextToken());
            begTime = Math.min(begTime, currS);
            s[i] = currS;
            int currT = Integer.parseInt(st.nextToken());
            t[i] = currT;
            endTime = Math.max(endTime, currT);
            b[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;
        int available = 0;
        for (int i = begTime; i <= endTime; i++) {
            for (int a = 0; a < n; a++) {
                if (i == s[a]) { // Beginning of cow a milk time
                    if (available == 0) {
                        total += b[a];
                    } else if (available == b[a]) {
                        available = 0;
                    } else if (available > b[a]) {
                        available -= b[a];
                    } else if (available < b[a]) {
                        int needed = b[a] - available;
                        available = 0;
                        total += needed;
                    }
                }
            }

            for (int c = 0; c < n; c++) {
                if (i == t[c]) { // Ending of cow c milk time
                    available += b[c];
                }
            }
        }

        out.println(total);
        out.close();
    }
}