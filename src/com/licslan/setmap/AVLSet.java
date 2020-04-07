package com.licslan.setmap;
/**
 * Set基于AVL底层实现
 * */
public class AVLSet<E extends Comparable<E>> implements Set<E> {

    //私有AVL对象 所有set底层操作都有 完全基于AVL 是不是很爽
    private AVLTree<E, Object> avl;

    public AVLSet(){
        avl = new AVLTree<>();
    }

    @Override
    public int getSize(){
        return avl.getSize();
    }

    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }

    @Override
    public void add(E e){
        avl.add(e, null);
    }

    @Override
    public boolean contains(E e){
        return avl.contains(e);
    }

    @Override
    public void remove(E e){
        avl.remove(e);
    }
}