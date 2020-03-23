package com.Benjamin.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName:KuaiShou20200322_3
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-22 下午7:28
 */
public class KuaiShou20200322_3 {
    private static class PhoneNumber {
        String phoneNumber;
        float score;

        public PhoneNumber(String phoneNumber, float score) {
            this.phoneNumber = phoneNumber;
            this.score = score;
        }

        @Override
        public String toString() {
            return "PhoneNumber{" +
                    "phoneNumber='" + phoneNumber + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    public static float getScore_123(String phoneNumber) {
        float ans = 0;
        int num = 0;
        boolean isUp = true;

        for (int i = 4; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) == phoneNumber.charAt(i - 1) + 1) {
                if (!isUp) {
                    isUp = true;
                    num = 0;
                }
                num++;
                ans = Math.max(ans, num + 1);
            } else if (phoneNumber.charAt(i) == phoneNumber.charAt(i - 1) - 1) {
                if (isUp) {
                    isUp = false;
                    num = 0;
                }
                num++;
                ans = Math.max(ans, num + 1);
            } else {
                ans = Math.max(ans, num + 1);
                num = 0;
            }
//            System.out.println("pn[" + i + "] = " + phoneNumber.charAt(i) + ", num = " + num);
        }

        if (ans < 3) {
            ans = 0;
        }
        return ans;
    }

    public static float getScore_111(String phoneNumber) {
        float ans = 0;
        int num = 0;

        for (int i = 4; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) == phoneNumber.charAt(i - 1)) {
                num++;
                ans = Math.max(ans, num + 1);
            } else {
                ans = Math.max(ans, num + 1);
                num = 0;
            }
//            System.out.println("pn[" + i + "] = " + phoneNumber.charAt(i) + ", num = " + num);
        }
        if (ans < 3) {
            return 0;
        }
        ans += 0.5;
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<PhoneNumber> ans = new ArrayList<>();
        String str = in.nextLine();
//        String str = "15112347234,15176313245,15176313346,15176313325,15166667234,15188847234,15912398765";
        String[] phoneNumbers = str.split(",");
        for (String phoneNumber : phoneNumbers) {
            float score = Math.max(getScore_111(phoneNumber), getScore_123(phoneNumber));
//            System.out.println(phoneNumber + " " + getScore_111(phoneNumber) + "   " + getScore_123(phoneNumber));
            if (score == 0) {
                continue;
            }
            PhoneNumber pn = new PhoneNumber(phoneNumber, score);

            boolean flag = false;
            for (int i = 0; i < ans.size(); i++) {
                PhoneNumber sub = ans.get(i);
                if (sub.score < pn.score) {
                    ans.add(i, pn);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans.add(pn);
            }
        }

        if (ans.size() == 0) {
            System.out.println("null");
        } else {
            StringBuffer sb = new StringBuffer();
            for (PhoneNumber pn : ans) {
                sb.append(pn.phoneNumber).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }

//        System.out.println(ans);
    }

}
