package com.Benjamin.exam;

import java.util.Scanner;

/**
 * ClassName:Qihu360_20200822_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 2020-08-22 19:53
 */
public class Qihu360_20200822_2 {
    private static class MyList {

        MyListNode head;
        MyListNode tail;
        int size;

        public MyList(int size) {
            MyListNode node = new MyListNode(0);
            head = node;
            for (int i = 1; i <= size; i++) {
                node.next = new MyListNode(i);
                node = node.next;
                tail = node;
            }
            head = head.next;
            this.size = size;
        }

        private static class MyListNode {
            int val;
            MyListNode next;

            public MyListNode(int val) {
                this.val = val;
            }
        }
    }

    private static void do1(MyList list) {
        MyList.MyListNode head = list.head;
        list.head = head.next;
        head.next = null;
        list.tail.next = head;
        list.tail = head;
    }

    private static void do2(MyList list) {
        MyList.MyListNode node = list.head;
        while (node.next != null) {
            int num1 = node.val;
            int num2 = node.next.val;
            node.val = num2;
            node.next.val = num1;
        }
    }

    private static MyList method(int n, int[] doArray) {
        MyList ans = new MyList(n);
        for (int i = 0; i < doArray.length; i++) {
            int doWhat = doArray[i];
            if (doWhat == 2 && doArray[i + 1] == 2) {
                i++;
                continue;
            }
            if (doWhat == 1) {
                do1(ans);
            } else {
                do2(ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MyList ans = method(10000, new int[]{});
        StringBuilder sb = new StringBuilder();
        MyList.MyListNode node = ans.head;
        while (node != null) {
            sb.append(node.val).append(" ");
            node = node.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] doArray = new int[m];
        for (int i = 0; i < m; i++) {
            doArray[i] = in.nextInt();
        }
        in.close();
        MyList ans = method(n, doArray);
        StringBuilder sb = new StringBuilder();
        MyList.MyListNode node = ans.head;
        while (node != null) {
            sb.append(node.val).append(" ");
            node = node.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
