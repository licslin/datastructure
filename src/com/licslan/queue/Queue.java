package com.licslan.queue;

public class Queue<E> implements QueueQ {

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
    public void enqueue(Object o) {
        E e =(E)o;
        arrayDynamic.addLast(e);

    }

    @Override
    public Object dequeue() {
        return arrayDynamic.removeFirst();
    }

    @Override
    public Object getFront() {
        return arrayDynamic.getFirst();
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
