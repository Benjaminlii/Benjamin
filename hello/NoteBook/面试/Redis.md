# Redis面试中常见问题总结

------

[TOC]

------

## 1. Redis支持的数据类型

1.  string:字符串,能保存任何类型的数据,包括二进制数据,最大512M(单个的key-value)

    格式: set key value

1.  hash:哈希类型,是一个string类型的field和value的映射表(参考Map,name指的是数据类型的名称,下同)

    格式: hmset name key1 value1 key2 value2........

1.  list:列表,简单的字符串列表,按照插入顺序排序

    格式: lpush name value1 value2......

1.  set:集合,无序,成员唯一

    格式: sadd name value1 value2......

    通过哈希表实现

1.  zset:有序集和,每一个value都对应一个score(double类型)用以排序

    格式: zadd name score1 value1 score2 value2......

    zset的成员是唯一的,但分数(score)却可以重复

## 2. 什么是Redis的持久化

### (1). 概念

​		持久化就是将Redis中用内存存储的数据写入磁盘,下次启动Redis服务可以恢复到内存中

### (2). 方式

-   RDB:即Redis DataBase.就是将Redis中的数据写入磁盘中
    -   核心功能在于rdbSave(写入RDB文件)和rdbLoad(从文件加载到内存)两个函数
-   AOF:即Append-Only File.字面意思是只可追加的文件,也就是以重做日志的方式去存储Redis中数据的变化
    -   每次执行服务器(定时)任务时,flushAppendOnlyFile函数都会被调用执行两个操作
        -   WRITE:将缓存写入文件
        -   SAVE:将文件保存入磁盘
    -   会对过时的更改日志进行删除

### (3). 两种持久化比较

1.  aof文件比rdb更新频率高,优先使用aof
1.  aof更安全也更大
1.  rdb性能好一些(重启时并不是靠恢复,而是直接读取)
1.  如果两个方式都进行了配置,优先使用AOF