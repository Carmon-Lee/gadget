package datastructure.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author guang.li
 * @version QueueBasedStack.java v 1.0 2020/9/29 12:08
 */
public class QueueBasedStack {

    private Deque<Integer> internalQ1 = new ArrayDeque<>();
    private Deque<Integer> internalQ2 = new ArrayDeque<>();

    public void push(int val) {
        if (internalQ1.isEmpty()) {
            internalQ1.addLast(val);
            transfer(internalQ2, internalQ1);
        } else {
            internalQ2.addLast(val);
            transfer(internalQ1, internalQ2);
        }
    }

    private void transfer(Deque<Integer> q1, Deque<Integer> q2) {
        while (!q1.isEmpty()) {
            q2.addLast(q1.removeFirst());
        }
    }

    public int pop() {
        if (!internalQ1.isEmpty()) {
            return internalQ1.removeFirst();
        } else {
            return internalQ2.removeFirst();
        }
    }


    public static void main(String[] args) {
        QueueBasedStack stack = new QueueBasedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
