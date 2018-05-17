package 剑指offer编程题;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * 思路：利用队列（链表）辅助实现。
 */
public class PrintFromTopToBottom {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static ArrayList<TreeNode> printFromTopToBottom(TreeNode root){
        ArrayList<TreeNode> rootList = new ArrayList<>();
        if(root==null){
            return rootList;
        }
        LinkedList<TreeNode> rootLink = new LinkedList<>();
        rootLink.add(root);

        while (!rootLink.isEmpty()){
            // poll() 方法:从队列中删除第一个元素（head）,在用空集合调用时不是抛出异常，只是返回 null;
            TreeNode node = rootLink.poll();
            if(node.left!=null){
                rootLink.addLast(node.left);
            }
            if(node.right!=null){
                rootLink.addLast(node.right);
            }
        }

        return rootList;

    }


}
