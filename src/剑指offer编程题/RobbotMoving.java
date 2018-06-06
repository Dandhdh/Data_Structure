package ��ָoffer�����;

/**
 * ������һ��m�к�n�еķ���һ�������˴�����0,0�ĸ��ӿ�ʼ�ƶ���
 * ÿһ��ֻ�������ң��ϣ����ĸ������ƶ�һ��
 * ���ǲ��ܽ�������������������λ֮�ʹ���k�ĸ��ӡ�
 *
 * ˼·�����õݹ�ʵ�֣�ÿ��ֻ�������������ĸ��㣬�����жϵ��λ���Ƿ�Խ�磬����֮���Ƿ����K���Ƿ��Ѿ��߹��ˡ�
 */
public class RobbotMoving {

    public int movingCount(int threshold, int rows ,int cols){
        // ��¼�Ƿ��Ѿ��߹�
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

    //��������������������λ
    private static int numSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }
        return sum;
    }

}
