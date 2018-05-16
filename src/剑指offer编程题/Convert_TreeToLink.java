package 剑指offer编程题;

import javax.swing.tree.TreeNode;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 思路：定义一个链表的尾节点，递归处理左右子树，最后返回链表的头节点
 */
public class Convert_TreeToLink {

    public static TreeNode Convert(TreeNode pRootOfTree){
        // lastlist为转换成子树转换成链表的尾节点
        TreeNode lastlist = covertNode(pRootOfTree,null);
        TreeNode pHead = lastlist;
        // 循环找到头节点
        while (pHead!=null && pHead.left!=null){
            pHead = pHead.left;
        }
        return pHead;
    }

    /**
     * @param root 需要转换子树的根节点
     * @param lastlist 已转换为链表的尾节点
     * @return 新转成的链表的尾节点
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
