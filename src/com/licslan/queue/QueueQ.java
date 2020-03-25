package com.licslan.queue;

public interface QueueQ<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();


}
