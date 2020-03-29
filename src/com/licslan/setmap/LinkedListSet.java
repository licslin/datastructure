package com.licslan.setmap;

import com.licslan.linkedlist.LinkedListNew;

/**
 * 利用链表实现set  其中的元素不需要有可比性
 * */
public class LinkedListSet<E> implements Set<E>{

    private LinkedListNew<E> list;
    public LinkedListSet(){
        list=new LinkedListNew<>();
    }
    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }
    @Override
    public int getSize(){
        return list.getSize();
    }
    @Override
    public boolean contains(E e){
        return list.contains(e);
    }
    @Override
    public void add(E e){
        //确保不能添加重新的方法
        if(!list.contains(e)) list.addFirst(e);
    }
    @Override
    public void remove(E e){
        list.removeElement(e);
    }


}
