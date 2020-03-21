# Redis面试中常见问题总结

------

[TOC]

------

## 1. Redis支持的数据类型

[Redis详解（四）------ redis的底层数据结构](https://www.cnblogs.com/ysocean/p/9080942.html)

[Redis详解（五）------ redis的五大数据类型实现原理](https://www.cnblogs.com/ysocean/p/9102811.html)

### (1). 对象类型和编码

​		Redis中每次创建一个键值对是,至少会创建两个对象,键对象和值对象,

​		Redis中每一个对象都是由redisObject来表示的:

```c
typedef struct redisObject{
    //类型
    unsigned type:4;
    //编码
    unsigned encoding:4;
    //指向底层数据结构的指针
    void *ptr;
    //引用计数
    int refcount;
    //记录最后一次被程序访问的时间
    unsigned lru:22;
}robj
```

​		==type==属性就是我们所讲的五大数据类型(键一般就是字符串,值可以是字符串,列表,集合等等)

​		==encoding==指的是每种数据结构存储的不同数据(例如字符串可以存储字符类型,也可以存储数值类型).也用来数据类型的不同实现方式(list的压缩列表实现和双端链表实现).

​		==ptr==指向的是底层数据结构的物理存储地址.

### (1). string

1). 定义

​		==字符串,能保存任何类型的数据==,包括二进制数据,最大512M(单个的key-value)

​		所有的key都是string类型,另外其他数据结构的构成元素也是字符串

​		格式: set key value

2). 编码

​		编码可以是int编码(long类型的整数值),embstr编码(长度大于44字节的字符串),raw编码(大于44字节的字符串)

3). 存储

​		raw和embstr编码使用==sdshdr==保存数据,其==内部维护一个字符数组,并存储已用容量和未使用容量==.(这与C语言中的字符串实现不同,C语言中的字符串的数组是不可变的,但是共同点是都==以'\0'结尾==,目的是为了使用c的部分str库函数)

```c
struct sdshdr{
    //记录buf数组中已使用字节的数量
    //等于 SDS 保存字符串的长度
    int len;
    //记录 buf 数组中未使用字节的数量
    int free;
    //字节数组，用于保存字符串
    char buf[];
}
```

​		使用sds而不是c格式的字符串的好处:方便获取字符串长度,杜绝溢出(会先检查空闲空间大小),减少内存重新分配(重用),二进制安全(二进制表示的数据中可能会出现'\0',sds虽然以'\0'结尾但是并不以'\0'为结束符,而是根据长度判断是否结束)

​		raw分配空间时,redisObject和sdshdr不在一起,使用指针连接.embstr分配空间则是连续的.

4). 转码

​		int编码保存的值超过long大小范围后,会转化为raw.对于embstr编码的数据在修改时一定会转化为raw编码.

### (2). list

1). 定义

​		==list:列表,简单的字符串列表==,按照插入顺序排序

​		可以头添加和尾添加,==底层使用链表实现==

​		格式: lpush name value1 value2......

2). 编码

​		编码可以是ziplist(压缩链表,将数据按照一定规则编码在一块连续的内存区域)和linkedlist(双端链表)

1>. 压缩链表

![image-20200221163321598](/home/benjamin/.config/Typora/typora-user-images/image-20200221163321598.png)

