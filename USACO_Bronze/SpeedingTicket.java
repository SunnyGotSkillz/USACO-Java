/*
ID: sunnygotskillz
LANG: JAVA
TASK: Speeding Ticket (USACO December 2015 Bronze #2)
*/

import java.io.*;
import java.util.*;

class SpeedingTicket {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("speeding.in"));
                                                  
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
    
    StringTokenizer st = new StringTokenizer(f.readLine());
						  
    int n = Integer.parseInt(st.nextToken());   
    int m = Integer.parseInt(st.nextToken());

    int[] limits = new int[100];
    int mile = 0;

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(f.readLine());
      int distance = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      for (int j = 0; j < distance; j++) {
        limits[mile++] = speed;
      }
    }

    mile = 0;
    int[] bessie = new int[100];
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(f.readLine());
      int distance = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      for (int j = 0; j < distance; j++) {
        bessie[mile++] = speed;
      }
    }
    
    int max = 0;
    for (int i = 0; i < 100; i ++) {
      max = Math.max(max, bessie[i] - limits[i]);
    }

    out.println(max);                           
    out.close();                                 
  }
}