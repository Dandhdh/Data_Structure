package ��ָoffer�����;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ��ʵ��һ����������֮���δ�ӡ��������
 * ����һ��(�����ڵ�����)���մ����ҵ�˳���ӡ���ڶ��㰴�մ��������˳���ӡ�������а��մ����ҵ�˳���ӡ���������ơ�
 *
 * ˼·��
 * ��������ջ�ĸ����ռ�ֱ�洢����ż����Ľڵ㣬Ȼ���ӡ�����
 * ��ʹ������ĸ����ռ���ʵ�֣���������ķ����ʵ�����������
 */
public class PrintTreeByZHI {

    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot==null){
            return result;
        }
        //s1��ʾ�����������������
        Stack<TreeNode> s1 = new Stack<>();
        //s2��ʾż���������������
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(pRoot);
        int level = 1;

        while (!s1.empty() || !s2.empty()){
            if(level%2!=0){
                ArrayList<Integer> list = new ArrayList<>();
                while (!s1.empty()){
                    // ��level��Ľ�����
                    TreeNode node = s1.pop();
                    if(node!=null){
                        // ����level+1��Ľ��
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
