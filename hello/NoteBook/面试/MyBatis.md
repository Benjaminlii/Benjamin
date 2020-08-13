# MyBatis面试中常见问题总结

-----

[TOC]

-----

## 1. #{}和${}的区别

​		\#{}是预编译处理，${}是字符串替换。

​		#{}可以防止sql注入,底层使用PreparedStatement执行sql。首先进行预编译，得到的PreparedStatement句柄其实是一个预编译好的SQL语句。词法分析、语义分析等过程都已经执行完毕，这就说明关键字、执行逻辑等都不会再变化，编译后注入的部分无法再改变执行逻辑，被当成字符串处理，从而达到了防止SQL注入的目的。

> 预编译的局限性：
>
> 由于预编译时数据库会进行词法和语义的解析、生成执行计划，因此占位符只能占位SQL语句中的普通值，而表名、列名、关键字等影响编译的部分是不可以使用占位符的。

​		${}将传入的参数拼接(替换其中的${})到SQL上然后直接执行,可能会遭到SQL注入攻击

>   SQL注入:
>
>   例如一条SQL语句:delete from t_table where t_id = ${value} and ..........;
>
>   这样的SQL语句在传入参数时如果传入的是"1 or 1 --"
>
>   那么第一个t_id的判断会失败,而后面的1会使整个where判断为true.后面的逻辑被--注释掉了.就会删除整个库.

为什么会有这样的区别?

​		MyBatis在底层实际还是使用JDBC与数据库进行交互,在底层的调用中,#{}会被解释成占位符?,使用set方法去设置值,会进行一定的检查.而${}则是直接进行替换.

​		在底层是SQL的两种方式:字符串拼接和占位符填充参数

>   在某些特殊场合下只能用${}，不能用#{}。例如：在使用排序时ORDER BY ${id}，如果使用#{id}，则会被解析成ORDER BY “id”,这显然是一种错误的写法。

[Mybatis中#和$的区别及sql预编译](https://blog.csdn.net/erfu6081/article/details/90168542?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)

## 2. MyBatis使用代理进行开发时,接口文件中的方法能不能重载?

​		不能进行重载,接口中的方法和xxxxxMapper.xml文件中的SQL是通过接口中方法名和xml文件中标签的id一一对应来进行映射的.进行重载的话就不是一一映射了.

