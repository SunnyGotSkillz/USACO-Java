/*
PROBLEM: MooBuzz (USACO December 2019 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=966

SOLUTION: http://www.usaco.org/current/data/sol_moobuzz_silver_dec19.html
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class MooBuzz {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));

        int n = Integer.parseInt(in.readLine());
        int[] arr = {-1, 1, 2, 4, 7, 8, 11, 13};
        int q = n / 8;
        int r = n % 8;
        int ans = arr[r] + 15*q;

        out.println(ans);
        out.close();
    }
}