/*
PROBLEM: Diamond Collector (USACO US Open 2016 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=643

Sort the array of diamonds. The solution to this problem involves thinking about it in divisions. Imagine dividing the array at each point and the answer would be
the optimal number of diamonds on the left + the optimal number of diamonds on the right of that division. In order to find these optimal numbers, create two arrays
that represent the left and right sides. left[i] represents the optimal number to the left if the division was at i. We use a while loop to find these left and right
indices, but also since we want the optimal number, we use the arrays as prefix maximums, where at each index we only take the max/best possible number up to that point.
Then we simulate all the divisions and add the right/left numbers and find the max, which will be the answer.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class DiamondCollector {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] diamonds = new int[n];
        int[] rightIndex = new int[n];
        int[] leftIndex = new int[n];
        for (int i = 0; i < n; i++) {
            diamonds[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(diamonds);

        for (int i = 0; i < n; i++) {
            int r = i;
            while (r < n && Math.abs(diamonds[r]-diamonds[i]) <= k) {
                r++;
            }
            rightIndex[i] = r-1;
        }

        for (int i = n-1; i >= 0; i--) {
            int l = i;
            while (l >= 0 && Math.abs(diamonds[l]-diamonds[i]) <= k) {
                l--;
            }
            leftIndex[i] = l+1;
        }

        int[] leftSize = new int[n];
        for(int i = 0; i < n; i++) {
            leftSize[i] = i - leftIndex[i] + 1;
            if(i > 0) {
                leftSize[i] = Math.max(leftSize[i], leftSize[i-1]);
            }
        }

        int[] rightSize = new int[n];
        for(int i = n-1; i >= 0; i--) {
            rightSize[i] = rightIndex[i] - i + 1;
            if(i < n-1) {
                rightSize[i] = Math.max(rightSize[i], rightSize[i+1]);
            }
        }

        int ans = -1;
        for (int i = 0; i < n-1; i++) {
            ans = Math.max(ans, leftSize[i] + rightSize[i+1]);
        }

        System.out.println(ans);
    }
}