package com.licslan.setmap;

import com.licslan.tree.avltree.BST;

import java.util.ArrayList;

/**
 * AVL树  树极端情况下退化为链表  此时AVL可以让BST tree自平衡  不会退化为链表
 * G.M.[A]delson-[V]elsky  &&  E.M.[L]andis  --->AVL
 * 上面2位俄罗斯计算机科学家发明的  1962发表论文首次提出 最早的自平衡二分搜索树结构
 *
 * 平衡2叉树  任意的叶子节点层级高度不超过 1  堆 线段树 trie UF
 * AVL:对于任意一个节点 左子树和右子树的高度差不能超过 1  有可能看起来不是那么平衡
 * AVL:平衡二叉树的高度和节点数量之间的关系也是O(logn)的
 *
 *
 * AVL树到底是如何维护自身的平衡的呢？ 降低树的高度  提高性能
 * 判断当前是不是二分搜索树  是不是平衡二叉树  左子树节点<右子树节点的值 左右子树高度差不超过 1
 *
 * AVL Tree  维护自身平衡  通过左旋转 右旋转    什么时候维护平衡呢？
 *
 * 加入节点后 AVL 如何维护自平衡  沿着节点向上维护平衡性 ？
 * 删除节点  AVL 如何维护自平衡？
 *
 *
 * */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        //相比BST我们对每个节点所在树的高度需要记录一下
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            //初始化时每个节点默认值为 1   新的叶子节点初始值为 1
            height = 1;
        }
    }

    //根节点
    private Node root;
    //树中的元素个数
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }



    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1 ; i < keys.size() ; i ++)
            //前面的值比后面的大  不满足升序的关系  就不是二分搜索树了
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    //中序遍历的二分搜索树  结果是有顺序的  升序
    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced(){
        //传入根节点
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法  每一个节点左右子树高度不超过 1
    private boolean isBalanced(Node node){

        //空的  可以认为平很二叉树
        if(node == null)
            return true;

        //获取平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        //判断左右子树是不是一颗平衡二叉树
        return isBalanced(node.left) && isBalanced(node.right);
    }



    // 获得节点node的高度
    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }


    //右旋转 左旋转 函数是内部使用的 封装成私有的 用户不用知道底层是怎么实现的
    //旋转之后让这颗树 既满足了二分搜索树的性质 也满足了平衡二叉树的性质

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x  顺时针旋转
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x  逆时针旋转
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 更新height  记录一下当前节点树的高度  选择左右子树高度较大的一个+1 就是当前节点树的高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //绝对值大于1  说明此时二分搜索树不是一个平衡二叉树  记录每个节点的平衡因子
        /*if(Math.abs(balanceFactor) > 1)
            System.out.println("unbalanced : " + balanceFactor);*/

        //在这里维护二分搜索树的平衡性之后再返回

        //插入元素在不平衡的节点的左侧的左侧 （右旋转  顺时针旋转）  左子树高度很高   左右子树高度差>1  LL

        //插入元素在不平衡的节点的左侧的右侧 （右旋转  顺时针旋转）  左子树高度很高   左右子树高度差>1  LR

        //插入元素在不平衡的节点的右侧的右侧 （左旋转  逆时针旋转）  右子树高度很高   左右子树高度差>1  RR

        //插入元素在不平衡的节点的右侧的左侧 （左旋转  逆时针旋转）  右子树高度很高   左右子树高度差>1  RL

        // 平衡维护  添加一个节点所出现的所有情况4种

        //整体向左倾斜  右旋转  顺时针  LL  左子树-右子树>0
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        //整体向右倾斜  左旋转  逆时针  RR  左子树-右子树<0
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        //LR   --> LL  再右旋转           左子树-右子树>0
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL   --> RR  再左旋转           左子树-右子树<0
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    /*private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }*/

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        Node retNode;
        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            //return node;
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            //return node;
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                //return rightNode;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                //return leftNode;
                retNode = leftNode;
            }
            else {

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            //此时下面的代码也要进行改造  否则也有可能打破AVL树的平衡
            //successor.right = removeMin(node.right);
            successor.right=remove(node.right,successor.key);
            successor.left = node.left;

            node.left = node.right = null;

            //return successor;
            retNode = successor;
            }//else
        }//else

        //删除节点  就要对删除后带来的结果进行自平衡操作  同增加节点一样  考虑NPE
        if(retNode == null)
            return null;

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;

    }

    public static void main(String[] args){


            System.out.println("Pride and Prejudice");

            ArrayList<String> words = new ArrayList<>();
            if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
                System.out.println("Total words: " + words.size());

                // Collections.sort(words);

                // Test BST
                long startTime = System.nanoTime();

                BST<String, Integer> bst = new BST<>();
                for (String word : words) {
                    if (bst.contains(word))
                        bst.set(word, bst.get(word) + 1);
                    else
                        bst.add(word, 1);
                }

                for(String word: words)
                    bst.contains(word);

                long endTime = System.nanoTime();

                double time = (endTime - startTime) / 1000000000.0;
                System.out.println("BST: " + time + " s");


                // Test AVL
                startTime = System.nanoTime();

                AVLTree<String, Integer> avl = new AVLTree<>();
                for (String word : words) {
                    if (avl.contains(word))
                        avl.set(word, avl.get(word) + 1);
                    else
                        avl.add(word, 1);
                }

                for(String word: words)
                    avl.contains(word);

                endTime = System.nanoTime();

                time = (endTime - startTime) / 1000000000.0;
                System.out.println("AVL: " + time + " s");
            }

            System.out.println();
        }
    }

