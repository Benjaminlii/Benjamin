package com.Benjamin.umlWork;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MyList
 * Package:com.Benjamin.umlWork
 * <p>
 * Description:
 * 自定义的集合类
 *
 * @author: Benjamin
 * @date: 19-12-12 下午4:14
 */
public class MyList<E> {
    private List<E> list = new ArrayList<>();

    public int getSize(){
        return list.size();
    }

    public E get(int sub){
        return list.get(sub);
    }

    public boolean add(E obj){
        return list.add(obj);
    }

    public Iterator getIterator(){
        return new MyInterator(this);
    }

    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        Iterator iterator = myList.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
