package com.licslan.queue;



public class LoopQueue<E> implements QueueQ<E> {

    //循环队列
    private E[] data;
    //头,尾
    private int front,tail;
    //队列中的原始数据
    private int size;

    //含有参数的
    public LoopQueue(int capacity){
        data= (E[])new Object[capacity+1];
        front=0;
        tail=0;
        size=0;
    }
    //无参
    public LoopQueue(){
        this(10);
    }
    public int getCapacity(){
        return data.length-1;
    }
    @Override
    public boolean isEmpty(){
        return front==tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e){
        //首先判断循环队列是否是满的
        if((tail+1)%data.length==front)
            //如果是满的就要对循环队列进行扩容
            resize(getCapacity()*2);

        //循环队列
        data[tail]=e;
        tail= (tail+1) % data.length;
        size++;

    }

    @Override
    public E dequeue(){
        //首先判断队列是否为空
        if(isEmpty())
            throw new IllegalArgumentException("cannot dequeue from an empty queue");
        E ret= data[front];
        data[front]=null;
        //移除队列头部一个元素  维护一下队头  队尾没有变化对于循环队列
        front=(front+1)%data.length;
        size--;
        //当然如果这个时候如果有大量空闲的空间 这个时候也需要缩容啦  避免大量内存空间浪费
        //如果当前空间数据占用原始空间的1/4  缩容时不能为0  空数组长度不能为0啊
        if(size==getCapacity()/4&&getCapacity()/2!=0)
            resize(getCapacity()/2);
        return ret;
    }
    @Override
    public E getFront(){
        //首先判断队列是否为空
        if(isEmpty())
            throw new IllegalArgumentException("it is an empty queue");
        return data[front];

    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n",size,getCapacity()));
        res.append("front [");
        for(int i=front;i!=tail;i=(i+1)%data.length){
            res.append(data[i]);
            //不是最后的一个元素
            if((i+1)%data.length!=tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    private void resize(int newCapacity) {
        //开辟新的空间
        E[] newdata = (E[]) new Object[newCapacity+1];
        //这个时候就要原来循环队列中的元素转移到新的扩容的循环队列里去
        for(int i=0;i<size;i++)
            newdata[i]=data[(i+front) % data.length];
        data=newdata;
        //维护一下队头
        front=0;
        //维护一下队尾
        tail=size;
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue= new LoopQueue<>();
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
    //void enqueue() O(1) 均摊(没有触发缩容)  E enqueue(E e) O(1) 均摊(没有触发缩容)  void dequeue() O(1)  getSize()  O(1)  E front() O(1)  boolean isEmpty() O(1)
}
