/*
ID: sunnygotskillz
LANG: JAVA
TASK: Cow Gymnastics (USACO December 2019 Bronze #1)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class CowGymnastics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] sessions = new int[n][k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int place = 1;
            for (int j = 0; j < n; j++) {
                int currCow = Integer.parseInt(st.nextToken());
                sessions[currCow-1][i] = place;
                place++;
            }
        }

        int consistent = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a+1; b < n; b++) {
                int counter = 0;
                for (int i = 0; i < k; i++) {
                    if (sessions[a][i] < sessions[b][i]) {
                        counter++;
                    }
                }

                if (counter == 0 || counter == k) consistent++;
            }
        }


        out.println(consistent);
        out.close();
    }
