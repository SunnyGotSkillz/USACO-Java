/*
PROBLEM: Lemonade Line (USACO US Open 2018 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=665
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);

        int inLine = 0;
        int counter = n;
        for (int i = n-1; i >= 0; i--) {
            int w = cows[i];
            if (inLine > w) {
                counter--;
            } else {
                inLine++;
            }
        }

        //System.out.println();
        out.println(counter);
        out.close();
    }
}