package ��ָoffer�����;

/**
 * ��һ������������У������ظ��Ľ�㣬��ɾ�����������ظ��Ľ�㣬�ظ��Ľ�㲻��������������ͷָ�롣
 *
 * ˼·�����½�һ��ͷ�ڵ㣬Ȼ��������ֵ��ͬ�Ľڵ㣬�ظ����Һ�ɾ��
 */
public class deleteDuplication {

    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return null;
        // �½�һ���ڵ㣬��ֹͷ��㱻ɾ��
        ListNode first = new ListNode(-1);
        first.next = pHead;
        ListNode p = pHead;
        // ָ��ǰһ���ڵ�
        ListNode preNode = first;

        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                int val = p.val;
                // ����ظ�����
                while (p != null && p.val == val) {
                    p = p.next;
                }
                // �ϸ����ظ�ֵָ����һ�����ظ�ֵ����ɾ���ظ�ֵ
                preNode.next = p;
            }else {
                // �����ǰ�ڵ����һ���ڵ�ֵ���ȣ�������ƶ�һλ
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
