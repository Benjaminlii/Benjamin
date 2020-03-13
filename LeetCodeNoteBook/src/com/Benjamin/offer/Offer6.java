package com.Benjamin.offer;


/**
 * ClassName:Offer6
 * Package:com.Benjamin.offer
 * <p>
 * Description:
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author: Benjamin
 * @date: 19-11-21 下午10:00
 */
public class Offer6 {
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i+1] < array[i]){
                return array[i+1];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Offer6().minNumberInRotateArray(new int[]{3,4,5,6,7,9,17,1,2}));
    }
}
