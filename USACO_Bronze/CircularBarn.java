/*
ID: sunnygotskillz
LANG: JAVA
TASK: Circular Barn (USACO February 2016 Bronze #2)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        int n = Integer.parseInt(f.readLine());
        int[] rooms = new int[n];
        int totalCows = 0;
        for (int i = 0; i < n; i++) {
            int cows = Integer.parseInt(f.readLine());
            rooms[i] = cows;
            totalCows += cows;
        }

        int steps = -1;
        for (int r = 0; r < n; r++) {
            int roomCounter = 0;
            int currIndex = r;
            int currentSum = totalCows;
            int currentSteps = 0;
            while (roomCounter != n) {
                currentSum -= rooms[currIndex];
                currentSteps += currentSum;
                roomCounter++;
                if (currIndex+1 == n) currIndex = 0;
                else currIndex++;
            }

            if (steps == -1) steps = currentSteps;
            else steps = Math.min(steps, currentSteps);
        }

        out.println(steps);
        out.close();
    }
}