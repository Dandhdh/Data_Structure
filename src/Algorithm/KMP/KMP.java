package Algorithm.KMP;

/**
 * KMP算法
 *
 * 时间复杂度 O(m+n)
 */
public class KMP {

    //返回第一个查询字符串首字符的index
    public int search(String str, String pattern) {
        char[] strs = str.toCharArray();
        char[] patterns = pattern.toCharArray();
        // i是str的下标、j是pattern的下标
        int L=strs.length, N=patterns.length, i=0, j=0;
        if(N<1) return 0;
        if(L<N) return -1;
        //获取pattern的部分匹配值
        // "部分匹配值"就是"前缀"和"后缀"的最长的共有元素的长度
        int[] lps = lps(pattern);
//        for(int a=0; a<lps.length; a++){
//            System.out.print(lps[a]+" ");
//        }
//        System.out.println();
        while(i<L) {
            if(strs[i]==patterns[j]) { // same value found, move both str and pattern pointers to their right
                ++i;
                ++j;
                if(j==N) return i-N; // whole match found
            }
            else if(j>0) j = lps[j-1]; // move pattern pointer to a previous safe location
            else ++i; // restart searching at next str pointer
        }
        return -1;
    }

    private int[] lps(String pattern) {
        int j=0, i=1, L=pattern.length();
        int[] res = new int[L];
        char[] chars = pattern.toCharArray();
        while(i<L) {
            if(chars[i]==chars[j]) res[i++] = ++j;
            else {
                int temp = i-1;
                while(temp>0) {
                    //获取 到前一个字符位置的子字符串prevLPS的部分匹配值
                    int prevLPS = res[temp];
                    if(chars[i]==chars[prevLPS]) {
                        res[i++] = prevLPS+1;
                        j = prevLPS;
                        break;
                    }
                    else temp = prevLPS-1;
                }
                if(temp<=0) {
                    res[i++] = 0;
                    j = 0;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();

        //String str = "babba";
        //String pattern = "bbb";
        String str = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";

        System.out.println(kmp.search(str, pattern));
    }
}