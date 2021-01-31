/*
ID: sunnygotskillz
LANG: JAVA
TASK: Field Reduction (USACO US Open 2016 Bronze #3)
*/

import java.io.*;
import java.util.*;

public class Main {
	// x1 and x2 are the smallest x-coordinates seen
	// x3 and x4 are the largest x-coordinates seen
	static int x1, x2, x3, x4;
	// y1 and y2 are the smallest y-coordinates seen
	// y3 and y4 are the largest y-coordinates seen
	static int y1, y2, y3, y4;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		int n = Integer.parseInt(br.readLine());
		x1 = Integer.MAX_VALUE;
		x2 = Integer.MAX_VALUE;
		x3 = 0;
		x4 = 0;
		y1 = Integer.MAX_VALUE;
		y2 = Integer.MAX_VALUE;
		y3 = 0;
		y4 = 0;
		int[] x = new int[n];
		int[] y = new int[n];
		// read in all the locations
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			update(x[i], y[i]);
		}
		// the original fence has this area
		int ans = (x4-x1) * (y4-y1);
		for(int i = 0; i < n; i++) {
			// check for each point if we use the smallest/largest coordinate
			// or the second-smallest/second-largest coordinate
			int xMin = x1;
			if(x[i] == xMin) {
				xMin = x2;
			}
			int xMax = x4;
			if(x[i] == xMax) {
				xMax = x3;
			}
			int yMin = y1;
			if(y[i] == yMin) {
				yMin = y2;
			}
			int yMax = y4;
			if(y[i] == yMax) {
				yMax = y3;
			}
			// check if the new area is smaller
			ans = Math.min(ans, (xMax - xMin) * (yMax - yMin));
		}
		// print the answer
		pw.println(ans);
		pw.close();
	}		
	
	// This function takes in a point and updates the
	// two smallest and two largest x and y coordinates.
	public static void update(int x, int y) {
		if(x < x1) {
			x2 = x1;
			x1 = x;
		}
		else if(x < x2) {
			x2 = x;
		}
		if(x > x4) {
			x3 = x4;
			x4 = x;
		}
		else if(x > x3) {
			x3 = x;
		}
		
		if(y < y1) {
			y2 = y1;
			y1 = y;
		}
		else if(y < y2) {
			y2 = y;
		}
		if(y > y4) {
			y3 = y4;
			y4 = y;
		}
		else if(y > y3) {
			y3 = y;
		}
	}
}