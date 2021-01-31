/*
PROBLEM: Swapity Swap (USACO February 2020 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1013
SOLUTION: http://www.usaco.org/current/data/sol_swap_bronze_feb20.html

For every i, we want to figure out how many minimum repitions of the process P will turn that number back to i. Then, for that cow, we can consider the remainder when
K is divided by P. Then, we find the index that the current cow i would be in.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int k;
    static int a1;
    static int a2;
    static int b1;
    static int b2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("swap.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        a1 = Integer.parseInt(st.nextToken());
        a2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        b1 = Integer.parseInt(st.nextToken());
        b2 = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int p = 1;
            int cur = nex(i); // next index that cow i will go to
            while (cur != i) { // stopping when the position of cow i is i
                p++;
                cur = nex(cur);
            }
            int z = k % p; // after every p cow i will be at i so we don't need to repeat the full k number of times
            for (int j = 0; j < z; j++) cur = nex(cur);
            arr[cur] = i; // position of cow i after k steps is cur
        }

        for (int i = 1; i <= n; i++) out.println(arr[i]);

        out.close();
    }

    public static int nex(int x) {
        if (x >= a1 && x <= a2) x = a1 + a2 - x;
        if (x >= b1 && x <= b2) x = b1 + b2 - x;
        return x;
    }
}