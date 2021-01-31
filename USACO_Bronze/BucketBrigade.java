/*
ID: sunnygotskillz
LANG: JAVA
TASK: Bucket Brigade (USACO US Open 2019 Bronze #1)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("buckets.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buckets.out")));

        int xB = 0, yB = 0, xR = 0, yR = 0, xL = 0, yL = 0;
        for (int i = 0; i < 10; i++) {
            String st = in.readLine();
            for (int j = 0; j < 10; j++) {
                if (st.charAt(j) == 'B') {
                    xB = j+1;
                    yB = i+1;
                } else if (st.charAt(j) == 'R') {
                    xR = j+1;
                    yR = i+1;
                } else if (st.charAt(j) == 'L') {
                    xL = j+1;
                    yL = i+1;
                }
            }
        }

        int bl = Math.abs(xL - xB) + Math.abs(yL - yB);
        int br = Math.abs(xR - xB) + Math.abs(yR - yB);
        int lr = Math.abs(xL - xR) + Math.abs(yL - yR);

        if (((xB == xL) || (yB == yL)) && (bl == br+lr)) {
            out.println(bl+1);
        } else {
            out.println(bl-1);
        }

        out.close();
    }
}