　　![img](https://images2018.cnblogs.com/blog/1120165/201805/1120165-20180528215852732-1088896020.png)

　　压缩列表的每个节点构成如下：

![image-20200221163350308](/home/benjamin/.config/Typora/typora-user-images/image-20200221163350308.png)

　　![img](https://images2018.cnblogs.com/blog/1120165/201805/1120165-20180528223605060-899108663.png)

　　①、previous_entry_ength：记录压缩列表前一个字节的长度。previous_entry_ength的长度可能是1个字节或者是5个字节，如果上一个节点的长度小于254，则该节点只需要一个字节就可以表示前一个节点的长度了，如果前一个节点的长度大于等于254，则previous length的第一个字节为254，后面用四个字节表示当前节点前一个节点的长度。利用此原理即当前节点位置减去上一个节点的长度即得到上一个节点的起始位置，压缩列表可以从尾部向头部遍历。这么做很有效地减少了内存的浪费。

　　②、encoding：节点的encoding保存的是节点的content的内容类型(前两位)以及长度(后面的所有位)encoding区域长度为1字节、2字节或者5字节长。

　　③、content：content区域用于保存节点的内容，节点内容类型和长度由encoding决定。内部数据如果是数值类型,那么转换为2进制存储,如果是字符串类型,那么将每个字符的ACSII码找出,然后用两位16进制数存储(一共16位的空间).

2>. 双端链表

```c
typedef  struct listNode{
    //前置节点
    struct listNode *prev;
    //后置节点
    struct listNode *next;
    //节点的值
    void *value;  
}listNode;

typedef struct list{
    //表头节点
    listNode *head;
    //表尾节点
    listNode *tail;
    //链表所包含的节点数量
    unsigned long len;
    //节点值复制函数
    void (*free) (void *ptr);
    //节点值释放函数
    void (*free) (void *ptr);
    //节点值对比函数
    int (*match) (void *ptr,void *key);
}list;
```

​		就是双向链表么.

### (3). hash

1). 定义

​		哈希类型,是一个string类型的field和value的映射表(参考Map,name指的是数据类型的名称,下同)

​		格式: hmset name key1 value1 key2 value2........

2). 编码

​		ziplist(相邻节点存储key和value)和hashtable(下面讲解)

1>. hashtable

​		类比HashMap

```c
typedef struct dictht{
    //哈希表数组
    dictEntry **table;
    //哈希表大小
    unsigned long size;
    //哈希表大小掩码，用于计算索引值 总是等于 size-1
    unsigned long sizemask;
    //该哈希表已有节点的数量
    unsigned long used;
}dictht;

typedef struct dictEntry{
    //键
    void *key;
    //值
    union{
        void *val;
        uint64_tu64;
        int64_ts64;
    }v;
    //指向下一个哈希表节点，形成链表,链地址法解决哈希冲突
    struct dictEntry *next;
}dictEntry;
```

2>. 哈希算法

```c
// 使用字典设置的哈希函数，计算键 key 的哈希值
int hash = dict->type->hashFunction(key);
// 有三种hash函数,分别对整型提供一种算法,字符串提供两种算法

// 使用哈希表的sizemask属性和第一步得到的哈希值，计算索引值
int index = hash & dict->ht[x].sizemask;
```

3>. 收缩和扩容

​		二倍扩容/收缩.对每一个元素重新哈希后放入新的内存空间,然后将原内存空间释放.

​		负载因子 = 哈希表大小/数组长度

​		执行磁盘访问时(BGSAVE和BGREWRITEAOF),负载因子大于5才会扩容,否则大于1就会扩容.

4>. 渐进式扩容

​		扩容并不是一次性完成的,数据量过大的情况下阻塞会非常明显.

​		所以依次扩容行为分为多次进行,在这期间产生了两个hash,当对数据的操作在其中一张表中没有找到时,就会查找另一张表.

3). 转码

​		保存元素小于512,每个元素长度小于64字节时,使用ziplist,否则使用hashtable

### (4). set

1). 定义

​		set:集合,无序,成员唯一

​		格式: sadd name value1 value2......

2). 编码

​		有intset和hashtable两种.

​		intset只能存储整数类型.

​		hashtable底层使用hash实现,可以理解为Java中的HashSet.

3). 转码

​		当集合中所有元素都是整数,并且总量不超过512时,使用intset,其他所有情况使用hashtable.

### (5). zset

1). 定义

​		有序集和,每一个value都对应一个score(double类型)用以排序

​		格式: zadd name score1 value1 score2 value2......

​		zset的成员是唯一的,但分数(score)却可以重复

2). 编码

​		可以是ziplist(之前提到过,使用两个相邻的节点存储元素和分值,内部存储时就已经按分值排序了)和skiplist(跳跃表,下面讲解)

1>. skiplist

