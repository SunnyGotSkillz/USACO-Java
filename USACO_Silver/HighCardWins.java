/*
PROBLEM: High Card Wins (USACO December 2015 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=571

Store the Bessie and Elsie cards in two sorted arrays. Then compare the values in both arrays. This is a greedy algorithm because we always try to take the smallest 
card that is still bigger than Elsie's.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));

        int n = Integer.parseInt(in.readLine());
        int[] elsie = new int[n];
        int[] bessie = new int[n];
        for (int i = 0; i < n; i++) {
            elsie[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(elsie);

        int j = 0;
        int k = 0;
        int counter = 1;
        while (k < n && counter <= 2*n) {
            if (j >= n) {
                bessie[k] = counter;
                k++;
            } else if (elsie[j] != counter) {
                bessie[k] = counter;
                k++;
            } else {
                j++;
            }
            counter++;
        }

        int b = 0; int e = 0;
        int points = 0;
        while (b < n && e < n) {
            if (bessie[b] > elsie[e]) {
                points++;
                b++;
                e++;
            } else if (bessie[b] < elsie[e]) {
                b++;
            }
        }

        out.println(points);
        out.close();
    }
}