package ��ָoffer�����;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * �������´�ӡ����������ÿ���ڵ㣬ͬ��ڵ�������Ҵ�ӡ��
 *
 * ˼·�����ö��У���������ʵ�֡�
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
            // poll() ����:�Ӷ�����ɾ����һ��Ԫ�أ�head��,���ÿռ��ϵ���ʱ�����׳��쳣��ֻ�Ƿ��� null;
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
