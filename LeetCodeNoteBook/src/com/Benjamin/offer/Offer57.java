package com.Benjamin.offer;

/**
 * ClassName:Offer57
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉树的下一个结点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * 思路:
 * 分为三种情况:
 * 1.该节点有右孩子节点,那么终须后继节点肯定出现在右子树中,并且应该是右子树最左边的节点,那么一致寻找左子节点就行
 * 2.该节点没有右孩子,那么向上寻找,找到所给节点不出现在左子树的节点,也就是向上寻找,当不是按左子树向上寻找时,退出
 *
 * @author: Benjamin
 * @date: 20-1-5 下午1:04
 */
public class Offer57 {
    private static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeLinkNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null){
            return null;
        }
        TreeLinkNode node;
        if (pNode.right != null){
            node = pNode.right;
            while (node.left != null){
                node = node.left;
            }
        }else {
            node = pNode;
            while (node.next != null && node.next.right == node){
                node = node.next;
            }
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeLinkNode treeLinkNode = new TreeLinkNode(1);
        treeLinkNode.left = new TreeLinkNode(2);
        treeLinkNode.right = new TreeLinkNode(3);
        treeLinkNode.left.left = new TreeLinkNode(4);
        treeLinkNode.left.right = new TreeLinkNode(5);
        treeLinkNode.right.left = new TreeLinkNode(6);
        treeLinkNode.right.right = new TreeLinkNode(7);
        System.out.println(new Offer57().GetNext(treeLinkNode));
    }
}
