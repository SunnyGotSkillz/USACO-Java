/*
PROBLEM: Milk Measurement (USACO December 2017 Bronze #2)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=763

Sort the input by days. Since each day only has one measurement, go through all the measurements and update two maps. One map will represent each cow and her milk
output. The other (tree) map will represent each milk output and the number of cows which produce that output. We update both of these maps, remove any pair in the
leaderboard map that has no cows, and check if the number of cows with the max output has changed. One edge case is if the number of cows with the max output has not
changed, but we still have a change in the cow that is the max. We deal with this by seeing if our current cow (from our current measurement) was at the top and if they
are still at the top after our modifications.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class MilkMeasurement {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        Measurement[] measures = new Measurement[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            measures[i] = new Measurement(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(measures);

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(measures[i].cow);
        }
        int cowCount = set.size();

        HashMap<Integer, Integer> cows = new HashMap<>();
        TreeMap<Integer, Integer> leaderboard = new TreeMap<>();
        leaderboard.put(0, cowCount);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int original = cows.getOrDefault(measures[i].cow, 0);

            boolean wasTop = false; // was this current cow a maximum
            if (leaderboard.lastKey() == original) wasTop = true;
            int wasCount = leaderboard.getOrDefault(cows.getOrDefault(measures[i].cow, -1), 0);

            if (cows.containsKey(measures[i].cow)) cows.replace(measures[i].cow, original+measures[i].change);
            else cows.put(measures[i].cow, measures[i].change);

            leaderboard.replace(original, leaderboard.get(original)-1);
            if (leaderboard.containsKey(cows.get(measures[i].cow))) leaderboard.replace(cows.get(measures[i].cow), leaderboard.get(cows.get(measures[i].cow))+1);
            else leaderboard.put(cows.get(measures[i].cow), 1);

            if (leaderboard.get(original) <= 0) leaderboard.remove(original);

            if (wasTop) {
                if (!(leaderboard.lastKey().equals(cows.get(measures[i].cow))) || wasCount != 1 || leaderboard.getOrDefault(cows.get(measures[i].cow), 0) != 1) {
                    ans++;
                }
            } else if (leaderboard.lastKey().equals(cows.get(measures[i].cow))) {
                ans++;
            }
        }

        out.println(ans);
        out.close();
    }

    public static class Measurement implements Comparable<Measurement> {
        int day;
        int cow;
        int change;
        public Measurement(int day, int cow, int change) {
            this.day = day;
            this.cow = cow;
            this.change = change;
        }

        public int compareTo(Measurement o) {
            return Integer.compare(day, o.day);
        }
    }
}