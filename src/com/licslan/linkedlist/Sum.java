package com.licslan.linkedlist;
/**
 * 利用递归对数组就和
 * */
public class Sum {

    public static int sum(int[] arr){
        return sum(arr,0);
    }

    //计算arr[l....n)区间内所有数字的和  [0,10)
    private static int sum(int[] arr,int l){
        if(l==arr.length)
            return 0;
        return arr[l]+sum(arr,l+1);
    }

    public static void main(String[] args) {
        int[] nums={0,1,2,3,4,5,6,7,8,9};
        System.out.println(sum(nums));
    }

    //递归算法  1.求解最基本的问题  2.把原问题转化为更小问题

    //注意递归函数的“宏观”语意
    //递归函数就是一个函数  完成一个功能
    //递归函数的调用 本质就是函数间调用 只不过调用的函数是自己而已
    //递归调用是有代价的  函数调用+系统栈空间
    //



}
