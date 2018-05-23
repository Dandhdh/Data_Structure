package ��ָoffer�����;

import java.sql.SQLOutput;

/**
 * ��һ���ַ���ת����һ��������Ҫ����ʹ���ַ���ת�������Ŀ⺯����
 * ��ֵΪ0�����ַ�������һ���Ϸ�����ֵ�򷵻�0
 *
 * ˼·����Ϊ������������������ַ�0��Ӧ48,9��Ӧ57�����ڷ�Χ���򷵻�false��
 */
public class StringToInt {

    public static int StrToInt(String str){
        if(str==null || str.length()==0){
            return 0;
        }
        int mark = 0;
        int number = 0;
        char[] chars = str.toCharArray();

        if(chars[0]=='-'){
            mark=1;
        }

        for (int i=mark; i<chars.length; i++){
            if(chars[i]=='+'){
                continue;
            }
            if(chars[i]<48 || chars[i]>57){
                return 0;
            }
            number = number * 10 + chars[i] -48;
        }
        return mark==0? number:-number;
    }


    public static void main(String[] args) {
        System.out.println(StrToInt("-123"));
    }

}
