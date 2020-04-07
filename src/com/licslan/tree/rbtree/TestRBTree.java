package com.licslan.tree.rbtree;

import com.licslan.tree.avltree.AVLTree;
import com.licslan.tree.avltree.BST;

import java.util.ArrayList;
import java.util.Random;

/**
 * 各种树性能测试  红黑树占优势在于添加和删除操作  查询过程中红黑树 与 AVL 相比并不占优势
 *
 * 红黑树牺牲了平衡性 2longn的高度  统计性能更优 （综合CRUD操作） 有序 TreeMap  TreeSet
 *
 * 红黑树删除节点过程非常非常难  添加节点也比较难  面试中不会用的  白板编程很少很少考察
 *
 *
 * Splay Tree 伸展树  基于局部性原理
 * */
public class TestRBTree {
    public static void main(String[] args) {


            // int n = 20000000;
            int n = 20000000;

            //此时不会退化为链表 可以测试BST  对于完全随机的数据普通的二分搜索树很好用！  极端情况退化为链表(平衡度不高)就不好了

            Random random = new Random(n);
            ArrayList<Integer> testData = new ArrayList<>(n);
            for(int i = 0 ; i < n ; i ++)
                testData.add(random.nextInt(Integer.MAX_VALUE));


            //下面这样就不行了  有顺序了  退化成为链表了  数据量很大不建议测试BST  即使性能很好的电脑也要花较长时间

            /*ArrayList<Integer> testData = new ArrayList<>(n);
            for(int i = 0 ; i < n ; i ++)
                testData.add(i);*/

            // Test BST
            long startTime = System.nanoTime();

            BST<Integer, Integer> bst = new BST<>();
            for (Integer x: testData)
                bst.add(x, null);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLTree<Integer, Integer> avl = new AVLTree<>();
            for (Integer x: testData)
                avl.add(x, null);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RBTree
            startTime = System.nanoTime();

            RBTree<Integer, Integer> rbt = new RBTree<>();
            for (Integer x: testData)
                rbt.add(x, null);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");
        }
    }
