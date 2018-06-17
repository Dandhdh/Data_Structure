package Interview_Question;

/**
 * 百度编程题
 * 买帽子
 * 度度熊想去商场买一顶帽子，商场里有N顶帽子，有些帽子的价格可能相同。
 * 度度熊想买一顶价格第三便宜的帽子，问第三便宜的帽子价格是多少？
 *
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * 输入描述：
 * 首先输入一个正整数N（N <= 50），接下来输入N个数表示每顶帽子的价格（价格均是正整数，且小于等于1000）
 *
 * 输出描述
 * 如果存在第三便宜的帽子，请输出这个价格是多少，否则输出-1
 *
 * 输入样例：
 * 10
 * 10 10 10 10 20 20 30 30 40 40
 *
 * 输出样例：
 * 30
 */
public class Buy_Hat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int price[] =new int[n];
        for (int i=0; i<n; i++){
            price[i] = sc.nextInt();
        }

        //快速排序
        Arrays.sort(price);

        //代表第几便宜的帽子
        int flag=1;

        //循环
        for(int i=0;i<n-1;i++){
            if(price[i]<price[i+1]){
                flag++;
            }
            //找出第三便宜的输出
            if(flag==3){
                System.out.println(price[i+1]);
                break;
            }
        }
        if(flag!=3){
            System.out.println(-1);
        }
    }
}
