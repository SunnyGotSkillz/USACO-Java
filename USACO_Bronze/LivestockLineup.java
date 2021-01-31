/*
PROBLEM: Livestock Lineup (USACO December 2019 Bronze #3)
LINK: http://usaco.org/index.php?page=viewproblem2&cpid=965

Try all permutations of the 8 cows and for each one, test to see if it fits the constraints.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static ArrayList<String> permutation;
    static boolean[] chosen;
    static int count = 8;
    static int n;
    static String[] cows;
    static String[][] constraints;
    static boolean done = false;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lineup.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));

        cows = new String[]{"Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"};
        Arrays.sort(cows);
        n = Integer.parseInt(in.readLine());
        constraints = new String[n][2];
        permutation = new ArrayList<>();
        chosen = new boolean[8];
        for (int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            constraints[i][0] = line.nextToken();
            line.nextToken(); line.nextToken(); line.nextToken(); line.nextToken();
            constraints[i][1] = line.nextToken();
        }

        search();

        for (String c : permutation) {
            out.println(c);
        }
        out.close();
    }

    private static void search() {
        if (permutation.size() == count) { // process permutation
            boolean poss = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < permutation.size(); j++) {
                    if (permutation.get(j).equals(constraints[i][0])) {
                        if (j == 0 && (permutation.get(j+1).equals(constraints[i][1]))) {
                            break;
                        } else if (j == permutation.size()-1 && (permutation.get(j-1).equals(constraints[i][1]))) {
                            break;
                        } else if (((j!=0) && (j != permutation.size()-1)) && ((permutation.get(j-1).equals(constraints[i][1])) || (permutation.get(j+1).equals(constraints[i][1])))) {
                            break;
                        } else {
                            poss = false;
                            break;
                        }
                    }
                }

                if (!poss) break;
            }
            if (poss) done = true;
        } else {
            for (int i = 0; i < count; i++) {
                if (chosen[i]) continue;
                chosen[i] = true;
                permutation.add(cows[i]);
                search();
                if (done) return;
                chosen[i] = false;
                permutation.remove(permutation.size()-1);
            }
        }
    }
}