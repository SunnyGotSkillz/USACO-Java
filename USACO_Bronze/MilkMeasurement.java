/*
ID: sunnygotskillz
LANG: JAVA
TASK: Milk Measurement (USACO December 2017 Bronze #3)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));

        int ans = 0;
        int n = Integer.parseInt(f.readLine());
        int[] M = new int[100];
        int[] E = new int[100];
        int[] B = new int[100];
        for (int i = 0; i < 100; i++) {
            M[i] = 7;
            E[i] = 7;
            B[i] = 7;
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int day = Integer.parseInt(st.nextToken());
            String cow = st.nextToken();
            int change = Integer.parseInt(st.nextToken());
            switch (cow) {
                case "Mildred":
                    for (int a = day-1; a < 100; a++) {
                        M[a] += change;
                    }
                    break;
                case "Elsie":
                    for (int a = day-1; a < 100; a++) {
                        E[a] += change;
                    }
                    break;
                case "Bessie":
                    for (int a = day-1; a < 100; a++) {
                        B[a] += change;
                    }
                    break;
            }
        }

        boolean b = true;
        boolean m = true;
        boolean e = true;
        int currentMax = 7;
        for (int c = 0; c < 100; c++) {
            boolean changedOnce = false;
            currentMax = Math.max(M[c], Math.max(E[c], B[c]));

            if (M[c] != currentMax && m) {
                m = false;
                changedOnce = true;
            }
            if (E[c] != currentMax && e) {
                e = false;
                changedOnce = true;
            }
            if (B[c] != currentMax && b) {
                b = false;
                changedOnce = true;
            }

            if (M[c] == currentMax && !m) {
                m = true;
                changedOnce = true;
            }
            if (E[c] == currentMax && !e) {
                e = true;
                changedOnce = true;
            }
            if (B[c] == currentMax && !b) {
                b = true;
                changedOnce = true;
            }

            if (changedOnce) ans++;
        }


        out.println(ans);
        out.close();
    }
}