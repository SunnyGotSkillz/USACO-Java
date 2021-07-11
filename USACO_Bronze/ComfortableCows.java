/*
PROBLEM: Comfortable Cows (USACO February 2021 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1108

Brute force will only pass the first 4 test cases.

Solution for all test cases: At each step when we add the ith cow, the "comfortable status" of only the cows surrounding that added cow can be changed. Keep track
of where in the grid there are cows and for each cow, how many currently surround her. Then for each step, we check and update the surroundings array as needed.
Based on the number of cows that now surround each cow, we can tell how many total more or less comfortable cows we have.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ComfortableCows {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        boolean[][] cows = new boolean[1001][1001];
        int[][] surround = new int[1001][1001];
        int num = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows[x][y] = true;
            if (x != 1000 && cows[x+1][y]) {
                surround[x][y]++;
                surround[x+1][y]++;
                if (surround[x+1][y] == 3) num++;
                else if (surround[x+1][y] == 4) num--;
            }

            if (y != 1000 && cows[x][y+1]) {
                surround[x][y]++;
                surround[x][y+1]++;
                if (surround[x][y+1] == 3) num++;
                else if (surround[x][y+1] == 4) num--;
            }

            if (x != 0 && cows[x-1][y]) {
                surround[x][y]++;
                surround[x-1][y]++;
                if (surround[x-1][y] == 3) num++;
                else if (surround[x-1][y] == 4) num--;
            }

            if (y != 0 && cows[x][y-1]) {
                surround[x][y]++;
                surround[x][y-1]++;
                if (surround[x][y-1] == 3) num++;
                else if (surround[x][y-1] == 4) num--;
            }

            if (surround[x][y] == 3) num++;
            System.out.println(num);
        }
    }
}