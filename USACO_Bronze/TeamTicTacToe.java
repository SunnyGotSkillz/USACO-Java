/*
PROBLEM: Team Tic Tac Toe (USACO US Open 2018 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=831

Use simulation to count how many individual cows can win then using the same method, test all possible teams of cows that can win.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("tttt.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tttt.out")));

        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            String line = in.readLine();
            for (int j = 0; j < 3; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int indiv = 0;
        int team = 0;
        for (int i = 65; i <= 90; i++) { // individual cows
            boolean done = false;
            for (int j = 0; j < 3; j++) { // rows
                if (board[j][0] == i && board[j][1] == i && board[j][2] == i) {
                    indiv++;
                    done = true;
                    break;
                }
            }

            if (done) continue;

            for (int j = 0; j < 3; j++) { // columns
                if (board[0][j] == i && board[1][j] == i && board[2][j] == i) {
                    indiv++;
                    done = true;
                    break;
                }
            }

            if (done) continue;

            if (board[0][0] == i && board[1][1] == i && board[2][2] == i) {
                indiv++;
                continue;
            }
            if (board[0][2] == i && board[1][1] == i && board[2][0] == i) indiv++;
        }

        for (int i = 65; i <= 90; i++) { // team cows
            for (int j = i+1; j <= 90; j++) {
                if (i != j) {
                    boolean done = false;
                    for (int k = 0; k < 3; k++) { // rows
                        if ((board[k][0] != board[k][1] || board[k][0] != board[k][2] || board[k][1] != board[k][2]) &&
                                (board[k][0] == i || board[k][0] == j) && (board[k][1] == i || board[k][1] == j) && (board[k][2] == i || board[k][2] == j)) {
                            team++;
                            done = true;
                            break;
                        }
                    }

                    if (done) continue;

                    for (int k = 0; k < 3; k++) { // columns
                        if ((board[0][k] != board[1][k] || board[0][k] != board[2][k] || board[1][k] != board[2][k]) &&
                                (board[0][k] == i || board[0][k] == j) && (board[1][k] == i || board[1][k] == j) && (board[2][k] == i || board[2][k] == j)) {
                            team++;
                            done = true;
                            break;
                        }
                    }

                    if (done) continue;

                    if ((board[0][0] != board[1][1] || board[0][0] != board[2][2]) &&
                            (board[0][0] == i || board[0][0] == j) && (board[1][1] == i || board[1][1] == j) && (board[2][2] == i || board[2][2] == j)) {
                        team++;
                        continue;
                    }
                    if ((board[0][2] != board[1][1] || board[0][2] != board[2][0]) &&
                            (board[0][2] == i || board[0][2] == j) && (board[1][1] == i || board[1][1] == j) && (board[2][0] == i || board[2][0] == j)) {
                        team++;
                    }
                }
            }
        }

        out.println(indiv);
        out.println(team);
        out.close();
    }
}