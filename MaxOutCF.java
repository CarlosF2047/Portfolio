//Carlos Fuentes
//03/25/2019
// Course: ITSE 2417
// Program 3
// References: help file

import java.util.Scanner;
import java.util.*;

public class MaxOutCF {

    public static void main(String[] args) {
        final int ROWS = 6;
        final int COLS = 6;

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the matrix (36 numbers, any more will be ignored)");
        int[][] board = new int[ROWS][COLS];

        for (int i = 0; i <= 6; i++) {
            for (int r = 0; r < ROWS; r++)
                for (int c = 0; c < COLS; c++)
                    if (s.hasNextInt()) {
                        board[r][c] = s.nextInt();
                    }

            for (int[] row : board)
                System.out.println(Arrays.toString(row));

            boolean[][] covered = new boolean[6][6];
            System.out.println("The maximum score for this board is " + maxVal(board, covered, 0, 0));
        }
        s.close();
    }

    private static int[] rowDiffs = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    private static int[] colDiffs = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    private static int maxVal(int[][] board, boolean[][] covered, int row, int col){
        // base case: no more possible discs so total up how much current placement is worth
        if(row == 6)
            return boardScore(board, covered);

        // recursive case
        // choices for current position: 1. cover if allowed or 2. leave uncovered.
        int nextCol = (col + 1) % 6;
        int nextRow = row + (col + 1) / 6;
        int doNotCoverCurrent = maxVal(board, covered, nextRow, nextCol);
        int coverCurrent = 0;
        if( canCoverCurrent(covered, row, col) ){
            covered[row][col] = true;
            coverCurrent = maxVal(board, covered, nextRow, nextCol);
            covered[row][col] = false;
        }
        return Math.max(doNotCoverCurrent, coverCurrent);
    }

    private static boolean canCoverCurrent(boolean[][] covered, int row, int col) {
        // only allowed to cover if all adjacent cells are uncovered
        boolean canCover = true;
        for(int j = 0; j < rowDiffs.length; j++){
            int nextRow = row + rowDiffs[j];
            int nextCol = col + colDiffs[j];
            if(inbounds(nextRow, nextCol) && covered[nextRow][nextCol])
                canCover = false;
        }
        return canCover;
    }

    private static int boardScore(int[][] board, boolean[][] covered) {
        int result = 0;
        for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[0].length; c++)
                if( covered[r][c])
                    result += board[r][c];
        if(result == 1330){
            for(int r = 0; r < board.length; r++){
                for(int c = 0; c < board[0].length; c++)
                    if( covered[r][c] )
                        System.out.print("X");
                    else
                        System.out.print("-");
                System.out.println();
            }
        }

        return result;
    }

    private static boolean inbounds(int r, int c){
        return r >= 0 && r < 6 && c >= 0 && c < 6;
    }
}


