/*
PROBLEM: Haybale Stacking (USACO January 2012 Old Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=104

Since brute force is too slow, the solution is to find the consecutive differences between stacks using the given ranges. Only 2 indices in this array have to be updated
since the differences inside the range will stay the same. Then we can calculate the prefix sum, which will give us the unsorted stacks array, since addition and subtraction
are inverse operations. Sort the stacks array and print the median.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int k;
    static int[] stacks;
    static int[] differences;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        stacks = new int[n];
        differences = new int[n+1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            differences[a-1]++;
            differences[b]--;
        }

        int val = 0;
        for (int i = 0; i < n; i++) {
            val += differences[i];
            stacks[i] = val;
        }

        Arrays.sort(stacks);

        System.out.println(stacks[n/2]);
    }
}