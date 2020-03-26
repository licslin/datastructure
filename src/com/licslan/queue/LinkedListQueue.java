package com.licslan.queue;

public class LinkedListQueue<E> implements QueueQ<E> {

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
    //改造链表为栈的实现  加入tail尾指针
    private Node head,tail;
    private int size;
    public LinkedListQueue(){
        head=null;
        tail=null;
        size=0;
    }

    
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enqueue(E e) {
        //O(1)
        //入队  从链表尾部操作
        if(tail==null){
            //空链表
            tail=new Node(e);
            //空链表 head=tail
            head =tail;
        }
        else {
            //如果尾部节点不为空 将之前tail的next指针指向新入队的元素
            tail.nxet=new Node(e);
            //新的tail就是刚才入队的下一个节点
            tail=tail.nxet;
        }
        size++;
    }

    @Override
    public E dequeue() {
        //O(1)
        //判断队列是否为空
        if(isEmpty())
            throw new IllegalArgumentException("it is a empty queue");
        //要被拿出来的那个元素
        Node retNode = head;
        //拿出来之后之前的head的下一个节点就作为head节点了
        head=head.nxet;
        //将要拿出来的那个head节点和链表断开  没有联系
        retNode.nxet=null;
        //链表里面就一个元素呢  这个时候如果拿出来后 就维护一下tail  tail==head==null 说明是个空的队列
        if(head==null)
            tail=null;
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        //判断队列是否为空
        if(isEmpty())
            throw new IllegalArgumentException("it is a empty queue");
        return head.e;
    }

    @Override
    public String toString(){
        //队尾入队 O(1)    队首出队  O(1)
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node cur = head;
        while (cur!=null) {
            res.append(cur + " -->");
            //一直遍历下去
            cur = cur.nxet;
        }
        res.append("NULL tail");
        return res.toString();
    }


    public static void main(String[] args) {
        LinkedListQueue<Integer> queue= new LinkedListQueue<>();
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
}
