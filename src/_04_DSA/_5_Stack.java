package _04_DSA;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _5_Stack {
    public boolean validParenthesis(String str) {
        Map<Character, Character> bracketMatch = new HashMap<>();
        bracketMatch.put(')', '(');
        bracketMatch.put('}', '{');
        bracketMatch.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                stack.push(str.charAt(i));
            } else {
                if(!stack.isEmpty() && stack.peek() == bracketMatch.get(str.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    void main() {
        System.out.println("valid Parenthesis: " + validParenthesis("([{})])"));
    }
}

/// ---------------------------------- finding minimum of stack ------------------------------
class MinStack {
    private Stack<Integer> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        // ... using temp stack - O(n)
        Stack<Integer> temp = new Stack<>();
        int min = stack.peek();

        while(!stack.isEmpty()) {
            int top = stack.pop();
            if(top < min) {
                min = top;
            }
            temp.push(top);
        }
        while(!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return min;
    }

    void main() {
        MinStack obj = new MinStack();
        obj.push(1);
        obj.push(2);
        obj.push(-1);
        obj.push(3);
        System.out.println("minimum element: " + obj.getMin());
        obj.pop();
        obj.pop();
        System.out.println("minimum element: " + obj.getMin());
    }
}

// 🚩------------- optimized for finding min in stack (using two stack) -------------
class MinStack2 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        // aur stack me 1 element ho. current value ko minimum se compare karo tab miStack me daalo. because getMin() O(1) time le.
        if(minStack.isEmpty() || val < minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if(minStack.isEmpty()) return;
        // jo value stack me pop hoga. wahi same value minSatck me pop karega.
        int top = stack.pop();
        if(top == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    void main() {
        MinStack2 obj = new MinStack2();
        obj.push(1);
        obj.push(2);
        obj.push(-1);
        obj.push(3);
        System.out.println("minimum element: " + obj.getMin());
        obj.pop();
        obj.pop();
        System.out.println("minimum element: " + obj.getMin());
    }
}
