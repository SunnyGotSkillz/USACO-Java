/*
PROBLEM: Do You Know Your ABCs (USACO December 2020 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem&cpid=1047
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class DoYouKnowYourABCs {
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        nums = new int[7];
        for (int i = 0; i < 7; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        boolean found = false;
        int a;
        int b = 1;
        int c = 2;
        for (a = 0; a <= 4; a++) {
            for (b = a+1; b <= 5; b++) {
                for (c = b+1; c <= 6; c++) {
                    if (check(a, b, c)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) break;
        }

        System.out.println(nums[a] + " " + nums[b] + " " + nums[c]);
    }

    public static boolean check(int a, int b, int c) {
        boolean ab = false;
        boolean bc = false;
        boolean ac = false;
        boolean abc = false;
        for (int i = 0; i < 7; i++) {
            if (i != a || i != b || i != c) {
                if (nums[i] == (nums[a] + nums[b]) && !ab) {
                    ab = true;
                } else if (nums[i] == (nums[b] + nums[c]) && !bc) {
                    bc = true;
                } else if (nums[i] == (nums[a] + nums[c]) && !ac) {
                    ac = true;
                } else if (nums[i] == (nums[a] + nums[b] + nums[c]) && !abc) {
                    abc = true;
                }
            }
        }

        return ab && bc && ac && abc;
    }
}