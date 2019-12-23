package com.Benjamin.test;

import java.io.*;

public class Test {
    private String keyWord[] = {"break", "include", "begin", "end", "if", "else", "while", "switch"};
    private char ch;

    boolean isKey(String str) {
        for (int i = 0; i < keyWord.length; i++) {
            if (keyWord[i].equals(str))
                return true;
        }
        return false;
    }

    boolean isLetter(char letter) {
        if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'))
            return true;
        else
            return false;
    }

    boolean isDigit(char digit) {
        if (digit >= '0' && digit <= '9')
            return true;
        else
            return false;
    }

    void analyze(char[] chars) {
        String arr = "";
        for (int i = 0; i < chars.length; i++) {
            ch = chars[i];
            arr = "";
            if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            } else if (isLetter(ch)) {
                while (isLetter(ch) || isDigit(ch)) {
                    arr += ch;
                    ch = chars[++i];
                }
                i--;
                if (isKey(arr)) {
                    System.out.println(arr + "\t4" + "\t关键字");
                } else {
                    System.out.println(arr + "\t4" + "\t标识符");
                }
            } else if (isDigit(ch) || (ch == '.')) {
                while (isDigit(ch) || (ch == '.' && isDigit(chars[++i]))) {
                    if (ch == '.') i--;
                    arr = arr + ch;
                    ch = chars[++i];
                }
                System.out.println(arr + "\t5" + "\t常数");
            } else switch (ch) {
                //运算符
                case '+':
                    System.out.println(ch + "\t2" + "\t运算符");
                    break;
                case '-':
                    System.out.println(ch + "\t2" + "\t运算符");
                    break;
                case '*':
                    System.out.println(ch + "\t2" + "\t运算符");
                    break;
                case '/':
                    System.out.println(ch + "\t2" + "\t运算符");
                    break;
                //分界符
                case '(':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case ')':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case '[':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case ']':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case ';':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case '{':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case '}':
                    System.out.println(ch + "\t3" + "\t分界符");
                    break;
                case '=': {
                    ch = chars[++i];
                    if (ch == '=') System.out.println("==" + "\t2" + "\t运算符");
                    else {
                        System.out.println("=" + "\t2" + "\t运算符");
                        i--;
                    }
                }
                break;
                case ':': {
                    ch = chars[++i];
                    if (ch == '=') System.out.println(":=" + "\t2" + "\t运算符");
                    else {
                        System.out.println(":" + "\t2" + "\t运算符");
                        i--;
                    }
                }
                break;
                case '>': {
                    ch = chars[++i];
                    if (ch == '=') System.out.println(">=" + "\t2" + "\t运算符");
                    else {
                        System.out.println(">" + "\t2" + "\t运算符");
                        i--;
                    }
                }
                break;
                case '<': {
                    ch = chars[++i];
                    if (ch == '=') System.out.println("<=" + "\t2" + "\t运算符");
                    else {
                        System.out.println("<" + "\t2" + "\t运算符");
                        i--;
                    }
                }
                break;
                default:
                    System.out.println(ch + "\t6" + "\t无识别符");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/home/benjamin/IdeaProjects/Benjamin/hello/src/main/java/com/Benjamin/test/code.txt");
        FileReader reader = new FileReader(file);
        int length = (int) file.length();
        char buf[] = new char[length + 1];
        reader.read(buf);
        reader.close();
        new Test().analyze(buf);
    }
}
