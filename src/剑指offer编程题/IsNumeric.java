package 剑指offer编程题;

import javax.swing.plaf.PanelUI;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）
 *
 * 思路：
 * 逐个字符进行判断，e或E和小数点最多出现一次，
 * 而e或E的前一个必须是数字，且不能是第一个或最后一个字符，
 * 符号的前一个字符不能是e或E。也可用正则表达式判断！
 */
public class IsNumeric {

    public static boolean isNumeric(String string){
        if(string==null){
            return false;
        }
        char[] str = string.toCharArray();
        int index = 0;
        // 记录e/E的个数(或者标记e/E的是否已经出现)
        int ecount = 0;
        int point = 0;

        // 如果第一个字符是符号就跳过
        if(str[0] == '-' || str[0] =='+'){
            index++;
        }

        for (int i=index; i<str.length; i++){

            // 再次出现的的-，+是否在e/E之后
            if (str[i]=='-' || str[i]=='+'){
                if(str[i-1]!='e' && str[i-1]!='E'){
                    return false;
                }
                continue;
            }

            if(str[i]=='e' || str[i]=='E'){
                ecount++;
                if(ecount>1){
                    return false;
                }

                if (i==0 || str[i-1]<48 ||
                        str[i-1]>57 || i==str.length-1){
                    return false;
                }
                point++;
                continue;
            }

            if (str[i]=='.'){
                point++;
                if(point>1){
                    return false;
                }
                continue;
            }

            // 出现非数字且不是e/E，则返回false，（小数点和符号用continue跳过了）
            if ((str[i] < 48 || str[i] > 57) && (str[i] != 'e') && (str[i] != 'E')){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "0.111e234";
        System.out.println(isNumeric(str));
    }

}
