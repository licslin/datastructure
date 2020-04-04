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

            //如果当前元素的值大于等于右孩子
            if(data.getIndex(i).compareTo(data.getIndex(j))>=0)break;
            //就下沉交换一下
            data.swap(i,j);
            i=j;
        }
    }


}
