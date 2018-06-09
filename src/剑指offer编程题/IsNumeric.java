package ��ָoffer�����;

import javax.swing.plaf.PanelUI;

/**
 * ��ʵ��һ�����������ж��ַ����Ƿ��ʾ��ֵ������������С����
 *
 * ˼·��
 * ����ַ������жϣ�e��E��С����������һ�Σ�
 * ��e��E��ǰһ�����������֣��Ҳ����ǵ�һ�������һ���ַ���
 * ���ŵ�ǰһ���ַ�������e��E��Ҳ����������ʽ�жϣ�
 */
public class IsNumeric {

    public static boolean isNumeric(String string){
        if(string==null){
            return false;
        }
        char[] str = string.toCharArray();
        int index = 0;
        // ��¼e/E�ĸ���(���߱��e/E���Ƿ��Ѿ�����)
        int ecount = 0;
        int point = 0;

        // �����һ���ַ��Ƿ��ž�����
        if(str[0] == '-' || str[0] =='+'){
            index++;
        }

        for (int i=index; i<str.length; i++){

            // �ٴγ��ֵĵ�-��+�Ƿ���e/E֮��
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

            // ���ַ������Ҳ���e/E���򷵻�false����С����ͷ�����continue�����ˣ�
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
