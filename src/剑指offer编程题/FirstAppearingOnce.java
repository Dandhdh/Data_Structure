package ��ָoffer�����;

/**
 * ��ʵ��һ�����������ҳ��ַ����е�һ��ֻ����һ�ε��ַ���
 *
 * ˼·��
 * ���������ռ�����жϣ����ַ����顣
 * �����ǰ�ַ���û�д��ڳ���һ�ε��ַ�������#�ַ���
 */
public class FirstAppearingOnce {

    char[] chars = new char[256];
    StringBuilder stringBuilder = new StringBuilder();

    public void insert(char ch){
        stringBuilder.append(ch);
        chars[ch]++;
    }

    public char FirstAppearingOnce(){
        char[] str = stringBuilder.toString().toCharArray();
        for(char c : str){
            if(chars[c]==1){
                return c;
            }
        }
        return '#';
    }
}
