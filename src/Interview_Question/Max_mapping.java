package Interview_Question;

/**
 * 有 n 个字符串，每个字符串都是由 A-J 的大写字符构成。现在你将每个字符映射为一个 0-9 的数字，不同字符映射为不同的数字。
 * 这样每个字符串就可以看做一个整数，唯一的要求是这些整数必须是正整数且它们的字符串不能有前导零。
 * 现在问你怎样映射字符才能使得这些字符串表示的整数之和最大？
 */

import java.util.*;

/**
 * 输入描述：
 * 每组测试用例仅包含一组数据，每组数据第一行为一个正整数 n ，
 * 接下来有 n 行，每行一个长度不超过 12 且仅包含大写字母 A-J 的字符串。
 * n 不大于 50，且至少存在一个字符不是任何字符串的首字母。
 *
 * 输出描述：
 * 输出一个数，表示最大和是多少。
 *
 * 输入例子
 * 2
 * ABC
 * BCA
 *
 * 输出例子
 * 1875
 */

/**
 * 解题思路：
 * 权值问题：所给ABC和BCA，
 * 对于A权值：100+1=101，B权值：10+100=110，C权值：1+10=11。
 * 权值排序后，110 > 101 > 11;
 * 则B = 9,A = 8,C = 7,D至J=0;
 * （1）：将字符和权值放入一个HashMap中，key为字符，value为权值。
 * （2）：同时保留一个HashSet表，放入每个字符串的第一个字符。这是为后面出现首字符为0的情况考虑。
 * （3）：将HashMap中每个Map.Entry<Character,Long>元素放入一个ArrayList（list）里面，运用集合和比较器来定义对象比较规则，这个list是按照权值降序排列的。
 * （4）：考虑特殊情况
 * 假如后期按照权值大小排序好的顺序为：A>B>C>D>E>F>G>H>I>J，并且有个字符串为J开头的，这个是放入一个set表中的（第（2）步），我们需要从list最后一个元素开始遍历，找到第一个不在set首字符表的元素，然后将其从list中移除，这个字符即对应数字0。假如set表中有（H,I,K），则需要将G移除掉，H,I,J依次对应数字3，2，1，G则为0。
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
        // 存放字符及其对应的权值
        HashMap<Character,Long> map = new HashMap<>();

        // 放入每个字符串的第一个字符，为后面的O的情况考虑
        HashSet<Character> headSet = new HashSet<>();

        // 计算出每个字符的权值
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
        // 按权值大小排序
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
