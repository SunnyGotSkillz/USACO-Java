/*
PROBLEM: Back and Forth (USACO December 2018 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=857
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static int[] possible = new int[2000]; // good estimate for range of possible values in first tank
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        ArrayList<Integer> firstBuckets = new ArrayList<>();
        ArrayList<Integer> secondBuckets = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            firstBuckets.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < 10; i++) {
            secondBuckets.add(Integer.parseInt(st.nextToken()));
        }

        tuesday(1000, firstBuckets, secondBuckets);

        for (int i=0; i<2000; i++) ans += possible[i];

        //System.out.println(ans);
        out.println(ans);
        out.close();
    }

    static void friday(int b1milk, ArrayList<Integer> B1, ArrayList<Integer> B2) {
        for (int i=0; i<B2.size(); i++) {
            int x = B2.get(i);
            possible[b1milk + x] = 1; // record this value as possible
        }
    }

    static void thursday(int b1milk, ArrayList<Integer> B1, ArrayList<Integer> B2) {
        for (int i=0; i<B1.size(); i++) {
            int x = B1.get(i);
            ArrayList<Integer> new_B2 = B2; new_B2.add(x);
            ArrayList<Integer> new_B1 = B1; new_B1.remove(i);
            friday(b1milk - x, new_B1, new_B2);
        }
    }

    static void wednesday(int b1milk, ArrayList<Integer> B1, ArrayList<Integer> B2) {
        for (int i=0; i<B2.size(); i++) {
            int x = B2.get(i);
            ArrayList<Integer> new_B1 = B1; new_B1.add(x);
            ArrayList<Integer> new_B2 = B2; new_B2.remove(i);
            thursday(b1milk + x, new_B1, new_B2);
        }
    }

    static void tuesday(int b1milk, ArrayList<Integer> B1, ArrayList<Integer> B2) {
        for (int i=0; i<B1.size(); i++) {
            int x = B1.get(i);
            ArrayList<Integer> new_B2 = B2; new_B2.add(x);
            ArrayList<Integer> new_B1 = B1; new_B1.remove(i);
            wednesday(b1milk - x, new_B1, new_B2);
        }
    }
}