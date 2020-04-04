package com.licslan.tree;
/**
 * 线段树
 * */
public class SegmentTree<E>{


    //线段树以数组形式表达
    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger=merger;
        data=(E[])new Object[arr.length];
        for(int i=0;i<arr.length;i++)
            data[i]=arr[i];
        //创建线段树所需要开辟的空间 是要4*N的空间  可能会浪费一部分空间   有的叶子节点null
        tree=(E[])new Object[4*arr.length];

        //创建线段树
        buildSegmentTree(0,0,data.length-1);
    }

    //在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if(l==r){
            tree[treeIndex]=data[l];
            return;
        }

        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);

        int mid = l+(r-l)/2;

        //递归创建左右子树
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        //融合左右子树的要做的事情
        tree[treeIndex]=merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
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

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    //设计一个接口  左右子树可以根据自己的实际业务情况融合要做的事情
    //不仅仅是加法  或者求最大值  最小值
    public interface Merger<E>{
        E merge(E a,E b);
    }

    public static void main(String[] args) {
        //Integer[] nums = {-2,0,3,0,-5,2,-11};
        Integer[] nums = {-2,0,3,-5,2,-1};
        //匿名函数
        /*SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                //这里主要是计算加法  用户可以自己实现要业务融合即可
                return a+b;
            }
        });*/

        //下面使用lamda
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> {
            //这里主要是计算加法  用户可以自己实现要业务融合即可
            return a+b;
        });

        System.out.println(segmentTree);
    }
}
