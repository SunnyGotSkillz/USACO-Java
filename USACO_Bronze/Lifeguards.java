/*
ID: sunnygotskillz
LANG: JAVA
TASK: Lifeguards (USACO January 2018 Bronze #2)
*/

import java.io.*;
import java.util.*;

public class lifeguards {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		
		// read in the information about the life guards
		int n = Integer.parseInt(br.readLine());
		int[] start = new int[n];
		int[] end = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start[i] = Integer.parseInt(st.nextToken());
			end[i] = Integer.parseInt(st.nextToken());
		}
		
		// figure out, for each time interval, how many lifeguards are covering it 
		int[] numCover = new int[1000];
		for(int i = 0; i < n; i++) {
			for(int t = start[i]; t < end[i]; t++) {
				numCover[t]++;
			}
		}
		int maxCover = 0;
		for(int i = 0; i < n; i++) {
			// we fire lifeguard i temporarily
			for(int t = start[i]; t < end[i]; t++) {
				numCover[t]--;
			}
			// count how many intervals are still covered
			int covered = 0;
			for(int t = 0; t < 1000; t++) {
				if(numCover[t] > 0) {
					covered++;
				}
			}
			maxCover = Math.max(maxCover, covered);
			// revert the firing
			for(int t = start[i]; t < end[i]; t++) {
				numCover[t]++;
			}
		}
		pw.println(maxCover);
		pw.close();
	}
}