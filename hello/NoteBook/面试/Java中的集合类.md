# Java中的集合类

------

[TOC]

-------

## 1. HashMap

### (1). 常量和构造方法

```java
//这两个是限定值 当节点数大于8时会转为红黑树存储
static final int TREEIFY_THRESHOLD = 8;
//当节点数小于6时会转为单向链表存储
static final int UNTREEIFY_THRESHOLD = 6;
//红黑树最小长度为 64
static final int MIN_TREEIFY_CAPACITY = 64;
//HashMap容量初始大小
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//HashMap容量极限
static final int MAXIMUM_CAPACITY = 1 << 30;
//负载因子默认大小
static final float DEFAULT_LOAD_FACTOR = 0.75f;
//Node是Map.Entry接口的实现类
//在此存储数据的Node数组容量是2次幂
//每一个Node本质都是一个单向链表
transient Node<K,V>[] table;
//HashMap大小,它代表HashMap保存的键值对的多少
transient int size;
//HashMap被改变的次数
transient int modCount;
//下一次HashMap扩容时被占用容量的大小
int threshold;
//存储负载因子的常量
final float loadFactor;

//默认的构造函数
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}
//指定容量大小
public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}
//指定容量大小和负载因子大小
public HashMap(int initialCapacity, float loadFactor) {
    //指定的容量大小不可以小于0,否则将抛出IllegalArgumentException异常
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    //判定指定的容量大小是否大于HashMap的容量极限
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    //指定的负载因子不可以小于0或为Null，若判定成立则抛出IllegalArgumentException异常
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);

    this.loadFactor = loadFactor;
    // 设置“HashMap阈值”，当HashMap中存储数据的数量达到threshold时，就需要将HashMap的容量加倍。
    // 也就是说,HashMap在自定义初始容量时,会将下一次扩容时的值设置为容量的下一个2的幂数
    // 本身是没有立即分配HashMap容量的,在下一次插入时,经判断需要进行扩容,那么扩容到这个threshold
    // 从下一次开始,每次threshold都为loadFactor * 
    this.threshold = tableSizeFor(initialCapacity);
}
//传入一个Map集合,将Map集合中元素Map.Entry全部添加进HashMap实例中
public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    //此构造方法主要实现了Map.putAll()
    putMapEntries(m, false);
}
```

​		如果指定容量的话,会先进行判断容量不能小于0,否则抛异常.不能大于容量极限,否则还是使用容量极限.

​		指定负载因子的话不能为0或者null.

​		制定了容量的话,会直接根据设置的值获取下一个2的幂数,作为下一次扩容的目标容积(使用threshold存储第一次初始化前的体积,这个值本来用来存放阀值的真实值).当前map不进行插入操作的话是一致没有容积的,等待第一次插入,会将容积扩展至threshold的大小,然后更新loadFactor为新的容积,更新threshold为容积的阀值倍.

```java
// 下面的代码运行都是输出0,意味着只要没有进行插入操作,HashMap是没有容积的.
// HashMap的容积只能来自扩容
HashMap hashMap = new HashMap();
System.out.println(hashMap.size());

HashMap hashMap = new HashMap(5);
System.out.println(hashMap.size());
```

### (2). hash函数

