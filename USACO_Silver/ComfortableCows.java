/*
PROBLEM: Comfortable Cows (USACO Febrary 2021 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1110

The solution basically involves simulating the process. Maintain a grid of 3000x3000 representing a cow's location (0 = no cow, 1 = cow, 2 = added cow). Also
use a queue that contains comfortable cows and a cows array and class to represent each cow that will be added. Anytime we add a cow, we only have to check its
surroundings since the status of no other cow will be changed.

Use a for loop to go through adding each cow and add the comfy cows into the queue. For each comfy cow, add an additional cow to make her uncomfy and use the
same method to check if this added cow makes any other surrounding cows comfy. If so, add them to the queue. 

We don't have to reset the added cows in each step because the cows that we add in one step will be the same as the ones we would add in the previous step. To
make sure we still get the correct answer, if we are adding the cow i in a spot where there was an added cow, subtract 1 from the answer since we know that the
surrounding cows will be satisfied.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ComfortableCows {
    static int n;
    static Cow[] cows;
    static int[][] grid;
    static Queue<Cow> q;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        cows = new Cow[n];
        grid = new int[3000][3000];
        q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            // To prevent index out of bounds
            int a = Integer.parseInt(st.nextToken()) + 1000;
            int b = Integer.parseInt(st.nextToken()) + 1000;
            cows[i] = new Cow(a, b);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int a = cows[i].x; int b = cows[i].y;
            if (grid[a][b] == 2) ans--;
            grid[a][b] = 1;
            addComfy(a, b);
            while (!q.isEmpty()) {
                int x = q.peek().x;
                int y = q.peek().y;
                if (isComfy(x, y) != 3) {
                    q.poll();
                    continue;
                }

                if (grid[x-1][y] == 0) x = x-1;
                else if (grid[x][y-1] == 0) y = y - 1;
                else if (grid[x][y+1] == 0) y = y + 1;
                else if (grid[x+1][y] == 0) x = x + 1;

                grid[x][y] = 2;
                addComfy(x, y);
                ans++;
                q.poll();
            }

            System.out.println(Math.abs(ans));
        }
    }

    public static class Cow {
        int x;
        int y;
        public Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int isComfy(int x, int y) {
        int c = 0;
        if (grid[x-1][y] > 0) c++;
        if (grid[x][y-1] > 0) c++;
        if (grid[x][y+1] > 0) c++;
        if (grid[x+1][y] > 0) c++;
        return c;
    }

    public static void addComfy(int a, int b) {
        if (isComfy(a, b) == 3) q.add(new Cow(a, b));
        if (grid[a-1][b] != 0 && isComfy(a-1, b) == 3) {
            q.add(new Cow(a-1, b));
        }
        if (grid[a][b-1] != 0 && isComfy(a, b-1) == 3) {
            q.add(new Cow(a, b-1));
        }
        if (grid[a][b+1] != 0 && isComfy(a, b+1) == 3) {
            q.add(new Cow(a, b+1));
        }
        if (grid[a+1][b] != 0 && isComfy(a+1, b) == 3) {
            q.add(new Cow(a+1, b));
        }
    }
}