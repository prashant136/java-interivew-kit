package _04_DSA;

import java.util.*;

public class _5_Stack {
    // ---------------------------- next greater elements --------------------------------
    // arr = [1,2,3,4,3], ans = [2,3,4,-1,-1]
    public int[] nextGreaterElementBruteForce(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[i]) {
                    ans[i] = nums[j];
                    break;
                }
            }
        }
        return ans;
    }

    // using stack = time - O(n), space - O(n)
    // for every element we check for greater element in stack, we traverse the stack till we found greater element, after founding greater element just put element in stack else return -1
    public int[] nextGreaterElementsStack(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length-1; i >= 0 ; i--) {
            // traverse stack till we found greater element at peek
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            // after traversing we found our greater element
            if(!stack.isEmpty()) {
                ans[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return ans;
    }

    // ------------------------- [ https://leetcode.com/problems/next-greater-element-ii/ ] ------------------------
    // brute force = time - O(n2), space - O(n)
    // arr = [1,2,3,4,3], ans = [2,3,4,-1,-1] (it is circular fashion)
    public int[] nextGreaterElementBruteForceI(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + i; j++) {
                // j circular way me ghumega.
                if(nums[j % n] > nums[i]) {
                    ans[i] = nums[j % n];
                    break;
                }
            }
        }
        return ans;
    }

    // using stack = time - O(n), space - O(n)
    // it is circular fashion
    public int[] nextGreaterElementsStackI(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            int num = nums[i % n];
            // traverse stack till we found greater element at peek
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            // after traversing we found our greater element
            if (i < n && !stack.isEmpty()) {
                ans[i] = stack.peek();
            }
            stack.push(num);
        }
        return ans;
    }

    // ---------------------- [ https://leetcode.com/problems/evaluate-reverse-polish-notation/ ] ---------------------
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (str.equals("-")) {       // ["4","3","-"] -> 1
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (str.equals("/")) {        // ["4","13","5","/","+"] -> 6
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

    // ------------------------- [ https://leetcode.com/problems/daily-temperatures/ ] --------------------------
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<List<Integer>> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().get(0) <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                ans[i] = stack.peek().get(1) - i;
            }
            stack.push(new ArrayList<>(List.of(temperatures[i], i)));
        }
        return ans;
    }

    // ----------------------- [ https://leetcode.com/problems/asteroid-collision/ ] -------------------------
    private boolean isPositive(int num) {
        return num > 0;
    }

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        for (int ele : asteroids) {
            list.add(ele);
        }

        for (int i = 1; i < asteroids.length; i++) {
            int prev = asteroids[i - 1];
            int curr = asteroids[i];
            if (isPositive(prev) && !isPositive(curr)) {
                if (Math.abs(prev) > Math.abs(curr)) {
                    list.remove(i);
                } else if (Math.abs(prev) < Math.abs(curr)) {
                    list.remove(i - 1);
                } else if (Math.abs(prev) == Math.abs(curr)) {
                    list.remove(i);
                    list.remove(i - 1);
                }
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int[] asteroidCollisionI(int[] asteroids) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(15);

        numbers.remove(1);
        numbers.remove(0);
        System.out.println(numbers);

        return null;
    }








    // ----------------------- [ https://leetcode.com/problems/backspace-string-compare/ ] -----------------------
    // String s = "ab#c";
    // String t = "ad#c";
    // output - true
    private String helper(String s) {
        StringBuilder str = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            str.append(stack.pop());
        }
        return str.toString();
    }
    public boolean backspaceCompare(String s, String t) {
        String str1 = helper(s);
        String str2 = helper(t);
        return str1.equals(str2);
    }

    // Optimized - do later...
    public boolean backspaceCompareI(String s, String t) {
        return false;
    }

    // ------------------- [ https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/ ] -----------------
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            str.insert(0, stack.pop());
        }
        return str.toString();
    } // // also can use StringBuilder to remove add string , no need to of stack


    // ------------------------- [ https://leetcode.com/problems/make-the-string-great/ ] --------------------------
    // str = Pp, output = ""
    // str = "abBAcC", output = ""
    // str = "leEeetcode", output = leetcode
    private boolean checkAdjacent(Character ch, Stack<Character> stack) {
        if (!stack.isEmpty()) {
            if ((Character.isUpperCase(ch) && stack.peek() == Character.toLowerCase(ch)) ||
                    (Character.isLowerCase(ch) && stack.peek() == Character.toUpperCase(ch))) {
                return true;
            }
        }
        return false;
    }
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (checkAdjacent(ch, stack)) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            str.insert(0, stack.pop());
        }
        return str.toString();
    } // // also can use StringBuilder to remove add string , no need to of stack


    // --------------- [ https://leetcode.com/problems/minimum-string-length-after-removing-substrings/] ---------------
    public int minLength(String s) {
        return 0;
    }


    // ------------------------ [https://leetcode.com/problems/valid-parentheses ] --------------------------
    public boolean validParenthesis(String str) {
        Map<Character, Character> bracketMatch = new HashMap<>();
        bracketMatch.put(')', '(');
        bracketMatch.put('}', '{');
        bracketMatch.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                stack.push(str.charAt(i));
            } else {
                if (!stack.isEmpty() && stack.peek() == bracketMatch.get(str.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    // ------------------ [ https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/ ] --------------------
    // s = ()), output = 1
    // s = (((, output = 3
    // s = )(((), output = 3
    // count = closing + inside opening brackets inside stack
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int closing = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    closing++;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.size() + closing;
    }

    // ---------------------------- [ https://leetcode.com/problems/score-of-parentheses ] ------------------------------
    public int scoreOfParentheses(String s) {
        return 0;
    }

    // 🟠🟠🟠 [HARD] ------------------------- [ https://leetcode.com/problems/longest-valid-parentheses ] ----------------------------
    public int longestValidParentheses(String s) {
        return 0;
    }


    //


    void main() {
        System.out.println("next greater element: " + Arrays.toString(nextGreaterElementBruteForce(new int[]{1,2,3,4,3})));
        System.out.println("next greater element: " + Arrays.toString(nextGreaterElementsStack(new int[]{1,2,3,4,3})));

        System.out.println("next greater element: " + Arrays.toString(nextGreaterElementBruteForceI(new int[]{1,2,3,4,3})));
        System.out.println("next greater element: " + Arrays.toString(nextGreaterElementsStackI(new int[]{1,2,3,4,3})));


        System.out.println("evalulate reverse polish notation: " + evalRPN(new String[]{"4","13","5","/","+"})); // 6

        System.out.println("daily temperature: " + Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));     // [1,1,4,2,1,1,0,0]

//        System.out.println("sdv: " + asteroidCollision(new int[]{3,5,-6,2,-1,4}));

        System.out.println("backspace string compare: " + backspaceCompare("ab#c", "ad#c"));

        System.out.println("remove all adjacent duplicates in string:" + removeDuplicates("abbaca"));  // ca
        System.out.println("make great: " + makeGood("leEeetcode"));

        System.out.println("valid Parenthesis: " + validParenthesis("([{})])"));

        System.out.println("minimum add to make parentheses valid: " + minAddToMakeValid(")((()"));

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
