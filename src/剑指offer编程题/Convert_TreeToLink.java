package ��ָoffer�����;

import javax.swing.tree.TreeNode;

/**
 * ����һ�ö��������������ö���������ת����һ�������˫������Ҫ���ܴ����κ��µĽ�㣬ֻ�ܵ������н��ָ���ָ��
 * Ҫ���ܴ����κ��µĽ�㣬ֻ�ܵ������н��ָ���ָ��
 *
 * ˼·������һ�������β�ڵ㣬�ݹ鴦��������������󷵻������ͷ�ڵ�
 */
public class Convert_TreeToLink {

    public static TreeNode Convert(TreeNode pRootOfTree){
        // lastlistΪת��������ת���������β�ڵ�
        TreeNode lastlist = covertNode(pRootOfTree,null);
        TreeNode pHead = lastlist;
        // ѭ���ҵ�ͷ�ڵ�
        while (pHead!=null && pHead.left!=null){
            pHead = pHead.left;
        }
        return pHead;
    }

    /**
     * @param root ��Ҫת�������ĸ��ڵ�
     * @param lastlist ��ת��Ϊ�����β�ڵ�
     * @return ��ת�ɵ������β�ڵ�
     */
    public static TreeNode covertNode(TreeNode root,TreeNode lastlist){
        if(root==null){
            return null;
        }
        TreeNode cur = root;
        if(cur.left!=null){
            lastlist = covertNode(cur.left,lastlist);
        }

        cur.left = lastlist;
        if(lastlist!=null){
            lastlist = covertNode(cur.right,lastlist);
        }
        return lastlist;
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
            left = null;
            right = null;
        }
    }

}
