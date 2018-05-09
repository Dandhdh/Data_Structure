package Sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * �����TimSort�㷨��java�е�ʵ������ע�ͣ�����ʵ���߼����Ƶ�ע��û�д���ֱ����ԭ����ע�͡�
 */
class TimSort<T> {

    /**
     * �������кϲ�����̳��ȡ���������̵����н���ͨ�������������ӳ�������������鶼������̣��ǾͲ��ᾭ���鲢����
     * <p/>
     * ���������ֵ����2���ݡ�Tim Perter ��C�����е�ʵ�ְ汾ʹ����64�����Ǹ��ݾ�������İ汾ʹ��32�����ʡ����������£�ʹ���˷�2���ݸ�ֵ���ͱ���Ҫ��д {@link # minRunLength}���������
     * �����С�����ֵ������Ҫ�ڹ��췽���м�СstackLen��ֵ����Ȼ����������Խ��ķ��ա�
     */
    private static final int MIN_MERGE = 32;

    /**
     * ��Ҫ�����������
     */
    private final T[] a;

    /**
     * �������ıȽ���
     */
    private final Comparator<? super T> c;

    /**
     * �ж�����˳�������Ե���ֵ
     * �����ϴ��뿴�����������һ��
     */
    private static final int MIN_GALLOP = 7;

    private int minGallop = MIN_GALLOP;

    /**
     * �鲢��������ʱ�������󳤶ȣ�����ĳ���Ҳ���Ը�������������
     * ��C�����е�ʵ�ַ�ʽ��ͬ��������Խ�С�����飬���ǲ�����ô�����ʱ���顣���ı��������������Ӱ��
     */
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;


    /**
     * ��ʱ���飬���ݷ��͵����ݿ�֪��ʵ�ʵĴ洢Ҫ��Object[],������T[]
     */
    private T[] tmp;

    /**
     * ջ�д��鲢��run��������һ��run i�ķ�Χ��runBase[i]��ʼ��һֱ������runLen[i]��
     * �����������ǰһ��run�Ľ�β������һ��run�Ŀ�ͷ��
     * ��������ĵ�ʽ���ǳ���:
     * runBase[i] + runLen[i] == runBase[i+1];
     **/

    private int stackSize = 0; //ջ��run������
    private final int[] runBase;
    private final int[] runLen;

    /**
     * ������췽����˽�е�����ֻ�������ڲ�������
     * �������ʵ����Ϊ�˱���һ����������е�״̬������
     */
    private TimSort(T[] a, Comparator<? super T> c) {
        this.a = a;
        this.c = c;

        // �����Ƿ�����ʱ����Ŀռ䡣SuppressWainings��Ϊ��������������ת�͵ľ���
        // ��ʱ����ĳ���д�ĺܾ����������׵��Լ���Ϥһ��javaλ������
        // ������� ���鳤�ȵ�һ�������INITIAL_TMP_STORAGE_LENGTH
        int len = a.length;
        @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
        T[] newArray = (T[]) new Object[len < 2 * INITIAL_TMP_STORAGE_LENGTH ?
                len >>> 1 : INITIAL_TMP_STORAGE_LENGTH];
        tmp = newArray;

        /**
         * �����Ƿ��䴢��run��ջ�Ŀռ䣬������������ʱ��չ��
         * C���԰汾�е�ջһֱʹ�ù̶�ֵ85����������һЩ��С������˵��Щ�˷���Դ�����ԣ�
         * ����汾����ʹ������Խ�С������ջ��
         * ��MIN_MERGE��С��ʱ����Щ��ħ������������������Խ��ķ��ա�
         * */
        int stackLen = (len < 120 ? 5 :
                len < 1542 ? 10 :
                        len < 119151 ? 24 : 40);
        runBase = new int[stackLen];
        runLen = new int[stackLen];
    }

    static <T> void sort(T[] a, Comparator<? super T> c) {
        sort(a, 0, a.length, c);
    }

