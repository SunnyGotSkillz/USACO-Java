/*
ID: sunnygotskillz
LANG: JAVA
TASK: Teleportation (USACO February 2018 Bronze #1)
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Teleportation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int walkingDistance = Math.abs(b-a);

        int teleportDistance = 0;
        int toTeleporter;
        if (Math.abs(x-a) <= Math.abs(y-a)) {
            teleportDistance += Math.abs(x-a);
            toTeleporter = y;
        } else {
            teleportDistance += Math.abs(y-a);
            toTeleporter = x;
        }

        teleportDistance += Math.abs(b-toTeleporter);

        out.println(Math.min(walkingDistance, teleportDistance));
        out.close();
    }
}