package io.windfree.algorithm.recursion;
/*
   Counting cells in a blob
 */
public class CountingCells {
    private static int[][] blob = {
            {1, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0, 0},
            {1, 1, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 1, 1}
    };

    private static int FILL = 1;
    private static int NO_FILL = 0;
    private static int CHECK = 2;
    private static int ROWS = blob.length;
    private  static int COLS = blob[0].length;

    private int count= 0;
    public int solve(int x, int y ) {
        if( x < 0 || y < 0 || x >= ROWS || y >= COLS) {
            return 0;
        }
        if (blob[x][y] != FILL) {
            return  0;
        }
        blob[x][y] = CHECK;
        return 1 + solve(x-1, y-1) + solve(x, y-1) + solve(x+1, y-1)
                 + solve(x-1,y) + solve(x+1,y)
                 + solve(x-1,y+1) + solve(x, y+1) + solve(x+1, y+1);
    }


    public static void main(String[] args) {
        CountingCells c = new CountingCells();
        int count = c.solve(5,3);
        System.out.println(count);
    }
}
