# Java SE面试中常见问题总结

------

[TOC]

------

## 1. Java面向对象

### (1). 封装

​		核心思想就是“隐藏细节”、“数据安全”、”重用”、”不必关心具体的实现”：将对象不需要让外界访问的成员变量和方法私有化，只提供符合开发者意愿的公有方法来访问这些数据和逻辑，保证了数据的安全和程序的稳定。

​		使用 `private` 修饰符把成员变量设置为私有，防止外部程序直接随意调用或修改成员变量，然后对外提供 `public` 的 `set` 和 `get` 方法按照开发者的意愿（可以编写一些业务逻辑代码，虽然很少这样做）设置和获取成员变量的值。

### (2). 继承

​		用来提高了程序的复用性、扩展性

​		在实际开发中,应该是先有子类,然后从中抽象封装出父类

​		使用extends关键字实现

​		Java只允许单继承

​		父类中public和protected修饰的成员变量和方法可以被子类集成,构造方法不能被继承,但可以在子类中使用super()调用.子类中可以提高级别而不能降低.

​		state修饰的变量或者方法属于类,不能被继承,但是子类可以直接调用

#### 1). 方法重写

​		方法重写针对于实例方法.

​		方法名和参数必须一致,访问修饰可以提高.(如果有必要可以在上方添加@Override注解自动检查)

​		返回值可以不一样,但是只能是子类.

​		抛出异常必须是父类该方法抛出异常的子集

​		对于类方法(static,静态),在子类中重写是进行隐藏(与重载的区别是如果向上进行转型,那么执行的还是父类的方法)

#### 2). 类实例化的过程

1.  类加载
    1.  读取class文件,加载到内存,如果有父类,也会加载父类(==加载和验证==)
    1.  分配空间(==准备==,会赋初值为默认值)
    1.  执行父类,子类静态代码块(==初始化==,在这一步之前进行了==解析==过程,将符号引用转化为直接引用)
1.  分配堆内存空间,所有值为默认值
1.  对对象属性进行默认初始化(显示赋值的)
1.  调用构造方法
    1.  先调用父类构造方法初始化父类数据
    1.  显示的执行子类的构造代码块(与静态代码块的区别在于不加static)
    1.  执行子类构造方法中的代码进行初始化
1.  将地址赋给引用(外层new关键字那里)

### (3). 多态

​		同一个行为具有多个不同表现形式.

​		==同一个接口使用不同的实例对象调用而执行不同的操作.==

​		Java中有两种引用类型:编译时类型和运行时类型.编译时类型由引用决定,运行时类型由实例对象的类型决定.

条件:

-   继承
-   重写
-   父类引用指向子类对象

​		在编译时会先判断父类中有没有此方法,如果没有,则编译错误.如果有,再去调用子类中的同名方法(要么继承下去,要么被重写,只有被重写才能体现出多态性)

​		在JVM底层实现中,实际上是由于方法的编译期静态多分派和运行期动态单分派来完成的.静态多分派是指在编译阶段,会对方法的重载进行选择,这里是根据静态类型中的方法进行选择,选择的依据是方法名,方法返回值和方法的参数列表,所以才叫多分派.而在运行期,在静态分派已经选择出的方法的基础上,JVM从实际类型出发,寻找符合该方法的方法,如果找不到就上升到父类中进行查找,这里查找的依据只有父类这一条路,所以叫单分派.这样就造成了,对于不同的子类,可能会由于动态分派选择到不同的方法去运行,也就实现了多态.

## 2. 访问权限修饰关键字

|           | 本类 | 本包 | 子类 | 外部包 |
| --------- | ---- | ---- | ---- | ------ |
| public    | √    | √    | √    | √      |
| protected | √    | √    | √    | ×      |
| default   | √    | √    | ×    | ×      |
| private   | √    | ×    | ×    | ×      |

​		访问权限的控制是在编译层的,通过反射还是可以访问私有成员的

## 3. Java中的异常

### (1). 异常体系

