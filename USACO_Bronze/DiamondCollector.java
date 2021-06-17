/*
PROBLEM: Diamond Collector (USACO US Open 2016 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=639
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class DiamondCollector {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] diamonds = new int[n];
        for (int i = 0; i < n; i++) {
            diamonds[i] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(diamonds);

        int max = 0;
        for (int i = 0; i < n-1; i++) {
            int currDiamond = diamonds[i];
            int counter = 1;
            for (int j = i+1; j < n; j++) {
                if (Math.abs(diamonds[j] - currDiamond) <= k) {
                    counter++;
                }
            }

            max = Math.max(counter, max);
        }

        out.println(max);
        out.close();
    }
}