/*
PROBLEM: Cow Dance Show (USACO January 2017 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=690

Binary search through every value of k to find the minimum value such that it is true. We use a priority queue in the check function.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int t;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(in.readLine());
        }

        out.println(binSearch(1, n));
        out.close();
    }

    static int binSearch(int low,int high) {
        if (low == high) return low;
        if (low + 1 == high) {
            if(check(low)) return low;
            return high;
        }
        int mid = (low+high)/2;
        if(check(mid)) return binSearch(low,mid);
        else return binSearch(mid+1,high);
    }

    static boolean check(int k) {
        int lastTime = 0;
        PriorityQueue<Integer> cows = new PriorityQueue<>();
        for(int i = 0; i < times.length; i++) {
            if (cows.size() == k) {
                lastTime = cows.poll();
            }
            if (lastTime + times[i] > t) {
                return false;
            }
            cows.add(lastTime + times[i]);
        }

        return true;
    }
}