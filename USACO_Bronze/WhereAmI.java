/*
PROBLEM: Where Am I (USACO December 2019 Bronze #2)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=964

Do a minimum binary search on all the possible values of k (1 to n), since answer set is monotonic. The check function will store all consecutive substrings with length
k. Then check if there are any duplicates.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class WhereAmI {
    static String pattern;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("whereami.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));

        n = Integer.parseInt(in.readLine());
        pattern = in.readLine();
        int ans = binSearch(1, n);

        out.println(ans);
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

    static boolean check(long k) {
        ArrayList<String> substrings = new ArrayList<>();
        for (int i = 0; i <= n-k; i++) {
            StringBuilder currentBuilder = new StringBuilder();
            for (int j = i; j <= i+k-1; j++) {
                currentBuilder.append(pattern.charAt(j));
            }
            substrings.add(currentBuilder.toString());
        }

        boolean possible = true;
        for (int i = 0; i < substrings.size(); i++) {
            for (int j = i+1; j < substrings.size(); j++) {
                if (substrings.get(i).equals(substrings.get(j))) {
                    possible = false;
                    break;
                }
            }
            if (!possible) break;
        }

        return possible;
    }
}