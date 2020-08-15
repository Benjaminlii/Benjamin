package com.Benjamin.exam;

import java.util.*;

/**
 * ClassName:MeiTuan20200815_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 小团的配送团队
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 小团是美团外卖的区域配送负责人，众所周知，外卖小哥一般都会同时配送若干单，小团在接单时希望把同一个小区的单子放在一起，然后由一名骑手统一配送。
 * 但是由于订单是叠在一起的，所以，他归类订单时只能知道新订单和已有的某个订单的小区是相同的，他觉得这样太麻烦了，所以希望你帮他写一个程序解决这个问题。
 * 即给出若干个形如a b的关系，表示a号订单和b号订单是同一个小区的 ，请你把同一个小区的订单按照编号顺序排序，并分行输出，
 * 优先输出最小的订单编号较小的小区订单集合。订单的编号是1到n。(可能存在同时出现a b和b a这样的关系,也有可能出现a a这样的关系)
 * <p>
 * 输入描述
 * 输入第一行是两个正整数n，m，表示接受的订单数量和已知的关系数量。(1<=n，m<=10000)
 * 接下来有m行，每行两个正整数a和b，表示a号订单和b号订单属于同一个小区(1<=a,b<=n);
 * 输出描述
 * 输出第一行包含一个整数x，表示这些订单共来自x个不同的小区。
 * 接下来的输出包含x行，每行表示输出若干个订单编号，表示这些订单属于同一个小区，按照订单编号升序输出。优先输出最小的订单编号较小的小区。
 * <p>
 * 样例输入
 * 5 5
 * 1 2
 * 2 2
 * 3 1
 * 4 2
 * 5 4
 * 样例输出
 * 1
 * 1 2 3 4 5
 *
 * @author: Benjamin
 * @date: 2020-08-15 17:03
 */
public class MeiTuan20200815_3 {
    public static List<TreeSet<Integer>> method(int num, int[][] arrays) {
        List<Set<Integer>> helpList = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            int key = arrays[i][0];
            int value = arrays[i][1];
            boolean flag = false;
            for (Set<Integer> integers : helpList) {
                if (integers.contains(key) || integers.contains(value)) {
                    integers.add(key);
                    integers.add(value);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(key);
                newSet.add(value);
                helpList.add(newSet);
            }
        }

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            nums.add(i);
        }
        List<TreeSet<Integer>> ansList = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (!nums.contains(i)) {
                continue;
            }
            boolean flag = false;
            for (Set<Integer> integers : helpList) {
                if (integers.contains(i)) {
                    TreeSet<Integer> integerTreeSet = new TreeSet<>();
                    for (Integer integer : integers) {
                        integerTreeSet.add(integer);
                        nums.remove(integer);
                    }
                    integers.clear();
                    ansList.add(integerTreeSet);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                TreeSet<Integer> integerTreeSet = new TreeSet<>();
                integerTreeSet.add(i);
                ansList.add(integerTreeSet);
            }
        }
        return ansList;

    }


    public static void main1(String[] args) {

        System.out.println(method(20, new int[][]{{1, 2}, {2, 3}, {4, 5}, {5, 4}}));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int num_ = in.nextInt();
        int[][] param = new int[num_][2];
        for (int i = 0; i < num_; i++) {
            param[i][0] = in.nextInt();
            param[i][1] = in.nextInt();
        }
        List<TreeSet<Integer>> ans = method(num, param);
        System.out.println(ans.size());
        for (TreeSet<Integer> an : ans) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : an) {
                sb.append(integer).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
    }
}
