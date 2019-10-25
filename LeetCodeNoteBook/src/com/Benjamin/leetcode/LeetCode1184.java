package com.Benjamin.leetcode;

/**
 * ClassName:LeetCode1184
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
 * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
 * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
 *
 * @author: Benjamin
 * @date: 19-10-25 下午3:01
 */
public class LeetCode1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int rtn = 0;
        int length1 = 0;
        int length2 = 0;
        int location = start;
        boolean flag = false;
        do {
            if (location == destination) {
                flag = true;
            }
            if (!flag) {
                length1 += distance[location];
            } else {
                length2 += distance[location];
            }

            location++;
            location %= distance.length;

        } while (location != start);

        return length1 > length2 ? length2 : length1;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode1184().distanceBetweenBusStops(new int[]{1, 2, 3, 4}, 0, 3));
    }
}
