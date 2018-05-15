package ��ָoffer�����;

import java.util.Arrays;

/**
 * ����ĳ��������ǰ���������������Ľ�������ؽ����ö�������
 * ���������ǰ���������������Ľ���ж������ظ������֡�
 *
 * ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�����ؽ������������ء�
 */
public class ReConstructBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //��ǰ������򹹽�������
    public static TreeNode reConstructBinaryTree(int[] preOrder, int[] middleOrder){
        if(preOrder==null || middleOrder==null){
            return null;
        }
        if(preOrder.length == 0 || middleOrder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preOrder[0]);
        for(int i=0; i<preOrder.length; i++){
            if (preOrder[0] == middleOrder[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(preOrder,1,i+1),
                        Arrays.copyOfRange(middleOrder,0,i));

                root.right = reConstructBinaryTree(Arrays.copyOfRange(preOrder,i+1,preOrder.length),
                        Arrays.copyOfRange(middleOrder,i+1,middleOrder.length));
            }
        }
        return root;
    }

    //������ͺ��򹹽�������
}
