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
    this(initialCapacity, DEFAULT_LOAD_FACTOR);DEFAULT_LOAD_FACTOR
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

​		制定了容量的话,会直接根据设置的值获取下一个2的幂数,作为下一次扩容的目标容积(使用threshold存储第一次初始化前的体积,这个值本来用来存放阀值的真实值).==当前map不进行插入操作的话是一直没有容积的==,等待第一次插入,会将容积扩展至threshold的大小,然后更新loadFactor为新的容积,更新threshold为容积的阀值倍.

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
        else if (p instanceof TreeNode) // 如果数组中的这个元素P是TreeNode类型
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
    1.  走到这里说明是在链表尾插入,进行相应的操作

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
}
```

1.  先进行一次函数调用,传入hash值,返回node对象,为了防止空指针,用三目运算符处理(一层判断)

    1.  判断三个条件:node数组不为null,数组长度大于0(即数组已经被初始化),和下标处有元素存在
        1.  如果key和数组该空间处的元素匹配,那么直接返回
        1.  如果不匹配,判断是否有后继
            1.  有后继的情况下判断是树还是链表
            1.  如果是树,调用方法进行遍历.如果是链表,在后续的逻辑中进行遍历
            1.  循环判断key是否匹配,匹配成功后返回
                1.  当循环到链表尾时,说明链表内部没有能够进行匹配的key,那么就是该key没有在HashMap中插入过
    1.  返回null

### (5). resize()方法

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
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAUDEFAULT_LOAD_FACTORLT_INITIAL_CAPACITY);
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
    // 原表不为空的话进行数据的迁移,原表为空则直接使用空的新表
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            // 当前遍历到的数组不为空,空位不需要迁移
            if ((e = oldTab[j]) != null) {
                // 将原数组中的元素移除到刚刚的e中
                // 再判断三种情况
                oldTab[j] = null;
                
                // 第一种情况,单个元素
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                
                // 第二种情况,该元素为红黑树的根节点
                else if (e instanceof TreeNode)
                    // 分割树，把这颗树打散成两颗树插入到新桶中去
                    // 内部的树实现的
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                
                // 剩下的一种情况,该节点是链表的头节点
                else {
                    // 构造两条链表
                    // 原链表中有一部分需要向后移动n位(n是扩容的容积变化量,也是扩容前的容积)
				// 如果n = 2^x
                    // 那么一个数组元素中的链表上的元素的hash值后x位全部都是一样的
                    // 因为从hash值到数组下表是通过对n取余(也就是对n-1进行按位与)获得的
                    // 所以后n位是一致的
                    // 那么倒数的第x位只有0和1之分
                    // 如果为0,那么以为这它对(n+1)和对n的取余是相同的
                    // 为1相反
                    // 所以倒数第x+1位如果为0,扩容后不需要移动
                    // 为1,向后移动x位
                    // 这里就是找出这种为1的元素,将其抽出成另一个链表,放到当前下标+n的数组位置上
                    Node<K,V> loHead = null, loTail = null;// 0 --> low
                    Node<K,V> hiHead = null, hiTail = null;// 1 --> high
                    Node<K,V> next;
                    // 通过Do循环 获取新旧索引的节点
                    do {
                        next = e.next;
                        // 提取倒数第n+1位进行判断
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                // 第一次插入
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
                    // 尾节点next指向null
                    // 将两个链表放入响应位置
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
    return newTab;
}
```

1.  判断原数组的大小
1.  如果大于0,证明已经进行过了初始化
    1.  和极限大小进行判断,如果试图更新到极限大小之外的容量,会直接返回原容量
    1.  如果试图更新的容量是合法的,设置新的容量

## 2. ArrayList

​		ArrayList使用数组实现

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

// 构造一个空列表。默认的初始容量grow时为10并不是在初始时就创建，而是dao在需要空间时初始化
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

### (2). add()方法

```java
// 在尾部添加一个元素
public boolean add(E e) {
    // 判断是否需要扩容,如果需要则进行扩容
    ensureCapacityInternal(size + 1);
    // 在第一个空位上放置元素
    elementData[size++] = e;
    return true;
}

// 指定位置添加元素
public void add(int index, E element) {
    // 检查插入位置是否合理
    rangeCheckForAdd(index);
    // 判断是否进行扩容......
    ensureCapacityInternal(size + 1);
    // 将指定位置后面的元素向后移动一个格子
    System.arraycopy(elementData, index, elementData, index + 1, size - index);
    elementData[index] = element;
    size++;
}
private void rangeCheckForAdd(int index) {
    // 插入的位置肯定不能大于size或者小于0
    if (index > size || index < 0)
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
}
```

