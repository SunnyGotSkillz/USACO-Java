/*
PROBLEM: Angry Cows (USACO January 2016 Bronze #2)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=592

Try exploding from each bale and seeing the max number of bales that will explode by taking each side of the inital bale separately.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class AngryCows {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("angry.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        int n = Integer.parseInt(in.readLine());
        int[] bales = new int[n];
        for (int i = 0; i < n; i++) {
            bales[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(bales);

        int ans = -1;
        for (int i = 0; i < n; i++) {
            int count = 1;
            int r = i+1;
            int t = 1;
            int bale = bales[i];
            while (r < n) {
                for (int j = r; j < n; j++) {
                    if (bales[j] <= bale + t) {
                        count++;
                    } else {
                        r = j;
                        break;
                    }
                }
                if (bale == bales[r-1]) {
                    break;
                }
                bale = bales[r-1];
                t++;
            }

            int l = i-1;
            t = 1;
            bale = bales[i];
            while (l >= 0) {
                for (int j = l; j >= 0; j--) {
                    if (bales[j] >= bale - t) {
                        count++;
                    } else {
                        l = j;
                        break;
                    }
                }
                if (bale == bales[l+1]) {
                    break;
                }
                bale = bales[l+1];
                t++;
            }

            ans = Math.max(ans, count);
        }

        out.println(ans);
        out.close();
    }
}