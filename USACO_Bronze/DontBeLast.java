/*
PROBLEM: Dont Be Last (USACO January 2017 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=687
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class DontBeLast {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("notlast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));

        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[7]; // Bessie, Elsie, Daisy, Gertie, Annabelle, Maggie, and Henrietta
        String[] names = {"Bessie", "Elsie", "Daisy", "Gertie", "Annabelle", "Maggie", "Henrietta"};
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String name = st.nextToken();
            int milk = Integer.parseInt(st.nextToken());
            if (name.equals("Bessie")) {
                cows[0] += milk;
            } else if (name.equals("Elsie")) {
                cows[1] += milk;
            } else if (name.equals("Daisy")) {
                cows[2] += milk;
            } else if (name.equals("Gertie")) {
                cows[3] += milk;
            } else if (name.equals("Annabelle")) {
                cows[4] += milk;
            } else if (name.equals("Maggie")) {
                cows[5] += milk;
            } else if (name.equals("Henrietta")) {
                cows[6] += milk;
            }
        }

        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < 7; i++) {
            if (cows[i] < min) min = cows[i];
            if (cows[i] > max) max = cows[i];
        }

        String ans = "Tie";
        for (int i = min+1; i <= max; i++) {
            int counter = 0;
            int index = -1;
            for (int j = 0; j < 7; j++) {
                if (cows[j] == i && counter == 0) {
                    counter++;
                    index = j;
                } else if (cows[j] == i && counter > 0) {
                    counter++;
                    break;
                }
            }
            if (counter == 1) {
                ans = names[index];
                break;
            } else if (counter > 1) {
                ans = "Tie";
                break;
            }
        }

        out.println(ans);
        out.close();
    }
}