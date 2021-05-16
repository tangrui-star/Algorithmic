package DataStructures.linkedList;

import java.util.Stack;

// 演示栈的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        // 入栈
        stack.add("Jack");
        stack.add("tom");
        stack.add("smith");

        // 出栈
        // smith tom jack  先进后出
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