    static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c) {
        if (c == null) {
            Arrays.sort(a, lo, hi);
            return;
        }

        rangeCheck(a.length, lo, hi);
        int nRemaining = hi - lo;
        if (nRemaining < 2)
            return;  // ������0����1 �Ͳ���Ҫ�����ˡ�

        // С��MIN_MERGE���ȵ�����Ͳ��ù鲢�����ˣ�ɱ��������ţ��
        if (nRemaining < MIN_MERGE) {
            int initRunLen = countRunAndMakeAscending(a, lo, hi, c);
            binarySort(a, lo, hi, lo + initRunLen, c);
            return;
        }

        /**
         * March over the array once, left to right, finding natural runs,
         * extending short natural runs to minRun elements, and merging runs
         * to maintain stack invariant.
         *
         * ���潫�����㷨���̵�����,�������Դ��ע����run�ĺ��壬�������Ϊ�������е���˼��
         *
         * �����ң�����һ�����顣�ҳ���Ȼ�ź��������(natural run)���Ѷ̵���Ȼ��������ͨ�������������
         * ��չ��minRun���ȵ��������С����ϲ�ջ�е������������У���֤���򲻱䡣
         */
        TimSort<T> ts = new TimSort<>(a, c); //�½�TimSort���󣬱���ջ��״̬
        int minRun = minRunLength(nRemaining);
        do {
            //��������Ҳ�������һ����������Ȼ��������
            int runLen = countRunAndMakeAscending(a, lo, hi, c);

            // If run is short, extend to min(minRun, nRemaining)
            // ��� ��Ȼ����ĳ��Ȳ���minRun���Ͱ� min(minRun,nRemaining)���ȵķ�Χ�ڵ������ź���
            if (runLen < minRun) {
                int force = nRemaining <= minRun ? nRemaining : minRun;
                binarySort(a, lo, lo + force, lo + runLen, c);
                runLen = force;
            }

            // Push run onto pending-run stack, and maybe merge
            //���Ѿ��ź��������ѹ��ջ�У�����ǲ�����Ҫ�ϲ�
            ts.pushRun(lo, runLen);
            ts.mergeCollapse();

            //��ָ�����runLen���룬׼����ʼ��һ��Ƭ�ε�����
            lo += runLen;
            //ʣ�´������������Ӧ�ļ��� runLen
            nRemaining -= runLen;
        } while (nRemaining != 0);

        // Merge all remaining runs to complete sort
        assert lo == hi;
        ts.mergeForceCollapse();
        assert ts.stackSize == 1;
    }

    /**
     * ���Ż��Ķ��ֲ�������
     *
     * ʹ�ö��ֲ��������㷨��ָ��һ���������������Ǹ�С�����������ѷ�������������
     * ����Ҫ O(n log n) �αȽϺ� O(n^2)�������ƶ���
     *
     * �����ʼ�Ĳ����������������ô���ǿ����������ǡ��������Ĭ�������е�λ��lo(��������)��
     * start(����������)�ķ�Χ�����Ѿ��ź���ġ�
     *
     * @param a     �����������
     * @param lo    ������Χ�ڵ��׸�Ԫ�ص�λ��
     * @param hi    ������Χ�����һ��Ԫ�صĺ�һ��λ��
     * @param start ������Χ�ڵĵ�һ��û���ź����λ�ã�ȷ�� (lo <= start <= hi)
     * @param c     ��������ıȽ���
     */
    @SuppressWarnings("fallthrough")
    private static <T> void binarySort(T[] a, int lo, int hi, int start,
                                       Comparator<? super T> c) {
        assert lo <= start && start <= hi;
        //���start ����㿪ʼ������Ԥ����Ҳ����ԭ����������ġ�
        if (start == lo)
            start++;
        //��startλ�ÿ�ʼ���Ժ��������Ԫ������
        for (; start < hi; start++) {
            //pivot �������ڲ��������ֵ��
            T pivot = a[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            // ��pivotӦ����������õı߽�����Ϊleft��right
            int left = lo;
            int right = start;
            assert left <= right;

            /*
             * ��֤���߼�:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (c.compare(pivot, a[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            assert left == right;

            /**
             * ��ʱ����Ȼ�ܱ�֤:
             * pivot >= [lo, left) && pivot < [left,start)
             * ���ԣ�pivot��ֵӦ����left���ڵ�λ�ã�Ȼ����Ҫ��[left,start)��Χ�ڵ�������������һλ
             * �ڳ��ռ䡣���pivot�������е�ĳ��ֵ��ȣ�leftָ����ָ���ظ���ֵ�ĺ�һλ��
             * ����������������ȶ��ġ�
             */
            int n = start - left;  //��Ҫ�ƶ��ķ�Χ�ĳ���

            // switch�����һ��С�Ż���1-2��Ԫ�ص��ƶ��Ͳ���ҪSystem.arraycopy�ˡ�
            // �������д�����Ǽ�࣬switchԭ�����������ã�
            switch (n) {
                case 2:
                    a[left + 2] = a[left + 1];
                case 1:
                    a[left + 1] = a[left];
                    break;
                default:
                    System.arraycopy(a, left, a, left + 1, n);
            }
            //�ƶ���֮�󣬰�pivot��ֵ�ŵ�Ӧ�ò����λ�ã�����left��λ����
            a[left] = pivot;
        }
    }

    /**
     * ��һ�δ�����TimSort�㷨�е�һ��С�Ż�����������������ǰ��һ�����е�˳��
     * ���������ֱ�ӷ���ͳ�ƽ��������ǽ����ڷ���֮ǰ����������е��ã�
     * ��ȷ��������д��׸�λ�õ���λ�õ����ж�������ġ�
     * ���صĽ��������������ʽ�ģ�lo��������еĿ�ʼλ�á�
     *
     * A run is the longest ascending sequence with:
     *
     * a[lo] <= a[lo + 1] <= a[lo + 2] <= ...
     *
     * or the longest descending sequence with:
     *
     * a[lo] >  a[lo + 1] >  a[lo + 2] >  ...
     *
     * Ϊ�˱�֤������ȶ��ԣ�����Ҫʹ���ϸ�Ľ����������ܱ�֤��ȵ�Ԫ�ز����뵹�������еĹ��̣�
     * ��֤����ԭ����˳�򲻱����ҡ�
     *
     * @param a  �������������
     * @param lo run���׸�Ԫ�ص�λ��
     * @param hi run�����һ��Ԫ�صĺ���һ��λ�ã���Ҫȷ��lo<hi
     * @param c  ��������ıȽ���
     * @return ���׸�Ԫ�ؿ�ʼ������������еĽ�βλ��+1 or �ϸ�Ľ��������еĽ�βλ��+1��
     */
    private static <T> int countRunAndMakeAscending(T[] a, int lo, int hi,
                                                    Comparator<? super T> c) {
        assert lo < hi;
        int runHi = lo + 1;
        if (runHi == hi)
            return 1;

        // �ҳ��������������У�������򣬵���֮
        if (c.compare(a[runHi++], a[lo]) < 0) { // ǰ����Ԫ���ǽ��򣬾Ͱ��ս���ͳ��
            while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) < 0)
                runHi++;
            reverseRange(a, lo, runHi);
        } else {                              // ǰ����Ԫ�������򣬰�������ͳ��
            while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) >= 0)
                runHi++;
        }

        return runHi - lo;
    }

    /**
     * ����������һ�η�Χ��Ԫ��
     *
     * @param a  ָ������
     * @param lo ��η�Χ����ʼλ��
     * @param hi ��η�Χ���յ�λ�õĺ�һ��λ��
     */
    private static void reverseRange(Object[] a, int lo, int hi) {
        hi--;
        while (lo < hi) {
            Object t = a[lo];
            a[lo++] = a[hi];
            a[hi--] = t;
        }
    }

    /**
     * ���ز���ϲ�����С���ȣ������Ȼ����ĳ��ȣ�С�ڴ˳��ȣ���ô��ͨ�����ֲ���������չ��
     * �˳��ȡ�{@link #binarySort}.
     *
     * ���ԵĽ����������������ģ�
     *
     * ��� n < MIN_MERGE, ֱ�ӷ��� n����̫С�ˣ���ֵ�������ӵĲ�������
     * ��� n ������2���ݣ����� n / 2��
     * ��������� ����һ���� k������ MIN_MERGE/2 <= k <= MIN_MERGE,
     * ����������ܱ�֤ n/k �ǳ��ӽ���С��һ��2���ݡ�
     * �������ʵ������һ�ֿռ���ʱ����Ż���
     *
     * @param n �������������ĳ���
     * @return ����鲢����̳���
     * ��δ���д��Ҳ����
     */
    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // ֻҪ���� 2���ݾͻ��� 1
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    /**
     * Pushes the specified run onto the pending-run stack.
     * ��ָ������������ѹ��ȴ��ϲ���ջ��
     *
     * @param runBase �������е��׸�Ԫ�ص�λ��
     * @param runLen  �������еĳ���
     */
    private void pushRun(int runBase, int runLen) {
        this.runBase[stackSize] = runBase;
        this.runLen[stackSize] = runLen;
        stackSize++;
    }

    /**
     * ���ջ�д��鲢���������У�������ǲ��������������Ͱ����ڵ��������кϲ���
     * ֱ�������������������
     *
     * 1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
     * 2. runLen[i - 2] > runLen[i - 1]
     *
     * ÿ����������е�ջ�е�ʱ�򶼻�ִ��һ���������������ջ�е���Ҫ���������
     * ��Ҫ���������������ά����
     *
     * �������£��е�����2048��
     */
    private void mergeCollapse() {
        while (stackSize > 1) {
            int n = stackSize - 2;
            if (n > 0 && runLen[n - 1] <= runLen[n] + runLen[n + 1]) {
                if (runLen[n - 1] < runLen[n + 1])
                    n--;
                mergeAt(n);
            } else if (runLen[n] <= runLen[n + 1]) {
                mergeAt(n);
            } else {
                break; // Invariant is established
            }
        }
    }

    /**
     * �ϲ�ջ�����д��ϲ������У����ʣ��һ�����С��������������������ִֻ��һ��
     */
    private void mergeForceCollapse() {
        while (stackSize > 1) {
            int n = stackSize - 2;
            if (n > 0 && runLen[n - 1] < runLen[n + 1])
                n--;
            mergeAt(n);
        }
    }

    /**
     * ��һ�������У���һ��ָ����key���������Ҳ�����Ӧ�������λ�ã���������д���
     * ��key��ͬ��ֵ(һ�����߶��)���Ƿ�����Щֵ������ߵ�λ�á�
     *
     * �ƶϣ� ͳ�Ƹ��ʵ�ԭ�����������˵���������ϲ������е�β�����ǲ���ģ���β��ʼ
     * �������ҵ��ĸ��ʸ�һЩ����ϸ��һ�£��������£����ֲ���Ҳ�� log(n)����������û��
     * �ü򵥵Ķ��ֲ��ҡ�
     *
     * @param key  ׼�������key
     * @param a    �������������
     * @param base ���з�Χ�ĵ�һ��Ԫ�ص�λ��
     * @param len  ������Χ�ĳ��ȣ�һ����len > 0
     * @param hint ��ʼ���ҵ�λ�ã���0 <= hint <= len;Խ�ӽ��������Խ��
     * @param c    ���򣬲���ʹ�õıȽ���
     * @return ����һ������ k, �� 0 <= k <=n, ������ a[b + k - 1] < a[b + k]
     * ����˵keyӦ�������� a[base + k],
     * �� a[base,base+k) < key && key <=a [base + k, base + len)
     */
    private static <T> int gallopLeft(T key, T[] a, int base, int len, int hint,
                                      Comparator<? super T> c) {
        assert len > 0 && hint >= 0 && hint < len;
        int lastOfs = 0;
        int ofs = 1;
        if (c.compare(key, a[base + hint]) > 0) { // key > a[base+hint]
            // �����ұߣ�ֱ�� a[base+hint+lastOfs] < key <= a[base+hint+ofs]
            int maxOfs = len - hint;
            while (ofs < maxOfs && c.compare(key, a[base + hint + ofs]) > 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // ���յ�ofs������ȷ���ģ��������� a[base+hint+lastOfs] < key <= a[base+hint+ofs]
            // ��һ��
            // ofs:     1   3   7  15  31  63 2^n-1 ... maxOfs
            // lastOfs: 0   1   3   7  15  31 2^(n-1)-1  < ofs


            // ��ΪĿǰ��offset�����hint�ģ���������Ա任
            lastOfs += hint;
            ofs += hint;
        } else { // key <= a[base + hint]
            // ������ߣ�ֱ��[base+hint-ofs] < key <= a[base+hint-lastOfs]
            final int maxOfs = hint + 1;
            while (ofs < maxOfs && c.compare(key, a[base + hint - ofs]) <= 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;
            // ȷ��ofs�Ĺ�����������ͬ
            // ofs:     1   3   7  15  31  63 2^n-1 ... maxOfs
            // lastOfs: 0   1   3   7  15  31 2^(n-1)-1  < ofs

            // Make offsets relative to base
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        }
        assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

        /*
         * ���ڵ������ a[base+lastOfs] < key <= a[base+ofs], ���ԣ�keyӦ����lastOfs��
         * �ұߣ��ֲ�����ofs����base+lastOfs-1�� base+ofs��Χ����һ�ζ�����ҡ�
         */
        lastOfs++;
        while (lastOfs < ofs) {
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (c.compare(key, a[base + m]) > 0)
                lastOfs = m + 1;  // a[base + m] < key
            else
                ofs = m;          // key <= a[base + m]
        }
        assert lastOfs == ofs;    // so a[base + ofs - 1] < key <= a[base + ofs]
        return ofs;
    }

    /**
     * ��gallopLeft���ƣ���ͬ�����������key��ֵ��ĳЩԪ����ȣ��Ƿ�����Щֵ���һ��Ԫ�ص�λ�õ�
     * ��һ��λ��
     *
     * @param key  ��Ҫ���Ҵ�����λ�õ��Ǹ�ֵ
     * @param a    �����������
     * @param base �����ҵ������е�һ��Ԫ�ص�λ��
     * @param len  �����ҵ����еĳ���
     * @param hint ��ʼ���ҵ�λ�ã�0 <= hint < len.��Խ�ӽ��������λ�ã�����Խ�졣
     * @param c    ��������ıȽ���
     * @return һ������ k,  ����0 <= k <= n ���� a[b + k - 1] <= key < a[b + k]
     */
    private static <T> int gallopRight(T key, T[] a, int base, int len,
                                       int hint, Comparator<? super T> c) {
        assert len > 0 && hint >= 0 && hint < len;

        int ofs = 1;
        int lastOfs = 0;
        if (c.compare(key, a[base + hint]) < 0) {
            // Gallop left until a[b+hint - ofs] <= key < a[b+hint - lastOfs]
            int maxOfs = hint + 1;
            while (ofs < maxOfs && c.compare(key, a[base + hint - ofs]) < 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to b
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        } else { // a[b + hint] <= key
            // Gallop right until a[b+hint + lastOfs] <= key < a[b+hint + ofs]
            int maxOfs = len - hint;
            while (ofs < maxOfs && c.compare(key, a[base + hint + ofs]) >= 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to b
            lastOfs += hint;
            ofs += hint;
        }
        assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

        /*
         * Now a[b + lastOfs] <= key < a[b + ofs], so key belongs somewhere to
         * the right of lastOfs but no farther right than ofs.  Do a binary
         * search, with invariant a[b + lastOfs - 1] <= key < a[b + ofs].
         */
        lastOfs++;
        while (lastOfs < ofs) {
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (c.compare(key, a[base + m]) < 0)
                ofs = m;          // key < a[b + m]
            else
                lastOfs = m + 1;  // a[b + m] <= key
        }
        assert lastOfs == ofs;    // so a[b + ofs - 1] <= key < a[b + ofs]
        return ofs;
    }

    /**
     * �ϲ���ջ��λ��i��i+1���������ڵ��������С� i����Ϊ��ջ�������ڶ��͵�����Ԫ�ء�
     * ���仰˵i == stackSize - 2 || i == stackSize - 3
     *
     * @param i ���ϲ��ĵ�һ���������ڵ�λ��
     */
    private void mergeAt(int i) {
        //У��
        assert stackSize >= 2;
        assert i >= 0;
        assert i == stackSize - 2 || i == stackSize - 3;
        //�ڲ���ʼ��
        int base1 = runBase[i];
        int len1 = runLen[i];
        int base2 = runBase[i + 1];
        int len2 = runLen[i + 1];
        assert len1 > 0 && len2 > 0;
        assert base1 + len1 == base2;

        /*
         * ��¼�ϲ�������еĳ��ȣ����i == stackSize - 3 �Ͱ����һ�����е���Ϣ
         * ��ǰ��һλ����Ϊ���κϲ����������¡�i+1��Ӧ�����б��ϲ���i�������ˣ�����
         * i+1 ���п�����ʧ��
         */
        runLen[i] = len1 + len2;
        if (i == stackSize - 3) {
            runBase[i + 1] = runBase[i + 2];
            runLen[i + 1] = runLen[i + 2];
        }
        //i+1��ʧ�ˣ����Գ���Ҳ��������
        stackSize--;

        /*
         * �ҳ��ڶ������е��׸�Ԫ�ؿ��Բ��뵽��һ�����е�ʲôλ�ã���Ϊ�ڴ�λ��֮ǰ�������Ѿ���λ�ˡ�
         * ���ǿ��Ա����ԣ����μӹ鲢��
         */
        int k = gallopRight(a[base2], a, base1, len1, 0, c);
        assert k >= 0;
        // ��ΪҪ����ǰ�벿��Ԫ�أ��������ͳ�����Ӧ�ı仯
        base1 += k;
        len1 -= k;
        // �������2 ���׸�Ԫ��Ҫ���뵽����1�ĺ��棬�Ǿ�ֱ�ӽ�����,
        // ������ ��Ϊ����2�������е�λ�ñ�����������1����,Ҳ����������Χ������������ģ�����
        if (len1 == 0)
            return;

        /*
         * ���������ƣ�������1�����һ��Ԫ��(a[base1+len1-1])���Բ��뵽����2��ʲôλ�ã���Եڶ�����������λ�ã����������е�λ�ã���
         * ���λ�ú����Ԫ��Ҳ�ǲ���Ҫ����鲢�ġ�����len2ֱ�����õ���������Ԫ��ֱ�Ӻ��ԡ�
         */
        len2 = gallopLeft(a[base1 + len1 - 1], a, base2, len2, len2 - 1, c);
        assert len2 >= 0;
        if (len2 == 0)
            return;

        // �ϲ�ʣ�µ������������У���������Ϊ�˽�ʡ�ռ䣬��ʱ����ѡ�� min(len1,len2)�ĳ���
        // �Ż��ĺ�ϸ��
        if (len1 <= len2)
            mergeLo(base1, len1, base2, len2);
        else
            mergeHi(base1, len1, base2, len2);
    }

    /**
     * ʹ�ù̶��ռ�ϲ��������ڵ��������У�����������ȶ��ԡ�
     * ʹ�ñ�����֮ǰ��֤��һ�����е��׸�Ԫ�ش��ڵڶ������е��׸�Ԫ�أ���һ�����е�ĩβԪ��
     * ���ڵڶ������е�����Ԫ��
     *
     * Ϊ�����ܣ����������len1 <= len2��ʱ����ã����Ľ��÷���mergeHiӦ����len1 >= len2
     * ��ʱ����á�len1==len2��ʱ���������ĸ�������
     *
     * @param base1 index of first element in first run to be merged
     * @param len1  length of first run to be merged (must be > 0)
     * @param base2 index of first element in second run to be merged
     *              (must be aBase + aLen)
     * @param len2  length of second run to be merged (must be > 0)
     */
    private void mergeLo(int base1, int len1, int base2, int len2) {
        assert len1 > 0 && len2 > 0 && base1 + len1 == base2;

        //����һ�����зŵ���ʱ������
        T[] a = this.a; // For performance
        T[] tmp = ensureCapacity(len1);
        System.arraycopy(a, base1, tmp, 0, len1);

        int cursor1 = 0;       // ��ʱ����ָ��
        int cursor2 = base2;   // ����2��ָ�룬����鲢����һ������
        int dest = base1;      // ��������ָ��

        // �����Ȱѵڶ������е��׸�Ԫ�أ��ƶ�����������е�λ�ã�Ȼ������Щ����Ҫ�鲢�����
        a[dest++] = a[cursor2++];

        // ����2ֻ��һ��Ԫ�ص�����������ƶ���ָ��λ��֮��ʣ�µ���ʱ����
        // �е���������1��Ԫ��ȫ��copy������
        if (--len2 == 0) {
            System.arraycopy(tmp, cursor1, a, dest, len1);
            return;
        }
        // ����1ֻ��һ��Ԫ�ص�����������ƶ������һ��λ�ã�Ϊ�˲����ǣ��Ȱ�����2�е�Ԫ��
        // ȫ�����ߡ��������Ϊ����1�е����һ��Ԫ�ر�����2�е�����Ԫ�ض������Ǹ÷���ִ�е�����
        if (len1 == 1) {
            System.arraycopy(a, cursor2, a, dest, len2);
            a[dest + len2] = tmp[cursor1]; // Last elt of run 1 to end of merge
            return;
        }

        Comparator<? super T> c = this.c;  // ��������ıȽ���

        int minGallop = this.minGallop;    //  "    "       "     "      "

        // ���˽�break��ǩ��ͬѧҪ����Java��������
        outer:
        while (true) {
            /*
             * �����������ֵ����¼һ����������������һ����Ĵ��������ݴ���Ϣ����������һЩ
             * �Ż�
             * */
            int count1 = 0; // ����1 ���� ������2����ٴ�
            int count2 = 0; // ����2 ���� ������1����ٴ�

            /*
             * ������ֱ�ӵĹ鲢�㷨�ĺϲ��Ĳ��֣������ͳ��count1��count2,
             * �������һ������һ����ֵ���ͻ�����ѭ��
             * */
            do {
                assert len1 > 1 && len2 > 0;
                if (c.compare(a[cursor2], tmp[cursor1]) < 0) {
                    a[dest++] = a[cursor2++];
                    count2++;
                    count1 = 0;

                    // ����2û��Ԫ���˾��������κϲ�
                    if (--len2 == 0)
                        break outer;
                } else {
                    a[dest++] = tmp[cursor1++];
                    count1++;
                    count2 = 0;
                    // �������1ֻʣ�����һ��Ԫ���˾Ϳ�������ѭ��
                    if (--len1 == 1)
                        break outer;
                }

                /*
                 * ����ж��൱�� count1 < minGallop && count2 <minGallop
                 * ��Ϊcount1��count2����һ��Ϊ0
                 * */
            } while ((count1 | count2) < minGallop);



            /*
             * ִ�е�����Ļ���һ�����л������ĵı���һ�����д���ô���������Կ��ܳ�����
             * ��������ô���ǾͰ�������߼���һ�ԡ�ֱ�����������Ա����ơ������ҵ��ĳ��ȣ�
             * ֱ��������copy�Ϳ����ˣ������������copy��Ч�ʡ�
             */
            do {
                assert len1 > 1 && len2 > 0;
                // gallopRight����֮ǰ�ù����Ǹ�����
                count1 = gallopRight(a[cursor2], tmp, cursor1, len1, 0, c);
                if (count1 != 0) {
                    System.arraycopy(tmp, cursor1, a, dest, count1);
                    dest += count1;
                    cursor1 += count1;
                    len1 -= count1;
                    if (len1 <= 1) // ��β�����˻�������
                        break outer;
                }
                a[dest++] = a[cursor2++];
                if (--len2 == 0) //��β�����˻�������
                    break outer;

                count2 = gallopLeft(tmp[cursor1], a, cursor2, len2, 0, c);
                if (count2 != 0) {
                    System.arraycopy(a, cursor2, a, dest, count2);
                    dest += count2;
                    cursor2 += count2;
                    len2 -= count2;
                    if (len2 == 0)
                        break outer;
                }
                a[dest++] = tmp[cursor1++];
                if (--len1 == 1)
                    break outer;
                // ����������Ա�����һ�������ֵ���٣����������״�����β�����
                // Ӧ������Ϊǰ������ݱ��ֺã�������������ƵĿ����Ը��ߣ�
                minGallop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP); //��������Ի��Ǻܴ�Ļ���������������s


            if (minGallop < 0)
                minGallop = 0;

            //ͬ������������������Ƕ�ѭ������֤�����ݵ�˳��̶Ȳ��ã�Ӧ��������ֵ�������˷���Դ
            minGallop += 2;
        }  //outer ����


        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field

        //���ﴦ����β����
        if (len1 == 1) {
            assert len2 > 0;
            System.arraycopy(a, cursor2, a, dest, len2);
            a[dest + len2] = tmp[cursor1]; //  Last elt of run 1 to end of merge
        } else if (len1 == 0) {
            //��Ϊ����1�е����һ��ֵ��������2�е�����ֵ�������ԣ�����������1���ˣ�����2����Ԫ��
            throw new IllegalArgumentException(
                    "Comparison method violates its general contract!");
        } else {
            assert len2 == 0;
            assert len1 > 1;
            System.arraycopy(tmp, cursor1, a, dest, len1);
        }
    }

    /**
     * Like mergeLo, except that this method should be called only if
     * len1 >= len2; mergeLo should be called if len1 <= len2.  (Either method
     * may be called if len1 == len2.)
     *
     * @param base1 index of first element in first run to be merged
     * @param len1  length of first run to be merged (must be > 0)
     * @param base2 index of first element in second run to be merged
     *              (must be aBase + aLen)
     * @param len2  length of second run to be merged (must be > 0)
     */
    private void mergeHi(int base1, int len1, int base2, int len2) {
        assert len1 > 0 && len2 > 0 && base1 + len1 == base2;

        // Copy second run into temp array
        T[] a = this.a; // For performance
        T[] tmp = ensureCapacity(len2);
        System.arraycopy(a, base2, tmp, 0, len2);

        int cursor1 = base1 + len1 - 1;  // Indexes into a
        int cursor2 = len2 - 1;          // Indexes into tmp array
        int dest = base2 + len2 - 1;     // Indexes into a

        // Move last element of first run and deal with degenerate cases
        a[dest--] = a[cursor1--];
        if (--len1 == 0) {
            System.arraycopy(tmp, 0, a, dest - (len2 - 1), len2);
            return;
        }
        if (len2 == 1) {
            dest -= len1;
            cursor1 -= len1;
            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
            a[dest] = tmp[cursor2];
            return;
        }

        Comparator<? super T> c = this.c;  // Use local variable for performance
        int minGallop = this.minGallop;    //  "    "       "     "      "
        outer:
        while (true) {
            int count1 = 0; // Number of times in a row that first run won
            int count2 = 0; // Number of times in a row that second run won

            /*
             * Do the straightforward thing until (if ever) one run
             * appears to win consistently.
             */
            do {
                assert len1 > 0 && len2 > 1;
                if (c.compare(tmp[cursor2], a[cursor1]) < 0) {
                    a[dest--] = a[cursor1--];
                    count1++;
                    count2 = 0;
                    if (--len1 == 0)
                        break outer;
                } else {
                    a[dest--] = tmp[cursor2--];
                    count2++;
                    count1 = 0;
                    if (--len2 == 1)
                        break outer;
                }
            } while ((count1 | count2) < minGallop);

            /*
             * One run is winning so consistently that galloping may be a
             * huge win. So try that, and continue galloping until (if ever)
             * neither run appears to be winning consistently anymore.
             */
            do {
                assert len1 > 0 && len2 > 1;
                count1 = len1 - gallopRight(tmp[cursor2], a, base1, len1, len1 - 1, c);
                if (count1 != 0) {
                    dest -= count1;
                    cursor1 -= count1;
                    len1 -= count1;
                    System.arraycopy(a, cursor1 + 1, a, dest + 1, count1);
                    if (len1 == 0)
                        break outer;
                }
                a[dest--] = tmp[cursor2--];
                if (--len2 == 1)
                    break outer;

                count2 = len2 - gallopLeft(a[cursor1], tmp, 0, len2, len2 - 1, c);
                if (count2 != 0) {
                    dest -= count2;
                    cursor2 -= count2;
                    len2 -= count2;
                    System.arraycopy(tmp, cursor2 + 1, a, dest + 1, count2);
                    if (len2 <= 1)  // len2 == 1 || len2 == 0
                        break outer;
                }
                a[dest--] = a[cursor1--];
                if (--len1 == 0)
                    break outer;
                minGallop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGallop < 0)
                minGallop = 0;
            minGallop += 2;  // Penalize for leaving gallop mode
        }  // End of "outer" loop
        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field

        if (len2 == 1) {
            assert len1 > 0;
            dest -= len1;
            cursor1 -= len1;
            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
            a[dest] = tmp[cursor2];  // Move first elt of run2 to front of merge
        } else if (len2 == 0) {
            throw new IllegalArgumentException(
                    "Comparison method violates its general contract!");
        } else {
            assert len1 == 0;
            assert len2 > 0;
            System.arraycopy(tmp, 0, a, dest - (len2 - 1), len2);
        }
    }

    /**
     * ��֤��ʱ����Ĵ�С�ܹ��������е���ʱԪ�أ�����Ҫ��ʱ��Ҫ��չ��ʱ����Ĵ�С��
     * ����Ĵ�С��ָ������������֤���Եĸ��Ӷȡ�
     *
     * һ�����벽��̫С������Ĵ�����Ȼ�����࣬�˷�ʱ�䣻һ������Ŀռ��㹻�󣬱�Ȼ��
     * �˷ѿռ䡣��������£��鲢�������ʱ�ռ�ÿ�δ�ĺϲ����� * 2��
     * ��󳤶Ȳ��ᳬ�����鳤�ȵ�1/2�� ���������2 ���Ž��ܵ���ϵ��
     *
     * @param minCapacity ��ʱ������Ҫ����С�ռ�
     * @return tmp ��ʱ����
     */
    private T[] ensureCapacity(int minCapacity) {
        // �����ʱ���鳤�Ȳ���������Ҫ���¼�����ʱ���鳤�ȣ�
        // ������ȹ���ֱ�ӷ��ص�ǰ��ʱ����
        if (tmp.length < minCapacity) {
            // �����Ǽ�����С�Ĵ���minCapacity��2���ݡ��������������������һ�¡�
            //
            // �������޷������� k,�����ֽ������£�
            // 00000000 10000000 00000000 00000000  k
            // 00000000 11000000 00000000 00000000  k |= k >> 1;
            // 00000000 11110000 00000000 00000000  k |= k >> 2;
            // 00000000 11111111 00000000 00000000  k |= k >> 4;
            // 00000000 11111111 11111111 00000000  k |= k >> 8;
            // 00000000 11111111 11111111 11111111  k |= k >> 16
            // �������λ��ʵ��ֻ�����λ�й�ϵ����λ�Ľ�������λ�����bitȫ�������1
            // ��� k++ �Ľ�� ���Ǹպ��Ǳ� minCapacity ���2����
            // д������6
            int newSize = minCapacity;
            newSize |= newSize >> 1;
            newSize |= newSize >> 2;
            newSize |= newSize >> 4;
            newSize |= newSize >> 8;
            newSize |= newSize >> 16;
            newSize++;

            if (newSize < 0) // Not bloody likely! ������������������bug��
                newSize = minCapacity;
            else
                newSize = Math.min(newSize, a.length >>> 1);

            @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
            T[] newArray = (T[]) new Object[newSize];
            tmp = newArray;
        }
        return tmp;
    }

    /**
     * ��鷶ΧfromIndex��toIndex�Ƿ��������ڣ�����������쳣
     *
     * @param arrayLen  ��������ĳ���
     * @param fromIndex �÷�Χ�����
     * @param toIndex   �÷�Χ���յ�
     * @throws IllegalArgumentException       if fromIndex > toIndex
     * @throws ArrayIndexOutOfBoundsException if fromIndex < 0 or toIndex > arrayLen
     */
    private static void rangeCheck(int arrayLen, int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        if (fromIndex < 0)
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        if (toIndex > arrayLen)
            throw new ArrayIndexOutOfBoundsException(toIndex);
    }
}

