/*
PROBLEM: High Card Low Card (USACO December 2015 Gold #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=573

This problem uses a greedy algorithm similar to USACO December 2015 Silver #2. There is an added complication of having the low cards win in the second half of the
game. 
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));

        int n = Integer.parseInt(in.readLine());
        boolean[] cards = new boolean[2*n]; // true for elsie and false for bessie
        int[] elsieHigh = new int[n/2]; // represents cards Elsie will play in the first n/2 rounds
        int[] elsieLow = new int[n/2]; // represents cards Elsie will play in the last n/2 rounds
        int[] bessieHigh = new int[n/2]; // represents cards Bessie will play in the first n/2 rounds
        int[] bessieLow = new int[n/2]; // represents cards Bessie will play in the last n/2 rounds
        for (int i = 0; i < n/2; i++) {
            elsieHigh[i] = Integer.parseInt(in.readLine());
            cards[elsieHigh[i]-1] = true;
        }
        for (int i = 0; i < n/2; i++) {
            elsieLow[i] = Integer.parseInt(in.readLine());
            cards[elsieLow[i]-1] = true;
        }

        // Find the cards that Bessie has based on the given Elsie cards
        int a = 0; int b = (2*n) - 1;
        while (a < n/2 && b >= 0) {
            if (!cards[b]) {
                bessieHigh[a] = b+1;
                a++;
            }
            b--;
        }
        a = 0;
        while (a < n/2 && b >= 0) {
            if (!cards[b]) {
                bessieLow[a] = b+1;
                a++;
            }
            b--;
        }
        Arrays.sort(bessieHigh);
        Arrays.sort(bessieLow);
        Arrays.sort(elsieHigh);
        Arrays.sort(elsieLow);

        int ans = 0;
        int be = 0; int e = 0;
        while (be < bessieHigh.length && e < elsieHigh.length) { // Go thru the first n/2 rounds
            if (bessieHigh[be] > elsieHigh[e]) {
                be++; e++;
                ans++;
            } else if (bessieHigh[be] < elsieHigh[e]) {
                be++;
            }
        }

        be = 0; e = 0;
        while (be < bessieLow.length && e < elsieLow.length) { // go thru the last n/2 rounds
            if (bessieLow[be] < elsieLow[e]) {
                be++; e++;
                ans++;
            } else if (bessieLow[be] > elsieLow[e]) {
                e++;
            }
        }

        out.println(ans);
        out.close();
    }
}