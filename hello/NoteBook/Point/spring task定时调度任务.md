# spring task定时调度任务

## 1.spring task介绍

​	spring task是spring3.0之后自助开发的定时任务工具,使用起来很简单,只需要提供spring相关jar包就可以使用,并且支持注解和配置文件两种形式.

## 2.配置文件方式实现

#### 	1.普通的pojo类

```java
@Service 
public class TaskJob { 
    public void job1() { 
        System.out.println(“任务进行中。。。”); 
    } 
}
```

#### 	2.spring task配置文件

```xml
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:task="http://www.springframework.org/schema/task" 
       ......
       xsi:schemaLocation="http://www.springframework.org/schema/task 
                           http://www.springframework.org/schema/task/spring-task-3.0.xsd">

      <task:scheduled-tasks> 
          <task:scheduled ref="taskJob" method="job1" cron="0 * * * * ?"/> 
      </task:scheduled-tasks> 
      <context:component-scan base-package=" com.gy.mytask " />
</beans>
```

​	每一个定时任务都需要在spring配置文件中<task:scheduled-tasks>标签内进行一条配置<task:scheduled>.

​	ref指定时任务所在的类在spring容器中Javabean的id,method值指需要定时去执行的任务.

​	Javabean需要配置进spring容器(注解或者配置文件)

## 3.使用注解方式实现

​	大致与配置文件形式相同.只需要将task:scheduled-tasks标签去除,并且加上以下标签:

```xml
<task:annotation-driven/>
```

​	在需要定时执行的方法上加上一下注解,给出例子:

```java
@Component
public class TimerManager {

    @Autowired
    private Service ervice;

    /**
     * @Scheduled 内cron表达式限定方法执行的时间
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void generateIncomePlan(){
        logger.info("---------------定时任务开始--------------");

        service.doSomeThing();

        logger.info("---------------定时任务结束--------------");
    }
}
```

## 4.crom表达式

1. cron表达式的格式

   秒   分   时   日   月   周   [年]

2. 各个字段的取值

   | 字段名 | 允许的值                                | 允许的特殊字符         |
   | ------ | --------------------------------------- | ---------------------- |
   | 秒     | 0~59                                    | ,  -  *  /             |
   | 分     | 0~59                                    | ,  -  *  /             |
   | 时     | 0~23                                    | ,  -  *  /             |
   | 日     | 1~31                                    | ,  -  *  ?  /  L  W  C |
   | 月     | 1~12   or   JAN~DEC                     | ,  -  *  /             |
   | 周     | 1~7   or   SUN~SAT  (1~7指从周日到周六) | ,  -  *  ?  /  L  C  # |
   | [年]   | 1970~2099  or  empty                    | ,  -  *  /             |

3. 特殊字符的意义

   | 特殊字符 | 表示的意义                                                   |
   | -------- | ------------------------------------------------------------ |
   | ,        | 表示枚举的值(周中使用 1,2,3 表示每周的周日周一周二都执行)    |
   | -        | 表示范围(周中使用 1-5 表示从周一至周四都执行)                |
   | *        | 表示任意值                                                   |
   | /        | 表示一个值的增加幅度(n/m表示从n开始每次增加m)                |
   | ?        | 表示不确定的值.只用在日和周中,如果其中一个出现了*,另一个要匹配其意义,不能使用*表示任意,应使用?表示满足之前的*的任意值 |
   | L        | 在日中表示一个月中的最后一天,用在周中表示当月的最后一个周x(xL,x可省略,默认为7,周六) |
   | W        | 只能出现在日期字段中,表示距离x日最近的工作日(xW),(日期字段中使用LW表示当月的最后一个工作日) |
   | #        | (x#y)表示当月的第7个周x(x为数字表示的周)                     |

4. Cron生成

   http://cron.qqe2.com/