/*
PROBLEM: Year of the Cow (USACO February 2021 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1107

Simulation.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class YearOfTheCow {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        String[] cows = new String[n]; //unique cows every statement
        boolean[] when = new boolean[n]; // true = previous, false = next
        String[] year = new String[n];
        String[] from = new String[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cows[i] = st.nextToken();
            st.nextToken(); st.nextToken();
            when[i] = st.nextToken().equals("previous");
            year[i] = st.nextToken();
            st.nextToken(); st.nextToken();
            from[i] = st.nextToken();
        }

        int[] yearsFromBessie = new int[n]; // Bessie at position 0
        List<String> order = Arrays.asList("Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat");
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (from[i].equals("Bessie")) {
                if (when[i]) {
                    yearsFromBessie[i] -= (12-order.indexOf(year[i]));
                } else {
                    yearsFromBessie[i] += year[i].equals("Ox") ? 12 : order.indexOf(year[i]);
                }
            } else {
                int j;
                for (j = 0; j < i; j++) {
                    if (cows[j].equals(from[i])) {
                        break;
                    }
                }
                int a = order.indexOf(year[i]);
                int b = order.indexOf(year[j]);
                if (when[i] && a < b) {
                    yearsFromBessie[i] += yearsFromBessie[j] - (b-a);
                } else if (when[i] && a >= b) {
                    yearsFromBessie[i] += yearsFromBessie[j] - (b+(12-a));
                } else if (!when[i] && a > b) {
                    yearsFromBessie[i] += yearsFromBessie[j] + (a-b);
                } else if (!when[i] && a <= b) {
                    yearsFromBessie[i] += yearsFromBessie[j] + ((12-b)+a);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (cows[i].equals("Elsie")) {
                ans = yearsFromBessie[i];
            }
        }

        System.out.println(Math.abs(ans));
    }
}