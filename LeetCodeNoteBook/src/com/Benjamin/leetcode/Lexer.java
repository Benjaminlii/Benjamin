package com.Benjamin.leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ClassName:Lexer
 * Package:com.Benjamin.leetcode
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-10-8 下午7:39
 */
public class Lexer {
    public static String buffer = null;//存储读取出来的字符串
    public static int typenum = 0;

    public static String[] reservedword = //保留字(1-10)
            {"begin", "end", "if", "else", "do", "while", "then", "var", "Integer", "procedure"};

    public static String[] operateword = //算术符号(11-14)
            {"+", "-", "*", "/"};

    public static String[] relationword = //关系符号(15-21)
            {">", "<", "<=", ">=", "<>", "=", ":"};

    public static String[] limiterword = //分隔符(22-29)
            {":=", ",", ".", ";", "(", ")", "[", "]"};

    // 读取文件
    public static void read(String path) {
        try {
            FileReader filepath = new FileReader(path);

            BufferedReader bufferedreader = new BufferedReader(filepath);
            String str;
            StringBuffer ans = new StringBuffer();
            while ((str = bufferedreader.readLine()) != null) {
                ans.append(str).append("\n");
            }
            bufferedreader.close();
            buffer = ans.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findString(String s, String[] ss, int start){
        int flag = 0;
        for (int j = 0; j < ss.length; j++) {
            if (ss[j].equals(s)) {
                flag = j + start;
                break;
            }
        }
        return flag;
    }

    // 是否为保留字
    public static int isReservedWord(String s) {
        return findString(s, reservedword, 1);
    }

    // 是否为算术符号
    public static int isOperateWord(String s) {
        return findString(s, operateword, 11);
    }

    // 是否为关系符号
    public static int isRelationWord(String s) {
        return findString(s, relationword, 15);
    }

    // 是否为分隔符
    public static int isLimiterWord(String s) {
        return findString(s, limiterword, 12);
    }

    // 对字符串进行分析
    public static int analysis(String s) {
        int sign = 0;
        boolean flag = false;//标志变量，用来判断是否为数字
        if (isReservedWord(s) >= 0) {//是否为保留字
            sign = isReservedWord(s);
            typenum = 1;
        } else {
            for (int k = 0; k < s.length(); k++) {//是否为数字串
                if (s.substring(k, k + 1).compareTo("0") >= 0 && s.substring(k, k + 1).compareTo("9") <= 0) {
                    flag = true;//如果一直是数字，则为真
                } else {
                    flag = false;
                    break;//当有一个不位数字，则为假
                }
            }
            if (flag) {
                //数字
                sign = 29;
                typenum = 5;
            } else {//标识符
                sign = 28;
                typenum = 6;
            }
        }
        return sign;
    }

    public static void main(String[] args) {
        String path = "/media/benjamin/Data/编译原理/词法分析器/Text.txt";//文件存储路径
        read(path);//读取文件
        System.out.println("InPut:\n" + buffer + "\n\nOutPut:");
        String Str1 = null;
        int p = 0, typenum1 = 0;
        int sign = 0, num = 0;
        boolean flag1 = true, flag2 = false;
        do {
            flag2 = false;//标识关系两个字节的关系运算符
            Str1 = null;//存储提取的一段字符串
            num = 0;//存储种别码
            while (!buffer.substring(p, p + 1).equals(" ") && !buffer.substring(p, p + 1).equals("\n")) {
                //读取buffer中的字符，直到遇到换行符或者空格
                if (isLimiterWord(buffer.substring(p, p + 1)) > 0) {//遇到分隔符
                    typenum1 = 4;
                    num = isLimiterWord(buffer.substring(p, p + 1));
                    break;
                }
                if (isOperateWord(buffer.substring(p, p + 1)) > 0) {//遇到算术符号
                    typenum1 = 2;
                    num = isOperateWord(buffer.substring(p, p + 1));
                    break;
                }
                if (isRelationWord(buffer.substring(p, p + 1)) > 0) {//遇到关系运算符
                    if (buffer.substring(p, p + 1).equals(":")) {
                        if (buffer.substring(p + 1, p + 2).equals("=")) {//遇到:=分隔符
                            typenum1 = 4;
                            num = 22;
                            break;
                        }
                    }
                    typenum1 = 3;
                    if (isRelationWord(buffer.substring(p, p + 2)) > 0) {//遇到两个字节的关系运算符
                        flag2 = true;
                        num = isRelationWord(buffer.substring(p, p + 2));
                    } else {//一个字节的关系运算符
                        num = isRelationWord(buffer.substring(p, p + 1));
                    }
                    break;
                }
                if (buffer.substring(p, p + 1).equals("#")) {//遇到结束符号
                    flag1 = false;//flag1表示着是否遇到结束符号
                    break;
                }
                if (Str1 == null) {//这是为了区别当Str1为null时，使用+=会造成字符串中多处一个null
                    Str1 = buffer.substring(p, p + 1);
                } else {
                    Str1 += buffer.substring(p, p + 1);
                }
                p++;
            }

            //输出

            if (Str1 != null) {//当提取的字符串不为空时
                sign = analysis(Str1);//分析字符串的种别码
                if (sign == 29) {    //如果遇到数字,二进制输出
                    int change = Integer.parseInt(Str1);
                    String result = "";
                    int sum;
                    for (int i = change; i >= 1; i = i / 2) {
                        if (i % 2 == 0) {
                            sum = 0;
                        } else {
                            sum = 1;
                        }
                        result = sum + result;
                    }
                    System.out.println("(" + sign + "," + result + ")");
                } else {
                    System.out.println("(" + sign + "," + Str1 + ")");
                }
//                System.out.println("("+sign+","+Str1+","+type[typenum]+")");
//                System.out.println("("+sign+","+Str1+")");
            }
            if (num > 0) {//当为特殊符号时(分隔符，算术符号，关系运算符)
                if (num == 22) {
//                    System.out.println("(25,:=,分隔符)");
                    System.out.println("(22,:=)");
                    p = p + 2;
                    continue;
                }
                if (flag2) {//两个字节的关系运算符
//                    System.out.println("("+num+","+buffer.substring(p,p+2)+","+type[typenum1]+")");
                    System.out.println("(" + num + "," + buffer.substring(p, p + 2) + ")");
                    p++;
                } else {
//                    System.out.println("("+num+","+buffer.substring(p,p+1)+","+type[typenum1]+")");
                    System.out.println("(" + num + "," + buffer.substring(p, p + 1) + ")");
                }
            }
            p++;
        } while (flag1);
        System.out.println("(0,#)");
    }
}

