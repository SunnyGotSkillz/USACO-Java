/*
PROBLEM: Social Distancing (USACO US Open 2020 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1038

Sort the intervals from left to right and binary search on the separating distance D. For a fixed D we want to check whether we can place at least N cows. This can be 
done with a greedy strategy; just place each cow at the leftmost position possible. Once the number of cows placed reaches N we can break to fit the time constraints.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int m;
    static long min = Integer.MAX_VALUE;
    static long max = Integer.MIN_VALUE;
    static Interval[] farm;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        farm = new Interval[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            min = Math.min(min, a);
            max = Math.max(max, b);
            farm[i] = new Interval(a, b);
        }
        Arrays.sort(farm); // Sort the intervals based on the left value

        out.println(search());
        out.close();
    }

    static long search() {
        long pos = 0;
        for(long a = max; a >= 1; a /= 2) {
            while(check(pos+a)) pos += a;
        }
        return pos;
    }

    // For each interval check how many cows can fit. We don't care about spots without grass (not in the intervals)
    static boolean check(long d) {
        int cows = 0;
        long prev = Long.MIN_VALUE;
        for (Interval i : farm) {
            while (Math.max(prev + d, i.a) <= i.b) { // We want to make sure (prev + d) is within the interval. If it outside the interval, we just use i.a since the spot wouldn't have grass
                prev = Math.max(prev + d, i.a); // Change prev to the last spot where we put a cow
                cows++;
                if (cows >= n) break;
            }
            if (cows >= n) break;
        }
        return cows >= n; // "Farm should fit AT LEAST n cows"
    }

    static class Interval implements Comparable<Interval> {
        long a;
        long b;
        public Interval(long a, long b) {
            this.a = a; this.b = b;
        }
        public int compareTo(Interval i) {
            return Long.compare(a, i.a);
        }
    }
}