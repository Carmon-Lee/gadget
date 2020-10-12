package juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Treiber Stack 并发栈
 * 这里底层使用的是一个链表存储，同时用一个AtomicReference类型变量保存栈的top节点引用
 *
 * @author guang.li
 * @version MyConcurrentStack.java v 1.0 2020/10/10 10:36
 */
public class MyConcurrentStack<T> {
    private AtomicReference<Node<T>> top = new AtomicReference<>();

    /**
     * 通过cas操作压栈
     * @param elem
     */
    public void push(T elem) {
        Node<T> newNode = new Node<>(elem);
        Node<T> oldNode;

        do {
            oldNode = top.get();
            newNode.next = oldNode;
        } while (!top.compareAndSet(oldNode, newNode));
    }

    public T pop() {
        Node<T> oldNode;
        Node<T> newNode;

        do {
            oldNode = top.get();
            if (oldNode == null) {
                return null;
            }
            newNode = oldNode.next;
        } while (top.compareAndSet(oldNode, newNode));

        return oldNode.value;
    }

    static class Node<T> {
        Node<T> next;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }


}
