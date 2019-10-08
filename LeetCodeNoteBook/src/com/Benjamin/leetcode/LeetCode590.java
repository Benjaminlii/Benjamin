package com.Benjamin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LeetCode590
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-8 下午5:58
 */
public class LeetCode590 {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private List<Integer> ans = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        if (root == null)
            return ans;
        if (root.children != null) {
            for (Node child : root.children) {
                postorder(child);
            }
            ans.add(root.val);
        } else {
            ans.add(root.val);
        }
        return ans;
    }

    public static void main(String[] args) {
        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);
        List<Node> nodeList3 = new ArrayList<>();
        nodeList3.add(node5);
        nodeList3.add(node6);
        Node node2 = new Node(2, null);
        Node node4 = new Node(4, null);
        Node node3 = new Node(3, nodeList3);
        List<Node> nodeList1 = new ArrayList<>();
        nodeList1.add(node3);
        nodeList1.add(node2);
        nodeList1.add(node4);
        Node node1 = new Node(1, nodeList1);

        System.out.println(new LeetCode590().postorder(node1));
    }
}