package com.Benjamin.offer;

/**
 * ClassName:Offer17
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 * @author: Benjamin
 * @date: 19-11-27 上午11:06
 */
public class Offer17 {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    // 判断子树
    public boolean HasSubtree1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return HasSubtree(root1.left, root2.left) && HasSubtree(root1.right, root2.right);
        }
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    // 子结构不代表子树
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2) || doesTree1EqualsTree2(root1, root2);
    }

    // roo2可以条件结束,root1不行
    // 这个方法判断两个树是否root1包含root2
    public boolean doesTree1EqualsTree2(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                return doesTree1EqualsTree2(root1.left, root2.left) && doesTree1EqualsTree2(root1.right, root2.right);
            }
            return false;
        } else return root2 == null;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(8);
        root1.left = new TreeNode(8);
        root1.left.left = new TreeNode(9);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(4);
        root1.left.right.right = new TreeNode(7);
        root1.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(8);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(2);

        System.out.println(new Offer17().HasSubtree(root1, root2));
    }
}
