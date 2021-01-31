/*
PROBLEM: The Cow Signal (USACO December 2016 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=665
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowsignal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            String line = in.readLine();
            for (int j = 0; j < k; j++) {
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < k; b++) {
                        out.print(line.charAt(a));
                    }
                }
                out.println();
            }
        }

        out.close();
    }
}