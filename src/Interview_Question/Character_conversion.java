package Interview_Question;

/**
 * 字符转换
 * 有一个仅包含’a’和’b’两种字符的字符串s，长度为n，每次操作可以把一个字符做一次转换（把一个’a’设置为’b’，或者把一个’b’置成’a’)；
 * 但是操作的次数有上限m，问在有限的操作数范围内，能够得到最大连续的相同字符的子串的长度是多少
 */

import java.util.Scanner;

/**
 * 输入描述：
 * 第一行两个整数 n , m (1<=m<=n<=50000)，第二行为长度为n且只包含’a’和’b’的字符串s。
 *
 * 输出描述：
 * 输出在操作次数不超过 m 的情况下，能够得到的 最大连续 全’a’子串或全’b’子串的长度。
 *
 * 输入例子
 * 8 1
 * aabaabaa
 *
 * 输出描述
 * 5
 *
 * 例子说明
 * 把第一个 'b' 或者第二个 'b' 置成 'a'，可得到长度为 5 的全 'a' 子串。
 */

/**
 * 解题思路：
 * 该字符串非 a 即 b 也就是说在区间 l~r之间把所有字符变为 a 所需的步骤数是 该区间内 b 的数量。反之亦然.
 * 用数组 count[i] 表示 字符串中位置区间 0~i 包含的 a 的个数
 * 则 区间 l~r 的 a 的个数为 count[r]-count[l-1]
 *
 * b 的个数用 a 的个数算出 即 区间 l~r 的 b 的个数为
 * r+1-count[r]-(l+1-1-count[l-1])=r+1-count[r]-l+count[l-1] = (r+1-l)-(count[r]-count[l-1])
 *
 * 在区间 l~r 的 a 和 b 的个数已知的情况下
 * 若 区间长度step内的 a 的个数 <= m 则 可以通过 m 个步骤 产生 长度为step的字符串 b
 * 若 区间长度step内的 b 的个数 <= m 则 可以通过 m 个步骤 产生 长度为step的字符串 a
 *
 * 归纳为 ：若 区间长度step内的 b 或 a 的个数 <= m 则 可以通过 m 个步骤 产生 长度为step的字符串
 *
 * 这样  就可以直接计算出一个字符串长度（区间长度step）是否可行，因此不需要进行递推，可以直接进行二分搜索，得到最大长度。
 * 检查一个长度step是否可行的时间复杂度为O(n),二分搜索的时间复杂度为O(log n)。
 * 因此，该方法总的时间复杂度为 O(n*log n)
 */
public class Character_conversion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();

        // count[i] 表示 字符串中位置区间 0~i 包含的 a 的个数
        int[] count = new int[n];
        int aNum = 0;
        for (int i=0; i<n; i++){
            if (str.charAt(i)=='a'){
                count[i]=++aNum;
            }else {
                count[i] = aNum;
            }
        }

        //运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
        //case通过率为78.33%
        // 将区间从大到小地穷举，时间复杂度较大
//        for (int i=n; i>0; i-- ){
//            if (isCoversion(i,n,m,count)){
//                System.out.println(i);
//                return;
//            }
//        }


        // 二分法搜索最大的区间值
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

    // 检查当前区间长度（step）是否能在m个步骤内实现全a或全b
    // b 的个数用 a 的个数算出 即 区间 l~r 的 b 的个数为：(r+1-l)-(count[r]-count[l-1])
    // 且 step = (r-l)
    public static boolean isCoversion(int step,int n,int m, int[] count){
        // 传入的step-1是区间长度
        step--;
        for (int i=0; (i+step)<n; i++){
            int number = 0;
            if(i!=0){
                number = count[i-1];
            }
            // 检查a公式 m>=区间内 b 的个数
            if (m>=step+1-(count[i+step]-number)){
                return true;
            }
            //检查 b公式： m>=区间内 a 的个数
            if(m>=count[i+step]-number){
                return true;
            }
        }
        return false;
    }
}
