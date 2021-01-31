/*
PROBLEM: The Moo Particle (USACO US Open 2020 Silver #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=1040
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class TheMooParticle {
    static int n;
    static Particle[] particles;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moop.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
    
        n = Integer.parseInt(in.readLine());
        particles = new Particle[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            StringTokenizer st = new StringTokenizer(in.readLine());
            particles[i] = new Particle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(particles);

        int minY = particles[0].y;
        for (int i = 1; i < n; i++) {
            if (particles[i].y >= minY) {
                union(i-1, i);
            } else {
                minY = particles[i].y;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                ans++;
            }
        }

        out.println(ans-1);
        out.close();
    }

    public static class Particle implements Comparable<Particle> {
        int x;
        int y;
        public Particle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Particle o) {
            if (x == o.x) {
                return Integer.compare(y, o.y);
            }
            return Integer.compare(x, o.x);
        }
    }

    static int find(int x) { 
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int a, int b) {
        int c = find(a);
        int d = find(b);
        if (c != d) {
            parent[d] = c;
        }
    }
}
