package ��ָoffer�����;

import java.util.Arrays;

/**
 * ͳ��һ�����������������г��ֵĴ�����
 *
 * ˼·�����ö��ֲ���+�ݹ�˼�룬����Ѱ�ҡ�
 * ��Ŀ��ֵ���м�ֵ���ʱ�����ж�
 */
public class GetNumberOfK {

    public static int GetNumberOfK(int[] array, int k){

        int result =0;
        int mid = array.length/2;

        if(array==null || array.length==0){
            return 0;
        }
        if(array.length==1){
            if(array[0]==k){
                return 1;
            }else {
                return 0;
            }
        }

        if(k<array[mid]){
            result += GetNumberOfK(Arrays.copyOfRange(array,0,mid),k);
        }else if(k>array[mid]){
            result += GetNumberOfK(Arrays.copyOfRange(array,mid+1,array.length),k);
        }else {
            // ��Ŀ��ֵ���м�ֵ���ʱ�����жϣ��ֱ������߱Ƚ�
            for(int i=mid; i<array.length; i++){
                if(array[i]==k){
                    result++;
                }else {
                    break;
                }
            }
            for (int i=mid-1; i>=0; i--){
                if (array[i]==k){
                    result++;
                }else {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,3,3,3,4,4,4,5};
        System.out.println(GetNumberOfK(arr,3));
    }
}
