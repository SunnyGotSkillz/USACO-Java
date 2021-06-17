/*
PROBLEM: Social Distancing 1 (USACO US Open 2020 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1035
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class SocialDistancing1 {
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new FileReader("socdist1.in"));
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist1.out")));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        String str = in.readLine();
        char[] barn = str.toCharArray();
        int lb = 0; int ub = 0;
        int blb = 0; int bub = 0;
        for (int i = 0; i < n; i++) {
            if (barn[i] == '1') {
                if (ub-lb > bub-blb) {
                    bub = ub; blb = lb;
                }
                lb = i; ub = i;
            } else if (barn[i] == '0') {
                ub++;
            }
        }
        if (ub-lb > bub-blb) {
            bub = ub; blb = lb;
        }

        if (bub > barn.length-1) bub = barn.length-1;
        if (barn[blb] == '1') blb++;
        if (barn[bub] == '1') bub--;
        if (bub == barn.length-1 && barn[bub] == '0') {
            barn[barn.length-1] = '1';
        } else if (bub == blb) {
            barn[bub] = '1';
        } else if (blb == 0 && barn[blb] == '0') {
            barn[0] = '1';
        } else {
            barn[blb + ((bub-blb) / 2)] = '1';
        }

        lb = 0; ub = 0;
        blb = 0; bub = 0;
        for (int i = 0; i < n; i++) {
            if (barn[i] == '1') {
                if (ub-lb > bub-blb) {
                    bub = ub; blb = lb;
                }
                lb = i; ub = i;
            } else if (barn[i] == '0') {
                ub++;
            }
        }
        if (ub-lb > bub-blb) {
            bub = ub; blb = lb;
        }

        if (bub > barn.length-1) bub = barn.length-1;
        if (barn[blb] == '1') blb++;
        if (barn[bub] == '1') bub--;
        if (bub == barn.length-1 && barn[bub] == '0') {
            barn[barn.length-1] = '1';
        } else if (bub == blb) {
            barn[bub] = '1';
        } else if (blb == 0 && barn[blb] == '0') {
            barn[0] = '1';
        } else {
            barn[blb + ((bub-blb) / 2)] = '1';
        }

        ArrayList<Integer> occ = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (barn[i] == '1') occ.add(i);
        }

        int dist = Integer.MAX_VALUE;
        for (int i = 1; i < occ.size(); i++) {
            dist = Math.min(dist, occ.get(i) - occ.get(i-1));
        }

        char[] barn2 = str.toCharArray();
        lb = 0; ub = 0;
        blb = 0; bub = 0;
        for (int i = 0; i < n; i++) {
            if (barn2[i] == '1') {
                if (ub-lb > bub-blb) {
                    bub = ub; blb = lb;
                }
                lb = i; ub = i;
            } else if (barn2[i] == '0') {
                ub++;
            }
        }
        if (ub-lb > bub-blb) {
            bub = ub; blb = lb;
        }
        if (bub > barn2.length-1) bub = barn2.length-1;
        if (barn2[blb] == '1') blb++;
        if (barn2[bub] == '1') bub--;
        barn2[blb + ((bub-blb)/3)] = '1';
        barn2[bub - ((bub-blb)/3)] = '1';

        ArrayList<Integer> occ2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (barn2[i] == '1') occ2.add(i);
        }

        int dist2 = Integer.MAX_VALUE;
        for (int i = 1; i < occ2.size(); i++) {
            dist2 = Math.min(dist2, occ2.get(i) - occ2.get(i-1));
        }

        System.out.println(Math.max(dist, dist2));
        //out.println(Math.max(dist, dist2));
        //out.close();
    }
}



/* PARTIAL SOLUTION - BRUTE FORCE ADDING 2 COWS TO THE STALLS AND FINDING MAX VALUE OF D
import java.util.*;
import java.io.*;
import java.lang.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("socdist1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist1.out")));

        int n = Integer.parseInt(in.readLine());
        String str = in.readLine();
        char[] stalls = str.toCharArray();
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (stalls[i] == '0') {
                stalls[i] = '1';
                for (int j = 0; j < n; j++) {
                    if (stalls[j] == '0') {
                        stalls[j] = '1';
                        ArrayList<Integer> occ = new ArrayList<>();
                        for (int k = 0; k < n; k++) {
                            if (stalls[k] == '1') occ.add(k);
                        }

                        int d = Integer.MAX_VALUE;
                        for (int k = 1; k < occ.size(); k++) {
                            d = Math.min(d, occ.get(k) - occ.get(k-1));
                        }

                        ans = Math.max(ans, d);
                        stalls[j] = '0';
                    }
                }
                stalls[i] = '0';
            }
        }

        out.println(ans);
        out.close();
    }
}
*/