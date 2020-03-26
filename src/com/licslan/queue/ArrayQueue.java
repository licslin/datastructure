package com.licslan.queue;

public class ArrayQueue<E> implements QueueQ<E> {


    //LICSLAN  day3 普通队列学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>

    //数据库CRUD 我们对自己封装的数组也进行相应的内存级别的CRUD   普通队列学习 用户不能知道底层做了什么操作
    private ArrayDynamic arrayDynamic;
    public ArrayQueue(int capacity){
        arrayDynamic=new ArrayDynamic(capacity);
    }
    public ArrayQueue(){
        arrayDynamic=new ArrayDynamic();
    }

    @Override
    public int getSize() {
        return arrayDynamic.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arrayDynamic.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        arrayDynamic.addLast(e);

    }

    @Override
    public E dequeue() {
        return (E) arrayDynamic.removeFirst();
    }

    @Override
    public E getFront() {
        return (E) arrayDynamic.getFirst();
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("ArrayQueue: ");
        res.append("Front [");
        for(int i=0;i<arrayDynamic.getSize();i++){
            res.append(arrayDynamic.getIndex(i));
            if(i!=arrayDynamic.getSize()-1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for(int i=0;i<10;i++){
            //入栈
            arrayQueue.enqueue(i);
            System.out.println(arrayQueue);

            if(i%3==2){
                //出栈
                arrayQueue.dequeue();
                System.out.println(arrayQueue);
            }

        }
    }

    //时间复杂度分析
    //void enqueue() O(1)  E enqueue(E e) O(n)  void dequeue() O(1)  getSize()  O(1)  E front() O(1)  boolean isEmpty() O(1)




}
