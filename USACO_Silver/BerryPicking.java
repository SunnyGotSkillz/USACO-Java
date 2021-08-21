/*
PROBLEM: Berry Picking (USACO January 2020 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=990

Let b = the number of berries per basket for Elsie. We want to maximize this number and have Elsie take the same number for all her buckets. Then based on this number b,
and how many are left over, we can calculate how many berries are left for Bessie. We try all possible values of b until we get a max answer for Bessie.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class BerryPicking {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("berries.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] berries = new int[n];
        st = new StringTokenizer(in.readLine());
        int max = -1;
        for (int i = 0; i < n; i++) {
            berries[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, berries[i]);
        }

        int ans = -1;
        for (int b = 1; b <= max; b++) {
            int basketsFilled = 0;
            int[] leftOver = new int[n];
            for (int i = 0; i < n; i++) {
                basketsFilled += berries[i] / b;
                leftOver[i] = berries[i] % b;
            }

            if (basketsFilled >= k) {
                ans = Math.max(ans, b*(k/2));
            } else if (basketsFilled >= k/2) {
                int bessieBerries = (basketsFilled-(k/2)) * b;
                Arrays.sort(leftOver);
                int i = n-1;
                int filled = 0;
                while (i >= 0 && filled < (k-basketsFilled)) {
                    bessieBerries += leftOver[i];
                    filled++;
                    i--;
                }
                ans = Math.max(ans, bessieBerries);
            }
        }

        out.println(ans);
        out.close();
    }
}