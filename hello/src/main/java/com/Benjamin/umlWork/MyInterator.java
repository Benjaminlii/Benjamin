package com.Benjamin.umlWork;

/**
 * ClassName:MyInterator
 * Package:com.Benjamin.umlWork
 * <p>
 * Description:
 * 自定义的迭代器
 *
 * @author: Benjamin
 * @date: 19-12-12 下午4:15
 */
public class MyInterator<E> implements Iterator {
    private int size;
    private int sub = 0;
    private MyList<E> list;

    public MyInterator(MyList<E> list) {
        this.list = list;
        this.size = list.getSize();
    }

    @Override
    public boolean hasNext() {
        return sub < size;
    }

    @Override
    public E next() {
        if (this.hasNext()) {
            return list.get(sub++);
        }else{
            return null;
        }
    }
}
