/*
PROBLEM: Bovine Genomics (USACO US Open 2017 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=736
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class BovineGenomics {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] spotty = new String[n];
        String[] plain = new String[n];
        for (int i = 0; i < n; i++) {
            spotty[i] = in.readLine();
        }
        for (int i = 0; i < n; i++) {
            plain[i] = in.readLine();
        }

        int ans = 0;
        for (int a = 0; a < m; a++) {
            boolean done = false;
            for (int b = 0; b < n; b++) {
                for (int c = 0; c < n; c++) {
                    if (spotty[b].charAt(a) == plain[c].charAt(a)) {
                        done = true;
                        break;
                    }
                }
                if (done) break;
            }
            if (!done) ans++;
        }

        out.println(ans);
        out.close();
    }
}