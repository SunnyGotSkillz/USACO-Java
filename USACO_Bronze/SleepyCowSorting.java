/*
PROBLEM: Sleepy Cow Sorting (USACO January 2019 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=892

Suppose that k instructions suffice. Then only the first k cows actively change positions. This means that the last n−k cows are already sorted in increasing order, 
with respect to each other. Conversely, suppose that the last n−k cows are sorted in increasing order. Is there a sequence of k instructions after which all n cows are 
sorted? The answer is yes: if k=0, then the cows are completely sorted already. If k>0, then the first cow can be inserted among the last n−k cows, such that now the 
last n+1−k cows are in increasing order. After repeating this k−1 more times, the last n cows are in increasing order, by the same argument. Of course, there are only 
n cows, so after only k instructions, the cows are sorted!
We've reduced the problem to computing the longest sorted suffix. This can be done in linear time by sweeping from the end of the array towards the front, 
so long as each element is smaller than its successor.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        int ans = n-1;
        for (int i = n-2; i >= 0; i--) {
            if (cows[i] < cows[i+1]) {
                ans--;
            } else {
                break;
            }
        }

        out.println(ans);
        out.close();
    }
}