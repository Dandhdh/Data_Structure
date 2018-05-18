package 剑指offer编程题;

import java.util.Arrays;

/**
 * 判断是不是后序遍历
 *
 * 输入一个整数数组，判断该数组是不是    某二叉搜索树  的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 思路：先找到右子树的开始位置，然后分别进行左右子树递归处理。
 */
public class VerifySquenceOfBST {

    public static boolean isPostOrder(int[] sequence){
        if( sequence==null || sequence.length==0){
            return false;
        }

        int rstart = 0;
        int length = sequence.length;

        // 计算比sequence[length-1]小的元素个数（即计算左子树的节点个数）
        for (int i = 0; i < length - 1; i++) {
            if (sequence[i] < sequence[length - 1])
                rstart++;
        }

        if (rstart == 0) {
            //左子树为空
            isPostOrder(Arrays.copyOfRange(sequence,0,length-1));
        }else {
            //剩下的坐标[rstart,length-2]为右子树的元素个数
            for (int i = rstart; i < length - 1; i++) {
                // 存在着右子树的元素比根节点的值小或等于，则返回false
                if (sequence[i] <= sequence[length - 1]) {
                    return false;
                }
            }
            // 递归判断根节点的左右子树
            isPostOrder(Arrays.copyOfRange(sequence,0,rstart));
            isPostOrder(Arrays.copyOfRange(sequence,rstart,length - 1));
        }
        return true;
    }

    public static void main(String[] args) {
        int[] srr = {3,5,4,7,9,8,6};
        System.out.println(isPostOrder(srr));
    }

}
