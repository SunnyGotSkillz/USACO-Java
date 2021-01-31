/*
PROBLEM: Counting Haybales (USACO December 2016 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=666
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] bales = new int[1000000000];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            int bale = Integer.parseInt(st.nextToken());
            bales[bale-1]++;
        }

        int[] prefix = new int[1000000001];
        prefix[0] = 0;
        for(int i = 1; i <= n; ++i) {
            prefix[i] = prefix[i-1] + bales[i-1];
        }

        out.println();
        out.close();
    }
}