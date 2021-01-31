/*
PROBLEM: Uddered But Not Herd (USACO January 2021 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1083
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        char[] cowphabet = in.readLine().toCharArray();
        char[] john = in.readLine().toCharArray();
        int ans = 1;
        int[] index = new int[john.length];
        for (int i = 0; i < john.length; i++) {
            for (int k = 0; k < cowphabet.length; k++) {
                if (john[i] == cowphabet[k]) {
                    index[i] = k;
                }
            }
        }

        for (int i = 1; i < index.length; i++) {
            if (index[i] <= index[i-1]) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}