```c
typedef struct zset{
    //跳跃表
    zskiplist *zsl;
    //字典
    dict *dice;						//字典的键存放元素的分值,字典的值存放元素本身
} zset;
typedef struct zskiplist {
    struct zskiplistNode *header, *tail;	// 链式存储,这里是有序链表
    unsigned long length;
    int level;
} zskiplist;
typedef struct zskiplistNode {
    robj *obj;						// 存储元素,这里和最外层的字典共享指针,保证数据的不重复
    double score;					// 存储分值
    struct zskiplistNode *backward;
    struct zskiplistLevel {
        struct zskiplistNode *forward;
        unsigned int span;
    } level[];
} zskiplistNode;
```

3). 转码

​		当满足元素数量小于128并且所有元素长度小于64字节时,使用ziplist,否则使用skiplist.



1.  list:列表,简单的字符串列表,按照插入顺序排序

    格式: lpush name value1 value2......

1.  set:集合,无序,成员唯一

    格式: sadd name value1 value2......

    通过哈希表实现

    zset:有序集和,每一个value都对应一个score(double类型)用以排序

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
    -   内容是redis通讯协议(==RESP==)格式的命令文本存储。即存储命令

### (3). 两种持久化比较

RDB特点:

-   方便备份(直接将文件解压,复制)
-   性能最大化,只占用子进程进行持久化
-   数据集很大的情况下启动快速
-   安全性不高,宕机只能恢复上一次持久化的数据
-   数据集较大的情况下子进程的持久化可能会使服务有较大时间的阻塞

AOF特点:

-   高数据安全性,宕机不会丢失数据
-   提供了每秒同步,每修改同步和不同步,每秒同步会丢失一秒内的数据,每修改同步效率低下
-   如果日志过大,启动初始化时间过长,会用新的文件存储这个时间内的操作,一旦准备好第二个文件，Redis会切换这两个文件并开始追加到新的那一个
-   文件大小大于RDB
-   日志改写:会删除之前失效的日志

### (4). RDB细节

