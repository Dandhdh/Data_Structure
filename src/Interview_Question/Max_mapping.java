package Interview_Question;

/**
 * �� n ���ַ�����ÿ���ַ��������� A-J �Ĵ�д�ַ����ɡ������㽫ÿ���ַ�ӳ��Ϊһ�� 0-9 �����֣���ͬ�ַ�ӳ��Ϊ��ͬ�����֡�
 * ����ÿ���ַ����Ϳ��Կ���һ��������Ψһ��Ҫ������Щ���������������������ǵ��ַ���������ǰ���㡣
 * ������������ӳ���ַ�����ʹ����Щ�ַ�����ʾ������֮�����
 */

import java.util.*;

/**
 * ����������
 * ÿ���������������һ�����ݣ�ÿ�����ݵ�һ��Ϊһ�������� n ��
 * �������� n �У�ÿ��һ�����Ȳ����� 12 �ҽ�������д��ĸ A-J ���ַ�����
 * n ������ 50�������ٴ���һ���ַ������κ��ַ���������ĸ��
 *
 * ���������
 * ���һ��������ʾ�����Ƕ��١�
 *
 * ��������
 * 2
 * ABC
 * BCA
 *
 * �������
 * 1875
 */

/**
 * ����˼·��
 * Ȩֵ���⣺����ABC��BCA��
 * ����AȨֵ��100+1=101��BȨֵ��10+100=110��CȨֵ��1+10=11��
 * Ȩֵ�����110 > 101 > 11;
 * ��B = 9,A = 8,C = 7,D��J=0;
 * ��1�������ַ���Ȩֵ����һ��HashMap�У�keyΪ�ַ���valueΪȨֵ��
 * ��2����ͬʱ����һ��HashSet������ÿ���ַ����ĵ�һ���ַ�������Ϊ����������ַ�Ϊ0��������ǡ�
 * ��3������HashMap��ÿ��Map.Entry<Character,Long>Ԫ�ط���һ��ArrayList��list�����棬���ü��ϺͱȽ������������ȽϹ������list�ǰ���Ȩֵ�������еġ�
 * ��4���������������
 * ������ڰ���Ȩֵ��С����õ�˳��Ϊ��A>B>C>D>E>F>G>H>I>J�������и��ַ���ΪJ��ͷ�ģ�����Ƿ���һ��set���еģ��ڣ�2��������������Ҫ��list���һ��Ԫ�ؿ�ʼ�������ҵ���һ������set���ַ����Ԫ�أ�Ȼ�����list���Ƴ�������ַ�����Ӧ����0������set�����У�H,I,K��������Ҫ��G�Ƴ�����H,I,J���ζ�Ӧ����3��2��1��G��Ϊ0��
 */
public class Max_mapping {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        String[] strs = new String[num];
        for(int i=0; i<num; i++){
            strs[i] = sc.nextLine();
        }
        System.out.println(solution(strs,num));
    }

    public static long solution(String[] strs, int num){
        // ����ַ������Ӧ��Ȩֵ
        HashMap<Character,Long> map = new HashMap<>();

        // ����ÿ���ַ����ĵ�һ���ַ���Ϊ�����O���������
        HashSet<Character> headSet = new HashSet<>();

        // �����ÿ���ַ���Ȩֵ
        for (int i=0; i<num; i++){
            int len = strs[i].length();
            headSet.add(strs[0].charAt(0));

            long count = 1;
            for (int j=len-1; j>=0; j--){
                char c = strs[i].charAt(j);
                if (map.containsKey(c)){
                    map.put(c,map.get(c)+count);
                }else {
                    map.put(c,count);
                }
                count*=10;
            }
        }

        ArrayList<Map.Entry<Character,Long>> list = new ArrayList<>(map.entrySet());
        // ��Ȩֵ��С����
        Collections.sort(list, new Comparator<Map.Entry<Character, Long>>() {
            @Override
            public int compare(Map.Entry<Character, Long> o1, Map.Entry<Character, Long> o2) {
                if(o1.getValue()>o2.getValue()){
                    return -1;
                }else if(o1.getValue()<o2.getValue()){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
        if(list.size()==10){
            for (int i =9; i>=0; i--){
                if (!headSet.contains(list.get(i).getValue())){
                    list.remove(i);
                    break;
                }
            }
        }

        long res = 0;
        int i = 0;
        int k = 9;
        while(i < list.size()){
            res += list.get(i++).getValue()*(k--);
        }
        return res;
    }
}
