package com.licslan.linkedlist;

public class LinkedList<E> {
    //私有
    private class Node{
        public E e;
        public Node nxet;

        public Node(E e,Node nxet){
            this.e=e;
            this.nxet=nxet;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }
    //链表头
    private Node head;
    //链表中元素个数
    private int size;
    //链表构造函数
    public LinkedList(){
        head=null;
        size=0;
    }
    //获取链表中元素个数
    public int getSize(){
        return size;
    }
    //链表是否为空
    public boolean isEmpty(){
        return size==0;
    }
    //在链表头部添加节点
    public void addFirst(E e){
        //新建一个元素为e的node
        Node node = new Node(e);
        //将该节点的下一个元素指向之前链表的头部
        node.nxet=head;
        //并将新添加节点作为head节点
        head=node;
        //维护一下连接数量节点
        size++;
        //上述写法优雅可以改写为
        //head=new Node(e,head); size++;
    }

    //分析 在链表中间添加元素 如果要在节点[2]后面插入一个节点[666]节点怎么操作呢?  [1]-->[2]-->[3]-->[4]-->[N]-->Null
    //1.首先要找到节点[666]的前一个节点[2]也就是prev节点 （关键 找到要添加的节点的前一个节点）
    //2.找到后就是就把插入节点[666]的next指向未插入数据节点[2]后面节点[3] 即当前要插入的节点node.next=prev.next
    //3.prev.next=node  (节点[2]的next指向节点[666])  size++ 即可
    //4.如果在把数据添加在head 索引为0？ 有点特殊
    //5.node.next=prev.next  prev.next=node   顺序很重要  2者顺序不可以颠倒 ！！！

    //在链表的index(0-based)位置添加新的元素e 练习使用  真正使用是会摈弃索引概念
    public void  add(int index,E e){
        if(index<0||index>size)
            throw new IllegalArgumentException("Illegal index！");

        if(index==0)
            addFirst(e);
        else{
            //先将要插入的节点认为是head节点（第一个节点）
            Node prve=head;
            //遍历找到index的前面一个节点prev node
            for(int i=0;i<index-1;i++)
                //不断遍历 从【head--->index-1】位置 直到index-1那个节点的值赋值给prev 这个时候就找到了prev节点
                prve=prve.nxet;

            //这个时候创建要插入的节点
            Node node = new Node(e);
            //当前创建的node节点就指向循环找到的index-1节点 prev  prev.next就是当前要插入节点的下一个节点 prev节点就是当前节点的上一个节点
            node.nxet=prve.nxet;
            //prve的next节点就是当前要插入的元素了
            prve.nxet=node;
            //维护size
            size++;
            //上述写法优雅可以改写为
            //prve.nxet=new Node(e,prve.next); size++;
        }

    }

    //向链表末尾添加元素
    public void addLast(E e){
        add(size,e);
    }

    //在上述添加链表元素如果是头部时 就会有点特殊 因为添加头部元素时待添加元素前面没有其他元素
    //正常情况我们添加一个元素时 需要找到待添加元素的前一个元素  这个时候咋办呢
    // ^_^ 可以在head节点添加一个虚拟节点呀 不存储任何元素 值null  dummyHead  为了编写方便 用户不用知道
    //详情改造请看LinkedListNew.class



    //链表  双链表  循环链表    Java里面的LinkedList -->循环双向链表
    //Null也剋以看成是一个链表  一个元素的也可以看作是链表


}