### (3). get()方法

```java
public E get(int index) {
    // 检验索引是否合法
    rangeCheck(index);
    return elementData(index);
}
// ArrayList中元素全部存储在前面,中间不会出现空闲元素
// 所以检查的时候判断下标和size的大小即可
private void rangeCheck(int index) {
    if (index >= size)
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
}
```

### (4). remove()方法

```java
// remove函数用户移除指定下标的元素，此时会把指定下标到数组末尾的元素向前移动一个单位，并且会把数组最后一个元素设置为null
public E remove(int index) {
    rangeCheck(index);//检查index的合理性

    modCount++;//这个作用很多，比如用来检测快速失败的一种标志。
    E oldValue = elementData(index);//通过索引直接找到该元素

    int numMoved = size - index - 1;//计算要移动的数组长度
    if (numMoved > 0)
        //这个方法也已经解释过了，就是用来移动元素的。
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    //将--size上的位置赋值为null，让gc(垃圾回收机制)更快的回收它。
    elementData[--size] = null; // clear to let GC do its work
    //返回删除的元素。
    return oldValue;
}
```

### (5). 扩容

```java
// 在使用默认List实例的情况下,保证至少扩容到默认初始化长度10
private void ensureCapacityInternal(int minCapacity) {
    // 如果是默认大小的list实例，最小容量应该比默认容量10要大，否则使用默认容量
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    ensureExplicitCapacity(minCapacity);
}

// ArrayList元素数+1,判断是否需要扩容
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    // overflow-conscious code
    // 如果期望容量大于当前数组容量就扩大数组
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

// 对扩容大小进行判断和选择
// 然后创建相应大小的数组,并使用Arrays.copyOf进行元素的移动
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    // 新容量为旧容量的1.5倍
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    // 至少应该扩容到原容量的1.5倍
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    // 如果新数组大小超出极限,那么使用极限大小作为新数组长度
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

## 3. HashSet

​		内部使用HashMap实现,使用HashMap的key存储元素,所以不可能出现重复(value为一个静态常量Object).

## 4. Hashtable

​		内部与HashMap实现类似,略有不同.

​		t就是小写的,在Java驼峰命名法出现之前,Hashtable就存在了.

### (1). 与HashMap区别

|          | Hashtable                                                    | HashMap                                                      |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| null值   | 不允许null值                                                 | 可以有一个null值的key(null的hash结果为0)                     |
| 线程安全 | 线程安全(synchronized实现)                                   | 线程不安全(扩容时体现)                                       |
| 初始大小 | 11或者传入参数值(构造方法执行完就会有空间)                   | 默认16或者参数的下一个2次幂(要拥有空间必须要进行一次put操作) |
| 扩容     | 二倍加一扩容                                                 | 二倍扩容                                                     |
| 扩容机制 | 全部元素再次hash一遍                                         | 判断key的hash结果的倒数第n位的值为0或者1                     |
| hash函数 | (hash & 0x7FFFFFFF) % tab.length (与操作去掉符号位,直接取余) | 与高16位按位异或后取余                                       |
| 继承关系 | 继承dictionary                                               | 继承abstractmap                                              |
|          |                                                              |                                                              |

>   关于扩容机制:
>
>   创建时，如果给定了容量初始值，那么Hashtable会直接使用你给定的大小，而HashMap会将其扩充为2的幂次方大小。也就是说Hashtable会尽量使用素数、奇数。而HashMap则总是使用2的幂作为哈希表的大小。
>
>   之所以会有这样的不同，是因为Hashtable和HashMap设计时的侧重点不同。Hashtable的侧重点是哈希的结果更加均匀，使得哈希冲突减少。当哈希表的大小为素数时，简单的取模哈希的结果会更加均匀。而HashMap则更加关注hash的计算效率问题。在取模计算时，如果模数是2的幂，那么我们可以直接使用位运算来得到结果，效率要大大高于做除法。HashMap为了加快hash的速度，将哈希表的大小固定为了2的幂。当然这引入了哈希分布不均匀的问题，所以HashMap为解决这问题，又对hash算法做了一些改动。这从而导致了Hashtable和HashMap的计算hash值的方法不同

### (2). 构造方法

```java
// 默认初始化大小为11,负载因子为0.75
public Hashtable() {
    this(11, 0.75f);
}
public Hashtable(int initialCapacity) {
    this(initialCapacity, 0.75f);
}
public Hashtable(int initialCapacity, float loadFactor) {
    // 对初始容量进行判断,不能为负数
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
    // 加载因子不能小于等于零,且不能为空
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal Load: "+loadFactor);
    // 至少要有一个大小的空间
    if (initialCapacity==0)
        initialCapacity = 1;
    this.loadFactor = loadFactor;
    // 在初始化时就会拥有容量
    table = new Entry<?,?>[initialCapacity];
    threshold = (int)Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
}
```

### (3). put()方法

```java
// 线程安全
public synchronized V put(K key, V value) {
    // 不允许插入value为null的元素
    if (value == null) {
        throw new NullPointerException();
    }

    Entry<?,?> tab[] = table;
    int hash = key.hashCode();
    // hash函数为 去掉符号位取余
    int index = (hash & 0x7FFFFFFF) % tab.length;
    @SuppressWarnings("unchecked")
    Entry<K,V> entry = (Entry<K,V>)tab[index];
    // 循环判断能否在数组中对应的链表中找到覆盖元素
    for(; entry != null ; entry = entry.next) {
        if ((entry.hash == hash) && entry.key.equals(key)) {
            V old = entry.value;
            entry.value = value;
            return old;
        }
    }
    // 找不到覆盖的元素,那么插入到链表前,这里是头插法
    addEntry(hash, key, value, index);
    return null;
}
private void addEntry(int hash, K key, V value, int index) {
    modCount++;

    Entry<?,?> tab[] = table;
    if (count >= threshold) {
        // 如果容量大于阈值,扩容
        rehash();
		// 扩容后重新hash
        tab = table;
        hash = key.hashCode();
        index = (hash & 0x7FFFFFFF) % tab.length;
    }
	
    // 头插法
    @SuppressWarnings("unchecked")
    Entry<K,V> e = (Entry<K,V>) tab[index];
    // 将链表e传入,链接到新节点的后继,然后放入数组中
    tab[index] = new Entry<>(hash, key, value, e);
    count++;
}
```

### (4). rehash()方法

```java
// 扩容方法
protected void rehash() {
    int oldCapacity = table.length;
    Entry<?,?>[] oldMap = table;

    // 二倍加一扩容
    int newCapacity = (oldCapacity << 1) + 1;
    // 对扩容后的大小进行合法性判断
    if (newCapacity - MAX_ARRAY_SIZE > 0) {
        if (oldCapacity == MAX_ARRAY_SIZE)
            return;
        newCapacity = MAX_ARRAY_SIZE;
    }
    // 分配新空间
    Entry<?,?>[] newMap = new Entry<?,?>[newCapacity];

    modCount++;
    // 计算新的阈值,不能超过极限大小
    threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
    table = newMap;
	// 放置原数组中的元素
    // 遍历原数组中的单元格
    for (int i = oldCapacity ; i-- > 0 ;) {
        // 遍历每一个单元格的链表
        for (Entry<K,V> old = (Entry<K,V>)oldMap[i] ; old != null ; ) {
            Entry<K,V> e = old;
            old = old.next;
			// 重新hash,插入元素到新的位置(还是头插法)
            int index = (e.hash & 0x7FFFFFFF) % newCapacity;
            e.next = (Entry<K,V>)newMap[index];
            newMap[index] = e;
        }
    }
}
```

### (5). remove()方法

```java
public synchronized V remove(Object key) {
    Entry<?,?> tab[] = table;
    // 通过hash函数计算出下标
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    @SuppressWarnings("unchecked")
    Entry<K,V> e = (Entry<K,V>)tab[index];
    // 遍历这个下标对应的链表
    for(Entry<K,V> prev = null ; e != null ; prev = e, e = e.next) {
        // 寻找匹配key的元素
        if ((e.hash == hash) && e.key.equals(key)) {
            modCount++;
            // 判断前继节点,也就是匹配的这个元素不是链表头结点
            // 删除节点
            if (prev != null) {
                prev.next = e.next;
            } else {
                tab[index] = e.next;
            }
            // 返回原值
            count--;
            V oldValue = e.value;
            e.value = null;
            return oldValue;
        }
    }
    return null;
}
```

### (6). get()方法

```java
public synchronized V get(Object key) {
    Entry<?,?> tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
	// 遍历对应下标处的链表
    for (Entry<?,?> e = tab[index] ; e != null ; e = e.next) {
        if ((e.hash == hash) && e.key.equals(key)) {
            return (V)e.value;
        }
    }
    return null;
}
```

## 5. 各种集合类的总结

### (1). HashMap

1.  默认容量为16,或者传入参数的下一个2次幂大小(目的是保证容量为2次幂大小,因为内部散列方法和resize都是利用了这个特性进行的)
1.  底层为数组+链表组成,链地址法解决哈希冲突
1.  扩容时对于链表,按照key.hash & oldSize的结果进行判断进入那一条链表(一前一后)
1.  线程不安全(put时造成覆盖,rehash时出现循环链表)
    1.  关于rehash,JDK7中会出现这样的问题,在JDK8中改为尾插法,这个问题得到了改善,但还是会出现数据覆盖的问题
    1.  JDK7中,使用的是头插法,那么就有e.next = newTable[i]的操作,如果e在A线程中标记为某个元素,而在B线程中,已经经历了多次头插法,这个元素已经不是头结点了.那么切换回A线程时,执行这一条语句就会将链表中间某个元素的next节点设置为链表头结点,就构成了循环链表
    1.  [漫画：高并发下的HashMap](https://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652079766&idx=1&sn=879783e0b0ebf11bf1a5767933d4e61f&chksm=f1748d73c6030465fe6b9b3fa7fc816d4704c91bfe46cb287aefccee459153d3287172d91d23&scene=21#wechat_redirect)
1.  哈希方法:key的hashcode和其无符号右移16为的结果进行按位异或
1.  散列方法:对数组长度-1进行按位与(length总为2^n^,那么length-1换为二进制表示就是0b00..111111111这样的,按位与就可以达到快速取余的效果,另一方面这样可以最大限度的保证散列的更均匀,因为hash值的后n位都参与了散列)
1.  链表的长度大于8时会进行调用树化的方法,转化为红黑树(在树化的方法内部,会判断数组长度是否小于64,如果满足,那么转为扩容,不进行树化),红黑树的元素数小于6时会退化为链表.(如果这两个值设置为一样的,那么有可能会碰见同一个元素的重复插入删除,就有可能频繁的进行树化和逆树化.并且根据泊松分布,链表长度超过8的可能性很小,所以选择8作为判定条件,treenodes的大小大约是常规节点的两倍，因此我们仅在容器包含足够的节点以保证使用时才使用它们)
1.  元素总数大于==数组长度==和负载因子的乘积会进行扩容
1.  可以有一个null的key,位于数组的0号位置(hash方法中对null值进行了直接返回0的设置)
1.  JDK1.7和JDK1.8中的区别:JDK1.7中链表使用头插法,可能会在扩容时造成循环链表,JDK8中改为尾插法,解决了这个问题.JDK1.8中的HashMap增加了红黑树,提升了性能,尾插法也是考虑到这一点.

### (2). Hashtable

1.  名字中的t是小写
1.  线程安全,内部使用sync保证,效率很低
1.  初始容量为11, 2n+1扩容,这样保证容量都是奇数,并且尽可能是素数.从而保证足够散列,HashMap追求计算的速度,所以使用位运算进行散列,为了避免哈希不均匀,又加入了二次哈希(第一次为去hashcode,第二次为hash()方法)
1.  没有树化操作
1.  哈希方法:去掉符号位,对数组长度进行取余
1.  扩容时因为没有HashMap的特性,所以进行的是全部元素rehash
1.  不允许null值,会抛异常

### (3). ArrayList

1.  初始长度10(长度的获得和HashMap一样,都是第一次add时进行扩容获得初始长度), 1.5倍扩容.扩容时先判断容量(大于10,小于极限值),然后申请新容量大小的数组后复制数据过去
1.  底层使用数组存储数据
1.  内部元素是整齐的,每次中间插入或者删除时都会整体移动元素
1.  扩容时创建新的数组,然后将原数据整体复制过去
1.  内部允许存储null,在indexOf(Object obj)方法中,先对obj进行空值的判断,如果不是空值,后面遍历数组,使用equals方法判断相等,否则直接使用\=\=判断是否为null.防止空指针
1.  线程不安全

### (4). LinkedList

1.  使用链表实现的list

1.  会保存头结点和尾节点,内部是双向链表.

1.  随机读取效率底下,get(int index)方法判断index的大小,然后选择从头或者尾进行遍历

1.  插入效率相对较高,省去数组实现时元素的移动.
1.  不需要扩容

### (5). ConcurrentHashMap

[HashMap？ConcurrentHashMap？相信看完这篇没人能难住你！](https://blog.csdn.net/weixin_44460333/article/details/86770169)

[《吊打面试官》系列-ConcurrentHashMap & HashTable](https://www.jianshu.com/p/3a2333b2f960)

#### 1). JDK1.7中:

​		线程安全的HashMap,内部使用锁分段技术,将内部数据分为一段一段,每一段配一把锁,当其中一个端被访问时,其他段也能被访问.

​		一个ConcurrentHashMap内部维护了多个segment(默认为16,可以修改,但不可以动态扩容),每一个segment都是ReentrantLock的子类,所以有加锁的功能.每一个segment内部又维护了一个HashEntry数组(这个可以扩容).也就是说,一个ConcurrentHashMap将哈希表分为多段,每一段都放在锁对象中.每一个segment都记录了当前segment的元素数和被更新次数.

​		在进行put操作时,先根据hash值确定要放入哪一个segment,获取这个segment的锁(如果没有成功获取到则进行自旋重新竞争,自旋达到一定次数,阻塞当前线程,直到这个锁被释放),然后在这个segment中再根据hash值确定桶的位置(如果这个segment没有被定义,那么先进行初始化).

​		get方法类似,也是先确定segment,在确定元素在内部的位置,然后在进行查找.(内部实现和HashMap是相似的,也要经历链表查找等过程,不会进行树化),这里也是分读写锁的,读锁不和任何锁互斥.map都是单个读取,所以写操作不会影响读操作的结果.

​		size()方法获取元素数,对每一个segment的元素总数和被修改次数进行统计,然后在进行一次这个过程,如果被修改次数没有发生变化,那么就证明在两次统计中,没有发生元素的增加和删除(由于被修改次数是在进行增加或者删除操作之前就会更新的,所以更能保证实时性).这个过程重复一定次数都没能成功判定的话,会对所有segment进行加锁,然后进行统计.

​		锁使用的是segment继承于ReentrantLock的锁,内部使用AQS.

#### 2). JDK1.8中:

​		在JDK1.7中已经满足n(默认16)的并发量,但当数据量上去之后,并发性能还是不够好.就抛弃了分段锁的设计.转而使用CAS+sync的加锁方式

​		put操作中,如果桶中不需要初始化,不是桶中无元素(这时使用CAS进行原子性的插入,竞争失败的线程自旋,进入其他逻辑.这里CAS,考虑一下[经典的ABA问题与解决方法](https://blog.csdn.net/qq_42576040/article/details/88240595))或者扩容状态(也就是==桶中存储的是node==,这个node包括很多种,可以是单个元素,可以使链表,也可以是红黑树,这个红黑树是和JDK1.8中的红黑树一起引入的,所以JDK1.7中的ConcurrentHashMap是没有红黑树的),==加sync锁进行元素的插入==

​		get操作还是不加锁

​		由于JDK6对synchronize进行了优化,所以性能上可以保证

### (6). Vactor

​		类似于ArrayList,但是Vactor是线程安全的

​		Vactor二倍扩容

​		Vactor性能差一些,因为内部使用了synchronized方法

### (7). TreeMap

​		带顺序的Map,内部使用红黑树实现

​		要保证顺序就使得程序运行效率贬低了,树中的插入,保持平衡等因素

### (8). Collections.synchronizedMap(Map)创建线程安全的map集合

​		这也是实现线程安全的Map的一种方式,传入一个普通Map,返回一个线程安全的Map.

​		返回的线程安全的Map中的方法实际上是调用传入的这个Map的各个方法,每个方法内部都使用sync代码块进行了同步.

​		mutex是锁对象,可以由构造方法传入,默认为this

## 6. 集合的优点

​		复用性

​		降低维护成本

## 7. 集合的层次结构

​		Collection		map

​		set和list

## 8. 迭代器

​		HashMap 中的 Iterator 迭代器是 fail-fast 的，而 Hashtable 的 Enumerator 不是 fail-fast 的。

​		所以，当其他线程改变了HashMap 的结构，如：增加、删除元素，将会抛出ConcurrentModificationException 异常，而 Hashtable 则不会。

>   fail-fast是啥？



**快速失败（fail—fast）**是java集合中的一种机制， 在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的内容进行了修改（增加、删除、修改），则会抛出Concurrent Modification Exception。

>   他的原理是啥？

迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCount 变量。

集合在被遍历期间如果内容发生变化，就会改变modCount的值。

每当迭代器使用hashNext()/next()遍历下一个元素之前，都会检测modCount变量是否为expectedmodCount值，是的话就返回遍历；否则抛出异常，终止遍历。