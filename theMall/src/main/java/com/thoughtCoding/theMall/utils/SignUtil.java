package com.thoughtCoding.theMall.utils;

import java.util.Map;
import java.util.TreeMap;

public class SignUtil {

    public static String getSign(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        TreeMap<String, Object> treeMap = new TreeMap<>();

        //转化为TreeMap排序
        for (String s : map.keySet()) {
            treeMap.put(s, map.get(s));
        }

        //转化为字符串
        for (String s : treeMap.keySet()) {
            stringBuffer.append(s + "=" + treeMap.get(s) + "&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);

        String param = stringBuffer.toString();

        String md5 = MD5Util.string2MD5(param);

        String rtn = md5.toUpperCase();

        return rtn;
    }
}
