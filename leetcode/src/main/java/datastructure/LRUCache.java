package datastructure;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
//    [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,-1,null,null,null,null,null,18,null,null,  24,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,  -1,18,null,null,null,-1,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
//    [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,-1,null,null,null,null,null,18,null,null,  -1,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,  22,18,null,null,null,-1,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null]

    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        if (head == tail || node == head) {
            return node.value;
        }
        if (node == tail) {
            moveTailToHead(node);
        } else {
            moveMidToHead(node);
        }
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            Node newnode = new Node();
            map.put(key, newnode);
            newnode.value = value;
            newnode.key = key;

            if (head == null) {
                head = newnode;
                tail = newnode;
            } else {
                newnode.next = head;
                head.prev = newnode;
                head = newnode;
            }
        } else {
            node.value = value;
            if (node != head && node == tail) {
                moveTailToHead(node);
            } else if (node != head) {
                moveMidToHead(node);
            }
        }

        if (map.size() > capacity) {
            map.remove(tail.key);
            Node t = tail;
            tail = tail.prev;
            tail.next = null;
            t.prev = null;
        }
    }

    private void moveMidToHead(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }

    private void moveTailToHead(Node node) {
        tail = tail.prev;
        tail.next = null;
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }



    static class Node {
        int value;
        int key;
        Node next;
        Node prev;
    }


    //    ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
//[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.put(5, 5);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
