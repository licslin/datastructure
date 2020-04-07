package com.licslan.tree;
/**
 * 我们的第四版Union-Find  优化思路   基于rank的优化 基于第三版中
 * 要比较一下合并前每个集合中数据元素的大小  将元素小的合并到元素多的上面
 * 减小树的高度  提高性能  O(h)  但是元素少的元素不一定就比较元素多的树的
 * 高度矮些    这个时候合并前要比较树的高度  树的高度矮的要指向指向树的高度高的
 * 将rank低的集合合并到rank高的集合上  树的高度低的合并到树的高度高的元素上面去
 * 这样最终形成的树总体高度会变小  性能也会提升对于查询来说   但总体来说基于rank
 * 的优化更加合理  choose rank
 * */
public class UnionFind4 implements UF {

    private int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数 树的高度
    private int[] parent; // parent[i]表示第i个元素所指向的父节点

    // 构造函数
    public UnionFind4(int size){

        rank = new int[size];
        parent = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < size ; i ++ ){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        while(p != parent[p])
            p = parent[p];
        return p;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected( int p , int q ){
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q){

        int pRoot = find(p);
        int qRoot = find(q);

        //如果他们的根节点相同  说明他们同属于一个集合  相连的
        if( pRoot == qRoot )
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上  树的高度低的合并到树的高度高的集合上面去
        // 两两合并后树的高度不会增加的  所以不用维护rank的高度  最终rank之前高的为主
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else{ // rank[pRoot] == rank[qRoot]
            //但是此时合并后  树的高度就会加一层  +1
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // 此时, 我维护rank的值
        }
    }
}
