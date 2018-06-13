package Interview_Question;

/**
 * ��Ѷ���Ա����
 * Ϳ���񣨻���СQ��
 * ����СQ�ֿ�ʼ��������������СQ�ó���һ����NxM���ظ�Ļ���, �����ʼ״̬�ǿհ׵�,��'X'��ʾ��
 * СQ�������صĻ滭����,
 * ÿ��СQ��ѡ��һ��б��, ���б�ߵķ�������'/',��б��Ϊ1,СQ��ѡ������б���е�һ�θ���,��Ϳ��Ϊ��ɫ,��'B'��ʾ;
 * ����Խ��ߵķ�������'\',��б��Ϊ-1,СQ��ѡ������б���е�һ�θ���,��Ϳ��Ϊ��ɫ,��'Y'��ʾ��
 * ���һ�����Ӽȱ���ɫͿ�����ֱ���ɫͿ����,��ô������Ӿͻ�����ɫ,��'G'��ʾ��
 * СQ�Ѿ����뻭������Ʒ������, �����������һ����������Ҫ���ٴβ�������������
 *
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * ����������
 * ÿ���������һ������������
 * ÿ�����������ĵ�һ�а�������������N��M(1 <= N, M <= 50), ��ʾ����ĳ���
 * ��������N�а���N������ΪM���ַ���, ���а����ַ�'B','Y','G','X',�ֱ��ʾ��ɫ,��ɫ,��ɫ,�հס�������ʾСQҪ��ɵ���Ʒ
 *
 * ���������
 * ���һ��������, ��ʾСQ������Ҫ���ٴβ�����ɻ滭��
 *
 * ����������
 * 4 4
 * YXXB
 * XYGX
 * XBYY
 * BXXY
 *
 * ���������
 * 3
 *
 * ����˵��
 * XXXX       YXXX       YXXB       YXXB
 * XXXX       XYXX       XYBX       XYGX
 * XXXX       XXYX       XBYX       XBYY
 * XXXX  -��  XXXY  -��   BXXY  -��  BXXY
 */
public class ColorSquare {

    // ��Ҫ�Ĳ�����
    public static int count = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();

            // ���ʱ����Ʒ�����壩
            char[][] chs = new char[n][m];
            // �հ׻��壨ÿһ���趼��temp[][]Ϳɫ������chs[][]�Ƚϣ�
            char[][] temp = new char[n][m];

            for (int i=0; i<n; i++){
                String s = sc.next();
                for (int j=0; j<m; j++){
                    chs[i][j] = s.charAt(j);
                }
                // ���temp[i]������ÿ��Ԫ�ض���'X'
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

    // б��Ϊ 1
    public static void left(char[][] temp, char[][] chs, int i, int j){
        while(i >= 0 && i < temp.length && j >= 0 && j < temp[0].length){
            // ��temp[i][j]Ϊ�հס�X�� �� chs[i][j]Ϊ��ɫʱ����ͿΪ����ɫ��--B
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

    // б��Ϊ -1
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
