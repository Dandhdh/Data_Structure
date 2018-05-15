package ��ָoffer�����;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ����һ��������β��ͷ��ӡ����ÿ���ڵ��ֵ
 *
 * ˼·��
 * ����ջʵ�֣���ʹ�õݹ�ķ�����
 */
public class printListFromTailToHead {

    //ʹ��ջʵ��
    public static ArrayList<Integer> printListFromTailToHead_ByStack(ListNode listNode){
        ArrayList<Integer> list = new ArrayList<>();
        if(listNode==null){
            return list;
        }
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null){
            stack.push(listNode);
            listNode = listNode.next;
        }

        while (stack!=null){
            list.add(stack.pop().val);
        }
        return list;
    }

    //�ݹ�ʵ��recursion
    public static List<ListNode> printListFromTailToHead_ByRecursion(ListNode listNode, List<ListNode> list){

        if(listNode==null){
            return null;
        }
        list = printListFromTailToHead_ByRecursion(listNode.next,list);
        list.add(listNode);
        return list;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val=val;
            this.next=null;
        }
    }
}
