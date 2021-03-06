/*
ID: sunnygotskillz
LANG: JAVA
TASK: Blocked Billboard 2 (USACO January 2018 Bronze #1)
*/

import java.io.*;
import java.util.*;

public class BlockedBillboard2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));

		// read in the locations of the corners of the rectangles
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());
		
		// count how many corners of the billboard are covered
		int cornerCover = 0;
		if(covered(x1, y1, x3, y3, x4, y4)) cornerCover++;
		if(covered(x1, y2, x3, y3, x4, y4)) cornerCover++;
		if(covered(x2, y1, x3, y3, x4, y4)) cornerCover++;
		if(covered(x2, y2, x3, y3, x4, y4)) cornerCover++;
		// if fewer than 2 corners are covered, the whole rectangle must be covered
		if(cornerCover < 2) {
			pw.println((x2-x1)*(y2-y1));
		}
		// if all 4 corners are covered, then nothing needs to be covered
		else if(cornerCover == 4) {
			pw.println(0);
		}
		else {
			// we only need to cover some portion of the rectangle
			// find the intersection of the two rectangles
			int xL = Math.max(x1, x3);
			int xR = Math.min(x2, x4);
			int yL = Math.max(y1, y3);
			int yR = Math.min(y2, y4);
			// subtract away the area of the intersection
			pw.println((x2-x1)*(y2-y1) - (xR-xL)*(yR-yL));
		}
		pw.close();
	}
	
	public static boolean covered(int x, int y, int x1, int y1, int x2, int y2) {
		// returns true if (x, y) is covered by the rectangle bounded by (x1, y1) and (x2, y2)
		// returns false otherwise
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
	
}