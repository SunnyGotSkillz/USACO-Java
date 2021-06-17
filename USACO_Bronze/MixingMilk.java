/*
ID: sunnygotskillz
LANG: JAVA
TASK: Mixing Milk (USACO December 2018 Bronze #1)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class MixingMilk {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int c1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int c2 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int c3 = Integer.parseInt(st.nextToken());
        int m3 = Integer.parseInt(st.nextToken());

        int status = 1;
        for (int i = 1; i <= 100; i++) {
            if (status == 1) {
                int temp = m2;
                m2 = Math.min(c2, m1+m2);
                m1 -= Math.abs(m2-temp);
                status = 2;
            } else if (status == 2) {
                int temp = m3;
                m3 = Math.min(c3, m2+m3);
                m2 -= Math.abs(m3-temp);
                status = 3;
            } else if (status == 3) {
                int temp = m1;
                m1 = Math.min(c1, m3+m1);
                m3 -= Math.abs(m1-temp);
                status = 1;
            }
        }

        out.println(m1);
        out.println(m2);
        out.println(m3);
        out.close();
    }
}