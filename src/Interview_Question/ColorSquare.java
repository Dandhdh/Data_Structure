package Interview_Question;

/**
 * 腾讯笔试编程题
 * 涂方格（画家小Q）
 * 画家小Q又开始他的艺术创作。小Q拿出了一块有NxM像素格的画板, 画板初始状态是空白的,用'X'表示。
 * 小Q有他独特的绘画技巧,
 * 每次小Q会选择一条斜线, 如果斜线的方向形如'/',即斜率为1,小Q会选择这条斜线中的一段格子,都涂画为蓝色,用'B'表示;
 * 如果对角线的方向形如'\',即斜率为-1,小Q会选择这条斜线中的一段格子,都涂画为黄色,用'Y'表示。
 * 如果一个格子既被蓝色涂画过又被黄色涂画过,那么这个格子就会变成绿色,用'G'表示。
 * 小Q已经有想画出的作品的样子, 请你帮他计算一下他最少需要多少次操作完成这幅画。
 *
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * 输入描述：
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数N和M(1 <= N, M <= 50), 表示画板的长宽。
 * 接下来的N行包含N个长度为M的字符串, 其中包含字符'B','Y','G','X',分别表示蓝色,黄色,绿色,空白。整个表示小Q要完成的作品
 *
 * 输出描述：
 * 输出一个正整数, 表示小Q最少需要多少次操作完成绘画。
 *
 * 输入样例：
 * 4 4
 * YXXB
 * XYGX
 * XBYY
 * BXXY
 *
 * 输出样例：
 * 3
 *
 * 例子说明
 * XXXX       YXXX       YXXB       YXXB
 * XXXX       XYXX       XYBX       XYGX
 * XXXX       XXYX       XBYX       XBYY
 * XXXX  -》  XXXY  -》   BXXY  -》  BXXY
 */
public class ColorSquare {

    // 需要的操作数
    public static int count = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();

            // 完成时的作品（画板）
            char[][] chs = new char[n][m];
            // 空白画板（每一步骤都给temp[][]涂色，并于chs[][]比较）
            char[][] temp = new char[n][m];

            for (int i=0; i<n; i++){
                String s = sc.next();
                for (int j=0; j<m; j++){
                    chs[i][j] = s.charAt(j);
                }
                // 填充temp[i]数组中每个元素都是'X'
                Arrays.fill(temp[i],'X');
            }

            for (int i=0; i<n; i++){
                for (int j=0; j<m; j++){
                    if (chs[i][j]!=temp[i][j]){
                        if (chs[i][j]=='Y' || (chs[i][j] == 'G' && temp[i][j] == 'B')){
                            right(temp,chs,i,j);
                            count++;
                        }else if (chs[i][j] == 'B' || (chs[i][j] == 'G' && temp[i][j] == 'Y')){
                            left(temp, chs, i, j);
                            count++;
                        }else if (chs[i][j] == 'G'){
                            right(temp, chs, i, j);
                            left(temp, chs, i, j);
                            count+=2;
                        }
                    }
                }
            }
            System.out.println(count);
        }
        sc.close();
    }

    // 斜率为 1
    public static void left(char[][] temp, char[][] chs, int i, int j){
        while(i >= 0 && i < temp.length && j >= 0 && j < temp[0].length){
            // 当temp[i][j]为空白‘X’ 且 chs[i][j]为蓝色时，则涂为“蓝色”--B
            if (chs[i][j] == 'B' && temp[i][j] == 'X') {
                temp[i][j] = 'B';
            }else if (chs[i][j] == 'G'){
                if(temp[i][j] == 'Y') {
                    temp[i][j] = 'G';
                }else if(temp[i][j] == 'X'){
                    temp[i][j] = 'B';
                }
            }else {
                break;
            }
            i++;
            j--;
        }
    }

    // 斜率为 -1
    public static void right(char[][] temp, char[][] chs, int i, int j){
        while(i >= 0 && i < temp.length && j >= 0 && j < temp[0].length){
            if (chs[i][j] == 'Y' && temp[i][j] == 'X') {
                temp[i][j] = 'Y';
            }else if (chs[i][j] == 'G'){
                if(temp[i][j] == 'B') {
                    temp[i][j] = 'G';
                }else if(temp[i][j] == 'X'){
                    temp[i][j] = 'Y';
                }
            }else {
                break;
            }
            i++;
            j++;
        }
    }
}
