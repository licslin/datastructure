package com.licslan.queue;

public class ArrayDynamic<E> {

    //LICSLAN  day1 数组学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>

    //数据库CRUD 我们对自己封装的数组也进行相应的内存级别的CRUD   动态数组学习 用户不能知道底层做了什么操作

    //定义一个空数组
    private E[] data;
    //数组实际大小  私有成员变量
    private int size;

    //获取数组元素个数
    public int getSize(){
        return size;
    }
    //获取数组的容量
    public int getcapacity(){
        return data.length;
    }
    //判断数组是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //初始化空数组 构造函数  传入数组容量
    public ArrayDynamic(int capacity){
        data=(E[]) new Object[capacity];
        size=0;
    }
    //无参构造函数
    public ArrayDynamic(){
        this(10);
    }

    //向指定元素位置插入元素  那么指定元素位置后面的元素需要往后移动
    public void add(int index,E e){
        /*if(size==data.length)
            throw new IllegalArgumentException("array is full");*/
        if(index<0||index>size)
            throw new IllegalArgumentException("Require index>=0 and index < size");
        if(size==data.length)
            //这个时候不是报错了  而是扩容 2倍 1.5  3都可以
            resize(2*data.length);

        //移动元素  从最后的开始  也就是从后往前元素一次都往后移动一位
        for(int i=size-1;i>=index;i--)
            //将大于等于index的值往后依次移动一位元素
            data[i+1]=data[i];
        //此时data[index]的值赋值为要插入的值
        data[index]=e;
        size++;

    }

    private void resize(int newCapacity) {
        //申明一个新的数组  并将原来数组的值赋值到新的数组里面来
        E[] newData = (E[]) new Object[newCapacity];
        for(int i=0;i<size;i++)
            newData[i]=data[i];

        //将data指向新的数组newData  那么以前的data数组JVM gc自己自动回收了
        data=newData;

    }

    public void addLast(E i) {
        add(size,i);
    }


    //获取指定元素的位置
    public E getIndex(int index){
        if(index<0||index>=size)
            throw new IllegalArgumentException("Get failed! Index is illegal");
        return data[index];
    }

    //getFirst
    public E getFirst(){
       return getIndex(0);
    }
    //getLast
    public E getLast(){
        return getIndex(size-1);
    }

    //删除指定索引位置的元素  动态删除  当删除到一定程度的时候  数组的容量capacity就会自动缩小
    public E remove(int index){
        E indexValue= data[index];
        if(index<0||index>=size)
            throw new IllegalArgumentException("Remove failed! Index is illegal");

        //将索引大于index的值都往前移动一位  从index索引下一位开始移动
        for(int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;

//        if(size==data.length/2)
//            resize(data.length/2);

        //上述的动态数组缩容时优化 缩容时不能为0  空数组长度不能为0啊
        if(size==data.length/4 && data.length/2!=0)
            resize(data.length/2);

        return indexValue;
    }


    //查找元素的索引值  如果找不到就返回-1  当然这里也只是找了一个元素e  并没有找到所有元素e
    public int findIndex(E e){
        for(int i=0;i<size;i++){
            if(data[i]==e){
                //返回索引值
                return i;
            }
        }
        return -1;
    }

    //删除第一位
    public E removeFirst(){
        return remove(0);
    }
    //删除最后一位
    public E removeLast(){
        return remove(size-1);
    }
    //删除某个元素  如果有重复元素并不能保证完全删除元素e
    public void removeE(E e){
        //首先找一下元素的索引位置
        int index = findIndex(e);
        if(index!=-1)
            remove(index);
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        res.append("[");
        for(int i =0;i<size;i++){
            res.append(data[i]);
            //如果不是最后一个元素
            if(i!=size-1)
                res.append(",");
        }
        //如果是最后一个元素
        res.append("]");
        return res.toString();
    }




    //时间复杂度分析  O(1)  O(n)  O(lgn) O(nlogn) O(n^2)

    //时间复杂度 忽略常数

    // T=2*N+2                 O(n)
    // T=2000*N+10000          O(n)
    // T=1*N*N + 0.00001       O(n^2)

    //渐进时间复杂度 描述当N趋近于无穷时的情况

    //[添加操作]
    //addLast()  O(1)  resize()  O(n)
    //addFirst() O(n)
    //add(index,e)  可能是  如果index==size O(1) 也有可能是O(n) index=0   计算数学期望  平均而言 O(N/2)=O(N)
    //动态数组  resize()操作  O(N)
    //[删除操作]
    //removeLast()  O(1)
    //removeFirst() O(n)
    //remove(index,e)  可能是  如果index==size O(1) 也有可能是O(n) index=0   计算数学期望  平均而言 O(N/2)=O(N)
    //[修改操作]
    //set() O(1)
    //[查询操作]
    //get(index) O(1)
    //contains(e) O(n)
    //find(e)  O(n)


    //总结一下数组的时间复杂度
    //add    O(n)     resize()
    //delete O(n)     resize()
    //update 已知索引 O(1)  未知索引O(n)
    //query  已知索引 O(1)  未知索引O(n)


    //算法领域时间复杂度通常按照最坏的考虑

    //复杂度震荡 removeLast and addLast to  Eager  so we should lazy   扩容 缩小    当size==capacity/4 才将capacity减半





}
