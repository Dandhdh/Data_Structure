package ��ָoffer�����;

/**
 * O(1)ʱ��ɾ������ڵ�
 *
 * ˼·��
 * ��Ҫɾ���ڵ����һ���ڵ��ֵ����Ҫɾ���Ľڵ㣬Ȼ��ָ������һ���ڵ�
 */
public class deleteNode {

    public void deleteNode(ListNode head, ListNode deListNode) {
        if (deListNode == null || head == null)
            return;

        if (head == deListNode) {
            head = null;
        } else {
            // ��ɾ���ڵ���ĩβ�ڵ㣬������һ��
            if (deListNode.nextNode == null) {
                ListNode pointListNode = head;
                while (pointListNode.nextNode.nextNode != null) {
                    pointListNode = pointListNode.nextNode;
                }
                pointListNode.nextNode = null;
            } else {
                //ʵ�����ǣ�����Ҫɾ���Ľڵ�deleteNode�����һ���ڵ㣬��ָ��ָ�����¸��ڵ�
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
