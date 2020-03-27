package com.licslan.tree;
/**
 * Binary Search Tree
 * 泛型但要是可比较的类型
 * */
public class BST<E extends Comparable<E>>{

    //修饰为私有
    private class Node{
        public E e;
        public Node left,right;
        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }
    //根节点
    private Node root;
    //bst中存储了多少个元素
    private int size;
    //构造函数
    public BST(){
        root=null;
        size=0;
    }
    //成员方法
    public int size(){
        return size;
    }
    //成员方法
    public boolean isEmpty(){
        return size==0;
    }

    //向二分搜索树中添加元素  递归方式实现
    public void add0(E e){
        //如果没有根节点 放入树根节点
        if(root==null){
            root=new Node(e);
            size++;
        }
        else add0(root,e);

    }
    //向以node为根的二分搜素树中插入元素e,递归算法
    private void add0(Node node,E e){
        //如果待插入元素和node节点元素比较  要大 元素重复什么都不干
        if(e.equals(node.e))
            return;
        //放入左子树 待插入元素和node节点元素比较  要小
        else if(e.compareTo(node.e)<0&&node.left==null){
            node.left=new Node(e);
            size++;
            return;
        }
        //放入右子树 待插入元素和node节点元素比较  要大
        else if(e.compareTo(node.e)>0&&node.right==null){
            node.right=new Node(e);
            size++;
            return;
        }
        //递归调用上述步骤
        if(e.compareTo(node.e)<0) add0(node.left,e);else add0(node.right,e);
    }

    //对上述二分搜索树添加add0()进行改进  此方法中不考虑根节点是否为空  递归算法 就非常简洁
    public void add(E e){
        root=add(root,e);
    }

    //以node为根节点二分搜索树插入元素e
    private Node add(Node node, E e) {
        //如果要插入的节点为空
        if(node==null){
            size++;
            return new Node(e);
        }
        //如果要插入的元素比该树的节点元素要小  就放入左子树节点
        if(e.compareTo(node.e)<0) node.left=add(node.left,e);
        //如果要插入的元素比该树的节点元素要大  就放入右子树节点
        else if(e.compareTo(node.e)>0) node.right=add(node.right,e);
        return node;
    }

    //查询二分搜索树中节点是否包含某个元素
    public boolean contains(E e){
        return contains(root,e);
    }

    //查看以node为根的二分搜索树中是否包含元素e  递归算法实现
    private boolean contains(Node node, E e) {
        if(node==null){
            return false;
        }
        if(e.compareTo(node.e)==0) return true;
//        else if(e.compareTo(node.left.e)==0) return true;
//        else if(e.compareTo(node.right.e)==0) return true;
//        return contains(node,e);
        else if(e.compareTo(node.e)<0) return contains(node.left,e);
        else return contains(node.right,e);
    }


}
