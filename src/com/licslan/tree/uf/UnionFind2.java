package com.licslan.tree.uf;
/**
 * 我们的第二版Union-Find  由孩子指父亲节点  组成森林结构 与第一版有很大的不同
 * */
public class UnionFind2 implements UF {

    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;

    // 构造函数
    public UnionFind2(int size){

        parent = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合  此时没有连接情况
        for( int i = 0 ; i < size ; i ++ )
            parent[i] = i;
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

        //查找当前元素的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        //如果他们的根节点相同  说明他们同属于一个集合
        if( pRoot == qRoot )
            return;

        //如果不相同  那么就将Q的根节点指向P的根节点 这样他们就相连了 就合并了
        parent[pRoot] = qRoot;
    }
}