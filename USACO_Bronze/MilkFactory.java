/*
PROBLEM: Milk Factory (USACO US Open 2019 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=940

A unique sink is the answer. A sink is a node in which all other nodes are going into it and it is not going into any other node.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class MilkFactory {
    static int n;
    static int[] outgoing;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("factory.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));

        n = Integer.parseInt(in.readLine());
        outgoing = new int[n];
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            outgoing[a]++;
        }

        int answer = -1;
        for (int i = 0; i < n; i++) {
            if (outgoing[i] == 0 && answer != -1 ) { // found two sinks -- bad!
                answer = -1;
                break;
            }
            if (outgoing[i] == 0) answer = i+1;  // found first sink; remember it
        }

        out.println(answer);
        out.close();
    }
}
