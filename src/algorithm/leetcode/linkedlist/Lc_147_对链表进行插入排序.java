package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_147_对链表进行插入排序 {

    // 对链表进行插入排序
    // https://leetcode-cn.com/problems/insertion-sort-list/

    public static void main(String[] args) {
        Lc_147_对链表进行插入排序 lc = new Lc_147_对链表进行插入排序();
        ListNode input = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));    // [4,2,1,3]

        System.out.println("对链表" + ListNodeSerialize.serialize(input) + "进行原地插入排序的结果是" + ListNodeSerialize.serialize(lc.insertionSortList(input)));
    }

    // 直接插入排序 - 时间复杂度O(N^2) - 空间复杂度(1)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode last = head;    // 最后一个已排序节点
        ListNode cur = head.next;    // 当前操作的节点

        while (cur != null) {
            if (cur.val >= last.val) {
                last = last.next;
            } else {
                ListNode prev = dummy;
                while (prev.next.val <= cur.val) {
                    prev = prev.next;    // 找到插入点
                }
                last.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = last.next;
        }

        return dummy.next;
    }

    // 归并排序 - 时间复杂度O(N*logN) - 空间复杂度(logN)
    public ListNode insertionSortListMerge(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = insertionSortListMerge(head);
        ListNode right = insertionSortListMerge(tmp);

        ListNode dummy = new ListNode(0), res = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                dummy.next = left;
                left = left.next;
            } else {
                dummy.next = right;
                right = right.next;
            }
            dummy = dummy.next;
        }

        dummy.next = left == null ? right : left;

        return res.next;
    }

}
