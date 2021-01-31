/*
ID: sunnygotskillz
LANG: JAVA
TASK: The Lost Cow (USACO Open 2017 Bronze #1)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lostcow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int currentPosition = x;
        int destination = x+1;
        int distance = 0;
        boolean found = false;
        int currSteps = 1;
        if (x == y) {
            found = true;
        }
        while (!found) {
            int steps = Math.abs(destination - currentPosition);
            for (int i = 1; i <= steps; i++) {
                if (currSteps < 0) currentPosition--;
                else currentPosition++;
                distance++;
                if (currentPosition == y) {
                    found = true;
                    break;
                }
            }
            if (found) break;
            else currSteps *= -2;
            destination = x + currSteps;
        }

        out.println(distance);
        out.close();
    }
}