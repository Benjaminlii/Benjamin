```java

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        String strs[] = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = in.next();
        }

        for (String str : strs) {
            System.out.println(getAns(str));
        }
    }

    public static String getAns(String str) {
        return new Tree(str).data == 24 ? "Yes" : "No";
    }

    private static class Tree {
        int data;
        Tree left;
        Tree right;

        public Tree(String str) {
            if (!str.equals("")) {
                int length = str.length();
                for (int i = length - 1; i >= 0; i--) {
                    if (str.charAt(i) == '+' || str.charAt(i) == '-') {
//                        System.out.println(str + ", " + str.charAt(i));
                        String strs[] = new String[]{str.substring(0, i), str.substring(i + 1)};
                        this.left = new Tree(strs[0]);
                        this.right = new Tree(strs[1]);
                        switch (str.charAt(i)) {
                            case '+':
                                this.data = this.left.data + this.right.data;
                                return;
                            case '-':
                                this.data = this.left.data - this.right.data;
                                return;
                        }
                    }
                }
                for (int i = length - 1; i >= 0; i--) {
                    if (str.charAt(i) == 'x' || str.charAt(i) == '/') {
//                        System.out.println(str + ", " + str.charAt(i));
                        String strs[] = new String[]{str.substring(0, i), str.substring(i + 1)};
                        this.left = new Tree(strs[0]);
                        this.right = new Tree(strs[1]);
                        switch (str.charAt(i)) {
                            case 'x':
                                this.data = this.left.data * this.right.data;
                                return;
                            case '/':
                                this.data = this.left.data / this.right.data;
                                return;
                        }
                    }
                }
                this.data = Integer.valueOf(str);
            }else {
                this.data = '^';
            }
        }
    }
}
/*
10
9+3+4x3
5+4x5x5
7-9-9+8
5x6/5x4
3+5+7+9
1x1+9-9
1x9-5/9
8/5+6x9
6x7-3x6
6x4+4/5
* */
```

