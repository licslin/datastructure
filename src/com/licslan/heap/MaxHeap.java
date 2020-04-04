package com.licslan.heap;

/**
 * 最大堆 MaxHeap 实现
 * */
public class MaxHeap<E extends Comparable<E>> {
    private ArrayDynamic<E> data;
    public MaxHeap(int capacity){
        data=new ArrayDynamic<>(capacity);
    }

    public MaxHeap(){
        data = new ArrayDynamic<>();
    }
    //返回堆中的元素个数
    public int size(){
        return data.getSize();
    }
    //堆是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }
    //返回完全二叉树数组表示 一个索引所表示的元素父节点的索引
    private int parent(int index){
        if(index==0)throw new IllegalArgumentException("index-0 doesn`t have parent");
        return (index-1)/2;
    }
    //返回完全二叉树数组  一个索引所表示的元素左孩子节点索引
    private int leftChild(int index){return index*2+1;}
    //返回完全二叉树数组  一个索引所表示的元素右孩子节点索引
    private int rightChild(int index){return index*2+2;}

    //堆中元素是使用数组组成的  sift up  上浮  形成堆的性质
    // 0    1   2  3  4  5  6  7  8  9 10
    // 62   41 30 28 16 22 13 19 17 15 -

    //像堆中添加元素
    public void add(E e){
        data.addLast(e);
        //添加完成还不够  还需要维护堆的性质
        siftUp(data.getSize()-1);
    }

    //上浮操作
    private void siftUp(int i) {
        //如果不满足堆的性质  就还需要对比
        while (i>0&&data.getIndex(parent(i)).compareTo(data.getIndex(i))<0){
            //不满足堆的性质就交换一下位置
            data.swap(i,parent(i));
            //如果不满足堆的性质  就还需要对比
            i=parent(i);
        }
    }

    //找到堆中最大的元素
    public E findMax(){
        if(data.getSize()==0)throw new IllegalArgumentException("can not findMax when heap is empty!");
        return data.getIndex(0);
    }
    //取出堆中最大的元素
    public E extractMax(){
        E ret=findMax();
        //交换堆中最大的元素和最后一个元素的值
        data.swap(0,data.getSize()-1);
        //将最后一个元素移除掉
        data.removeLast();
        //sift down 下沉
        siftDown(0);
        return ret;
    }

    private void siftDown(int i) {
        //如果左孩子索引比堆元素数量小
        while (leftChild(i)<data.getSize()){
            //说明左孩子是存在的
            int j= leftChild(i);
            //如果右孩子也存在 并且右孩子大于左孩子 （右边>左边）
            if(j+1<data.getSize()&&
                    data.getIndex(j+1).compareTo(data.getIndex(j))>0){
                //右孩子值赋值给j
                j=rightChild(i);
            }
            //data[j]是leftChild & rightChild 中的最大值

            //如果当前元素的值大于等于右孩子(左右孩子最大的一个值)  不违反堆的性质 就不交换了
            if(data.getIndex(i).compareTo(data.getIndex(j))>=0)break;

            //否则就下沉交换一下
            data.swap(i,j);
            //将j赋值给i  进行新一轮循环来比较刚才上述的逻辑操作
            i=j;
        }
    }


    // add  &  extractMax 的时间复杂度都是O(logn)
    // 二叉树高度的这个级别  堆是一颗完全二叉树 永远不会退化为链表的线性结构

    //replace  取出最大元素后  放入一个新元素
    // 实现方案一：  先exarctMax 再 add 两次O(logn)
    // 实现方案二：  可以直接将堆顶元素替换以后siftDown 一次O(logn)

    //取出堆中最大元素  并且替换成元素e
    public E replace(E e){
        E ret=findMax();
        //替换
        data.set(0,e);
        //下沉
        siftDown(0);
        return ret;
    }

    //heapify  将任意数组整理成堆的形状
    // 实现方案一：  将数组中N个元添加到我们实现的heap结构的对象中就可以了  O(logn)
    // 实现方案二：  将当前数组看成是一颗完全二叉树 从最后一个非叶子节点不断向前siftDown就行了  O(n)

    //heapify
    public MaxHeap(E[] arr){
        data=new ArrayDynamic<>(arr);
        //将当前数组看成是一颗完全二叉树 从最后一个非叶子节点不断向前siftDown就行了  O(n)
        for(int i=parent(arr.length-1);i>=0;i--)
            siftDown(i);
    }



}
