package 剑指offer编程题;

import java.util.Arrays;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class ReConstructBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //由前序和中序构建二叉树
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

    //由中序和后序构建二叉树
}
