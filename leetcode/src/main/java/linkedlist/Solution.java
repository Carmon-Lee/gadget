package linkedlist;

/**
 * @author yourname
 * Created on 2021-03-14
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // 1 2 1
    // 1 2 2 1
    public boolean isPalindrome(ListNode head) {
        // 1 find mid
        ListNode mid = findMid(head);
        // 2 split and reverse
        ListNode next = mid.next;
        mid.next = null;

        //
        return matches(head, reverse(next).get("head"));
    }

    private ListNode findMid(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private Map<String, ListNode> reverse(ListNode head) {
        // 1 2 3
        Map<String, ListNode> result = new HashMap<>();
        if (head==null || head.next==null) {
            result.put("head", head);
            result.put("tail", head);
            return result;
        }
        ListNode t = head.next;
        head.next = null;

        Map<String, ListNode> next = reverse(t);
        next.get("tail").next = head;
        next.put("tail", head);

        return next;
    }

    private boolean matches(ListNode n1, ListNode n2) {
        while(n1!=null || n2!=null) {
            if (n1==null || n2==null) {
                return true;
            }
            if (n1.val!=n2.val) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, null);
        ListNode l2 = new ListNode(3, null);
        ListNode l3 = new ListNode(2, null);
        ListNode l4 = new ListNode(4, null);
        ListNode l5 = new ListNode(3, null);
        ListNode l6 = new ListNode(2, null);
        ListNode l7 = new ListNode(1, null);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        System.out.println(new Solution().isPalindrome(l1));


    }
}
