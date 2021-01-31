/*
PROBLEM: Maximum Median (Codeforces Div 2 C)
LINK: https://codeforces.com/contest/1201/problem/C

Basic example problem for Binary Search on Answer
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static long k;
    static long[] arr;

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        arr = new long[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        System.out.println(search());  
    }

    // binary searches for the correct answer in the entire search space of possible medians
    // We know that the number of operations required to raise the median to x increases monotonically as x increases, so we can use binary search.
    // For example if the maximum median is 5, we know that answers 1-4 would also return true but anything above 5 would return false.
    static long search() {
        long pos = 0; 
        long max = (long) 2E9;
        for(long a = max; a >= 1; a /= 2) {
            while(check(pos+a)) pos += a; // checks to see if this median would be less than or equal to the number of operations possible
        }
        return pos; 
    }

    // checks whether the number of given operations is sufficient to raise the median of the array to x
    static boolean check(long x) {
        long operationsNeeded = 0;
        // Starting at the current median of the sorted array, and going to the end of the array
        for(int i = (n-1)/2; i < n; i++) {
            // We need to see how much needs to be added to every element to make x the new median. 
            operationsNeeded += Math.max(0, x-arr[i]);
        }

        if(operationsNeeded <= k) { return true; }
        else { return false; }
    }
}
