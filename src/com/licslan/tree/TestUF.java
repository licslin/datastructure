package com.licslan.tree;

import java.util.Random;

public class TestUF {

    private static double testUF(UF uf, int m){

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();


        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        // UnionFind1 慢于 UnionFind2
//        int size = 100000;
//        int m = 10000;

        // UnionFind2 慢于 UnionFind1, 但UnionFind3最快  UnionFind4快于UnionFind3是基于数据量很大 比如千万级别+的
        int size = 100000;
        int m = 100000;

        //数组实现并查集功能
        UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : " + testUF(uf1, m) + " s");

        //数组结构优化成树结构
        UnionFind2 uf2 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf2, m) + " s");

        //size优化
        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) + " s");

        //rank优化
        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UnionFind4 : " + testUF(uf4, m) + " s");

        //非递归实现  路径压缩优化  严格意义上  时间复杂度O(log*n) 几乎可以理解为O(1)级别的  O(1)>O(log*n)很快>O(logn)
        UnionFind5 uf5 = new UnionFind5(size);
        System.out.println("UnionFind5 : " + testUF(uf5, m) + " s");

        //路径压缩+递归   树的高度整体更矮  递归算法性能整体比非递归实现性能要差一点点
        UnionFind6 uf6 = new UnionFind6(size);
        System.out.println("UnionFind6 : " + testUF(uf6, m) + " s");


        //并查集时间复杂度在数学上分析比较难  这里不做考察  不是平常的简单时间复杂度就可以计算的  粗略认为时O(h)


        //对于数据是合并和查询时动态交替进行的更适合用并查集方案解决
        //堆 线段树 tire 并查集  四种常见树的变种
    }
}
