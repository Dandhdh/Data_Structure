package 剑指offer编程题;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * 思路：平衡因子的绝对值<= 1.
 */
public class IsBalancedTree {

    public static boolean isBalanced_Solution(TreeNode root){
        if(root==null){
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        int diff = left - right;
        if(diff>=-1 && diff<=1){
            return true;
        }
        return false;
    }

    public static int getDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int depth = 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        depth = leftDepth > rightDepth ? leftDepth:rightDepth;
        return depth+1;
    }

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(1);
        root.right.right.right = new TreeNode(122);
        root.right.right.right.right = new TreeNode(122);

        System.out.println(isBalanced_Solution(root));

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
