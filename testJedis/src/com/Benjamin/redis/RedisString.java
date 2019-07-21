package com.Benjamin.redis;

import redis.clients.jedis.Jedis;

public class RedisString {
    public static void main(String[] args) {
        //创建 Jedis对象，连接到Redis服务器，端口
        /**
         * String host:Redis服务端所所处的linux服务器的IP地址
         * int port：Redis服务运行的端口号
         */
        String host = "192.168.1.126";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        jedis.auth("litao.");

        //调用Jedis对象的方法操作Redis数据
        jedis.rpush("people", "Benjamin", "zzw", "llf", "hahaha", "hohoho");

        System.out.println(jedis.lindex("people", 0));
        jedis.flushDB();
    }
}