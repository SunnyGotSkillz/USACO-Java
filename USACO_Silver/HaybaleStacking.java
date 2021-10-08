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

public class HaybaleStacking {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("stacking.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stacking.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] diff = new int[n+1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            diff[a-1]++;
            diff[b]--;
        }

        for (int i = 1; i < n; i++) {
            diff[i] += diff[i-1];
        }
        Arrays.sort(diff);

        out.println(diff[n/2 + 1]);
        out.close();
    }
}