```java
//主要是将传入的参数key本身的hashCode与h无符号右移16位进行二进制异或运算得出一个新的hash值
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

​		这样做的理由是从数学角度上讲可以降低哈希冲突的发生

### (3). put()方法

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
// HashMap.put的具体实现
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 判定table不为空并且table长度不可为0,否则将从resize函数中获取
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 这样写法有点绕,其实这里就是通过索引获取table数组中的一个元素看是否为Nul
    // 这里是上层传入的hash函数的结果,需要再次进行取余
    if ((p = tab[i = (n - 1) & hash]) == null)
        // 若判断成立,则New一个Node出来赋给table中指定索引下的这个元素
        // 判断成功说明没有产生哈希冲突
        tab[i] = newNode(hash, key, value, null);
    else {  //若判断不成立
        Node<K,V> e; K k;
        // 对这个元素进行Hash和key值匹配
        // 链表头元素或者红黑树根节点元素覆盖
        // 要判断key相同,那么hash值必须相等,然后判断hashCode(),如果hashCode()相等,就不用进行equels判断,如果
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode) // 如果数组中德这个元素P是TreeNode类型
            // 判定成功则在红黑树中查找符合的条件的节点并返回此节点
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else { // 若以上条件均判断失败，则执行以下代码
            // 向Node单向链表中添加数据
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    // 若节点数大于等于8
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        // 转换为红黑树
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e; // p记录下一个节点
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold) // 判断是否需要扩容
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

1.  直接进行一次函数调用,将key的hash值传入,然后后面的操作都在该函数的调用中进行
    1.  获取Node数组对象和长度,如果对象为null或者长度为0,意味着没有对数组进行初始化,这是第一次插入操作.这时会调用resize()进行初始化容量,并获取到容量大小
    1.  判定传入的key的hash值对应的数组空间中(这里中间经历了一层函数调用,传入到这一层的就是hash值,直接进行取余也就是n-1的按位与计算得到下标)是否有值,也就是是否产生了哈希冲突.如果没有,直接将数据放入这个数组空间.
    1.  如果发生了哈希冲突先判断当前数组中存储的元素,也就是链表头或者红黑树根节点是否key和传入元素一致,如果是,覆盖掉.
        1.  对当前节点是树还是链表进行判断,如果是树调用函数(找到key匹配的位置,如果一直找到了null,就是找不到,那么在null处查找)进行查找,如果是链表,继续向下进行遍历
        1.  循环判断是否是链表尾,是否key一致,直到找到队尾或者key一致的元素,也就是当前元素应该插入的地方
    1.  如果应该进行的是覆盖操作,进行元素的替换,直接返回,不进行之后的容量判断操作
    1.  容量加一
    1.  判断扩容,调用resize()进行扩容
    1.  走到这里说明是在链表尾插入,进行响应的操作

### (4). get()方法

```java
// 这里直接调用getNode函数实现方法
public V get(Object key) {
    Node<K,V> e;
    // 经过hash函数运算 获取key的hash值
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    // 判定三个条件 table不为Null & table的长度大于0 & table指定的索引值不为Null
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        // 判定 匹配hash值 & 匹配key值 成功则返回 该值
        if (first.hash == hash && 
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        // 若 first节点的下一个节点不为Null
        if ((e = first.next) != null) {
            if (first instanceof TreeNode) // 若first的类型为TreeNode 红黑树
                // 通过红黑树查找匹配值 并返回
                return ((TreeNode<K,V>)first).getTreeNode(hash, key); 
            // 若上面判定不成功 则认为下一个节点为单向链表,通过循环匹配值
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    // 匹配成功后返回该值
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}fanhui
```

1.  先进行一次函数调用,传入hash值,返回node对象,为了防止空指针,用三目运算符处理(一层判断)

    1.  判断三个条件:node数组不为null,数组长度大于0(即数组已经被初始化),和下表处有元素存在
        1.  如果key和数组该空间处的元素匹配,那么直接返回
        1.  如果不匹配,判断是否有后继
            1.  有后继的情况下判断是树还是链表
            1.  如果是树,调用方法进行遍历.如果是链表,在后续的逻辑中进行遍历
            1.  循环判断key是否匹配,匹配成功后返回
                1.  当循环到链表尾时,说明链表内部没有能够进行匹配的key,那么就是该key没有在HashMap中插入过
    1.  返回null

    ### (5). 扩容

```java
// 重新设置table大小/扩容 并返回扩容的Node数组即HashMap的最新数据
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table; // table赋予oldTab作为扩充前的table数据
    // 原来数组长度
    int oldCap = (oldTab == null) ? 0 : oldTab.length; 
    // 原来数组的下一次拓展长度
    int oldThr = threshold;
    // 新的.....
    int newCap, newThr = 0;
    // 正常的扩容状态
    if (oldCap > 0) {
        // 判定数组是否已达到极限大小，若判定成功将不再扩容，直接将老表返回
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 若新表大小(oldCap*2)小于数组极限大小 并且 老表大于等于数组初始化大小
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            // 旧数组大小oldThr 经二进制运算向左位移1个位置 即 oldThr*2当作新数组的大小
            // 即数组的新大小为原来二倍
            newThr = oldThr << 1;
    }
    // 本次扩容中原数组长度为0,即当前为初始化操作,判断老表中下次扩容大小oldThr大于0
    // 这里对应的就是带参数的构造的初始化
    else if (oldThr > 0)
        newCap = oldThr;  // 将oldThr赋予控制新表大小的newCap
    else { // 若其他情况(无参构造的初始化,两个属性都为0,为初始化,是编译期自动加上的0)则将获取初始默认大小
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    // 前面的逻辑中没有对新表的扩容进行设置
    // 即当前为带参数构造的初始化操作
    if (newThr == 0) {  
        float ft = (float)newCap * loadFactor;  // 通过新表大小*负载因子获取
        // 如果新表的更新大小超过了极限,并且当前计算的阀值小于极限,那么将其设置为极限
        // 意味着不需要进行在进行扩容了
        // 注意float和int转换时的范围,不能超过极限值
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    // 下次扩容的大小的更新
    threshold = newThr; 
    @SuppressWarnings({"rawtypes","unchecked"})
    // 申请新容量大小的内存
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab; // 将当前表赋予table
    if (oldTab != null) { // 若oldTab中有值需要通过循环将oldTab中的值保存到新表中
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {// 获取老表中第j个元素 赋予e
                oldTab[j] = null; // 并将老表中的元素数据置Null
                if (e.next == null) // 若此判定成立 则代表e的下面没有节点了
                    newTab[e.hash & (newCap - 1)] = e; // 将e直接存于新表的指定位置
                else if (e instanceof TreeNode)  // 若e是TreeNode类型
                    // 分割树，将新表和旧表分割成两个树，并判断索引处节点的长度是否需要转换成红黑树放入新表存储
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null; // 存储与旧索引的相同的节点
                    Node<K,V> hiHead = null, hiTail = null; // 存储与新索引相同的节点
                    Node<K,V> next;
                    // 通过Do循环 获取新旧索引的节点
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    // 通过判定将旧数据和新数据存储到新表指定的位置
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    //返回新表
    return newTab;
}
```

1.  判断原数组的大小
1.  如果大于0,证明已经进行过了初始化
    1.  和极限大小进行判断,如果试图更新到极限大小之外的容量,会直接返回原容量
    1.  如果试图更新的容量是合法的,设置新的容量

## 2. ArrayList

### (1). 常量和构造方法

```java
// 默认初始化长度
private static final int DEFAULT_CAPACITY = 10;

// 用户显式指定list为空时使用的数组
private static final Object[] EMPTY_ELEMENTDATA = {};

// 当使用默认无参构造器创建的空list数组，在扩容时会考虑使用默认的扩容方案DEFAULT_CAPACITY
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

// 数据数组
transient Object[] elementData;

// list的长度
private int size;

// 构造一个空列表。默认的初始容量grow时为10并不是在初始时就创建，而是在需要空间时初始化
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

// 构造一个使用指定 collection 并按其元素迭代的顺序排列的列表。 
public ArrayList(Collection<? extends E> c) {
    // 集合c元素的object[]数组(不能确保一定为实际类型为object类型)
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
        // c.toArray might (incorrectly) not return Object[] (see 6260652)  // 如果实际类型不是Object就复制到新Object数组中
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    } 
    // 传入是一个空的collection
    else {
        // replace with empty array.
        this.elementData = EMPTY_ELEMENTDATA;
    }
}

// 构造一个具有指定初始容量并立即初始化分配空间的空列表。 
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        // 这里证明了ArrayList不像HashMap,如果使用带容量的构造,对象一生成,就有了容量
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}
```

### (2). 扩容

```java
// 如果指定的容量比默认的方案大，就设置为指定容量扩容。
public void ensureCapacity(int minCapacity) {
    // 默认空的list实例最小扩容为10，否则就为0。elementData设置为非DEFAULTCAPACITY_EMPTY_ELEMENTDATA表示用户指定了容量或collection构造arrayList
    int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
        ? 0 : DEFAULT_CAPACITY;

    if (minCapacity > minExpand) {
        ensureExplicitCapacity(minCapacity);
    }
}
// 在指定容量和默认容量间选择更大的扩容容量
private void ensureCapacityInternal(int minCapacity) {
    // 如果是默认大小的list实例，最小容量应该比默认容量10要大，否则使用默认容量
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    ensureExplicitCapacity(minCapacity);
}

// 如果指定的容量大于数组长度elementData.length就调用grow()扩大数组
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    // overflow-conscious code
    // 如果期望容量大于当前数组容量就扩大数组
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

// 扩大数组容量为1.5倍旧容量或更大的指定容量
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    // 新容量为旧容量的1.5倍
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    // 如果指定容量大于1.5倍旧容量就取指定容量
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    // 如果新容量超过数组最大容量
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}

// 如果指定容量超过2^31就抛出异常，否则将容量设置为Integer.MAX_VALUE
private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
    MAX_ARRAY_SIZE;
}
```

