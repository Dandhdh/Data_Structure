package 剑指offer编程题;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 *
 * 思路：定义两个栈，一个存放入的值。另一个存最小值。
 */
public class GetMinOfStack {

    private static Stack<Integer> stack1 = new Stack<>();
    private static Stack<Integer> stack2 = new Stack<>();

    //每次入栈都跟stack2（存放最小值）的栈顶元素比较
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
