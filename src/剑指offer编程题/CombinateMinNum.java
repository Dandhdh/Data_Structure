package ��ָoffer�����;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ����һ�����������飬����������������ƴ�������ų�һ��������ӡ��ƴ�ӳ���������������С��һ��
 *
 * ˼·��
 * �Ƚ���������ת����String���飬Ȼ��String������������ź�����ַ�������ƴ�ӳ�����
 * �ؼ������ƶ��������
 * ��ʹ�ñȽϺͿ��ŵ�˼�룬��ǰ��������������Ƚϣ���С��ŵ���ǰ�棬����ٵݹ���á�
 */
public class CombinateMinNum {

    public static String PrintMinNumber(int[] numbers){
        if(numbers==null || numbers.length==0){
            return "";
        }
        int len = numbers.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++){
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String c1 = str1 + str2;
                String c2 = str2 + str1;
                return c1.compareTo(c2);
            }
        });

        for(int i=0; i<len; i++){
            sb.append(str[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {32,12,111,342,102};
        System.out.println(PrintMinNumber(nums));
    }

}
