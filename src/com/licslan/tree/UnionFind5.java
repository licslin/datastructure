package com.licslan.tree;
/**
 * 我们的第五版Union-Find  优化思路  基于路径压缩&size优化&rank的优化 基于第三版中
 * 要比较一下合并前每个集合中数据元素的大小  将元素小的合并到元素多的上面
 * 减小树的高度  提高性能  O(h)  但是元素少的元素不一定就比较元素多的树的
 * 高度矮些    这个时候合并前要比较树的高度  树的高度矮的要指向指向树的高度高的
 * 将rank低的集合合并到rank高的集合上  树的高度低的合并到树的高度高的元素上面去
 * 这样最终形成的树总体高度会变小  性能也会提升对于查询来说   但总体来说基于rank
 * 的优化更加合理  choose rank  而第二版中  极端情况下可能是形成链表形式  性能就会变差
 * 如果数据量很大的话  这个时候就要基于整体的路径优化  降低树的整体高度  算法竞赛中常用的
 * 优化手段
 *
 * */
public class UnionFind5 implements UF {

    // rank[i]表示以i为根的集合所表示的树的层数
    // 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
    // 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
    private int[] rank;
    private int[] parent; // parent[i]表示第i个元素所指向的父节点

    // 构造函数
    public UnionFind5(int size){

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

        while( p != parent[p] ){
            //对于第四版中加一行代码即可  在查找过程中  让自己当前的节点指向父亲的父亲节点就行
            //因为整体来说他们不管以怎样的形式连接   他们都是相连的  不管极端条件下面的链表形式
            //还是树的结构  这行代码的操作  整体会降低树的高度了 提高查询效率  其他逻辑不变
            //此时不做rank的维护也是出于性能考究  不维护性能会更好  也没有什么影响此时
            //此时使用的粗略的rank排名  不用精确的知道每个元素节点所处树的高度 此时可以胜任并查集的工作了 （是否连接 & 合并元素 & 查找节点 ）
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
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

        if( pRoot == qRoot )
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if( rank[pRoot] < rank[qRoot] )
            parent[pRoot] = qRoot;
        else if( rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // 此时, 我维护rank的值
        }
    }
}
