package com.licslan.stack;

public interface StackE<E> {
    //the number of stack
    int getSize();

    //stack is empty or not
    boolean isEmpty();

    //push e to stack
    void push(E e);

    //get e out from stack
    E pop();

    //get first e from stack
    E peek();

}
