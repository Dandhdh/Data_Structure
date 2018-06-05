package 剑指offer编程题;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * 思路：利用递归进行判断，
 * 若左子树的左孩子等于右子树的右孩子且左子树的右孩子等于右子树的左孩子，并且左右子树节点的值相等，则是对称的。
 */
public class isSymmetrical {

    public boolean isSymmetrical(TreeNode pRoot){
        if(pRoot==null)
            return true;
        return isCommon(pRoot.left,pRoot.right);
    }

    public boolean isCommon(TreeNode leftNode, TreeNode rightNode){
        if(leftNode==null && rightNode==null){
            return true;
        }

        if (leftNode!=null && rightNode!=null){
            return leftNode.val == rightNode.val &&
                    isCommon(leftNode.left,rightNode.right) &&
                    isCommon(leftNode.right, rightNode.left);
        }
        return false;
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
