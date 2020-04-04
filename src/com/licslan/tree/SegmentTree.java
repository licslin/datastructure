package com.licslan.tree;
/**
 * 线段树
 * */
public class SegmentTree<E>{


    //线段树以数组形式表达
    private E[] tree;
    private E[] data;
    public SegmentTree(E[] arr){
        data=(E[])new Object[arr.length];
        for(int i=0;i<arr.length;i++)
            data[i]=arr[i];
        //创建线段树所需要开辟的空间 是要4*N的空间  可能会浪费一部分空间   有的叶子节点null
        tree=(E[])new Object[4*arr.length];
    }
    public E get(int index){
        if(index<0||index>=data.length)throw new IllegalArgumentException("index is Illegal!");
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    //返回完全二叉树的数组表示中 一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index+1;
    }
    //返回完全二叉树的数组表示中 一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index+2;
    }
}
