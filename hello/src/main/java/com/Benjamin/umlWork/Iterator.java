package com.Benjamin.umlWork;

/**
 * ClassName:Iterator
 * Package:com.Benjamin.umlWork
 * <p>
 * Description:
 * 迭代器的接口
 *
 * @author: Benjamin
 * @date: 19-12-12 下午4:09
 */
public interface Iterator<E> {

    /**
     * 当前迭代器是否未迭代完
     * @return 是否有下一个值
     */
    boolean hasNext();

    /**
     * 得到迭代器中的下一个元素,并将游标移动
     * @return 下一个元素
     */
    E next();
}
