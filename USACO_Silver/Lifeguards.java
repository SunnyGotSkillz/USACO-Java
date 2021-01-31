/*
PROBLEM: Lifeguards (USACO January 2018 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=786
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));

        int n = Integer.parseInt(in.readLine());
        TreeSet<Integer> set = new TreeSet<>(); // which lifeguards are currently working
        State[] l = new State[2*n]; // all times in which a lifeguards starts or ends work
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            l[2*i] = new State(x, i);
            l[2*i+1] = new State(y, i);
        }
        Arrays.sort(l);

        int actualCover = 0; // full time covered by all lifeguards
        int last = 0; // time covered by previous lifeguard
        int[] alone = new int[n]; // how much time each lifeguard worked alone
        for (State s : l) {
            if (set.size() == 1) {
                alone[set.first()] += s.time - last; // adds to how much that lifeguard works alone
            }
            if (!set.isEmpty()) {
                actualCover += s.time - last;
            }
            if (set.contains(s.index)) {
                set.remove(s.index);
            } else {
                set.add(s.index);
            }
            last = s.time;
        }

        int ret = 0;
        for (int i : alone) {
            ret = Math.max(ret, actualCover - i); // find how much difference each lifeguard's time makes to the overall time worked by all lifeguards
        }

        out.println(ret);
        out.close();
    }

    static class State implements Comparable<State> {
        public int time;
        public int index;
        public State(int a, int b) {
            time = a;
            index = b;
        }

        public int compareTo(State o) {
            return time - o.time;
        }
    }
}

