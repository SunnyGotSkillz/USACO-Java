/*
PROBLEM: Clockwise Fence (USACO February 2021 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1108

Imagine John starts at point (0,0). Traverse through his path and find his max and min for x and y coordinates. Then traverse another two times, but this time, figure
out whether he hits the max or min for both x and y first. Also keep note of which index he hits these on the x and y. Then use many conditional statements to 
choose between clockwise and counterclockwise based on whether they hit the min or max for each and whether they went in the x or y direction first.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ClockwiseFence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            String pathStr = in.readLine();
            char[] path = pathStr.toCharArray();
            int currX = 0; int currY = 0;
            int maxX = 0; int minX = 0;
            int maxY = 0; int minY = 0;
            for (char c : path) {
                if (c == 'N') {
                    currY++;
                    maxY = Math.max(currY, maxY);
                } else if (c == 'S') {
                    currY--;
                    minY = Math.min(currY, minY);
                } else if (c == 'W') {
                    currX--;
                    minX = Math.min(currX, minX);
                } else {
                    currX++;
                    maxX = Math.max(currX, maxX);
                }
            }

            currX = 0;
            int xi = 0;
            boolean hitsMaxX = false;
            for (int j = 0; j < path.length; j++) {
                char c = path[j];
                if (c == 'E') {
                    currX++;
                    if (currX == maxX) {
                        hitsMaxX = true;
                        xi = j;
                        break;
                    }
                } else if (c == 'W') {
                    currX--;
                    if (currX == minX) {
                        xi = j;
                        break;
                    }
                }
            }

            currY = 0;
            int yi = 0;
            boolean hitsMaxY = false;
            for (int j = 0; j < path.length; j++) {
                char c = path[j];
                if (c == 'N') {
                    currY++;
                    if (currY == maxY) {
                        hitsMaxY = true;
                        yi = j;
                        break;
                    }
                } else if (c == 'S') {
                    currY--;
                    if (currY == minY) {
                        yi = j;
                        break;
                    }
                }
            }

            if (hitsMaxX && hitsMaxY) {
                if (xi < yi) System.out.println("CCW");
                else System.out.println("CW");
            } else if (hitsMaxX && !hitsMaxY) {
                if (xi < yi) System.out.println("CW");
                else System.out.println("CCW");
            } else if (!hitsMaxX && !hitsMaxY) {
                if (xi < yi) System.out.println("CCW");
                else System.out.println("CW");
            } else {
                if (xi < yi) System.out.println("CW");
                else System.out.println("CCW");
            }
        }
    }
}