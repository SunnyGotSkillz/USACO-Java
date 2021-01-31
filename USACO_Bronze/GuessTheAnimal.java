/*
PROBLEM: Guess The Animal (USACO January 2019 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=893

Create a HashMap with the animals and string arrays with the characteristics. The answer is M+1, where M is the max number of common characteristics between any pair
A and B of animals. This is because Elsie can keep asking the common chars and be left with only those 2 animals. At this point, to finalize the animal, just ask one
extra question.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("guess.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("guess.out")));

        int n = Integer.parseInt(in.readLine());
        HashMap<String, String[]> animals = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String animal = st.nextToken();
            int size = Integer.parseInt(st.nextToken());
            animals.put(animal, new String[size]);
            for (int j = 0; j < size; j++) {
                String ch = st.nextToken();
                animals.get(animal)[j] = ch;
            }
        }

        int m = 0;
        for (Map.Entry<String, String[]> a : animals.entrySet()) {
            for (Map.Entry<String, String[]> b : animals.entrySet()) {
                if (a.equals(b)) continue;
                String[] aChar = a.getValue(); String[] bChar = b.getValue();
                int common = 0;
                for (int i = 0; i < aChar.length; i++) {
                    for (int j = 0; j < bChar.length; j++) {
                        if (aChar[i].equals(bChar[j])) common++;
                    }
                }
                m = Math.max(m, common);
            }
        }

        out.println(m+1);
        out.close();
    }
}