![image-20200221133732048](http://benjaminlee.cn:8989/hello/images/image-20200221133732048.png)

-   Throwable:所有错误或者异常的超类,这个类或者其子类才能被throw抛出,才能作为catch中的参数类型
    -   Error:不应该捕获的严重类型,出现就应该退出程序
        -   VirtulMachineError:虚拟机错误(硬件错误，还是内存不够等等)
        -   AWTError:AWT是Java用户界面和绘制图形图像的包,这个错误多是GUI中产生的
    -   Exception:通常讲的异常,==受检查异常==,指程序可以处理的异常
        -   RuntimeException:==运行时异常==,可以不进行处理也可以加上try-catch或者throws
            -   空指针,数组越界,类型转换,算数错误等等
        -   IOException:输入输出异常,这里代表性的泛指非运行时异常(又称可检查异常),必须要显示的加上try-catch或者throws,否则编译不通过
        -   等等,没有列举出全部

### (2). throws和throw关键字

​		throws关键字用于在方法后面声明方法内部可能会抛出的异常.

​		throw用于手动的抛出异常(通常在try-catch中会有一些处理后再向外层抛出,具体使用与业务逻辑有关)

### (3). finally关键字

​		无论是否发生异常,finally代码块中的代码都会被执行

>   关于return和finally的顺序:
>
>   return语句其实是先将后面的变量从局部变量区放入操作数栈顶,然后执行return,返回操作数栈顶的值
>
>   finally代码块中的代码执行在这两个过程之间.也就是说,如果finally代码块中没有出现return语句,是不会影响方法的返回值的.如果有,那么操作数栈顶的值会被替换,会改变方法的返回值.

## 4. Synchronized详解

[深入理解Java并发之synchronized实现原理](https://blog.csdn.net/javazejian/article/details/72828483)

### (1). Synchronized使用

#### 1). 对象锁(同步代码块)

```java
synchronized (object) {
	// 临界区代码
}
```

​		不同线程访问同一个对象的对象锁,只能有一个线程成功进入,其他线程阻塞在代码块外面.当该线程退出代码块时,其他的线程进行CPU的竞争(就绪态),成功竞争到CPU的线程获取锁.

#### 2). 方法锁(同步方法)

```java
public synchronized method(){
    // 整个方法内部都是临界区
}
```

​		不同线程不能同时进入此方法,锁对象为this,所以多个线程调用==同一个对象的不同同步方法==也会进行同步

​		方法级的同步是隐式，即无需通过字节码指令来控制的，它实现在方法调用和返回操作之中。JVM可以从方法常量池中的方法表结构(method_info Structure) 中的 ACC_SYNCHRONIZED 访问标志区分一个方法是否同步方法。当方法调用时，调用指令将会 检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先持有monitor（虚拟机规范中用的是管程一词）， 然后再执行方法，最后再方法完成(无论是正常完成还是非正常完成)时释放monitor。在方法执行期间，执行线程持有了monitor，其他任何线程都无法再获得同一个monitor。

​		==synchronized修饰的方法并没有monitorenter指令和monitorexit指令，取得代之的确实是ACC_SYNCHRONIZED标识==，该标识指明了该方法是一个同步方法，JVM通过该ACC_SYNCHRONIZED访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。

>   与sync代码块的区别在于,sync代码块的实现方式实际上是在代码块的开始和结束处调用了monitorenter 和 monitorexit 两个指令.分别获取和释放monitor对象的持有权.
>
>   而同步方法中是进行了标记,没有使用字节码指令,但实际上还是获取Monitor对象

#### 3). 类锁

```java
public MyClass{
    public synchronized static void method(){}
}
```

​		不同的线程不能同时进入这个类的这个静态方法.使用当前类的类对象==MyClass.class为锁==,所以其他线程只要调用这个类其中的任意一个静态同步方法,都会被阻塞.

### (2). Synchronized原理(代码块)

#### 1). Java对象模型

​		每一个Java类在被JVM加载的时候,JVM都会给这个类创建一个instanceKlass保存在方法区,在JVM层用来表示该Java类.使用new创建一个对象的时候,JVM会创建一个instanceOopDesc对象,这个对象中包含了对象头和实例数据(还会有填充数据来保证每个对象的内存都是8字节的整数倍)

​		对象头包含两部分:Mark Word(运行时数据,哈希码,GC分代年龄,锁状态标志,等等)和Klass Point(只想的是对象所属类的instanceKlass).

​		对象的实例（instantOopDesc)保存在堆上，对象的元数据（instantKlass）保存在方法区，对象的引用保存在栈上。

#### 2). 监视锁Monitor

​		每一个Object对象中都内置了一个Monitor.(对象头中Mark Word中的LockWord指向的是Monitor的起始地址)

​		Monitor相当于许可证,拿到Monitor可以进行操作,没拿到需要等待.

​		在Java虚拟机中,monitor是由ObjectMonitor实现的.

ObjectMonitor中有几个关键属性:

-   _owner:指向持有Monitor对象的线程
-   _WaitSet:存放在该Monitor上处于wait状态的线程队列(obj.wait())
-   _EntryList:存放处于等待锁block状态的线程队列
-   _recursions:锁的可重入次数
-   _count:用来记录当前占有锁的线程的重入次数

一些操作:

-   线程T等待获取锁(同步代码块外等待):_EntryList中加入T
-   线程T获取对象锁(进入代码块):\_EntryList移除T,\_Owner置为T,计数器\_count+1
-   线程T获取对象锁之后调用了wait()方法:在之前的基础上给\_WaitSet中加入T

#### 3). Synchronized底层

##### 1>. 同步代码块

-   monitorenter指令插入到同步代码块的开始位置
-   monitorexit指令插入到同步代码块的结束位置

​		JVM保证这两个指令是一一对应的.

​		当线程执行到monitorenter时,会尝试获取该对象所对应的monitor的所有权.

##### 2>. 同步(静态)方法

​		synchronized方法则会被翻译成普通的方法调用和返回指令如:invokevirtual、areturn指令.

​		在VM字节码层面并没有任何特别的指令来实现被synchronized修饰的方法，而是在==Class文件的方法表中将该方法的access_flags字段中的synchronized标志置为1==，表示该方法是同步方法并==使用调用该方法的对象或该方法所属的Class在JVM的内部对象表示Klass做为锁对象==。

### (3). 锁优化

​		==JDK 6==之后对synchronized锁进行优化,新增了轻量级锁和偏向锁.

#### 1). 偏向锁

​		“偏向”的意思是，偏向锁假定将来只有第一个申请锁的线程会使用锁（不会有任何线程再来申请锁），因此，只需要在Mark Word中CAS记录owner（本质上也是更新，但初始值为空），如果记录成功，则偏向锁获取成功，记录锁状态为偏向锁，以后当前线程等于owner就可以零成本的直接获得锁；否则，说明有其他线程竞争，膨胀为轻量级锁。

​		==记录线程id,之后这个线程再次进行访问不需要进行同步.当其他线程尝试去获取这个锁时,偏向模式结束.==

#### 2). 轻量级锁

​		使用轻量级锁时，不需要申请互斥量，仅仅将==Mark Word中的部分字节CAS更新指向线程栈中的Lock Record(锁记录)==，如果更新成功，则轻量级锁获取成功，记录锁状态为轻量级锁；否则，说明已经有线程获得了轻量级锁，目前发生了锁竞争（允许短时间的锁竞争,使用==自旋锁优化==），接下来膨胀为重量级锁。

1.  在代码进入同步块的时候，如果同步对象锁状态为无锁状态（锁标志位为“01”状态，是否为偏向锁为“0”），虚拟机首先将==在当前线程的栈帧中建立一个名为锁记录（Lock Record）的空间==，用于存储锁对象目前的Mark Word的拷贝
1.  拷贝对象头中的==Mark Word复制到锁记录==（Lock Record）中
1.  线程尝试==使用CAS将对象头中的Mark Word替换为指向锁记录的指针==，成功则代表获得锁，失败表示其他线程竞争锁，当前线程尝试使用自旋操作来获取锁。
1.  自旋多次后仍然没有获取到锁,该锁升级为重量级锁,使用mutex阻塞当前线程

#### 3). 自旋锁

-   当前线程竞争锁失败时，打算阻塞自己
-   不直接阻塞自己，而是自旋（空等待，比如一个空的有限for循环）一会
-   在自旋的同时重新竞争锁
-   如果自旋结束前获得了锁，那么锁获取成功；否则，自旋结束后阻塞自己

#### 4). 重量级锁

​		在轻量级锁自旋一定次数还没有竞争到锁资源时,轻量级锁膨胀为重量级锁,并阻塞当前线程.

​		synchronized重量级锁本质依赖监视器锁monitor实现,而monitor的本质是依赖于底层操作系统的Mutex Lock实现,监视器锁可以认为直接对应底层操作系统中的互斥量（mutex）,要进行线程之间的切换需要从用户态转换成内核态,成本非常高.

## 5. 深拷贝和浅拷贝

​		Object对象有个clone()方法，实现了对象中各个属性的复制，但它的可见范围是protected的，所以实体类使用克隆的前提是：

1.  实现Cloneable接口，这是一个标记接口，自身没有方法。 
1.  覆盖clone()方法，可见性提升为public。

​		调用Object的clone()方法可以返回一个与当前对象完全一样的拷贝对象.

​		浅拷贝：创建一个新对象，然后将当前对象的非静态字段复制到该新对象，如果字段是值类型的，那么对该字段执行复制；如果该字段是引用类型的话，则复制引用但不复制引用的对象。

​		深拷贝：创建一个新对象，然后将当前对象的非静态字段复制到该新对象，无论该字段是值类型的还是引用类型，都复制独立的一份。当你修改其中一个对象的任何内容时，都不会影响另一个对象的内容。

​		==深拷贝和浅拷贝的区别在于:对对象内部的引用是直接复制还是递归的进行拷贝.==

## 6. 线程池

### (1). 为什么需要线程池

​		减少线程的创建和销毁操作,增加复用

### (2). 线程池参数

```java
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
```

-   corePoolSize:核心线程数,线程池中至少存活的线程,不会进行销毁.(关于超时时间可以进行设置是否进行销毁)
-   maxumumPoolSize:所有线程总数上限(核心线程+临时线程)
-   keepAliveTime:线程最大空闲时间,线程闲置的时间到达这个时间会被销毁(默认不使用于核心线程)
-   unit:超时时间的单位
-   workQueue:任务队列,如果线程池的核心线程没有空闲,此时出现了新的任务,新任务会被放入这个队列
    -   有界队列:超出队列的上限后会创建临时线程
    -   无界队列:永远不会创建临时线程
-   threadFactory:一个接口对象,用于定义生成线程的方式,如线程名格式
-   handler:当任务队列满了,出现新任务时的拒绝策略
    -   ThreadPoolExecutor.AbortPolicy：直接抛出异常，这是==默认策略==
    -   ThreadPoolExecutor.DiscardPolicy：直接丢弃任务，但是不抛出异常。
    -   ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后将新来的任务加入等待队列
    -   ThreadPoolExecutor.CallerRunsPolicy：由线程池所在的线程处理该任务，比如在 main 函数中创建线程池，如果执行此策略，将有 main 线程来执行该任务

### (3). 几种封装好的线程池实现(不推荐使用,但是很重要)

>   以下各种实现都有包含ThreadFactory的构造,下面没有列举

#### 1). newFixedThreadPool

```java
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
```

​		一个==线程数量固定==的线程池，规定的最大线程数量，超过这个数量之后进来的任务，会放到等待队列中，如果有空闲线程，则在等待队列中获取，遵循先进先出原则。

​		核心线程数和最大线程数一致

​		默认使用的是 LinkedBlockingQueue(无界队列) 作为等待队列

#### 2). newSingleThreadExecutor

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}
```

​		==只有一个线程==的线程池,其他任务进来,会在等待队列中排队.

​		使用LinkedBlockingQueue(无界队列) 作为等待队列,可能会无限长

#### 3). newCachedThreadPool

```java
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```

​		缓存型线程池,全部都是临时线程.

​		==队列中不保存等待任务==,当前没有空闲线程直接创建新的临时线程.临时线程的空闲时间很短.

​		关键在于使用SynchronousQueue作为等待队列,它不会保留任务.

#### 4). newScheduledThreadPool

```java
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
    return new ScheduledThreadPoolExecutor(corePoolSize);
}
public ScheduledThreadPoolExecutor(int corePoolSize) {
    super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
          new DelayedWorkQueue());
}
```

​		计划型线程池,可以设置固定时间的延时或者定期执行任务。

​		使用DelayedWorkQueue 作为等待队列,保证队列中的任务只有到了指定的延时时间，才会执行任务。

### (4). 线程池工作原理

1.  当提交一个新任务到线程池时，线程池判断corePoolSize线程池是否都在执行任务，如果有空闲线程，则创建一个新的工作线程来执行任务，直到当前线程数等于corePoolSize；
1.  如果当前线程数为corePoolSize，继续提交的任务被保存到阻塞队列中，等待被执行；
1.  如果阻塞队列满了，那就创建新的线程执行当前任务，直到线程池中的线程数达到maxPoolSize，这时再有任务来，由饱和策略来处理提交的任务

## 7. 反射

与反射机制相关的类:

-   Class类:类在内存中的实体
-   Field类:类的成员变量
-   Method类:类的方法
-   Constructor类:类的构造方法

### (1). 获取Class对象

-   Class.forName(className)

### (2). 获取构造方法

-   Class.getDeclaredConstructors()获取所有构造方法的数组.(不加Declared不能获取私有构造)
-   Class.getDeclaredConstructors(参数类型数组也就是Class[])获取指定参数的构造.(要try-catch,可能找不到)

### (3). 调用构造方法

-   Constructor.newInstance(参数1, 参数2)调用相应构造方法
    -   如果是私有构造,需要进行设置setAccessible(true)

### (4). 获取方法

​		与获取构造方法类似

-   Class.getDeclaredMethod(String methodName, Class[] paraTypeArray)获取指定名称,参数的方法
-   Class.getDeclaredMethods()获取全部的方法数组

### (5). 调用方法

-   Method.invoke(参数1,参数2,参数3.......)返回Object
    -   私有方法setAccessible(true)

### (5). 获取属性

-   Class.getDeclaredField(String fieldName)根据属性名获取属性对象
-   Class.getDeclaredFields()获取所有属性数组
    -   通过set设置属性,私有setAccessible(true)

## 8. 抽象类和接口的区别

### (1). 组成

-   抽象类可以有实现了的方法,接口不可以有
-   抽象类可以有各种访问级别的成员变量,接口只能有public static final的变量
-   抽象类可以有构造器(用于子类中调用以构造父类部分的属性),接口不能
-   抽象类中不能有private的方法,接口中只能有public

### (2). 用途

-   抽象类用于表示不可实例化的类,本质上还是类,而接口只是方法的集合,并不是类
-   抽象类只能单继承,而接口可以实现多个

## 8. 关于Object.hashcode()方法

​		hashCode返回的并不一定是对象的（虚拟）内存地址，具体取决于运行时库和JVM的具体实现。

​		Object.hashCode是一个native方法，看不到源码。

​		Object.hashCode()在JRE中应该遵循的一些契约:

-   一致性，在程序的一次执行过程中，对==同一个对象必须一致地返回同一个整数==。

-   如果两个对象通过==equals(Object)==比较，==结果相等==，那么对这两个对象分别调用==hashCode方法应该产生相同的整数结果==。

-   如果两个对象通过java.lang.Object.equals(java.lang.Ojbect)比较，==结果不相等，不必保证对这两个对象分别调用hashCode也返回两个不相同的整数。==


## 9. JDK个版本的新特性

#### java5

1.  泛型
1.  增强for循环
1.  自动封箱拆箱
1.  枚举
1.  注解
1.  新的线程模型和并发库（`java.util.concurrent`)。

#### java6

1.  集合框架增强。
1.  新的数组拷贝方法。`Arrays.copyOf`和`Arrays.copyOfRange`
1.  Scripting. 可以让其他语言在java平台上运行。 java6包含了一个基于Mozilla Rhino实现的javascript脚本引擎。
1.  支持JDBC4.0规范。

#### java7

1.  二进制前缀`0b`或者`0B`。整型（byte, short, int, long）可以直接用二进制表示。
1.  字面常量数字的下划线。用下划线连接整数提升其可读性，自身无含义，不可用在数字的起始和末尾。
1.  泛型实例化类型自动推断(new后面跟的不需要注明泛型)。
1.  try-with-resources语句(带资源的try)。
1.  单个catch中捕获多个异常类型（用`|` 分割）并通过改进的类型检查重新抛出异常。

#### java8

1.  lambada表达式(Lambda Expressions)。Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中)。
1.  HashMap改进，在键值哈希冲突时能有更好表现。
1.  Date Time API。加强对日期和时间的处理。
1.  java.util 包下的改进，提供了几个实用的工具类。
    -   并行数组排序。
    -   标准的==Base64==编解码。
    -   支持无符号运算。
1.  HotSpot
    -   删除了 永久代（PermGen）.
    -   方法调用的字节码指令支持默认方法。

#### java9

1.  java模块系统 （Java Platform Module System）。


-   ####  

-   

-   

-   

-   

## 10. Java中基本数据类型和其内存大小

1.  整型
    1.  byte 1字节
    1.  short 2字节
    1.  int 4字节
    1.  long 8字节
1.  字符型
    1.  char 2字节(ASCII码和UFT-8,默认为'\0')
1.  浮点型
    1.  float 4字节
    1.  double 8字节
1.  布尔型
    1.  boolean (布尔类型比较复杂,单个的布尔类型,编译时会被当做int类型处理,原因是32位机一次处理的数据量就是4字节.而对于bollean[],会被转化成byte[],每个元素占1字节)

## 11. GC诊断

1.  查看GC日志
1.  jmap查看堆内存初始化配置信息以及堆内存的使用情况
1.  jstat 可以监测 Java 应用程序的实时运行情况,包括堆内存信息以及垃圾回收信息
1.  jstack 经常用来查看线程的堆栈信息

1.  young GC:
    1.  排查每次YoungGC后幸存对象大小,如果每次YoungGC后幸存对象较大，可能存在问题
    1.  如果youngGC过于繁忙,扩大young区,Young区在整个堆占比在25%~40%比较合理
1.  old GC
    1.  检查Young区与Old区比值
    1.  通过jstat查看每次YoungGC后晋升到Old区对象占比
    1.  如果不停的CMS GC，Old区降不下去，建议先执行jmap -histo pid | head -n20 查看TOP20对象分布

