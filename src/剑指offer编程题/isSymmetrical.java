package ��ָoffer�����;

/**
 * ��ʵ��һ�������������ж�һ�Ŷ������ǲ��ǶԳƵġ�
 * ע�⣬���һ��������ͬ�˶������ľ�����ͬ���ģ�������Ϊ�ԳƵġ�
 *
 * ˼·�����õݹ�����жϣ�
 * �������������ӵ������������Һ��������������Һ��ӵ��������������ӣ��������������ڵ��ֵ��ȣ����ǶԳƵġ�
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
