package 剑指offer编程题;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 *
 * 思路：先新建一个头节点，然后向后查找值相同的节点，重复查找后删除
 */
public class deleteDuplication {

    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return null;
        // 新建一个节点，防止头结点被删除
        ListNode first = new ListNode(-1);
        first.next = pHead;
        ListNode p = pHead;
        // 指向前一个节点
        ListNode preNode = first;

        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                int val = p.val;
                // 向后重复查找
                while (p != null && p.val == val) {
                    p = p.next;
                }
                // 上个非重复值指向下一个非重复值：即删除重复值
                preNode.next = p;
            }else {
                // 如果当前节点和下一个节点值不等，则向后移动一位
                preNode = p;
                p = p.next;
            }
        }
        return first.next;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val=val;
            this.next=null;
        }
    }
}
