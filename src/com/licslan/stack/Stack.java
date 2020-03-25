package com.licslan.stack;

public class Stack<E> implements StackE {
    //LICSLAN  day2 栈学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>
    //栈的理解
    //1.栈也是一种线性结构
    //2.相比数组栈对应的操作也是数组的子集
    //3.只能从一端添加元素  从另一端取出元素   栈顶   入栈  1234    出栈  4321   last in first out

    //栈的应用
    //1.无处在的[undo撤销]
    //2.程序调用的系统栈  子过程 子逻辑的调用  比如对递归的理解

    //stack<E>
    //void push(E)  E pop()   E peek()  int getSize()  boolean isEmpty()
    //用户来看不关心你底层怎么实现栈的功能(上述的5个方法)  底层实现有多种方法
    //Interface Stack<E>  <-----implement-------ArrayStack<E>   利用动态数组来实现栈

    ArrayDynamic<E> arrayDynamic;
    public Stack(int capaticy){
        arrayDynamic = new ArrayDynamic<>(capaticy);
    }

    //无参构造方法 不知道stack的初始大小
    public Stack(){
        arrayDynamic = new ArrayDynamic<>();
    }

    public int getCapacity(){
        return arrayDynamic.getcapacity();
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
    public void push( Object e) {
        E n = (E) e;
        arrayDynamic.addLast(n);
    }

    @Override
    public Object pop() {
        return arrayDynamic.removeLast();
    }

    @Override
    public Object peek() {
        return arrayDynamic.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for(int i=0;i<arrayDynamic.getSize();i++){
            res.append(arrayDynamic.getIndex(i));
            if(i!=arrayDynamic.getSize()-1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        for(int i=0;i<5;i++){
            integerStack.push(i);
            System.out.println("元素是  "+integerStack);
        }

        integerStack.pop();
        System.out.println("出栈后的元素是： "+integerStack);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Stack<Integer> integerStackNew = new Stack<>(10);
        for(int i=0;i<15;i++){
            integerStackNew.push(i);
            System.out.println("元素是  "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        }

        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());
        System.out.println("再操作就报错啦~~~~^_^");
        integerStackNew.pop();
        System.out.println("出栈后的元素是： "+integerStackNew+"  and capacity is "+integerStackNew.getCapacity());


    }

}
