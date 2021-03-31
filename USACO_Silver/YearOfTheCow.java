/*
PROBLEM: Year of the Cow (USACO Febrary 2021 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1111

Define a cycle to be a 12 year interval. For example cycle 1 is [1...12] and cycle 2 is [13...24] and so on. Since the ancestors cannot be in a multiple of 12,
each of them belong in a cycle x. The cycle number can be found by doing (a+11)/12. Store the unique cyles in a ordered tree set. Since Bessie needs a initial jump
to go to the furthest possible multiple of 12, the worst case will be that she has to wait this entire number of years. Then store the gaps between cycles and based
on the given number of jumps, we still remove these gaps from the final answer since there is no point in waiting these gaps. Remove k-1 gaps since we need that 
first jump, and multiply the answer by 12.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class YearOfTheCow {
    static int n;
    static int k;
    static int[] ancestors;
    static TreeSet<Integer> cycles;
    static ArrayList<Integer> gaps;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ancestors = new int[n];
        cycles = new TreeSet<>();
        gaps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(in.readLine());
            cycles.add((a+11)/12);
        }

        int last = 0;
        int ans = cycles.last();
        while (!cycles.isEmpty()) {
            gaps.add(cycles.first() - last - 1);
            last = cycles.first();
            cycles.pollFirst();
        }
        Collections.sort(gaps);

        int c = 0;
        for (int i = gaps.size()-1; c < k-1 && i >= 0; i--) {
            ans -= gaps.get(i);
            c++;
        }

        System.out.println(ans*12);
    }
}