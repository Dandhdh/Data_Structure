package Sort;

public class QuickSort {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] data = new int[] {11,10,55,78,100,111,45,56,79,90,345,1000};
        System.out.println("����֮ǰ��");
        QuickSort.output(data);
        System.out.println();
        System.out.println("����֮��");
        QuickSort.qsort(data,0,data.length-1);
        QuickSort.output(data);
    }

    public static void qsort(int[] arr, int left, int right){
        if(left < right){
            int index = partition(arr,left,right);//������ֳ�������
            qsort(arr,left,index-1);//�ݹ�������������
            qsort(arr,index+1,right);//�ݹ�������������
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int key = arr[left];//���ùؼ���keyΪҪ��������ĵ�һ��Ԫ��
        while(left<right){
            while(left<right&&arr[right]>=key)
                --right;
            arr[left] = arr[right];//������keyС�ļ�¼�����
            while(left<right&&arr[left]<=key)
                left++;
            arr[right] = arr[left];//������keyС�ļ�¼���Ҳ�
        }
        arr[left] = key;
        return left;//���ػ����м�㣬����key�ұߵ���ȫ����key��key��ߵ���ȫ����keyС  
    }
    //�����ӡ
    public static void output(int[] arr){
        for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+"\t");
            }
        }
}