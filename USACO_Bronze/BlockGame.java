/*
LANG: JAVA
TASK: Block Game (USACO December 2016 Bronze #2)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=664
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("blocks.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));

        int n = Integer.parseInt(in.readLine());
        int[] letters = new int[26];
        for (int i = 0; i < n; i++) {
            int[] seen = new int[26];
            StringTokenizer st = new StringTokenizer(in.readLine());
            String first = st.nextToken();
            String next = st.nextToken();
            for (int c = 0; c < first.length(); c++) {
                char currLetter = first.charAt(c);
                int currASC = currLetter;
                letters[currASC-97] = letters[currASC-97] + 1;
                seen[currASC-97]++;
            }
            if (!next.equals(first)) {
                for (int c = 0; c < next.length(); c++) {
                    char currLetter = next.charAt(c);
                    int currASC = currLetter;
                    if (seen[currASC-97] == 0) {
                        letters[currASC-97]++;
                    } else if (seen[currASC-97] > 0) {
                        seen[currASC-97]--;
                    }
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            out.println(letters[i]);
        }

        out.close();
    }
}