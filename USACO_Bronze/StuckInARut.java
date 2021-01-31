/*
PROBLEM: Stuck in a Rut (USACO December 2020 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1061

The solution to get partial credit just simulates the problem using brute force but there is not enough memory to support the large coordinates in later test cases.
The best solution is to find all the possible intersections that could happen between any pair of cows. Then, in chronological order, process them. or each 
time point of interest, he loops over all pairs of cows to figure out if there is an intersection caused by the pair at the point in time in question.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        char[] dir = new char[n];
        for (int j = 0; j < n; j++) {
            dir[j] = in.next().charAt(0);
            xs[j] = in.nextInt();
            ys[j] = in.nextInt();
        }
        int[] answer = new int[n];
        Arrays.fill(answer, Integer.MAX_VALUE);
        List<Integer> differences = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                differences.add(Math.abs(xs[k] - xs[j]));
                differences.add(Math.abs(ys[k] - ys[j]));
            }
        }
        Collections.sort(differences);
        for (int d : differences) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dir[j] == 'E' && dir[k] == 'N' && xs[j] < xs[k] && ys[k] < ys[j]) {
                        if (xs[j] + d == xs[k] && ys[k] + Math.min(answer[k], d) > ys[j]) {
                            answer[j] = Math.min(answer[j], d);
                        } else if (ys[k] + d == ys[j] && xs[j] + Math.min(answer[j], d) > xs[k]) {
                            answer[k] = Math.min(answer[k], d);
                        }
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {
            System.out.println(answer[j] == Integer.MAX_VALUE ? "Infinity" : answer[j]);
        }
    }
}


/* This solution is for partial credit and just uses a 2D array and while loop to simulate brute forcing the problem
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int[] ans = new int[n];
        int[][] cows = new int[n][2];
        int[][] start = new int[n][2];
        boolean[][] ruts = new boolean[10000][10000];
        char[] dir = new char[n];
        int maxX = -1;
        int maxY = -1;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            dir[i] = st.nextToken().charAt(0);

            int x = Integer.parseInt(st.nextToken());
            cows[i][0] = x;
            start[i][0] = x;
            if (dir[i] == 'N') maxX = Math.max(maxX, x);

            int y = Integer.parseInt(st.nextToken());
            cows[i][1] = y;
            start[i][1] = y;
            if (dir[i] == 'E') maxY = Math.max(maxY, y);

            ruts[x][y] = true;
        }

        boolean[] done = new boolean[n];
        int doneCount = 0;
        while (doneCount < n) {
            for (int i = 0; i < n; i++) {
                if (!done[i]) {
                    if (dir[i] == 'N') {
                        cows[i][1]++;
                        if (ruts[cows[i][0]][cows[i][1]]) {
                            done[i] = true;
                            doneCount++;
                            ans[i] = cows[i][1] - start[i][1];
                        }

                    } else if (dir[i] == 'E') {
                        cows[i][0]++;
                        if (ruts[cows[i][0]][cows[i][1]]) {
                            done[i] = true;
                            doneCount++;
                            ans[i] = cows[i][0] - start[i][0];
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                ruts[cows[i][0]][cows[i][1]] = true;
            }

            for (int i = 0; i < n; i++) {
                if (!done[i] && dir[i] == 'E') {
                    if (cows[i][0] > maxX) {
                        doneCount++;
                        done[i] = true;
                        ans[i] = -1;
                    }
                } else if (!done[i] && dir[i] == 'N') {
                    if (cows[i][1] > maxY) {
                        doneCount++;
                        done[i] = true;
                        ans[i] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (ans[i] == -1) System.out.println("Infinity");
            else System.out.println(ans[i]);
        }
    }
*/