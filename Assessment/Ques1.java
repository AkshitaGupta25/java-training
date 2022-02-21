package Assessment;

import java.util.Stack;

public class Ques1 {
    public static boolean validParentheses(String s){
        if (!s.isEmpty()) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                if (current == '(' || current == '[' || current == '{') {
                    stack.push(current);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char peekChar = stack.peek();
                    if ((current == ')' && peekChar != '(') || (current == '}' && peekChar != '{') || (current == ']' && peekChar != '[')) {
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }
            if(stack.isEmpty()){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String s = "{}[]{{}";
        System.out.println(validParentheses(s));

    }
}
