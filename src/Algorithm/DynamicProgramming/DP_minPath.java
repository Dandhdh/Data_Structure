package Algorithm.DynamicProgramming;

/**
 * ����������
 * ����һ��m*n�ľ���ÿ��λ����һ���Ǹ������������߸��ø��Ե�·�����ȣ�
 * �����Ͻǿ�ʼ��һ�������ˣ���ÿ��ֻ�ܳ��Һ����ߣ��ߵ����½ǣ�
 * ������˵�����·���У��ܺ���С������·��������ͼ��ʾ������ͼ����ʾ�Ĳ�ɫ��������֪��ĳЩ�Ǹ�����ֵ
 */

/**
 * ����˼·��
 * ����һ�������λ�ڻ�����λ��ĳ��(x, y)������ô������ô�����أ�ֻ������������߻����ϱߡ�����
 * dp[x, y] = min(dp[x-1, y], dp[x, y-1]) + a[x, y],����a[x, y]��������(x, y)���Ȩ��ȡֵ��
 * Ȼ����λ�������һ�������ϱߵ�һ�У��õ����е�״̬ת�Ʒ���Ϊ
 *
 * ״̬ת�Ʒ��̣�
 *
 * dp[i,0] = chess[0][0] + chess[1][0] + ... + chess[i][0];
 * dp[0,j] = chess[0][0] + chess[0][1] + ... + chess[0][j];
 * dp[i,j] = min( dp[i,j-1] , dp[i-1][j] ) + chess[i][j];
 */
public class DP_minPath {

    //��[0][0]�㿪ʼ���������½�
    public int minPath(int[][] chess) {
        int row = chess.length;
        int col = chess[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = chess[0][0];
        for(int i=1; i<row; i++)
            dp[i][0] = dp[i-1][0] + chess[i][0];
        for(int j=1; j<col; j++)
            dp[0][j] = dp[0][j-1] + chess[0][j];
        for(int i=1; i<row; i++) {
            for(int j=1; j<col; j++) {
                dp[i][j] = (dp[i-1][j] < dp[i][j-1] ? dp[i-1][j] : dp[i][j-1]) + chess[i][j];
            }
        }
        return dp[row-1][col-1];
    }
    //���淽����ʹ�ÿռ��� n^2

    /**
     * �Ż���
     *
     * �۲�״̬ת�Ʒ��̷��֣�ÿ�θ���(x, y)��ֻ��Ҫ���֪����һ�м��ɣ�û��Ҫ֪����������ݡ�
     * �����������������Ķ�̬�滮���⣬�������á��������顱�ķ�ʽ���ռ��ϵ��Ż���
     * ʹ�ù��������״̬ת�Ʒ�������ͼ��ʾ��
     *
     * ��������:
     * dp(j) = chess[0][0]+chess[0][1]+...+chess[0][j]
     * dp(j) = min(dp(j)+dp(j-1))+chess[i][j];
     */
    public int minPath_better(int[][] chess) {
        int row = chess.length;
        int col = chess[0].length;
        int[] dp = new int[col];
        dp[0] = chess[0][0];
        for(int j=1; j<col; j++)
            dp[j] = dp[j-1] + chess[0][j];
        for(int i=1; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(j == 0)
                    dp[j] += chess[i][j];
                else
                    dp[j] = (dp[j] < dp[j-1] ? dp[j] : dp[j-1]) + chess[i][j];
            }
        }
        return dp[col-1];
    }
    //���淽����ʹ�ÿռ��� n

}
