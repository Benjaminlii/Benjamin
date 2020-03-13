package com.Benjamin.offer;

import java.util.Arrays;

/**
 * ClassName:Offer23
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * <p>
 * 最后一个元素是根节点,在前面分割出左右子树们进行递归
 * 递归边界是数组长度为0
 * 如果不能分割为两个子树,返回false
 *
 * @author: Benjamin
 * @date: 19-11-30 下午12:12
 */
public class Offer23 {

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1){
            return true;
        }
        int root = sequence[sequence.length - 1];
        int i = 0;
        while (sequence[i] < root) {
            i++;
        }
        for (int j = i+1; j < sequence.length; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        boolean left = VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));
        boolean right = VerifySquenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length-1));
        if (i == 0){
            return right;
        }
        if (i == sequence.length-1){
            return left;
        }
        return left && right;
    }

    public static void main(String[] args) {
        System.out.println(new Offer23().VerifySquenceOfBST(new int[]{3, 4, 9, 5, 12, 11, 10}));
        System.out.println(new Offer23().VerifySquenceOfBST(new int[]{}));
    }
}
