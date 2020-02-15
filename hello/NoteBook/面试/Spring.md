# Spring面试中常见问题总结

------

[TOC]

------

## 1. IOC和AOP

​		首先英文缩写记清楚,不要念错

### (1). IOC

​		IOC(Inversion of Control),从字面上理解就是==控制反转==，将对在自身对象中的一个内置对象的控制权反转。所谓的反转，即==把内置对象的控制权反转给一个容器==，而应用程序只需要提供对象的类型即可。IOC是面向对象编程的一种设计原则.可以用来==降低代码之间的耦合==.大致含义就是在代码中需要使用一个对象时,并不是立即new一个对象出来,而是从一个调控系统内所有对象的外界实体,也就是容器中将这个对象的一个实体传递到调用处接收的位置.对象的实体在容器加载时就已经被创建.而且对象的创建与当前代码的运行解耦合了.

​		==依赖注入==(Dependency Injection,即DI),容器中的对象所需要的依赖来自于配置文件(xml)中的配置,也就是由容器去完成依赖项的添加.就是==在xml文件中配置好类的属性==.Spring中需要依赖项属性的set和get方法.Spring的IOC有三种注入方式 ：构造器注入、setter方法注入、根据注解注入。

​		==依赖查找==(Dependency Lookup,即DL),是指在容器中对象的类中,手动将依赖项从容器中取出,设置依赖项.实际项目中使用容器对象(@Autowired不是依赖查找,而是通过扫描注解,在容器内部进行依赖的注入,没有手动配置罢了)进行依赖项的查找.

### (2). AOP

​		AOP(Aspect Oriented Programming),意为==面向切面编程==.是通过预编译和运行时动态代理的方式==实现程序功能的统一维护==的技术(何为维护参考事务).中文的这种翻译带有略微的一些误导性,切面主要是指将程序维护代码中先行执行部分和后续执行部分之间插入用户代码的位置,就像在一个方法中切入一个过程一样.

​		AOP可以在不修改源代码的情况下,通过预编译和运行期动态代理实现给程序统一添加功能.

​		利用AOP还可以对业务逻辑的各个部分(将日志,性能统计,事务处理等等功能提取出来)进行隔离,降低耦合度.

​		==Spring的AOP通过动态代理实现==,底层实现使用JDK代理(只提供接口的代理，不支持类的代理)或者Cglib(生成指定类的一个子类对象)实现.主要思想就是通过==获取实例对象的动态代理对象==(代理是指代理对象内部有实例对象作为成员变量,代理对象可以调用实例变量的方法),==在代理对象中对实例对象的方法再次进行封装==,但封装的内部会==在调用前后进行一些逻辑上的处理==,如开启关闭事务,打印日志等等.

