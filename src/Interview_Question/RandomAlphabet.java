package Interview_Question;


import java.util.LinkedList;
import java.util.Random;

/**
 * YY笔试题
 * 随机生成20个不重复的小写字母并进行排序，排序方式为倒序。
 */
public class RandomAlphabet {

    public static void main(String[] args) {

        Random r = new Random();

        // 倒叙生成26个小写字母
        LinkedList<Character> result = new LinkedList<Character>();
        for (int i=25; i>=0; i--){
            result.addLast((char)(i+97));
        }
        // System.out.println(result);
        // 随机减去其中的6个
        for (int i=0; i<6; i++){
            int index = r.nextInt(26);
            result.remove(index);
        }
        System.out.println(result);
    }
}
