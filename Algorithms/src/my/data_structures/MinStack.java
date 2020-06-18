package my.data_structures;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> mins;
    
    public MinStack() {
        stack = new Stack<Integer>();
        mins = new Stack<Integer>();
    }
    
    public void push(int x) {
        if (mins.isEmpty() || x <= getMin()) mins.push(x);
        stack.push(x);
    }
    
    public void pop() {
        if (stack.pop() == getMin()) mins.pop(); 
    }
    
    public int peek() {
        return stack.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}

