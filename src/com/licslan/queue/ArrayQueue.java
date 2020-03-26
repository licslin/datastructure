package com.licslan.queue;

public class Queue<E> implements QueueQ<E> {


    //LICSLAN  day3 普通队列学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>

    //数据库CRUD 我们对自己封装的数组也进行相应的内存级别的CRUD   普通队列学习 用户不能知道底层做了什么操作
    private ArrayDynamic arrayDynamic;
    public Queue(int capacity){
        arrayDynamic=new ArrayDynamic(capacity);
    }
    public Queue(){
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
        res.append("Queue: ");
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
        Queue<Integer> queue= new Queue<>();
        for(int i=0;i<10;i++){
            //入栈
            queue.enqueue(i);
            System.out.println(queue);

            if(i%3==2){
                //出栈
                queue.dequeue();
                System.out.println(queue);
            }

        }
    }

    //时间复杂度分析
    //void enqueue() O(1)  E enqueue(E e) O(n)  void dequeue() O(1)  getSize()  O(1)  E front() O(1)  boolean isEmpty() O(1)




}
