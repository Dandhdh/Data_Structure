package Algorithm.BigData;

public class BitMapDemo {

    //�������ݵ�
    private byte[] bits;

    //�ܹ��洢��������
    private int capacity;


    public BitMapDemo(int capacity){
        this.capacity = capacity;

        //1bit�ܴ洢8�����ݣ���ôcapacity������Ҫ���ٸ�bit�أ�capacity/8+1,����3λ�൱�ڳ���8
        bits = new byte[(capacity >>3 )+1];
    }

    public void add(int num){

        // num/8�õ�byte[]��index
        int arrayIndex = num >> 3;

        // num%8�õ���byte[index]��λ��
        int position = num & 0x07;

        //��1����position���Ǹ�λ����Ȼ����1��Ȼ�����ǰ��������|���������Ǹ�λ�þ��滻��1�ˡ�
        bits[arrayIndex] |= 1 << position;

    }

    public boolean contain(int num){
        // num/8�õ�byte[]��index
        int arrayIndex = num >> 3;

        // num%8�õ���byte[index]��λ��
        int position = num & 0x07;

        //��1����position���Ǹ�λ����Ȼ����1��Ȼ�����ǰ��������&���ж��Ƿ�Ϊ0����
        return (bits[arrayIndex] & (1 << position)) !=0;

    }

    public void clear(int num){
        // num/8�õ�byte[]��index
        int arrayIndex = num >> 3;

        // num%8�õ���byte[index]��λ��
        int position = num & 0x07;

        //��1����position���Ǹ�λ����Ȼ����1��Ȼ���ȡ�������뵱ǰֵ��&�����������ǰ��λ����.
        bits[arrayIndex] &= ~(1 << position);

    }

    public static void main(String[] args) {
        BitMapDemo bitmap = new BitMapDemo(100);
        bitmap.add(7);
        System.out.println("����7�ɹ�");

        boolean isexsit = bitmap.contain(7);
        System.out.println("7�Ƿ����:"+isexsit);

        bitmap.clear(7);
        isexsit = bitmap.contain(7);
        System.out.println("7�Ƿ����:"+isexsit);
    }
}
