package com.licslan.tree.uf;
/**
 * 并查集  union find 解决连接问题   社交网络....  网络问题
 *
 *
 * 对于一组数据  主要有2个动作  union(p,q)  isConnected(p,q)
 * */
public interface UF {
    int getSize();
    boolean isConnected(int p,int q);  //find(p)==find(q)
    void unionElements(int p,int q);
}
