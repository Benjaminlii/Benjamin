# @Autowired与static

把static用到Spring的@Autowired上，会导致注入的对象一直报空指针

静态变量、类变量不是对象的属性，而是一个类的属性，所以静态方法是属于类（class）的，普通方法才是属于实体对象（也就是New出来的对象）的，spring注入是在容器中实例化对象，所以不能使用静态方法。

