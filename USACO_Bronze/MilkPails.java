/*
ID: sunnygotskillz
LANG: JAVA
TASK: Milk Pails (USACO February 2016 Bronze #1)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int xSize = Integer.parseInt(st.nextToken());
        int ySize = Integer.parseInt(st.nextToken());
        int mSize = Integer.parseInt(st.nextToken());

        int max = 0;
        int xLimit = mSize / xSize;
        int yLimit = mSize / ySize;
        boolean filled = false;

        for (int i = 0; i <= xLimit; i++) {
            for (int j = 0; j <= yLimit; j++) {
                int currentSize = (i*xSize) + (j*ySize);
                if (currentSize == mSize) {
                    filled = true;
                    max = currentSize;
                    break;
                } else if (currentSize < mSize && currentSize > max) {
                    max = currentSize;
                }
            }

            if (filled) break;
        }

        out.println(max);
        out.close();
    }
}