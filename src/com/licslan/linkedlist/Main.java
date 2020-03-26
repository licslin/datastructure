package com.licslan.linkedlist;

public class Main {

    //链表学习
    //LICSLAN  day4 链表学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>

    //数据库CRUD 我们对自己封装的数组也进行相应的内存级别的CRUD   链表学习 用户不能知道底层做了什么操作

    //线性数据结构
    //1.动态数组(用户来看)
    //2.栈
    //3.队列
    //上述3种底层依托静态数组  靠resize 解决固定容量问题

    //4.链表  真正的动态数据结构
    // 最简单的动态数据结构   数据结构学习的重点！！！
    // 高级动态数据结构 树  tire ...
    // 学习链表可以更好的理解引用 （指针）
    // 能更好的理解递归
    // 辅助组成其他的数据结构 （图  hash）
    // 数据存储在节点中Node  {E e Node next}   [1]-->[2]-->[3]-->[4]-->[N]-->Null
    // 优点 真正的动态 不需要处理固定容量问题
    // 缺点 丧失了随机访问的能力

    // 数组与链表对比
    // 数组：最好用于索引有语义的情况  支持快速查询  静态数据结构
    // 链表：不适用于索引有语义的情况  动态         动态数据结构
    public static void main(String[] args) {
        LinkedListNew<Integer> listNew = new LinkedListNew<>();
        for(int i=0;i<10;i++) {
            listNew.addFirst(i);
            System.out.println(listNew);
        }
        listNew.add(3,1  );
        System.out.println(listNew);
        listNew.remove(3);
        System.out.println(listNew);




        //链表时间复杂度
        //add    addLast(e)  O(n)  addFirst(e)  O(1)  add(index,e)  O(n/2)=O(n)
        //delete removeLast(e) O(n) removeFirst(e) O(1) remove(index,e) O(n/2)=O(n)
        //update set(index,e) O(n)
        //query get(index) O(n) contains(e) O(n) getFirst() O(1)
        //整体时间复杂度  O(n)


        //链表头部add/del O(1)  else O(n)
        //链表头部 query  O(1)  else O(n)
    }
}
