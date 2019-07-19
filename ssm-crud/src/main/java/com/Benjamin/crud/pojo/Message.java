package com.Benjamin.crud.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 * 封装了服务是否成功，反馈信息等数据
 */
public class Message {
    //请求的状态吗,200成功，400失败
    private int code;
    //请求的提示信息
    private String message;
    //返回的数据
    private Map<String, Object> extend = new HashMap<>();

    public static Message success(){
        Message rtn = new Message();
        rtn.setCode(200);
        rtn.setMessage("处理成功！");
        return rtn;
    }

    public static Message error(){
        Message rtn = new Message();
        rtn.setCode(400);
        rtn.setMessage("处理失败！");
        return rtn;
    }

    public Message add(String key, Object obj){
        this.getExtend().put(key, obj);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
