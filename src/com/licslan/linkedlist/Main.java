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

        //链表时间复杂度
        //add    addLast(e)  O(n)  addFirst(e)  O(1)  add(index,e)  O(n/2)=O(n)
        //delete removeLast(e) O(n) removeFirst(e) O(1) remove(index,e) O(n/2)=O(n)
        //update set(index,e) O(n)
        //query get(index) O(n) contains(e) O(n) getFirst() O(1)
        //整体时间复杂度  O(n)


        //链表头部add/del O(1)  else O(n)
        //链表头部 query  O(1)  else O(n)





    static class ListNode{
        //链表与递归
        //1-->2-->2-->7-->6-->2-->5-->2-->2  删除value为2的元素?
        //1-->7-->6-->5
        int val;
        ListNode next;
        ListNode(int x){val=x;}
        // 链表节点的构造函数
        // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
        public ListNode(int[] arr){

            if(arr == null || arr.length == 0)
                throw new IllegalArgumentException("arr can not be empty");

            this.val = arr[0];
            ListNode cur = this;
            for(int i = 1 ; i < arr.length ; i ++){
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        // 以当前节点为头结点的链表信息字符串
        @Override
        public String toString(){

            StringBuilder s = new StringBuilder();
            ListNode cur = this;
            while(cur != null){
                s.append(cur.val + "->");
                cur = cur.next;
            }
            s.append("NULL");
            return s.toString();
        }
    }
    //不使用虚拟头节点
    static class SolutionA{

        //这里要删除多个元素  所以就写循环了  之前都是一个元素 找到要删除的元素删除就好了 所以不用不停的循环的
        public ListNode removeE(ListNode head,int val){
            //删除头节点  A
            while (head!=null&&head.val==val){
                //认为头节点和要删除的值相等就不停的删除
                ListNode delNode=head;
                head=head.next;
                delNode.next=null;
                //上述可以改造为  head=head.next;
            }

            //如果链表中都是要删除的节点呢   A操作就会把里面的元素都删除完了 那就返回一个空链表就行
            if(head==null)
                return null;

            //认为待删除节点的前一个节点为prev  说明前一个元素不会被删除   后面可能有会被删除的元素
            ListNode prev=head;
            //删除中间节点
            while (prev.next!=null){
                if(prev.next.val==val){
                    ListNode delNode = prev.next;
                    prev.next = delNode.next;
                    delNode.next=null;
                    //上述可以改造为  prev.next=prev.next.next;
                }else {
                    prev=prev.next;
                }
            }
            return head;
        }
    }//class {}


    //使用虚拟头节点
    static class SolutionB{
        //这里要删除多个元素  所以就写循环了  之前都是一个元素 找到要删除的元素删除就好了 所以不用不停的循环的
        public ListNode removeE(ListNode head,int val){
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next=head;
            //加入虚拟节点就不需要单独写删除头节点的逻辑了
            //认为待删除节点的前一个节点为prev  说明前一个元素不会被删除   后面可能有会被删除的元素
            ListNode prev=dummyHead;
            //删除中间节点
            while (prev.next!=null){
                if(prev.next.val==val){
                    prev.next=prev.next.next;
                }else {
                    prev=prev.next;
                }
            }
            return dummyHead.next;
        }
    }//class {}



    //链表天然递归操作
    static class SolutionC{
        //这里要删除多个元素  所以就写循环了  之前都是一个元素 找到要删除的元素删除就好了 所以不用不停的循环的
        public ListNode removeE(ListNode head,int val){
            if(head==null)
                return null;
            ListNode listNode = removeE(head.next, val);
            if(head.val==val)
                return listNode;
            else {
                head.next=listNode;
                return head;
            }
        }
    }//class {}

    //链表天然递归操作 SolutionC-->SolutionD  LeetCode 203
    static class SolutionD{
        //这里要删除多个元素  所以就写循环了  之前都是一个元素 找到要删除的元素删除就好了 所以不用不停的循环的
        public ListNode removeE(ListNode head,int val){
            if(head==null)
                return null;
            head.next = removeE(head.next, val);
            if(head.val==val)
                return head.next;
            else {
                return head;
            }
        }
    }//class {}

    //链表天然递归操作 SolutionD-->SolutionE  三目运算符
    static class SolutionE{
        //这里要删除多个元素  所以就写循环了  之前都是一个元素 找到要删除的元素删除就好了 所以不用不停的循环的
        public ListNode removeE(ListNode head,int val){
            if(head==null)
                return null;
            head.next = removeE(head.next, val);
            return head.val==val ? head.next : head;
        }
    }//class {}

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


        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Main.SolutionC()).removeE(head, 6);
        System.out.println(res);
    }

}
