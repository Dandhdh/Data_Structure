package 剑指offer编程题;

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 *
 * 思路：
 * 借助辅助空间进行判断，如字符数组。
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
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
