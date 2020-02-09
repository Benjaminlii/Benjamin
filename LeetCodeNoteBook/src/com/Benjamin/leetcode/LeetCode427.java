package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode427
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 建立四叉树
 * 我们想要使用一棵四叉树来储存一个 N x N 的布尔值网络。网络中每一格的值只会是真或假。树的根结点代表整个网络。对于每个结点, 它将被分等成四个孩子结点直到这个区域内的值都是相同的.
 * 每个结点还有另外两个布尔变量: isLeaf 和 val。isLeaf 当这个节点是一个叶子结点时为真。val 变量储存叶子结点所代表的区域的值。
 * 你的任务是使用一个四叉树表示给定的网络。下面的例子将有助于你理解这个问题：
 * 给定下面这个8 x 8 网络，我们将这样建立一个对应的四叉树：
 * 由上文的定义，它能被这样分割：
 * 对应的四叉树应该像下面这样，每个结点由一对 (isLeaf, val) 所代表.
 * 对于非叶子结点，val 可以是任意的，所以使用 * 代替。
 * 提示：
 * N 将小于 1000 且确保是 2 的整次幂。
 * <p>
 * 重新封装函数,加入数组下标,分割下标进行递归创建
 * 每一层先对所有的元素进行是否一致的验证,验证通过则直接返回节点
 * 否则向下递归
 *
 * @author: Benjamin
 * @date: 20-2-9 下午12:14
 */
public class LeetCode427 {
    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", isLeaf=" + isLeaf +
                    ", topLeft=" + topLeft +
                    ", topRight=" + topRight +
                    ", bottomLeft=" + bottomLeft +
                    ", bottomRight=" + bottomRight +
                    '}';
        }
    }

    public Node construct(int[][] grid) {
        return muthod(grid, 0, grid.length, 0, grid.length);
    }

    private Node muthod(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        int num = grid[rowStart][colStart];
        boolean flag = true;
        MARK:
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                if (num != grid[i][j]) {
                    flag = false;
                    break MARK;
                }
            }
        }
        if (flag) {
            // 这里分为两种情况
            // 1.当前划分区域所有元素一样
            // 2.当前划分区域只有一个元素,特殊的情况1
            return new Node(num == 1, true, null, null, null, null);
        } else {
            Node topLeft = muthod(grid, rowStart, (rowStart + rowEnd) / 2,
                    colStart, (colStart + colEnd) / 2);

            Node topRight = muthod(grid, rowStart, (rowStart + rowEnd) / 2,
                    (colStart + colEnd) / 2, colEnd);

            Node buttonLeft = muthod(grid, (rowStart + rowEnd) / 2, rowEnd,
                    colStart, (colStart + colEnd) / 2);

            Node buttonRight = muthod(grid, (rowStart + rowEnd) / 2, rowEnd,
                    (colStart + colEnd) / 2, colEnd);
            return new Node(false, false, topLeft, topRight, buttonLeft, buttonRight);
        }

    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9, 10, 11, 12},
                new int[]{13, 14, 15, 16}
        };
        System.out.println(new LeetCode427().construct(nums));
    }
}
