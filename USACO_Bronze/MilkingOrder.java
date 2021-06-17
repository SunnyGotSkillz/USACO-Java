/*
PROBLEM: Milking Order (USACO US Open 2018 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=832

Figure out if any number in the given order matches the positions we already know. Then figure out if cow 1 is in the giver order. Use different methods of simulation
based on the cases. For example if cow 1 is not in the given order, then we want to put the cows as late as possible each time to make room in an earlier spot for cow 1.
If cow 1 is there, then put cows as early as possible to give cow 1 the earliest possible cow.

The cleaner solution would be to test every possible position for cow 1 and using a check method to see if this position would work. Then if it does, we loop over 
the cows in the given order. For each cow, we want to place it in the earliest free position such that the cow is positioned after the previous cow in the order.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class MilkingOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        Arrays.fill(cows, -1);

        st = new StringTokenizer(in.readLine());
        int[] social = new int[m];
        boolean one = false;
        for (int i = 0; i < m; i++) {
            social[i] = Integer.parseInt(st.nextToken());
            if (social[i] == 1) one = true;
        }

        int[] placed = new int[n+1];
        Arrays.fill(placed, -1);
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken())-1;
            cows[p] = c;
            placed[c] = p;
        }

        int i, j = 0;
        boolean b = false;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (social[i] == cows[j]) {
                    b = true;
                    break;
                }
            }
            if (b) break;
        }

        if (b && !one) {
            int c = i-1;
            if (c >= 0) {
                for (int a = j; a >= 0; a--) {
                    if (cows[a] == -1) {
                        cows[a] = social[c];
                        placed[social[c]] = a;
                        c--;
                    }
                    if (c < 0) break;
                }
            }

            c = m - 1;
            if (c > i) {
                for (int a = n-1; a > j; a--) {
                    if (cows[a] == -1 && placed[social[c]] < 0) {
                        cows[a] = social[c];
                        placed[social[c]] = a;
                        c--;
                    } else if (placed[social[c]] >= 0) {
                        a = placed[social[c]];
                        c--;
                    }

                    if (c <= i) break;
                }
            }

        } else if (b && one) {
            int o;
            for (o = 0; o < m; o++) {
                if (social[o] == 1) break;
            }
            if (o < i) {
                int c = 0;
                for (int a = 0; a < j; a++) {
                    if (cows[a] == -1) {
                        cows[a] = social[c];
                        placed[social[c]] = a;
                        c++;
                    }
                    if (c >= i) break;
                }
            } else if (o > i) {
                int c = i + 1;
                if (c < m) {
                    for (int a = j; a < n; a++) {
                        if (cows[a] == -1 && placed[social[c]] < 0) {
                            cows[a] = social[c];
                            placed[social[c]] = a;
                            c++;
                        } else if (placed[social[c]] >= 0) {
                            a = placed[social[c]];
                            c++;
                        }

                        if (c >= m) break;
                    }
                }
            }
        } else if (!b && !one){
            int c = m-1;
            for (int a = n-1; a >= 0; a--) {
                if (cows[a] == -1 && placed[social[c]] < 0) {
                    cows[a] = social[c];
                    placed[social[c]] = a;
                    c--;
                }
                if (c < 0) break;
            }
        } else {
            int c = 0;
            for (int a = 0; a < n; a++) {
                if (cows[a] == -1 && placed[social[c]] < 0) {
                    cows[a] = social[c];
                    placed[social[c]] = a;
                    c++;
                }
                if (c >= m) break;
            }
        }

        int ans = 1;
        if (placed[1] > 0) ans = placed[1];
        else {
            for (int a = 0; a < n; a++) {
                if (cows[a] == -1) {
                    ans = a;
                    break;
                }
            }
        }

        out.println(ans+1);
        out.close();
    }
}