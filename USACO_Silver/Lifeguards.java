/*
PROBLEM: Lifeguards (USACO January 2018 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=786

Put all points (since they are unique) in one array with their point in time and their interval index. Sort these points and go through them. We want to track which lifeguard
does the most work (has the most alone time). The lifeguard with the least alone time is the least useful and the answer will be the total time - min alone time. We can
use a set to keep track of the cow(s) on duty an an array which tracks how much alone time each lifeguard has. Based on this set, we can update the total time and alone
array, as well as update which cows are on duty based on our interval array with indices and times.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Lifeguards {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));

        int n = Integer.parseInt(in.readLine());
        Point[] points = new Point[2*n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            points[2*i] = new Point(Integer.parseInt(st.nextToken()), i);
            points[2*i+1] = new Point(Integer.parseInt(st.nextToken()), i);
        }
        Arrays.sort(points);

        TreeSet<Integer> set = new TreeSet<>();
        int[] alone = new int[n];
        int totalTime = 0;
        int last = 0;
        for (Point p : points) {
            if (set.size() == 1) {
                alone[set.first()] += p.time - last;
            }
            if (!(set.isEmpty())) {
                totalTime += p.time - last;
            }
            if (set.contains(p.index)) {
                set.remove(p.index);
            } else {
                set.add(p.index);
            }
            last = p.time;
        }

        int ans = 0;
        for (int i : alone) {
            ans = Math.max(ans, totalTime - i);
        }

        out.println(ans);
        out.close();
    }

    public static class Point implements Comparable<Point> {
        int time;
        int index;
        public Point(int time, int index) {
            this.time = time;
            this.index = index;
        }

        public int compareTo(Point o) {
            return Integer.compare(time, o.time);
        }
    }
}
