package com.licslan.setmap;
import com.licslan.tree.bst.BST;

/**
 * 利用二分搜索树实现set  集中元素不可重复思想  但是元素需要有可比性
 * */


public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet(){
        bst=new BST<>();
    }
    @Override
    public int getSize(){
        return bst.size();
    }
    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }
    @Override
    public void  add(E e){
        bst.add(e);
    }
    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }
    @Override
    public void remove(E e){
        bst.remove(e);
    }

}
