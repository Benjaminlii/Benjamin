package com.Benjamin.exam;

/**
 * ClassName:Mogujie20200319_1
 * Package:com.Benjamin.others
 * <p>
 * Description:
 * 不使用链表实现一个FIFO的队列,要求入队和出队的时间复杂度为O(1)
 * 使用size去判空和判满要比start和end容易很多
 *
 * @author: Benjamin
 * @date: 20-3-19 下午3:13
 */
public class Mogujie20200319_1 {
    int[] array;
    int start;
    int end;
    int length;
    int size;

    public Mogujie20200319_1(int length) {
        this.array = new int[length];
        this.start = 0;
        this.end = 0;
        this.size = 0;
        this.length = length;
    }

    boolean in(int num) {
        if (size == length) {
            return false;
        }
        array[end] = num;
        end++;
        end %= array.length;
        size++;
        return true;
    }

    Integer out() {
        if (size == 0) {
            return null;
        }
        int ans = array[start];
        start++;
        start %= array.length;
        size--;
        return ans;
    }

    public static void main(String[] args) {
        Mogujie20200319_1 q = new Mogujie20200319_1(5);
        System.out.println(q.in(1));
        System.out.println(q.in(2));
        System.out.println(q.in(3));
        System.out.println(q.in(4));
        System.out.println(q.in(5));
        System.out.println(q.in(6));
        System.out.println(q.out());
        System.out.println(q.out());
        System.out.println(q.out());
        System.out.println(q.out());
        System.out.println(q.out());
        System.out.println(q.out());
    }
}
