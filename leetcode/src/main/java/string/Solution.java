package string;

/**
 * Created on 2021-05-04
 */
public class Solution {

    private int[][] container;
    private int[] positions;

    public Solution(int stackSize) {
        container = new int[3][stackSize];
        for (int i=0;i<3;i++) {
            container[i] = new int[stackSize];
        }
        positions = new int[] {-1,-1,-1};
    }

    public void push(int stackNum, int value) {
        if (positions[stackNum]>=container[0].length-1) {
            return;
        }
        positions[stackNum]++;
        container[stackNum][positions[stackNum]] = value;
    }

    public int pop(int stackNum) {
        if (positions[stackNum]<0) {
            return -1;
        }
        int res = container[stackNum][positions[stackNum]];
        positions[stackNum]--;
        return res;
    }

    public int peek(int stackNum) {
        if (positions[stackNum]<0) {
            return -1;
        }
        return container[stackNum][positions[stackNum]];
    }

    public boolean isEmpty(int stackNum) {
        return positions[stackNum]<0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(1);
        solution.push(0,1);
        solution.push(0,2);
        solution.pop(0);
        solution.pop(0);
        solution.pop(0);
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */