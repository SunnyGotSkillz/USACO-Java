/*
ID: sunnygotskillz
LANG: JAVA
TASK: Fence Painting (USACO December 2015 Bronze #1)
*/

import java.io.*;
import java.util.*;

class FencePainting {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("paint.in"));
                                                  
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
    
    	StringTokenizer st = new StringTokenizer(f.readLine());

    	int a = Integer.parseInt(st.nextToken());
    	int b = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(f.readLine());
    	int c = Integer.parseInt(st.nextToken());
    	int d = Integer.parseInt(st.nextToken());
    	
    	int lower = Math.min(a, c);
    	int higher = Math.max(b, d);
    	out.println(Math.abs(higher-lower));
    	out.close();
	}
}