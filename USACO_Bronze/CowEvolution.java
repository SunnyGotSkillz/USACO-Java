/*
PROBLEM: Race (USACO January 2020 Bronze #3)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=941
SOLUTION: http://www.usaco.org/current/data/sol_evolution_bronze_open19.html

Create an array list with all the unique characteristics. Then create an array of array lists representing each population and their characteristics. Go through every
pair of characteristics and see if there is any "crossing", where a crossing is where one characteristic A appears once in a population, B appears once, and both appear
together in a population. This is because this is the simplest example of a case where a tree would not be proper.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int n;
    static ArrayList<String> all_characteristics;
    static ArrayList<String>[] chars;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("evolution.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("evolution.out")));

        n = Integer.parseInt(in.readLine());
        all_characteristics = new ArrayList<>();
        chars = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            chars[i] = new ArrayList<>(k);
            for (int j = 0; j < k; j++) {
                String s = st.nextToken();
                chars[i].add(s);
                boolean found = false;
                for (int c = 0; c < all_characteristics.size(); c++) {
                    if (all_characteristics.get(c).equals(s)) {
                        found = true;
                        break;
                    }
                }
                if (!found) all_characteristics.add(s);
            }
        }

        boolean ok = true;
        for (int a = 0; a < all_characteristics.size(); a++) {
            for (int b = a+1; b < all_characteristics.size(); b++) {
                if (crossing(a, b)) {
                    ok = false;
                    break;
                }
            }
            if (!ok) break;
        }

        if (ok) out.println("yes");
        else out.println("no");

        out.close();
    }

    static boolean crossing(int a, int b) {
        int A = 0, B = 0, AB = 0;
        for (int i = 0; i < n; i++) {
            ArrayList<String> v = chars[i];
            boolean has_a = false, has_b = false;
            for (int j = 0; j < v.size(); j++) {
                if (v.get(j).equals(all_characteristics.get(a))) has_a = true;
                if (v.get(j).equals(all_characteristics.get(b))) has_b = true;
            }
            if (has_a && has_b) AB++;
            else if (has_a) A++;
            else if (has_b) B++;
        }

        return AB > 0 && A > 0 && B > 0;
    }
}