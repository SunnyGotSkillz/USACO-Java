/*
PROBLEM: Bull in China Shop (USACO US Open 2016 Bronze #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=640

The figurine is small enough and there are few enough pieces that we can check every possible pair of figurines along with every possible pair of ways to shift the two pieces.
Because the board has size N, we can shift either vertically or horizontally in either direction by at most N-1.
*/

public class BullInChinaShop {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcs.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcs.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[][] goal = read(br, n);
        boolean[][][] grids = new boolean[k][n][n];
        for(int i = 0; i < k; i++) {
            grids[i] = read(br, n);
        }

        for(int i = 0; i < k; i++) {
            for(int j = i+1; j < k; j++) {
                for(int idx = -n+1; idx <= n-1; idx++) {
                    for(int idy = -n+1; idy <= n-1; idy++) {
                        for(int jdx = -n+1; jdx <= n-1; jdx++) {
                            for(int jdy = -n+1; jdy <= n-1; jdy++) {
                                boolean good = true;
                                for(int x = 0; good && x < n; x++) {
                                    for(int y = 0; good && y < n; y++) {
                                        boolean iLoc = get(grids[i], idx + x, idy + y);
                                        boolean jLoc = get(grids[j], jdx + x, jdy + y);
                                        if(iLoc && jLoc) { // if there is a # on both grids
                                            good = false;
                                        }
                                        if(goal[x][y] != (iLoc || jLoc)) { // if there is no # on the original figure but there is one in a grid
                                            good = false;
                                        }
                                    }
                                }
                                if(good) {
                                    pw.println((i+1) + " " + (j+1));
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.close();
    }

    public static boolean get(boolean[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y];
    }

    public static boolean[][] read(BufferedReader br, int n) throws IOException {
        boolean[][] grid = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < n; j++) {
                grid[i][j] = s.charAt(j) == '#';
            }
        }
        return grid;
    }

}