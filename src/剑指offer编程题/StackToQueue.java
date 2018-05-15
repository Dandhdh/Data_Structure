package ��ָoffer�����;

import java.util.Stack;

/**
 * ������ջ��ʵ��һ�����У���ɶ��е�Push��Pop������ �����е�Ԫ��Ϊint���͡�
 *
 * ˼·��һ��ջѹ��Ԫ�أ�����һ��ջ��Ϊ���壬��ջ1��Ԫ�س�ջ��ѹ��ջ2�С�
 * Ҳ���Խ�ջ1�е����һ��Ԫ��ֱ�ӳ�ջ��������ѹ��ջ2���ٳ�ջ��
 */
public class StackToQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public StackToQueue() {
        this.stack1 = new Stack<Integer>();
        this.stack2 = new Stack<Integer>();
    }

    public void push(int val){
        stack1.push(val);
    }

    public int pop()throws Exception{
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("ջΪ�գ�");
        }

        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
