package Interview_Question;

/**
 * �ַ�ת��
 * ��һ����������a���͡�b�������ַ����ַ���s������Ϊn��ÿ�β������԰�һ���ַ���һ��ת������һ����a������Ϊ��b�������߰�һ����b���óɡ�a��)��
 * ���ǲ����Ĵ���������m���������޵Ĳ�������Χ�ڣ��ܹ��õ������������ͬ�ַ����Ӵ��ĳ����Ƕ���
 */

import java.util.Scanner;

/**
 * ����������
 * ��һ���������� n , m (1<=m<=n<=50000)���ڶ���Ϊ����Ϊn��ֻ������a���͡�b�����ַ���s��
 *
 * ���������
 * ����ڲ������������� m ������£��ܹ��õ��� ������� ȫ��a���Ӵ���ȫ��b���Ӵ��ĳ��ȡ�
 *
 * ��������
 * 8 1
 * aabaabaa
 *
 * �������
 * 5
 *
 * ����˵��
 * �ѵ�һ�� 'b' ���ߵڶ��� 'b' �ó� 'a'���ɵõ�����Ϊ 5 ��ȫ 'a' �Ӵ���
 */

/**
 * ����˼·��
 * ���ַ����� a �� b Ҳ����˵������ l~r֮��������ַ���Ϊ a ����Ĳ������� �������� b ����������֮��Ȼ.
 * ������ count[i] ��ʾ �ַ�����λ������ 0~i ������ a �ĸ���
 * �� ���� l~r �� a �ĸ���Ϊ count[r]-count[l-1]
 *
 * b �ĸ����� a �ĸ������ �� ���� l~r �� b �ĸ���Ϊ
 * r+1-count[r]-(l+1-1-count[l-1])=r+1-count[r]-l+count[l-1] = (r+1-l)-(count[r]-count[l-1])
 *
 * ������ l~r �� a �� b �ĸ�����֪�������
 * �� ���䳤��step�ڵ� a �ĸ��� <= m �� ����ͨ�� m ������ ���� ����Ϊstep���ַ��� b
 * �� ���䳤��step�ڵ� b �ĸ��� <= m �� ����ͨ�� m ������ ���� ����Ϊstep���ַ��� a
 *
 * ����Ϊ ���� ���䳤��step�ڵ� b �� a �ĸ��� <= m �� ����ͨ�� m ������ ���� ����Ϊstep���ַ���
 *
 * ����  �Ϳ���ֱ�Ӽ����һ���ַ������ȣ����䳤��step���Ƿ���У���˲���Ҫ���е��ƣ�����ֱ�ӽ��ж����������õ���󳤶ȡ�
 * ���һ������step�Ƿ���е�ʱ�临�Ӷ�ΪO(n),����������ʱ�临�Ӷ�ΪO(log n)��
 * ��ˣ��÷����ܵ�ʱ�临�Ӷ�Ϊ O(n*log n)
 */
public class Character_conversion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();

        // count[i] ��ʾ �ַ�����λ������ 0~i ������ a �ĸ���
        int[] count = new int[n];
        int aNum = 0;
        for (int i=0; i<n; i++){
            if (str.charAt(i)=='a'){
                count[i]=++aNum;
            }else {
                count[i] = aNum;
            }
        }

        //���г�ʱ:���ĳ���δ���ڹ涨ʱ�������н����������Ƿ�ѭ���д���㷨���Ӷȹ���
        //caseͨ����Ϊ78.33%
        // ������Ӵ�С����٣�ʱ�临�ӶȽϴ�
//        for (int i=n; i>0; i-- ){
//            if (isCoversion(i,n,m,count)){
//                System.out.println(i);
//                return;
//            }
//        }


        // ���ַ�������������ֵ
        int l=1, r=n,mid=0;
        int result = 0;
        while (l<=r){
            mid = (l+r)/2;
            if(isCoversion(mid,n,m,count)){
                l = mid+1;
                result = mid;
            }else {
                if (l==r){
                    break;
                }
                r = mid;
            }
        }
        System.out.println(result);

    }

    // ��鵱ǰ���䳤�ȣ�step���Ƿ�����m��������ʵ��ȫa��ȫb
    // b �ĸ����� a �ĸ������ �� ���� l~r �� b �ĸ���Ϊ��(r+1-l)-(count[r]-count[l-1])
    // �� step = (r-l)
    public static boolean isCoversion(int step,int n,int m, int[] count){
        // �����step-1�����䳤��
        step--;
        for (int i=0; (i+step)<n; i++){
            int number = 0;
            if(i!=0){
                number = count[i-1];
            }
            // ���a��ʽ m>=������ b �ĸ���
            if (m>=step+1-(count[i+step]-number)){
                return true;
            }
            //��� b��ʽ�� m>=������ a �ĸ���
            if(m>=count[i+step]-number){
                return true;
            }
        }
        return false;
    }
}
