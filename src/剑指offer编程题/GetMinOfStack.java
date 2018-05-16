package ��ָoffer�����;

import java.util.Stack;

/**
 * ����ջ�����ݽṹ�����ڸ�������ʵ��һ���ܹ��õ�ջ��СԪ�ص�min������
 *
 * ˼·����������ջ��һ��������ֵ����һ������Сֵ��
 */
public class GetMinOfStack {

    private static Stack<Integer> stack1 = new Stack<>();
    private static Stack<Integer> stack2 = new Stack<>();

    //ÿ����ջ����stack2�������Сֵ����ջ��Ԫ�رȽ�
    public  static void push(int val){
        stack1.push(val);
        if(stack2.isEmpty()){
            stack2.push(val);
        }else{
            if(stack2.peek()>val){
                stack2.push(val);
            }
        }
    }

    public static int pop(){
        int val = stack1.pop();
        if(val==stack2.peek()){
            stack2.pop();
        }
        return val;
    }

    public static int min(){
        return stack2.peek();
    }



}
