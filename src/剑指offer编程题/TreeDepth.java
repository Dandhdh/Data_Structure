package ��ָoffer�����;

/**
 * ����һ�ö����������������ȡ��Ӹ���㵽Ҷ������ξ����Ľ�㣨������Ҷ��㣩�γ�����һ��·����
 * �·���ĳ���Ϊ������ȡ�
 *
 * ˼·�����õݹ�����ֱ𷵻������������
 */
public class TreeDepth {

    public int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
}
