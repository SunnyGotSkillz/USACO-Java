/*
PROBLEM: Paired Up (USACO US Open 2017 Silver #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=738
*/

import java.util.*;
import java.io.*;

public class PairedUp {
	public static void main(String[] args) throws Exception {
		BufferedReader stdin = new BufferedReader(new FileReader("pairup.in"));
		int n = Integer.parseInt(stdin.readLine().trim());
		cow[] list = new cow[n];

		// Read cows and sort.
		for (int i=0; i<n; i++) {
			StringTokenizer tok = new StringTokenizer(stdin.readLine());
			int numC = Integer.parseInt(tok.nextToken());
			int milk = Integer.parseInt(tok.nextToken());
			list[i] = new cow(numC, milk);
		}
		Arrays.sort(list); // sort pairs by amout of milk

		int M = 0, i=0, j=n-1;
	    while (i <= j) { // two pointers go through front and end of the array
		    int x = Math.min(list[i].numC, list[j].numC);
		    if (i==j) x /= 2;
		    M = Math.max(M, list[i].amtMilk + list[j].amtMilk);
		    list[i].numC -= x;
		    list[j].numC -= x;
		    if (list[i].numC == 0) i++; // move on if these specific cows have been paired up
		    if (list[j].numC == 0) j--; // move on if these specific cows have been paired up
		 }

		PrintWriter out = new PrintWriter(new FileWriter("pairup.out"));
		out.println(M);
		out.close();
		stdin.close();
	}

	static class cow implements Comparable<cow> {
		public int numC;
		public int amtMilk;

		public cow(int nC, int milk) {
			numC = nC;
			amtMilk = milk;
		}

		public int compareTo(cow other) {
			return this.amtMilk - other.amtMilk;
		}
	}
}
