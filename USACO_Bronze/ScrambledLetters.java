/*
PROBLEM: Scrambled Letters (USACO December 2012 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=206

The lowest position for a word will be when it is alphabetically sorted, but all the other words are reversely sorted. Similarly, the highest position for a word
will be when it is reversely sorted and all other words are alphabetically sorted. Create two arrays: one will be with all words alphabetically sorted by character
and the other will be all words alphatically sorted with characters reversely sorted. Then we can go through each input word and use the binarySearch method to 
determine where a sorted word would be placed in the reversely sorted array and where a reversely sorted word would be placed in the sorted array for the lowest and
highest positions.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ScrambledLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("scramble.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scramble.out")));

        int n = Integer.parseInt(in.readLine());
        String[] BEST = new String[n];
        String[] WORST = new String[n];
        String[] IN = new String[n];
        for(int i=0; i<n; i++) {
            IN[i] = in.readLine();
            char[] str = IN[i].toCharArray();
            Arrays.sort(str);
            BEST[i] = new String(str);
            WORST[i] = new StringBuilder(new String(str)).reverse().toString();
        }
        Arrays.sort(BEST);
        Arrays.sort(WORST);

        for (int i = 0; i < n; i++) {
            char[] arr = IN[i].toCharArray();
            Arrays.sort(arr);
            String bi = new String(arr);
            String wi = new StringBuilder(bi).reverse().toString();

            // Arrays.binarySearch: if value not found, returns -(insertionPoint - 1)
            int lo = Arrays.binarySearch(WORST, bi);
            if(lo < 0) lo = -(lo+1);
            lo++;

            int hi = Arrays.binarySearch(BEST, wi);
            if(hi < 0) hi = -(hi+1);
            else hi++;

            out.println(lo + " " + hi);
        }

        out.close();
    }
}