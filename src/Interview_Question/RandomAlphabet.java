package Interview_Question;


import java.util.LinkedList;
import java.util.Random;

/**
 * YY������
 * �������20�����ظ���Сд��ĸ��������������ʽΪ����
 */
public class RandomAlphabet {

    public static void main(String[] args) {

        Random r = new Random();

        // ��������26��Сд��ĸ
        LinkedList<Character> result = new LinkedList<Character>();
        for (int i=25; i>=0; i--){
            result.addLast((char)(i+97));
        }
        // System.out.println(result);
        // �����ȥ���е�6��
        for (int i=0; i<6; i++){
            int index = r.nextInt(26);
            result.remove(index);
        }
        System.out.println(result);
    }
}
