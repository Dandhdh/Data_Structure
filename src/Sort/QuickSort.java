package Sort;

public class QuickSort {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] data = new int[] {11,10,55,78,100,111,45,56,79,90,345,1000};
        System.out.println("排序之前：");
        QuickSort.output(data);
        System.out.println();
        System.out.println("排序之后：");
        QuickSort.qsort(data,0,data.length-1);
        QuickSort.output(data);
    }

    public static void qsort(int[] arr, int left, int right){
        if(left < right){
            int index = partition(arr,left,right);//讲数组分成两部分
            qsort(arr,left,index-1);//递归排序左子数组
            qsort(arr,index+1,right);//递归排序右子数组
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int key = arr[left];//设置关键数key为要排序数组的第一个元素
        while(left<right){
            while(left<right&&arr[right]>=key)
                --right;
            arr[left] = arr[right];//交换比key小的记录到左侧
            while(left<right&&arr[left]<=key)
                left++;
            arr[right] = arr[left];//交换比key小的记录到右侧
        }
        arr[left] = key;
        return left;//返回划分中间点，即，key右边的数全部比key大，key左边的数全部比key小  
    }
    //输出打印
    public static void output(int[] arr){
        for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+"\t");
            }
        }
}