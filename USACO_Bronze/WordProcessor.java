/*
PROBLEM: Word Processor (USACO January 2020 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=987
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class WordProcessor {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("word.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("word.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = st.nextToken();
        }
        int[] numChars = new int[n];
        for (int i = 0; i < n; i++) {
            numChars[i] = words[i].length();
        }

        int i = 0;
        ArrayList<String> line = new ArrayList<>();
        int sum = 0;
        while (i < n) {
            if (sum+numChars[i] <= k) {
                line.add(words[i]);
                sum += numChars[i];
                i++;
            } else {
                for (int j = 0; j < line.size(); j++) {
                    if (j == line.size()-1) out.print(line.get(j));
                    else out.print(line.get(j) + " ");
                }
                sum = 0;
                line = new ArrayList<>();
                out.println();
            }
        }
        for (int j = 0; j < line.size(); j++) {
            if (j == line.size()-1) out.print(line.get(j));
            else out.print(line.get(j) + " ");
        }

        out.close();
    }
}