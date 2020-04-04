package com.licslan.queue;

import com.licslan.heap.MaxHeap;

/**
 * 使用heap实现优先队列这种数据结构
 * */
public class PriorityQueue<E extends Comparable<E>> implements QueueQ<E> {

    //最大堆
    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize(){
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty(){
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront(){
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E e){
        maxHeap.add(e);
    }

    @Override
    public E dequeue(){
        return maxHeap.extractMax();
    }
}