[\<Redis\>RDB持久化](https://www.jianshu.com/p/136efaa2206e)

#### 1). 工作原理

​		Redis调用fork会产生一个子进程,主进程将数据写入一个临时的RDB文件,写入结束后替换掉旧的文件

#### 2). SAVE和BGSAVE

​		有两个命令可以生成RDB文件:SAVE(会阻塞主线程)和BGSAVE(在子线程中完成).实际创建RDB的工作由rdbSave完成,这两个命令内部的调用细节不同.BGSAVE内部会创建子进程,子进程处理,父进程中会轮询的等待子进程信号

​		BGSAVE命令在子线程中生成RDB文件的过程中,主线程如果再次调用了SAVE和BGSAVE命令,会被拒绝.

​		BGSAVE和BGREWRITEAOF命令不能同时执行,会相互延迟执行.(这里实际上不会出现什么问题,但是处于性能上的考虑,禁止同时执行)

#### 3). 文件载入

​		文件的载入工作在服务器启动的时候自动执行(检测到RDB文件就会进行载入),并没有专门用于载入RDB文件的命令.

​		如果服务器开启了AOF持久化功能,那么会优先使用AOF文件还原数据库状态.

​		RDB文件载入时,服务处于阻塞状态

#### 4). 自动间隔性保存

​		Redis的默认设置:

```
save 900 1			//900秒内进行一次同步
save 300 10			//300秒内进行10次同步
save 60  10000		//60秒内进行10000次同步
```

​		当满足以上条件时,会==执行BGSAVE命令==.

​		服务器会根据配置文件中的该配置设置saveParams属性数组:

```c
struct saveParams{
    // 秒数
    time_t  seconds;
    // 修改数 
    int changes;
}
```

​		Redis还会维持一个dirty计数器(上一次SAVE后产生的脏数据数),和一个lastsave属性(距离上一次SAVE的时间).Redis会周期性(100毫秒)的执行serverCron,来检查是否达到上面的条件,如果满足就调用BGSAVE

### (5). AOF细节

[\<Redis\>AOF持久化](https://www.jianshu.com/p/1e34fdc51e3b)

#### 1). AOF文件的存储

​		Redis调用flushAppendOnlyFile函数执行WRITE(将缓存写入内存中的AOF文件中)和SAVE(将AOF文件从内存持久化到磁盘)两个工作.

​		支持三种工作方式:

-   每秒同步:==原则上==每秒进行一次同步,SAVE由子线程执行,不会引起主线程恩阻塞
    -   当进行同步时,子线程正在进行同步,如果子线程同步未超过2秒,那么跳过本次同步,如果超过,本次不进行SAVE(原因是本次的WRITE延迟,要避免影响到下一次同步)
    -   子线程没在进行同步,如果距离上一次同步不超过一秒,不进行SAVE
    -   性能与安全性兼顾
-   每命令同步:每执行一次任务同步一次,==SAVE是由主线程执行的,会阻塞主线程==
    -   安全性最高,但是效率被同步拉低
-   不同步:==Redis被关闭,AOF功能被关闭==,或者==系统缓存被刷新==时会阻塞主线程进行SAVE
    -   宕机会丢失数据,但是不用进行同步所以效率最高

#### 2). 文件读取和数据还原

​		==AOF文件采用RESP通讯协议保存命令==.

​		只要根据AOF文件中的协议,重新执行一遍AOF文件中的所有命令就可以还原Redis的数据了.

步骤:

1.  创建一个不连接网络的伪客户端
1.  读取AOF文件,还原出命令以及参数
1.  使用伪客户端执行这些命令

使用伪客户端的原因是恢复数据不需要网络,效果完全一样.

#### 3). AOF重写

​		BGREWRITEAOF命令

​		Redis会在AOF文件中进行命令的重写,==相当于合并命令到另一个文件==,这个过程在子线程中进行,主线程可以继续处理命令请求.

​		==重写期间的命令会写入重写缓冲区==,在重写完成之后==追加在新AOF文件末尾==.

​		这个过程完成之后使用新的AOF文件代替原来的旧文件.

## 3. Redis通讯协议RESP

​		RESP是Redis客户端和服务端的一种通讯协议,请求格式都相同,使用数组搭配多行字符串.而返回有很多种

​		每一行消息是以\r\n结尾的,也就是分行

1.  简单字符串回复: " + "开头
1.  错误消息: " - "开头
1.  整型数字: " : "开头
1.  复杂字符串回复: " $ "开头
1.  数组格式回复: " * "开头

### (1). 请求格式

```resp
*3				// 这里星号指数组,后面数字代表数组长度,也就是命令的分段数,后面会紧跟3个多行字符串
$3				// 美元符号指多行字符串,后面数字代表字符串长度
SET				// 这是多行字符串的内容
$3
KEY
$5
VALUE
```

### (2). 响应格式

​		1234四种格式的消息或者5复合前面4中基础格式的消息.

## 4. Redis的架构模式

### (1). 单机版

​		多个client连接==一个Redis服务端==

​		容量有限,处理能力有限

### (2). 主从复制

​		根据==一个主服务器复制出多个从服务器==,从服务器负责查询,主服务器进行数据的添加删除和修改.每当主服务器上的数据有变动时,会同步到从服务器上.

​		降低了master的读压力,但是没有缓解写压力.

### (3). 哨兵

​		在主从复制的基础上==添加了哨兵机制,主服务器下线时进行故障转移(将另一台从服务器切换为主服务器来预防单点故障)==.

-   监控:哨兵会不断检查主服务器和从服务器是否运作正常
-   提醒:当一台服务器出现问题时,会通过API向管理员或者应用程序发送通知
-   自动故障迁移:当主服务器不能正常工作时,哨兵会进行故障转移

​		优点是自动故障迁移,保证稳定性,缺点还是没有缓解主服务器的写压力

### (4). 集群(proxy)

​		使用代理进行服务的分发(通过hash).减缓各服务器的压力.

​		Twemproxy是Twitter开源的一个Redis和memcache轻量级代理服务器.

​		通过代理对象将写请求分发到多个主服务器上,将读请求分发到多个从服务器上.各个服务器之间进行同步.

​		优点在于增加了各种算法,合理的分配服务,还支持故障节点的自动删除.缺点是增加了新的proxy,需要维护.

### (5). 集群(直接连接)

​		Redis集群由对台Redis服务器组成,这种直连方式对服务器部分主从.每个节点要处理部分写请求和读请求.通过同步进行统一.

​		优点是可大量扩展,高可用(部分节点不可用时,整个集群还是工作的),自动故障处理.

​		缺点是资源隔离性较差,数据通过异步复制,不保证强一致性.

## 5. Redis分布式锁

-   setnx(key, value)
    -   “set if not exits”
    -   若该key-value不存在，则成功加入缓存并且返回1，否则返回0。
    -   相当于获取锁,如果key已经存在了,返回0
-   expire(key, seconds)
    -   设置key-value的有效期为seconds秒。
-   getset(key, value)
    -   先进行get获取原值,再设置新的值(用于解决死锁)

setnx和expire中间出现故障的解决办法:

   1.  放弃使用expire命令.将当前时间戳作为value存入此锁中，通过当前时间戳和Redis中的时间戳进行对比，如果超过一定差值，认为锁已经时效，防止锁无限期的锁下去.如果两个线程同时发现锁超时,可能会同时获取到锁.这个问题通过getset()解决,通过getset原子操作保证只能有

       ```java
       while(jedis.setnx(lock, now+超时时间)==0）{
           if(now>jedis.get(lock) && now>jedis.getset(lock, now+超时时间)){
               // 这里先判断锁是否过期
               // 然后如果锁过期了,尝试竞争锁,只有一个线程能成功正确的返回之前的过期时间
               // 这时多个线程中的其他线程都会返回新的超时时间
               // 这个超时时间被更改并不重要,主要就是用于防止永久锁,问题不大
               break;
           }else{
               Thread.sleep(300);
           }
       }
       // 执行业务代码;
       jedis.del(lock);
       ```

   2.  合并命令

       ```redis
       // redis6.2后可将上述两步合并起来
       set key value seconds milliseconds nx|xx
       
       // seconds:秒
       // milliseconds：毫秒
       // nx：只有键不存在时，才对键进行设置操作
       // xx：只有键存在时，才对键进行设置操作
       // set操作成功完成时，返回ok，否则返回nil
       ```


## 6. 一致性哈希算法

## 7. Redis之外的NoSQL数据库

HBase, MongoDB

## 8. Redis热点问题

​		一个键的访问量超出了服务器最大访问量,那么这个服务器就会崩溃.

​		解决方案:Redis集群+主从复制,使用Redis集群尽可能将各个键分散放置,而对于单个键的访问量,通过主从复制来解决.因为对于数据库来说,最多处理的就是读请求,通过主从复制就可以讲读请求分散到多个从服务器,有效降低服务器的压力.

## 9. 缓存雪崩,缓存穿透和缓存击穿

​		==缓存雪崩==是指Redis中的某一时刻有大量的键到期,被销毁,这一瞬间所有的访问量都到了数据库中,导致数据库压力突然增大.解决方案:1.将键的==失效时间分散==开 2.给所有访问缓存的操作==加锁==(Redis的==SETNX==,成功则创建一个键,挡住后面的访问量),限制线程数(可以理解为限流,可以降低锁的粒度来优化) 3.==缓存预热==,在即将发生大并发访问量前手动的进行缓存的加载.

​		==缓存穿透==是指当重复的查询一个不存在的数据时,缓存中不存在,就每次都去数据库中寻找,缓存就失去了意义.解决方案:1.对于查询出的null值也进行==key-null的存储==,只不过键的存活时间设置的短一些,防止null数据长时间得不到更新 2.布隆过滤器先进行一次过滤(复杂,不支持删除,需要维护另外的一个集合)

​		==缓存击穿==是指一个热点的键一直在扛着很高的访问量,在这个键失效的瞬间,会对服务器造成压垮性的压力.解决方案:1.==热点缓存的更新或者永不过期== 2.==分级缓存==(缓存1失效,去取缓存2,二者不会同时失效,缺点是缓存2可能有脏数据) 3.给热点缓存的==访问加锁==