package com.Benjamin.leetcode;

import java.util.ArrayList;

/**
 * ClassName:LeetCode208
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * <p>
 * 思路:
 * 使用多叉树存储每一个字符,根节点空出来,每一个单词的末尾存储一个标记用以表示
 * 查找时每一层查找是否存在字符串的首个字符,如果有,向下一层查找是否存在str[1,end]
 * 当字符串用光时,则满足前缀查找如果下一层子节点中有结束符,则满足字符串匹配
 *
 * @author: Benjamin
 * @date: 19-10-14 下午5:02
 */
public class LeetCode208 {

    public void doSomething() {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));      // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
        System.out.println(trie.startsWith("appl"));     // 返回 true
        System.out.println(trie.search("appe"));     // 返回 true
    }

    public static void main(String[] args) {
        new LeetCode208().doSomething();
    }

    private class Trie {
        private char data = '\0';

        private ArrayList<Trie> childes = new ArrayList<>();

        public Trie() {

        }

        public Trie(char date) {
            this.data = date;
        }

        /**
         * 向前缀树中插入一个字符串
         */
        public void insert(String word) {
            if (!"".equals(word)) {
                char ch = word.charAt(0);
                String str = word.substring(1);

//                System.out.println(ch);
                Trie child = new Trie(ch);
                boolean flag = false;

                for (Trie childe : childes) {
                    if (childe.data == ch) {
                        flag = true;
                        child = childe;
                    }
                }
                if (!flag) {
                    childes.add(child);
                }
                child.insert(str);
            } else {
                childes.add(new Trie('\0'));
            }
        }

        /**
         * 字符串匹配
         */
        public boolean search(String word) {
            if (!"".equals(word)) {
                char ch = word.charAt(0);
                Trie child = null;
                String str = word.substring(1);

                boolean flag = false;

                for (Trie childe : childes) {
                    if (childe.data == ch) {
                        flag = true;
                        child = childe;
                        break;
                    }
                }
                if (!flag) {
                    return false;
                } else {
                    return child.search(str);
                }
            } else {
                for (Trie childe : this.childes) {
                    if (childe.data == '\0') {
                        return true;
                    }
                }
                return false;
            }
        }

        /**
         * 前缀查找
         */
        public boolean startsWith(String prefix) {
            if (!"".equals(prefix)) {
                char ch = prefix.charAt(0);
                Trie child = null;
                String str = prefix.substring(1);

                boolean flag = false;

                for (Trie childe : childes) {
                    if (childe.data == ch) {
                        flag = true;
                        child = childe;
                        break;
                    }
                }
                if (!flag) {
                    return false;
                } else {
                    return child.startsWith(str);
                }
            } else {
                return true;
            }
        }
    }
}
