package ��ָoffer�����;

import java.util.Stack;

/**
 * �ж��Ƿ��ǳ�ջ˳��
 *
 * ���������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������Ƿ�Ϊ��ջ�ĵ���˳��
 * ����ѹ��ջ���������־�����ȡ�
 *
 * ��������1,2,3,4,5��ĳջ��ѹ��˳��
 * ����4��5,3,2,1�Ǹ�ѹջ���ж�Ӧ��һ���������У�
 * ��4,3,5,1,2�Ͳ������Ǹ�ѹջ���еĵ������С���ע�⣺���������еĳ�������ȵģ�
 *
 * ˼·����ջ��ѹ�뵯��Ԫ�أ�������ջ��
 */
public class IsPopOrder {

    public static boolean isPopOrder(int [] pushOrder,int [] popOrder) {

        if(pushOrder==null || popOrder==null){
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int i=0; i<pushOrder.length; i++){
            stack.push(pushOrder[i]);
            while ( !stack.isEmpty() && stack.peek()==popOrder[index]){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushOrder = {1,2,3,4,5};
        int[] popOrder = {4,5,3,2,1};
        System.out.println(isPopOrder(pushOrder,popOrder));
    }
}
