package com.Benjamin.leetcode;

import java.util.*;

/**
 * ClassName:LeetCode381
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * 注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * @author: Benjamin
 * @date: 19-8-14 上午9:29
 */
public class LeetCode381 {
    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        boolean param_1 = obj.insert(4);
        boolean param_2 = obj.insert(3);
        boolean param_3 = obj.insert(4);
        boolean param_4 = obj.insert(2);
        boolean param_5 = obj.insert(4);
        boolean param_6 = obj.remove(4);
        boolean param_7 = obj.remove(3);
        boolean param_8 = obj.remove(4);
        boolean param_9 = obj.remove(4);
        int param_10 = obj.getRandom();
        System.out.println("p1 = " + param_1 +
                "\np2 = " + param_2 +
                "\np3 = " + param_3 +
                "\np4 = " + param_4 +
                "\np5 = " + param_5 +
                "\np6 = " + param_6 +
                "\np7 = " + param_7 +
                "\np8 = " + param_8 +
                "\np9 = " + param_9 +
                "\np10 = " + param_10);
    }

    private static class RandomizedCollection {

        private Map<Integer, List<Integer>> map;
        private List<Integer> list;

        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /**
         * 向数据结构中插入元素val
         *
         * @param val 插入的元素
         * @return 元素是否原本不存在
         */
        public boolean insert(int val) {
            boolean rtn;
            if (map.containsKey(val)) {
                map.get(val).add(list.size());
                rtn = false;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(this.list.size());
                map.put(val, list);
                rtn = true;
            }

            list.add(val);
            return rtn;
        }

        /**
         * 在数据结构中删除元素val
         *
         * @param val 要删除的元素
         * @return 是否删除成功(如果val元素不存在, 则删除失败)
         */
        public boolean remove(int val) {
            if (!map.containsKey(val) || map.get(val).size() == 0) {
                return false;
            } else {
                //要删除的元素和其下标
                List<Integer> listOfVal = map.get(val);
                int valSub = listOfVal.get(listOfVal.size() - 1);

                //使用list中最后一个元素进行位置上的替换
                int lastVal = list.get(list.size() - 1);
                List<Integer> listOfChange = map.get(lastVal);
                int lastValSubOfList = listOfChange.indexOf(list.size() - 1);

                //删除最后一个val元素,并使用list中的最后一个元素覆盖
                list.set(valSub, lastVal);
                listOfChange.set(lastValSubOfList, valSub);
                list.remove(list.size() - 1);
                listOfVal.remove(listOfVal.size()-1);
                Collections.sort(listOfChange);

                return true;
            }
        }

        public int getRandom() {
            if (list.size() == 0) return 0;
            return list.get(new Random().nextInt(list.size()));
        }

        @Override
        public String toString() {
            return "RandomizedCollection{" +
                    "map=" + map +
                    ", list=" + list +
                    '}';
        }
    }

}