>   Spring的AOP通过动态代理实现,底层实现使用JDK代理(只提供接口的代理，不支持类的代理)或者Cglib(生成指定类的一个子类对象)实现( JDK 本身只提供接口的代理，而不支持类的代理；而 CGLib 很好的弥补了这点，它通过创建子类，在子类中拦截父类的方法并织入横切逻辑，实现对类的代理，但是其不能对目标类中的 final 或 private 方法进行代理，因为被 final 或 private 修饰的方法不能被继承。

​		实际上我们调用的是代理对象的方法.在其内部做了处理后又调用了原来实例对象的方法.

#### 1). 概念解释

-   切面(Aspect):就是被抽取出来的公共逻辑
-   连接点(JoinPoint):指通知方法执行的位置
-   通知(Advice):就是在某个连接点上添加的统一功能.前置通知、返回通知、后置通知、异常通知、环绕通知
-   切入点(PointCut):要拦截进行切入的方法,使用切入点表达式或者注解标记声明
-   目标对象(TargetObject):被代理的对象
-   织入(Weaving):在代理对象中给目标对象的方法中的连接点处添加增强,创建该代理对象(spring在运行是完成织入,也就是动态代理,在使用对象时在内存中“临时”生成AOP动态代理类)

#### 2). JDK 代理代码实现

```java
/**
     * 生成代理类对象
     */
public static Service createService(){
    // 目标类
    Service service = new ServiceImpl();
    //切面类
    MyAspect myAspect = new MyAspect();
    
    /* 代理类
         * Proxy.newProxyInstance
         * 	    参数1：loader ，类加载器，动态代理类 运行时创建，任何类都需要类加载器将其加载到内存。
         * 			一般情况：当前类.class.getClassLoader();
         * 				目标类实例.getClass().get...
         * 	 	参数2：Class[] interfaces 代理类需要实现的所有接口
         * 	 			方式1：目标类实例.getClass().getInterfaces()  ;
         *							注意：只能获得自己接口，不能获得父元素接口
         * 	 			方式2：new Class[]{UserService.class}
         * 	 			例如：jdbc 驱动  --> DriverManager  获得接口 Connection
         * 	 	参数3：InvocationHandler  处理类，接口，必须进行实现类，一般采用匿名内部
         * 	 		提供 invoke 方法，代理类的每一个方法执行时，都将调用一次invoke
         * 	 			参数31：Object proxy ：代理对象
         * 	 			参数32：Method method : 代理对象当前执行的方法的描述对象（反射）
         * 	 				执行方法名：method.getName()
         * 	 				执行方法：method.invoke(对象，实际参数)
         * 	 			参数33：Object[] args :方法实际参数
         */
    Service proxy = (Service) Proxy.newProxyInstance(
        MyBeanFactory.class.getClassLoader(),// 本类的类加载器
        service.getClass().getInterfaces(),// 本类的接口数组
        new InvocationHandler() {// 处理类:一个InvocationHandler接口对象,内部实现切入点等等细节
            @Override
            // proxy代理对象,没用到
            // method执行的方法,在内部通过反射执行
            // args方法参数
            public Object invoke(Object proxy, Method method, Object[] args) 
                throws Throwable {
                //前执行
                myAspect.before();

                //执行目标方法,需要传入目标类
                Object obj = method.invoke(service, args);

                //后执行
                myAspect.after();

                return obj;
            }
        });

    return proxy;
}

```



## 2. BeanFactory和ApplicationContext

​		都可以做Spring的容器,其中ApplicationContext是BeanFactory的子接口.

BeanFactory实现的功能:

-   读取bean配置文档
-   管理bean的加载,实例化,控制bean的生命周期
-   维护bean之间的依赖关系

ApplicationContext在BeanFactory的基础上还提供了更完整的框架功能:

-   支持国际化
-   统一的资源文件访问格式
-   监听器
-   同时加载多个配置文件,分层

==BeanFactroy采用的是延迟加载形式来注入Bean的==，即只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化。

==ApplicationContext，它是在容器启动时，一次性创建了所有的Bean。==

## 3. Spring Bean的生命周期

### (1). ==实例化Bean==

​		对于BeanFactory,当客户端请求一个未被初始化的bean时,容器获取对象中的信息,进行实例化

​		对于ApplicationContext,容器一启动,就获取所有对象的信息,进行初始化

### (2). 设置对象属性(==依赖注入==)

​		实例化完成后的对象被封装在BeanWrapper(BeanWrapperImpl在内部使用Spring的BeanUtils工具类对Bean进行反射操作，设置属性。)中,根据配置的信息,进行依赖的填充.

### (3). ==处理Aware接口==

​		Spring检查该对象是否实现了xxxAware接口,并将相关的xxxAware实例注入给Bean:

1.  如果这个Bean已经实现了BeanNameAware接口，会调用它实现的setBeanName(String beanId)方法，此处传递的就是Spring配置文件中Bean的id值；
1.  如果这个Bean已经实现了BeanFactoryAware接口，会调用它实现的setBeanFactory()方法，传递的是Spring工厂自身。
1.  如果这个Bean已经实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文；

​		所以BeanNameAware接口是为了让自身Bean能够感知到，获取到自身在Spring容器中的id属性。

​		同理，其他的==Aware接口也是为了能够感知到自身的一些属性==。

​		比如实现了ApplicationContextAware接口的类，能够获取到ApplicationContext，实现了BeanFactoryAware接口的类，能够获取到BeanFactory对象。

### (4). BeanPostProcessor接口的before处理

​		调用BeanPostProcessor接口实现的postProcess==Before==Initialization(Object bean, String beanName)方法,进行一些==初始化前的前置处理==.

​		这个方法传入bean对象和其名字.并将返回的对象设置为bean.

​		可以用于进行原对象和代理对象的替换,修改bean的属性等功能的实现.

### (5). InitializingBean

​		==初始化时会附加进行的操作==

​		如果bean实现了 InitializingBean 接口,会调用 void afterPropertiesSet() 方法进行初始化

### (6). init-method

​		bean在Spring配置文件中配置了init-method属性，则会自动调用其配置的初始化方法

​		如果同时实现了,那么先进行接口方法的调用,在进行配置文件中指定方法的调用	

### (6). BeanPostProcessor接口

​		调用BeanPostProcessor接口实现的postProcess==After==Initialization(Object bean, String beanName)方法,进行一些初始化前的==后置处理==.

​		与(4)对应.

------

​		到这里bean就已经被正确的创建了.

------

### (7). DisposableBean

​		==清理bean时会附加进行的操作.==

​		当bean不再被需要的时候,会经历清理阶段.

​		如果bean实现了DisposableBean这个接口,会调用其实现的void destroy()方法

### (8). destroy-method

​		同样，在对象销毁有一个参数配置destroy-method，和init-method相同，在调用销毁的时候，先执行 DisposableBean的destroy方法，后执行 destroy-method声明的方法。

## 4. spring支持的bean的作用域

### (1). singleton

​		默认,单例模式

​		一般的开发中,并不会在bean中加入可变的成员变量,所以即使单例模式下没有进行任何的多线程同步处理,也是线程安全的,但要明白原因.

### (2). prototype

​		多例模式,只要请求bean,就生成一个实例

### (3). request

​		为每个请求生成不同的实例

### (4). session

​		为每个会话生成不同的实例

### (5). global-session

​		global session作用域类似于标准的HTTP Session作用域，不过它仅仅==在基于portlet的web应用中才有意义==。Portlet规范定义了全局Session的概念，它被所有构成某个portlet web应用的各种不同的portlet所共享。在global session作用域中定义的bean被限定于全局portlet Session的生命周期范围内。

>   portlet类似servlet,是另一套解决方案

## 5. spring如何处理线程并发问题

​		在Spring中，绝大部分Bean都可以声明为singleton作用域，因为Spring对一些Bean中非线程安全状态采用ThreadLocal进行处理，解决线程安全问题。

​		Spring 中的 一些bean, 如 RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder 等非线程安全的“状态性对象”采用ThreadLocal进行封装，让他们也称为线程安全的“状态性对象”，因此有状态的bean就能够以singleton的方式在多线程环境中正常工作。

​		Spring中DAO和Service都是以单实例的bean形式存在，Spring通过ThreadLocal类将有状态的变量（例如数据库连接Connection）本地线程化，从而做到多线程状况下的安全。

## 6. spring注入bean的几种方式

### (1). 基于xml文件

-   set方法注入(property标签,name属性是字段名,ref是依赖项在容器中的name)

    ```xml
    <!--配置bean,配置后该类由spring管理--> 
    <bean name="springAction" class="com.bless.springdemo.action.SpringAction"> 
    <!--依赖注入,配置当前类中相应的属性--> 
        <property name="springDao" ref="springDao"></property> 
    </bean> 
    <bean name="springDao" class="com.bless.springdemo.dao.impl.SpringDaoImpl"></bean>
    ```

-   构造器注入(1.通过index设置参数位置,2.通过type设置类型)

    ```xml
    <!--
       构造函数配置
       <constructor-arg> 用于配置构造方法一个参数argument
       方案一：
                name ：参数的名称
       方案二：
                index ：参数的索引号，从0开始 。如果只有索引，匹配到了多个构造方法时，默认使用第一个。
                type ：确定参数类型
       填充值：
                value：设置普通数据
                ref：引用数据，一般是另一个bean id值
    -->
    <bean id="studentConstructor" class="testXml.constructor.Student">
        <constructor-arg name="id" value="1"/>
        <constructor-arg name="name" value="Benjamin"/>
        <constructor-arg name="age" value="19"/>
    </bean>
    ```

-   静态工厂注入

    工厂方法是静态的

    ```java
    <!--
        使用Spring实现静态工厂
        这里class为静态工厂的全类名
        factory-method为静态工厂生产该对象是调用的静态方法名
    -->
    <bean id="demoBeanStaticFactory" class="testInject.staticFactory.MyBeanFactory" factory-method="getDemo"/>
    ```

-   实例工厂

    工厂类需要将工厂控制反转给容器,并且容器中的方法不是静态的
    
    ```java
    <!--
        Spring实例工厂
        先配置实例工厂的bean
        在要生产的bean中设置实例工厂以及方法
        factory-bean为实例工厂的bean id
        factory-method为实例工厂中的相应方法
    -->
    <bean id="demoFactory" class="testInject.factory.MyBeanFactory"/>
<bean id="demoBean" factory-bean="demo" factory-method="createDemoImpl"/>
    ```
    
### (2). 自动装配

#### 1). spring的自动装配

1.  no:不进行自动装配,使用ref进行装配bean
1.  byName:使用bean的名称进行自动装配(一个bean的property与其他bean的name进行匹配)
1.  byType:通过参数的数据类型记性匹配
1.  constructor:利用构造函数进行装配,其中构造函数使用byType进行装配
1.  autodetect:自动探测,先判断有无构造函数,否则使用byType进行装配

#### 2). 基于注解

​		使用@AutoWire配置自动装载模式.

​		容器会自动装载一个后置处理器,当扫描到@AutoWire,@Resource或者@Inject时,就会在IoC容器查询需要的bean.

​		首先根据类型进行查找,如果匹配到多个对象,使用名称进行查找.如果没有匹配到对象,会抛异常.解决方法:设置属性required=false(允许不被装配)。

>   @Autowired可用于：构造函数、成员变量、Setter方法
>
>   注：@Autowired和@Resource之间的区别
>
>   (1) @Autowired默认是按照==类型==装配注入的，默认情况下它要求依赖对象必须存在（可以设置它required属性为false）。
>
>   (2) @Resource默认是按照==名称==来装配注入的，只有当找不到与名称匹配的bean才会按照类型来装配注入。

## 7. spring使用的设计模式

1.  工厂模式:BeanFactory创建类的实例
1.  单例模式:默认情况下,bean都是单例的
1.  代理模式:AOP的实现使用了JDK的动态代理和CGLIB字节码生成技术
1.  模板方法:事务,AOP等等
1.  观察者模式:定义对象键一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都会得到通知被制动更新，如Spring中listener的实现--ApplicationListener。

## 8. spring事务实现方式和原理

​		spring事务的本质就是数据库事务,就是调用连接对象的相关方法进行回滚和读取保存点.

​		spring根据注解扫描和xml文件中的配置,为指定的方法生成代理类,使用AOP进行事务开启,回滚和提交等操作的统一进行.

​		实际上是调用数据库本身的事务功能.

### (1). sprint事务种类

#### 1). 编程式事务

​		配置TransactionTemplate, 需要注入TransactionManager

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<bean id="transactionTemplate"
      class="org.springframework.transaction.support.TransactionTemplate">
    <property name="transactionManager">
        <ref bean="transactionManager"/>
    </property>
</bean>
```

​		将TransactionTemplate注入到业务层方法中,并使用

```java
public class ProTransExample {
    @Resource(name = "transactionTemplate")
    private TransactionTemplate transactionTemplate;

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void save() {
        /**
         * 说明:
         *  如果你的方法需要返回值请使用这个类
         *      new TransactionCallback<Object>()
         *
         *  如果你的方法不需要返回值那么使用👇下面这个例子就可以了
         *      new TransactionCallbackWithoutResult()
         */
        transactionTemplate.execute(new TransactionCallbackWithoutResult(){
          @Override
          protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                String sql = "insert into books(isbn,name,price,pubdate) values(?,?,?,?)";
                jdbcTemplate.update(sql,"20-166-890-China","阿甘正传",99.00,new Date());
          }
        });
    }
}
```



#### 2). 声明式事务

两种方法:

-   在xml文件中配置切入点表达式(按照报名,类名,方法名,参数筛选出方法集合)和通知
-   使用@Transactional注解进行

### (2). spring事务传播行为

-   PROPAGATION_REQUIRED：如果当前没有事务，就创建一个新事务，==如果当前存在事务，就加入该事务==，该设置是最常用的设置。
-   PROPAGATION_SUPPORTS：支持当前事务，如果当前存在事务，就加入该事务，==如果当前不存在事务，就以非事务执行==。
-   PROPAGATION_MANDATORY：支持当前事务，如果当前存在事务，就加入该事务，==如果当前不存在事务，就抛出异常==。
-   PROPAGATION_REQUIRES_NEW：创建新事务，无论当前存不存在事务，都==创建新事务==。
-   PROPAGATION_NOT_SUPPORTED：==以非事务方式执行操作==，如果当前存在事务，就把==当前事务挂起==。
-   PROPAGATION_NEVER：以非事务方式执行，==如果当前存在事务，则抛出异常==。
-   PROPAGATION_NESTED：如果当前存在事务，则在==嵌套事务==内执行。如果当前没有事务，则按REQUIRED属性执行。
    

### (3). spring中的事务隔离级别

-   ISOLATION_DEFAULT：这是个 PlatfromTransactionManager 默认的隔离级别，==使用数据库默认的事务隔离级别==。
-   ISOLATION_READ_UNCOMMITTED：==读未提交==，允许另外一个事务可以看到这个事务未提交的数据。
-   ISOLATION_READ_COMMITTED：==读已提交==，保证一个事务修改的数据提交后才能被另一事务读取，而且能看到该事务对已有记录的更新。
-   ISOLATION_REPEATABLE_READ：==可重复读==，保证一个事务修改的数据提交后才能被另一事务读取，但是不能看到该事务对已有记录的更新。
-   ISOLATION_SERIALIZABLE：==串行化==,一个事务在执行的过程中完全看不到其他事务对数据库所做的更新。

## 9. spring事件

>   **在谈Spring的事件监听之前，让我们先了解一下Spring容器，什么是ApplicationContext ？**
>
>   它是Spring的核心，Context我们通常解释为上下文环境，但是理解成容器会更好些。 
>
>   ApplicationContext则是应用的容器。
>
>   Spring把Bean（object）放在容器中，需要用就通过get方法取出来。
>
>   此接口提供给Spring应用配置的能力，当应用启动时，此接口的实现是只读的，但是如果该实现支持，其内容也是可以重新载入的。

1.  上下文开始事件:容器开始时触发(容器调用ConfigurableApplicationContext的Start()方法,此处的开始是指所有的bean都已被加载,后置处理器都被激活,所有单例bean都已被实例化, 所有的容器对象都已准备好可使用)
1.  上下文更新事件:调用ConfigurableApplicationContext 接口中的refresh()方法(再次开始,热加载)
1.  上下文停止事件:调用ConfigurableApplicationContext的Stop()方法(所有bean都接收到了stop信号,这时bean还没有被销毁,可以调用start方法重启)
1.  上下文关闭事件:当容器被关闭时触发(所有bean都被销毁)
1.  请求处理事件:一个http请求被处理完之后触发该事件