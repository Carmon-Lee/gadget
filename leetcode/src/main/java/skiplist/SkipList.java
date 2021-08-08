package skiplist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author liguang
 * Created on 2021-02-18
 */
public class SkipList {

    private Node head;
    private int maxLevel;
    private double probability;

    public SkipList(int maxLevel, double probability) {
        this.maxLevel = maxLevel;
        this.probability = probability;
    }

    public void insert(int value) {
        if (exists(value)) {
            return;
        }
        Node newNode = createNode(value);
        if (head == null) {
            head = newNode;
            return;
        }
        int newNodeLevel = newNode.getNextIndex().length;
        Node[] updates = new Node[newNodeLevel];
        for (int i = 0; i < updates.length; i++) {
            if (updates[i] == null) {
                continue;
            }
            Node[] nextIndex = updates[i].getNextIndex();
            if (nextIndex != null && nextIndex.length >= i) {
                newNode.getNextIndex()[i] = nextIndex[i];
                nextIndex[i] = newNode;
            }
        }
    }

    private Node createNode(int value) {
        int level = calLevel();
        Node[] nextIdx = new Node[level+1];
        return new Node(value, nextIdx);
    }

    private int calLevel() {
        int level = 0;
        Random rand = new Random();
        while (level < maxLevel - 1 && rand.nextDouble() < probability) {
            level++;
        }
        return level;
    }

    public boolean exists(int target) {
        Node cur = head;
        for (int i = maxLevel - 1; i >= 0; i--) {
            if (cur == null) {
                return false;
            }
            if (cur.getValue() >= target) {
                return cur.getValue() == target;
            }
            Node[] nextIndex = cur.getNextIndex();
            if (nextIndex == null) {
                return false;
            }
            // 下沉一层
            if (nextIndex.length - 1 < i) {
                continue;
            }
            if (nextIndex[i] != null && nextIndex[i].getValue() < target) {
                cur = nextIndex[i];
            }
        }
        return cur != null && cur.getValue() == target;
    }

    public void delete(int value) {

    }

    static class Node {
        private int value;
        private Node[] nextIndex;

        public Node(int value, Node[] nextIndex) {
            this.value = value;
            this.nextIndex = nextIndex;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node[] getNextIndex() {
            return nextIndex;
        }

        public void setNextIndex(Node[] nextIndex) {
            this.nextIndex = nextIndex;
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList(4, 0.5);
        for (int i = 0; i < 16; i++) {
            skipList.insert(i + 1);
        }
    }


}
