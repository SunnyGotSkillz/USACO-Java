/*
PROBLEM: Just Stalling (USACO January 2021 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1085


*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main3 {
    static int n;
    static long[] a;
    static long[] b;
    static long ans = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());
        a = new long[n];
        b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st2.nextToken());
        }

        Arrays.sort(a);

        int[] available = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i] <= b[j]) {
                    available[i]++;
                }
            }
        }
        Arrays.sort(available);
        for (int i = 0; i < n; i++) {
            ans *= (available[i]-i);
        }

        System.out.println(Math.max(ans, 0));
    }
}

/* PARTIAL CREDIT: FIND ALL PERMUATIONS
public class JustStalling {
    static ArrayList<Long> permutation;
    static boolean[] chosen;
    static int n;
    static long[] a;
    static long[] b;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());
        a = new long[n];
        b = new long[n];
        permutation = new ArrayList<>();
        chosen = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st2.nextToken());
        }

        search();

        System.out.println(ans);
    }

    private static void search() {
        if (permutation.size() == n) { // process permutation
            ans++;
        } else {
            for (int i = 0; i < n; i++) {
                if (chosen[i]) continue;
                if (a[i] > b[permutation.size()]) continue;
                chosen[i] = true;
                permutation.add(a[i]);
                search();
                chosen[i] = false;
                permutation.remove(permutation.size()-1);
            }
        }
    }
}
*/