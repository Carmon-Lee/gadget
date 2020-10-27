package dp;


/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class ReadMe {

    public int closedIsland(int[][] grid) {
        if (grid.length<3 || grid[0].length<3) {
            return 0;
        }
        int count = 0;
        // -1 表示连接水域
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (i==0 || i==grid.length-1 || j==0 ||j==grid[0].length-1) {
                    dfs1(grid, i, j);
                }
            }
        }
        //System.out.println(Arrays.deepToString(grid));
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (isClosed(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs1(int[][] grid, int x, int y) {
        if (grid[x][y]!=0) {
            return;
        }
        grid[x][y] = -1;
        for (int i=0;i<4;i++) {
            int nx = x+surX[i];
            int ny = y+surY[i];
            if (!withinBound(grid, nx, ny) || grid[nx][ny]!=0) {
                continue;
            }
            dfs1(grid, nx, ny);
        }
    }

    int[] surX = new int[]{0,0,-1,1};
    int[] surY = new int[]{-1,1,0,0};
    private boolean isClosed(int[][] grid, int x, int y) {
        System.out.println(x+"  "+y);
        if (grid[x][y]!=0) {
            return false;
        }
        boolean closed = true;
        grid[x][y] = 1;
        if (x==0 || x==grid.length-1 || y==0 || y==grid[0].length-1) {
            System.out.println("1");
            closed = false;
        }
        for (int i=0;i<4;i++) {
            int nx = x+surX[i];
            int ny = y+surY[i];
            if (withinBound(grid, nx, ny) && grid[nx][ny]==-1) {
                System.out.println("2");
                closed = false;
            }
        }

        boolean surClosed= true;
        for (int i=0;i<4;i++) {
            int nx = x+surX[i];
            int ny = y+surY[i];
            if (!withinBound(grid, nx, ny) || grid[nx][ny]!=0) {
                continue;
            }
            surClosed = surClosed && isClosed(grid, nx, ny);
        }
        return closed && surClosed;
    }

    private boolean withinBound(int[][] grid, int x, int y) {
        return x>=0 && x<grid.length && y>=0 && y<grid[0].length;
    }

    public static void main(String[] args) {
        System.out.println(new ReadMe().closedIsland(new int[][]{{0,0,1,1,0,1,0,0,1,0},{1,1,0,1,1,0,1,1,1,0},{1,0,1,1,1,0,0,1,1,0},{0,1,1,0,0,0,0,1,0,1},{0,0,0,0,0,0,1,1,1,0},{0,1,0,1,0,1,0,1,1,1},{1,0,1,0,1,1,0,0,0,1},{1,1,1,1,1,1,0,0,0,0},{1,1,1,0,0,1,0,1,0,1},{1,1,1,0,1,1,0,1,1,0}}));
    }
}
