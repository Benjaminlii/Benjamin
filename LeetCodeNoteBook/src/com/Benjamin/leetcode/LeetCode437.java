package com.Benjamin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:LeetCode437
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * ......10
 * ...../  \
 * ....5   -3
 * .../ \    \
 * ..3   2   11
 * ./ \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * 1.  5 -> 3
 * 2.  5 -> 2  -> 1
 * 3. -3 -> 11
 * <p>
 * 思路一:暴力法,双重递归
 * 因为路径的开始可以不是根节点,所以遍历整棵树,以每一个节点为根节点找出其中满足的路径
 *
 * 思路二:前缀和只差为两节点见距离
 * 一个节点的前缀和表示从根节点到这个节点的路径和
 * 使用map存储当前遍历到节点的路径上所有节点中,前缀和 和 符合的节点的数量
 * 满足当前节点前缀和 - 某个节点前缀和 = sum,就说明找到了一条符合题意的路径
 *
 *
 * @author: Benjamin
 * @date: 20-3-4 上午11:29
 */
public class LeetCode437 {

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
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    // 思路一
    private int ans;

    // 遍历树,以每一个根节点再在子树内部寻找
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sumFromRoot(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return ans;
    }

    private void sumFromRoot(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        if (sum == 0) {
            ans++;
        }
        sumFromRoot(root.left, sum);
        sumFromRoot(root.right, sum);
    }

    // 思路二
    public int pathSum_(TreeNode root, int sum) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        return method(root, map, 0, sum);
    }

    private int method(TreeNode root, Map<Integer, Integer> map, int num, int sum) {
        if (root==null){
            return 0;
        }
        int ans = 0;
        // 计算当前遍历到的节点的前缀和
        num += root.val;
        /*
         * 根据前缀和的性质有
         * 当前前缀和 - 路径中的某节点前缀和 = 两节点间距离
         * 如果:
         * 两节点间距离　=　sum,那么说明找到符合的一条路径
         * 化简得:
         * 当前前缀和 - 路径中的某节点前缀和 = sum
         * 那么某节点前缀和 = 当前前缀和 - sum
         */
        int findNum = num - sum;
        // 有多少符合这个前缀和的节点,就有多少条满足的路径
        ans += map.getOrDefault(findNum,0);
        // 将当前节点更新到map中
        map.put(num, map.getOrDefault(num,0) + 1);

        // 下一层
        ans += method(root.left, map, num, sum);
        ans += method(root.right, map, num, sum);

        //避免影响其他路径,所以要消除当前遍历到的节点对map的影响
        map.put(num, map.get(num) - 1);
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);
        System.out.println(new LeetCode437().pathSum_(root, 8));
    }

}
