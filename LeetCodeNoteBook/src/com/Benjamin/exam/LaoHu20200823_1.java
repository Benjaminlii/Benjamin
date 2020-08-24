package com.Benjamin.exam;

/**
 * ClassName:LaoHu20200823_1
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-23 16:51
 */
public class LaoHu20200823_1 {
    private static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 排队
     *
     * @param head ListNode类 头结点
     * @return ListNode类
     */
    public ListNode lineUp(ListNode head) {
        // write code here
        ListNode ans = head;
        ListNode list = new ListNode(0);
        ListNode flag = list;
        while (head.next != null) {
            ListNode node = head.next;
            head.next = node.next;
            node.next = null;
            flag.next = node;
            flag = node;
            if (head.next !=null){
                head = head.next;
            }else{
                break;
            }
        }
        head.next = list.next;
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(12);
        head.next = new ListNode(13);
        head.next.next = new ListNode(14);
        head.next.next.next = new ListNode(15);
        head.next.next.next.next = new ListNode(16);
        head.next.next.next.next.next = new ListNode(17);
        head.next.next.next.next.next.next = new ListNode(18);
        head = new LaoHu20200823_1().lineUp(head);
        while (head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
    }
}