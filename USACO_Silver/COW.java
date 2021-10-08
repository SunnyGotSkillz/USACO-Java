/*
PROBLEM: COW (USACO February 2015 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=527

Keep track of the number of 'C's and 'CO's and 'COW's. When we see a 'C', increment the C counter. When we see the 'O', increment the CO counter by how many Cs there
are. When we see a 'W', increment the answer by how many COs there are.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class COW {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cow.out")));

        int n = Integer.parseInt(in.readLine());
        String str = in.readLine();
        long cCount = 0;
        long coCount = 0;
        long cowCount = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'C') cCount++;
            if (str.charAt(i) == 'O') coCount += cCount;
            if (str.charAt(i) == 'W') cowCount += coCount;
        }

        out.println(cowCount);
        out.close();
    }
}