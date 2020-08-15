package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * ClassName:MeiTuan20200815_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 小团的旅行线路
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 小团是一个旅游爱好者，快要过春节了，他想统计一下，在过去的一年中他进行过几次旅行，于是他打开了美团app的订单记录，记录显示了他的购买车票的记录。
 * 记录是按时间顺序给出的，已知一次旅行的线路一定是一个闭环，即起点和终点是同一个地点。
 * 因此当每找到一段闭合的行程，即认为完成了一次旅行。数据保证不会出现不在闭环路径中的数据。
 * 请你在小团的购票记录中统计出他全年共进行了多少次旅行？
 * <p>
 * 输入描述
 * 输入第一行包含一个正整数n，表示小团的购票记录数量。(1<=n<=10000)
 * 接下来有n行，每行是两个长度不超过10的仅由小写字母组成的字符串S_a S_b，表示购买了一张从S_a到S_b的车票。
 * 输出描述
 * 输出仅包含一个整数，表示小团的旅行次数。
 * <p>
 * 样例输入
 * 6
 * beijing nanjing
 * nanjing guangzhou
 * guangzhou shanghai
 * shanghai beijing
 * fuzhou beijing
 * beijing fuzhou
 * 样例输出
 * 2
 *
 * @author: Benjamin
 * @date: 2020-08-15 16:32
 */
public class MeiTuan20200815_2 {

    private static class Node {
        String start;
        String end;

        public Node(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int method(List<Node> nodes) {
        Stack<Node> nodeStack = new Stack<>();
        int count = 0;
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (nodeStack.empty()) {
                nodeStack.push(node);
            } else {
                Node preNode = nodeStack.peek();
                if (preNode.end.equals(node.start)) {
                    node.start = preNode.start;
                    nodeStack.pop();
                }
                if (node.start.equals(node.end)) {
                    count++;
                }
                nodeStack.push(node);
            }
        }
        return count;
    }

    public static void main1(String[] args) {
        List<Node> list = new ArrayList<>();
        list.add(new Node("beijing", "nanjing"));
        list.add(new Node("nanjing", "guangzhou"));
        list.add(new Node("guangzhou", "shanghai"));
        list.add(new Node("shanghai", "beijing"));
        list.add(new Node("fuzhou", "beijing"));
        list.add(new Node("beijing", "fuzhou"));
        System.out.println(method(list));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            String startAndEnd = in.nextLine();
            String[] sae = startAndEnd.split(" ");
            list.add(new Node(sae[0], sae[1]));
        }
        System.out.println(method(list));
    }
}
