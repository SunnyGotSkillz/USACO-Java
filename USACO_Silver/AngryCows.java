/*
PROBLEM: Angry Cows (USACO January 2016 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=594

For this problem, we have to binary search over the possible search space of R. For each possible R value, we use a check function to find how many cows are needed
for that particular R and check if it is <= the number of possible cows. We search for the minimum value that returns true using binary search because the search space
is monotonic. A value of 5 would work for anything greater than 5, but for nothing less than 5.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int k;
    static int[] bales;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("angry.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bales = new int[n];
        for (int i = 0; i < n; i++) {
            bales[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(bales);

        out.println(binSearch(0, 1000000000));
        out.close();
    }

    static int binSearch(int low, int high) {
        if (low == high) return low;
        if (low + 1 == high) {
            if(check(low)) return low;
            return high;
        }
        int mid = (low+high)/2;
        if(check(mid)) return binSearch(low,mid);
        else return binSearch(mid+1,high);
    }

    static boolean check(int r) {
        int cowsNeeded = 1;
        int start = bales[0];
        int end = start + (2*r);
        for (int i = 0; i < n; i++) {
            if (bales[i] > end) {
                cowsNeeded++;
                start = bales[i];
                end = start + (2*r);
            }
        }

        return (cowsNeeded <= k);
    }
}