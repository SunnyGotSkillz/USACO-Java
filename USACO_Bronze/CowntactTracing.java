/*
PROBLEM: Cowntact Tracing (USACO US Open 2020 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1037
SOLUTION: http://www.usaco.org/current/data/sol_tracing_bronze_open20.html

Complete search over every possible patient zero and value of k. Then simulate with those values and see if it matches up with the given end results. k can have
and upper bound of infinity if it is greater than 250, the max time.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class CowntactTracing {
    static boolean[] cow_ends_infected = new boolean[101];
    static int n;
    static int[] cowx = new int[251]; // Store x and y times separately in big array to have it sorted easily
    static int[] cowy = new int[251];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("tracing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tracing.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        String farm = in.readLine();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(in.readLine());
            int time = Integer.parseInt(st.nextToken());
            cowx[time] = Integer.parseInt(st.nextToken());
            cowy[time] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            cow_ends_infected[i] = farm.charAt(i-1) == '1';
        }

        boolean[] possible_i = new boolean[101]; // possible patient zeros
        boolean[] possible_k = new boolean[252]; // possible k values
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 251; j++) {
                if (consistent_with_data(i, j)) {
                    possible_i[i] = true;
                    possible_k[j] = true;
                }
            }
        }

        int lower_K = 251, upper_K = 0, num_patient_zero = 0;
        for (int K = 0; K <= 251; K++) if (possible_k[K]) upper_K = K;
        for (int K=251; K>=0; K--) if (possible_k[K]) lower_K = K;
        for (int i=1; i<=n; i++) if (possible_i[i]) num_patient_zero++;

        out.print(num_patient_zero + " " + lower_K + " ");
        if (upper_K == 251) out.print("Infinity");
        else out.print(upper_K);
        out.close();
    }

    static boolean consistent_with_data(int zero, int k) { // simulate infections with these values to see if valid
        boolean[] infected = new boolean[101];
        int[] handshakes = new int[101];
        infected[zero] = true;
        for (int t = 0; t <= 250; t++) {
            int x = cowx[t]; int y = cowy[t];
            if (x > 0) {
                if (infected[x]) handshakes[x]++;
                if (infected[y]) handshakes[y]++;
                if (handshakes[x] <= k && infected[x]) infected[y] = true;
                if (handshakes[y] <= k && infected[y]) infected[x] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (infected[i] != cow_ends_infected[i]) return false;
        }

        return true;
    }
}