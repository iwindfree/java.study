package io.windfree.algorithm.recursion;
/*
 (0,0) 위치에서 춟발하여 (N-1, N-1) 까지 가는 path 를 찾는 문제.
 셀의 값이 1 인 곳은 벽이기 때문에 더이상 진행할 수 없음.
 */
public class findPath1 {
    private static final int PATH = 0;
    private static final int WALL = 1;
    private static final int VALID = 2;
    private static final int INVALID = 3;

    private static  int ROW_COUNT ;
    private static int COL_COUNT;
    private static int[][] maze = {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 1, 1, 0, 1},
            {0, 1, 1, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 0, 1, 0, 0}
    };

    public  static void main(String[] args) {
        ROW_COUNT = maze.length;
        COL_COUNT = maze[0].length;
        print();
        findPath(0,0);
        System.out.println("\r\n---------------------");
        System.out.println();
        print();
    }

    private static  boolean findPath(int x, int y) {
        if(x < 0 || y < 0 )
            return false;
        if(x >= ROW_COUNT || y >= COL_COUNT)
            return false;
        if(maze[x][y] != PATH)
            return false;
        if(x == ROW_COUNT -1 && y== COL_COUNT -1 )
            return true;

        maze[x][y] = VALID;
        if (findPath(x - 1, y) || findPath(x, y - 1) || findPath(x + 1, y) || findPath(x, y + 1)) {
            System.out.print("(" + x + " ," + y + ") ");
            return true;
        }
        maze[x][y] = INVALID;
        return false;
    }

    private static void print( ) {
        for(int i = 0; i < maze.length; i++)  {
            for(int j = 0 ; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + "  ");
            }
            System.out.println();
        }
    }

}
