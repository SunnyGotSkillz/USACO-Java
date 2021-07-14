/*
PROBLEM: Acowdemia 1 (USACO US Open 2021 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1131

This solution does not implement a typical Bronze algorithm. For that, go to the official solution.

This solution uses a binary search on answer and check function to find the max possible h index.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Acowdemia1 {
    static int n;
    static int l;
    static int[] c;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(c);

        System.out.println(maxSearch());
    }

    static int maxSearch() { // searches for maximum true
        int left = 0;
        int right = n;
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (check(mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    static boolean check(int h) {
        int count = 0;
        int tempL = l;
        for (int i = n-1; i > n-1-h; i--) {
            if (c[i] < h && h - c[i] == 1) {
                tempL -= 1;
            } else if (c[i] < h && h - c[i] > 1) {
                return false;
            }
            count++;
            if (tempL < 0) return false;
        }

        return count >= h && tempL >= 0;
    }
}