package com.licslan.stack;

public class LinkedListNew<E> {
    private class Node{
        public E e;
        public Node nxet;

        public Node(E e, Node nxet){
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
    //链表头 虚拟节点
    private Node dummyHead;
    //链表中元素个数
    private int size;
    //链表构造函数
    public LinkedListNew(){
        //空链表存在一个虚拟头节点
        dummyHead=new Node(null,null);
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

    //分析 在链表中间添加元素 如果要在节点[2]后面插入一个节点[666]节点怎么操作呢?  [1(head)]-->[2]-->[3]-->[4]-->[N]-->Null
    //1.首先要找到节点[666]的前一个节点[2]也就是prev节点 （关键 找到要添加的节点的前一个节点）
    //2.找到后就是就把插入节点[666]的next指向未插入数据节点[2]后面节点[3] 即当前要插入的节点node.next=prev.next
    //3.prev.next=node  (节点[2]的next指向节点[666])  size++ 即可
    //4.如果在把数据添加在head 索引为0？ 有点特殊
    //5.node.next=prev.next  prev.next=node   顺序很重要  2者顺序不可以颠倒 ！！！
    //6.[1(head)]-->[2]-->[666]-->[3]-->[4]-->[N]-->Null
    //7.[dummyHead 便于编写代码逻辑的虚拟节点 null]-->[1(head)]-->[2]-->[666]-->[3]-->[4]-->[N]-->Null
    //8.[1(head)]-->[2]-->[3]-->[4]-->[N]-->Null  可以理解为   [1(head) index:0]-->[2 index:1]-->[3 index:2]-->[4 index:3]-->[N index:M]-->Null
    //9.[dummyHead null]-->[1(head)]-->[2]-->[3]-->[4]-->[N]-->Null  可以理解为   [dummyHead null index:不计为索引值]-->[1(head) index:0]-->[2 index:1]-->[3 index:2]-->[4 index:3]-->[N index:M]-->Null

    //在链表的index(0-based)位置添加新的元素e 练习使用  真正使用是会摈弃索引概念
    public void  add(int index,E e){
        if(index<0||index>size)
            throw new IllegalArgumentException("Illegal index！");


        //加dummyHead后 以前的逻辑就可以改造如下了
        //虚拟节点开始初始 
        //dummyHead可以认为是遍历时 0 head节点 前面的一个节点
        Node prve=dummyHead;
        //遍历找到index的前面一个节点prev node
        for(int i=0;i<index;i++)
            //不断遍历 从【dummyHead--->index-1】位置 直到index-1那个节点的值赋值给prev 这个时候就找到了prev节点  index前一个元素就是index-1
            //这个时候要把index前面的一个值找出来  即index-1的值
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

    //在链表头部添加节点
    public void addFirst(E e){
        add(0,e);
    }
    //向链表末尾添加元素
    public void addLast(E e){
        add(size,e);
    }

    //在上述添加链表元素如果是头部时 就会有点特殊 因为添加头部元素时待添加元素前面没有其他元素
    //正常情况我们添加一个元素时 需要找到待添加元素的前一个元素  这个时候咋办呢
    // ^_^ 可以在head节点添加一个虚拟节点呀 不存储任何元素 值null  dummyHead  为了编写方便 用户不用知道
    //详情改造请看class

    //获取链表index位置的元素
    public E get(int index){
        if(index<0||index>size)
            throw new IllegalArgumentException("Illegal index!");
        //遍历到index位置  这个时候就要把index位置的值给找出来
        //从链表的第一个节点开始遍历（head节点）
        Node current = dummyHead.nxet;
        for(int i=0;i<index;i++)
            current=current.nxet;
         //返回index节点的值e
        return current.e;
    }
    //获取链表第一个元素
    public E getFirst(){
        return get(0);
    }
    //获取链表最后一个元素
    public E getLast(){
        return get(size-1);
    }
    //在链表的index(0-based)位置修改新的元素e 练习使用  真正使用是会摈弃索引概念  练习使用
    public void set(int index,E e){
        if(index<0||index>size)
            throw new IllegalArgumentException("Illegal index!");
        Node current = dummyHead.nxet;
        for(int i=0;i<index;i++)
            current=current.nxet;

        current.e=e;

    }
    //查找链表是否包含某个元素e
    public boolean contains(E e){
        Node node=dummyHead.nxet;
        while (node!=null){
            if(node.e.equals(e)){
                return true;
            }
            node=node.nxet;
        }
        return false;
    }
    //上述方法也可以改写为for  size


    //在链表的index(0-based)位置删除元素e 练习使用  真正使用是会摈弃索引概念
    public E remove(int index){
        if(index<0||index>size)
            throw new IllegalArgumentException("Illegal index!");
        Node prve=dummyHead;
        for(int i=0;i<index;i++)
            //找到index前面一个节点的元素 即index-1
            prve = prve.nxet;

        //要删除节点retNode
        Node retNode = prve.nxet;
        //跳过  prve的(next) 下一个节点不是retNode  而是retNode的(next) 下一个节点
        prve.nxet = retNode.nxet;
        //脱离链表  方便垃圾回收
        retNode.nxet=null;
        size--;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }














    @Override
    public String toString(){
        StringBuilder res= new StringBuilder();
        /*Node cur= dummyHead.nxet;
        while (cur!=null){
            res.append(cur+" -->");
            cur=cur.nxet;
        }*/
        for(Node cur=dummyHead.nxet;cur!=null;cur=cur.nxet)
            res.append(cur+" -->");
        res.append(" NULL");
        return res.toString();
        //上述2种写法都可以
    }
}
