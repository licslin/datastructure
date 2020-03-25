package com.licslan.array;

public class ArrayGeneric<E> {
    //LICSLAN  day1 数组学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>

    //数据库CRUD 我们对自己封装的数组也进行相应的内存级别的CRUD
    //定义一个空数组
    private E[] data;
    //数组实际大小  私有成员变量
    private int size;

    //初始化空数组 构造函数  传入数组容量
    public ArrayGeneric(int capacity){
        data=(E[]) new Object[capacity];
        size=0;
    }
    //无参构造函数
    public ArrayGeneric(){
        this(10);
    }
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

    //向数组添加元素  其实就是在数组中第一个没有元素的位置赋值
    /*public void addLast(int e){
        if(size==data.length)
            throw new IllegalArgumentException("array is full");
        data[size]=e;
        //维护一下数组中实际数量加一个位置
        size++;
        //data[size++]=e
    }*/

    public void addLast(E e){
        //上诉方法改写为下面的  size 就是实际元素的个数  比如有5个元素 那么索引5的位置肯定就是数组中第一个没有元素的地方啦
        add(size,e);
    }

    public void addFirst(E e){
        add(0,e);
    }
    //向指定元素位置插入元素  那么指定元素位置后面的元素需要往后移动
    public void add(int index,E e){
        if(size==data.length)
            throw new IllegalArgumentException("array is full");
        if(index<0||index>size)
            throw new IllegalArgumentException("Require index>=0 and index < size");

        //移动元素  从最后的开始  也就是从后往前元素一次都往后移动一位
        for(int i=size-1;i>=index;i--)
            //将大于等于index的值往后依次移动一位元素
            data[i+1]=data[i];

        //此时data[index]的值赋值为要插入的值
        data[index]=e;
        size++;

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

    //获取指定元素的位置
    public E getIndex(int index){
        if(index<0||index>=size)
            throw new IllegalArgumentException("Get failed! Index is illegal");
        return data[index];
    }

    //修改指定元素的值
    void set(int index,E e){
        if(index<0||index>=size)
            throw new IllegalArgumentException("Set failed! Index is illegal");
        data[index]=e;
    }

    //-----------------------------------------------------------------------------------------//

    //包含某个元素
    public boolean contains(E e){
        for(int i=0;i<size;i++)
            if(data[i]==e)
                return true;

        return false;
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

    //删除指定索引位置的元素
    public void delete(int index){
        if(index<0||index>=size)
            throw new IllegalArgumentException("Delete failed! Index is illegal");

        //将索引大于index的值都往前移动一位
        for(int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
    }

    //删除指定索引位置的元素
    public E remove(int index){
        E indexValue= data[index];
        if(index<0||index>=size)
            throw new IllegalArgumentException("Remove failed! Index is illegal");

        //将索引大于index的值都往前移动一位  从index索引下一位开始移动
        for(int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        return indexValue;
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



}
