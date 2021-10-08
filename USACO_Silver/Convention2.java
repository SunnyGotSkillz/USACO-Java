/*
PROBLEM: Convention II (USACO December 2018 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=859

SOLUTION: http://www.usaco.org/current/data/sol_convention2_silver_dec18.html

Use a priority queue and simulation.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Convention2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));

        int n = Integer.parseInt(in.readLine());
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
        }
        Arrays.sort(cows);

        int next_cow = 1;
        int finished = 0;
        PriorityQueue<Wait> waiting = new PriorityQueue<>();
        int ans = 0;
        while (next_cow < n || waiting.size() > 0) {
            while (next_cow < n && cows[next_cow].a <= finished) {
                waiting.add(new Wait(cows[next_cow].s, next_cow));
                next_cow++;
            }
            if (waiting.size() == 0 && next_cow < n) {
                finished = cows[next_cow].a + cows[next_cow].t;
                next_cow++;
            } else if (waiting.size() > 0) {
                int most_senior = waiting.peek().index;
                ans = Math.max(ans, finished - cows[most_senior].a);
                finished += cows[most_senior].t;
                waiting.poll();
            }
        }

        out.println(ans);
        out.close();
    }

    public static class Cow implements Comparable<Cow> {
        public int a;
        public int t;
        public int s;
        public Cow(int a, int t, int s) {
            this.a = a;
            this.t = t;
            this.s = s;
        }

        public int compareTo(Cow o) {
            return Integer.compare(a, o.a);
        }
    }

    public static class Wait implements Comparable<Wait> {
        public int priority;
        public int index;
        public Wait(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }

        public int compareTo(Wait o) {
            return Integer.compare(priority, o.priority);
        }
    }
}