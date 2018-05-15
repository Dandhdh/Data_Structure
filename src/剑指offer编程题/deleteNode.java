package 剑指offer编程题;

/**
 * O(1)时间删除链表节点
 *
 * 思路：
 * 将要删除节点的下一个节点的值赋给要删除的节点，然后指向下下一个节点
 */
public class deleteNode {

    public void deleteNode(ListNode head, ListNode deListNode) {
        if (deListNode == null || head == null)
            return;

        if (head == deListNode) {
            head = null;
        } else {
            // 若删除节点是末尾节点，往后移一个
            if (deListNode.nextNode == null) {
                ListNode pointListNode = head;
                while (pointListNode.nextNode.nextNode != null) {
                    pointListNode = pointListNode.nextNode;
                }
                pointListNode.nextNode = null;
            } else {
                //实际上是，将需要删除的节点deleteNode变成下一个节点，并指针指向下下个节点
                deListNode.data = deListNode.nextNode.data;
                deListNode.nextNode = deListNode.nextNode.nextNode;
            }
        }
    }

    static class ListNode{
        int data;
        ListNode nextNode;
        ListNode(int data){
            this.data=data;
            this.nextNode=null;
        }
    }
}
