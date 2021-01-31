/*
PROBLEM: Mad Scientist (USACO February 2020 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1012
*/

import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("breedflip.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));

        int n = Integer.parseInt(in.readLine());
        String a = in.readLine();
        String b = in.readLine();
        boolean same = false;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!same && a.charAt(i) != b.charAt(i)) {
                same = true;
            } else if (same && a.charAt(i) == b.charAt(i)) {
                same = false;
                ans++;
            }
        }

        out.println(ans);
        out.close();
    }
}
