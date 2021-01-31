/*
PROBLEM: Convention (USACO December 2018 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=858&lang=en

For this problem, we have to binary search over the possible search space. We sort the cow times in increasing order because this is most optimal. Then, we run binary
search thru all the possible answers because the search space is monotonic. It is monotonic because the problem says "minimum max" or in other words, if the maximum 
waiting time is impossible to fulfill with the given number of buses, then anything less will also be impossible. Our check function counts the number of the buses 
we would need if every cow only would have to wait <= x minutes. We add one to the bus counter when either the bus is full or when a cow has waited more than the 
maximum waiting time.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static int m;
    static int c;
    static int[] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("convention.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);

        out.println(binSearch(0, 1000000000));
        out.close();
    }

    static int binSearch(int low, int high) {
        if (low == high) return low;
        if (low + 1 == high) {
            if(pos(low)) return low;
            return high;
        }
        int mid = (low+high)/2;
        if(pos(mid)) return binSearch(low,mid);
        else return binSearch(mid+1,high);
    }

    static boolean pos(int x) {
        int busNeeded = 1;
        int counter = 1;
        int first = 0;
        for (int i = 1; i < n; i++) {
            if ((cows[i] - cows[first] > x) || counter == c) {
                busNeeded++;
                first = i;
                counter = 1;
            } else {
                counter++;
            }
        }
        return (busNeeded <= m);
    }
}