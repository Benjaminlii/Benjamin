package com.Benjamin.offer;

/**
 * ClassName:Offer4
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * <p>
 * 思路:
 * 使用递归解决
 * 每次在先序队列中寻找第一个出现在当前递归中中序队列的元素,那么这个元素就是这个中序对应的树中的根节点,向下递归,并在最后返回到上一层
 * 向下递归时考虑递归边界,当中序数组长度为0时,数为空,返回null即可.
 *
 * @author: Benjamin
 * @date: 19-11-20 上午11:15
 */
public class Offer4 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", \nleft=" + left +
                    ", \nright=" + right +
                    "\n}";
        }

        public static void print_per(TreeNode root) {
            if (root != null) {
                System.out.print(root.val + " ");
                print_per(root.left);
                print_per(root.right);
            }
        }

        public static void print_in(TreeNode root) {
            if (root != null) {
                print_in(root.left);
                System.out.print(root.val + " ");
                print_in(root.right);
            }
        }

        public static void print_hou(TreeNode root) {
            if (root != null) {
                print_hou(root.left);
                print_hou(root.right);
                System.out.print(root.val + " ");
            }
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, in, 0, in.length - 1);
    }

    /**
     * 自定义的递归函数
     * @param pre 先序序列
     * @param in 中序序列
     * @param start 被分割的中序序列的首元素下标
     * @param end 被分割的中序序列的尾元素下标
     * @return 这个被分割的中序序列对应的树结构
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in, int start, int end) {
        // 相等时,说明是叶子节点,还要继续往下进行
        if (start > end) {
            return null;
        }

        // 这里初始化为start,实际上不会出现后面找不到rootSub的情况
        int rootSub = start;
        // 在先序序列中找到第一个出现在被分割的中序序列中的元素,作为分割元素
        // 这里不能使用二分查找,序列是不有序的
        MARK:
        for (int i : pre) {
            // 注意这个for中间都是相等
            for (rootSub = start; rootSub <= end; rootSub++) {
                if (i == in[rootSub]) {
                    break MARK;
                }
            }
        }
        // 建立根节点,向左右递归
        TreeNode root = new TreeNode(in[rootSub]);
        root.left = reConstructBinaryTree(pre, in, start, rootSub - 1);
        root.right = reConstructBinaryTree(pre, in, rootSub + 1, end);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new Offer4().reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        TreeNode.print_per(root);
        System.out.println();
        TreeNode.print_in(root);
        System.out.println();
        TreeNode.print_hou(root);
    }


}
