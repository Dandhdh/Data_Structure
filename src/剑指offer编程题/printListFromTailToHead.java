package 剑指offer编程题;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个链表，从尾到头打印链表每个节点的值
 *
 * 思路：
 * 借助栈实现，或使用递归的方法。
 */
public class printListFromTailToHead {

    //使用栈实现
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

    //递归实现recursion
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
