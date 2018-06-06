package 剑指offer编程题;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 *
 * 思路：利用递归实现，每次只能走上下左右四个点，进行判断点的位置是否越界，点数之和是否大于K，是否已经走过了。
 */
public class RobbotMoving {

    public int movingCount(int threshold, int rows ,int cols){
        // 记录是否已经走过
        int flag[][] = new int[rows][cols];
        return helper(0,0,rows,cols,flag,threshold);
    }

    private static int helper(int i, int j, int rows, int cols, int[][] flag, int threshold){
        if(i<0 || i>rows || j<0 || j>cols ||
                numSum(i)+numSum(j)>threshold ||
                flag[i][j] == 1){
            return 0;
        }
        flag[i][j]=1;

        return helper(i-1,j,rows,cols,flag,threshold)
                + helper(i+1,j,rows,cols,flag,threshold)
                + helper(i,j-1,rows,cols,flag,threshold)
                + helper(i,j+1,rows,cols,flag,threshold) +1;

    }

    //计算行坐标或列坐标的数位
    private static int numSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }
        return sum;
    }

}
