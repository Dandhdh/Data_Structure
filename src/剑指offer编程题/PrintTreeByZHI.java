package 剑指offer编程题;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 请实现一个函数按照之字形打印二叉树，
 * 即第一行(即根节点那行)按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，依此类推。
 *
 * 思路：
 * 利用两个栈的辅助空间分别存储奇数偶数层的节点，然后打印输出。
 * 或使用链表的辅助空间来实现，利用链表的反向迭实现逆序输出。
 */
public class PrintTreeByZHI {

    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot==null){
            return result;
        }
        //s1表示奇数，从右向左输出
        Stack<TreeNode> s1 = new Stack<>();
        //s2表示偶数，从左向右输出
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(pRoot);
        int level = 1;

        while (!s1.empty() || !s2.empty()){
            if(level%2!=0){
                ArrayList<Integer> list = new ArrayList<>();
                while (!s1.empty()){
                    // 将level层的结点输出
                    TreeNode node = s1.pop();
                    if(node!=null){
                        // 放入level+1层的结点
                        list.add(node.val);
                        s2.push(node.left);
                        s2.push(node.right);
                    }
                }

                if(!list.isEmpty()){
                    result.add(list);
                    level++;
                }
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s2.empty()) {
                    TreeNode node = s2.pop();
                    if (node != null) {
                        list.add(node.val);
                        s1.push(node.right);
                        s1.push(node.left);
                    }
                }

                if (!list.isEmpty()) {
                    result.add(list);
                    level++;
                }
            }
        }
        return result;
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(02);
        root.right = new TreeNode(23);
        root.left.left = new TreeNode(01);
        root.left.right = new TreeNode(21);

        System.out.println(Print(root));



    }

}
