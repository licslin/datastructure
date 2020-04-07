package com.licslan.tree;

import com.licslan.tree.bst.BST;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //二叉树
        //二分搜索树(Binary Search Tree)是二叉树 其每个节点>左子树的所有节点  其每个节点<右子树所有节点  其每一颗子树也是二分搜索树


        BST<Integer> bstA = new BST<>();
        int [] numsA ={5,3,6,8,4,2};
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        for (int num:numsA)
            bstA.add(num);
        System.out.println("~~~~~~~~~~~~前序遍历递归算法~~~~~~~~~~~~~~~~~");
        bstA.preOrder();
        System.out.println("~~~~~~~~~~~~前序遍历非递归算法~~~~~~~~~~~~~~~~~");
        bstA.preOrderNR();
        System.out.println("~~~~~~~~~~~~层序遍历~~~~~~~~~~~~~~~~~");
        bstA.levelOrder();
        System.out.println("~~~~~~~~~~~~中序遍历~~~~~~~~~~~~~~~~~");
        bstA.inOrder();
        System.out.println("~~~~~~~~~~~~后续遍历~~~~~~~~~~~~~~~~~");
        bstA.postOrder();

        //深度优先遍历的意义  更快的找到问题的解  常用于算法设计中  --最短路径<无权图>
        //图中的深度优先遍历和广度优先遍历



        //测试删除最小值
        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");
    }}




