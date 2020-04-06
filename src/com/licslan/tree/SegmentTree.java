package com.licslan.tree;
/**
 * 线段树
 * */
public class SegmentTree<E>{


    //线段树以数组形式表达
    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger=merger;
        data=(E[])new Object[arr.length];
        for(int i=0;i<arr.length;i++)
            data[i]=arr[i];
        //创建线段树所需要开辟的空间 是要4*N的空间  可能会浪费一部分空间   有的叶子节点null
        tree=(E[])new Object[4*arr.length];

        //创建线段树
        buildSegmentTree(0,0,data.length-1);
    }

    //在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if(l==r){
            tree[treeIndex]=data[l];
            return;
        }

        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);

        int mid = l+(r-l)/2;

        //递归创建左右子树
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        //融合左右子树的要做的事情
        tree[treeIndex]=merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public E get(int index){
        if(index<0||index>=data.length)throw new IllegalArgumentException("index is Illegal!");
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    //返回完全二叉树的数组表示中 一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index+1;
    }
    //返回完全二叉树的数组表示中 一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index+2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    //设计一个接口  左右子树可以根据自己的实际业务情况融合要做的事情
    //不仅仅是加法  或者求最大值  最小值
    public interface Merger<E>{
        E merge(E a,E b);
    }

    //返回区间[queryL,queryR]的值
    public E query(int queryL,int queryR){
        if(queryL<0||queryL>=data.length||queryR<0||queryR>data.length||queryL>queryR){
            throw new IllegalArgumentException("index is Illegal");
        }
        return query(0,0,data.length-1,queryL,queryR);
    }

    //在treeID为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        //如果刚刚好
        if(l==queryL&&r==queryR)return tree[treeIndex];
        int mid = l+(r-l)/2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        //如果刚好在线段树右区间内  去右子树去找
        if(queryL>=mid+1){return query(rightTreeIndex,mid+1,r,queryL,queryR);}
        //如果刚好在线段树左区间内  去左子树去找
        else if(queryR<=mid){return query(leftTreeIndex,l,mid,queryL,queryR);}
        //如果查找的数据有一部分在左区间 有一部分在有区间呢？
        E leftResult=query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult=query(rightTreeIndex,mid+1,r,mid+1,queryR);
        //再将左右区间得到的值融合就可以
        return merger.merge(leftResult,rightResult);
    }


    //将index位置的值更新为e
    public void set(int index,E e){
        if(index<0||index>=data.length)throw new IllegalArgumentException("Index is illegal");
        data[index]=e;
        set(0,0,data.length-1,index,e);
    }

    //在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if(l==r){
            tree[treeIndex]=e;
            return;
        }
        int mid=l+(r-l)/2;
        int leftIndex=leftChild(treeIndex);
        int rightIndex=rightChild(treeIndex);
        if(index>mid+1){
            set(rightIndex,mid+1,r,index,e);
        }//index<=mid
        else {
            set(leftIndex,l,mid,index,e);
        }
        tree[treeIndex]=merger.merge(tree[leftIndex],tree[rightIndex]);
    }


    public static void main(String[] args) {
        //Integer[] nums = {-2,0,3,0,-5,2,-11};
        Integer[] nums = {-2,0,3,-5,2,-1};
        //匿名函数
        /*SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                //这里主要是计算加法  用户可以自己实现要业务融合即可
                return a+b;
            }
        });*/

        //下面使用lamda
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> {
            //这里主要是计算加法  用户可以自己实现要业务融合即可
            return a+b;
        });

        System.out.println(segmentTree);
        //测试一下query函数
        System.out.println(segmentTree.query(0,2));
    }


    //LeetCode NO303  区域和检索  不可变   给定一个数组  求数组索引i到j(i<=j)范围内的元素的总和  和上述的求和query方法一致


    //上述问题可以使用线段树来解决
    //LeetCode NO303  使用线段树来实现
    class NumArray00{
        //线段树
        private SegmentTree<Integer> segmentTree;
        //基于用户传进来的数组来构造我们自己的线段树就行了
        public NumArray00(int[] nums){
            //数组不为空 就不用进行初始化
            if(nums.length>0){
                //将int类型数据转换成Integer
                Integer[] data=new Integer[nums.length];
                for(int i=0;i<nums.length;i++) data[i]=nums[i];
                //求和操作  对Integer类型的数组进行求和
                segmentTree=new SegmentTree<>(data,(a,b)->a+b);
            }

        }
        public int sumRange(int i,int j){
            if(segmentTree==null)throw new IllegalArgumentException("Segment Tree is null");
           return segmentTree.query(i,j);
        }
    }

    //LeetCode NO303  不使用线段树来实现
    class NumArray01{
        //O(n)
        private int[] sum;  //sum[i]存储前i个元素和  sum[i]存储nums[0...i-1]的和
        public NumArray01(int[] nums){
            sum=new int[nums.length+1];
            sum[0]=0;
            for (int i=1;i<sum.length;i++)
                sum[i]=sum[i-1]+nums[i-1];
        }
        public int sumRange(int i,int j){
            //O(1)
            return sum[j+1]-sum[i];
        }
    }

    //LeetCode NO307  其中多加了修改的逻辑  该怎么处理呢？  修改的时候 时间复杂度O(n)
    class NumArray02{
        //O(n)
        private int[] sum;  //sum[i]存储前i个元素和  sum[i]存储nums[0...i-1]的和
        private int[] data;
        public NumArray02(int[] nums){
            data=new int[nums.length];
            for(int i=0;i<nums.length;i++)
                data[i]=nums[i];

            sum=new int[nums.length+1];
            sum[0]=0;
            for (int i=1;i<sum.length;i++)
                sum[i]=sum[i-1]+nums[i-1];
        }
        //修改索引在第i地方的值？
        public void update(int index,int val){

            //性能比较差  O(N)
            data[index]=val;
            for(int i=index+1;i<sum.length;i++)
                sum[i]=sum[i-1]+data[i-1];
        }
        public int sumRange(int i,int j){
            //O(1)
            return sum[j+1]-sum[i];
        }
    }

    //LeetCode NO307  其中多加了修改的逻辑  该怎么处理呢？  修改的时候 时间复杂度O(n)  使用线段树来实现这个逻辑  提升修改的性能



    //查询区间数组  线段树是一种高级数据结构
    //          线段树  数组
    //update    O(logn) O(n)
    //query     O(logn) O(n)


    //线段树不是一颗完全二叉树  但是可以看成是一颗 满二叉树  部分叶子节点为null   懒惰更新？  二维线段树？  树状数组？  区间相关问题 RMQ
}
