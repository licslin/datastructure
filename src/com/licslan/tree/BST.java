package com.licslan.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
    //返回新的二分搜索树的根
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
        //本节点直接找到 就返回
        if(e.compareTo(node.e)==0) return true;
//        else if(e.compareTo(node.left.e)==0) return true;
//        else if(e.compareTo(node.right.e)==0) return true;
//        return contains(node,e);
        //本节点没有找到 就去本节点左子树节点试试
        else if(e.compareTo(node.e)<0) return contains(node.left,e);
        //本节点没有找到 就去本节点右子树节点试试
        else return contains(node.right,e);
    }

    //二分搜素树的前序遍历 先访问节点伪代码  f traverse(node){ if(node==null) return;} traverse(node.left) && traverse(node.right)
    //二分搜素树的前序遍历也叫深度优先遍历   一扎到底先     广度优先遍历就是二分搜素树的层序遍历
    public void preOrder(){
        preOrder(root);
    }

    //前序遍历以node作为根的二分搜索树 递归算法
    private void preOrder(Node node) {
        //非常清晰的2段式
        if(node==null)
            return;
        //先访问该节点
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //层序遍历  广度优先遍历就是二分搜素树的层序遍历
    public void levelOrder(){
        //接口 实现用链表吧
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            Node cur = nodeQueue.remove();
            System.out.println(cur.e);
            if(cur.left!=null) nodeQueue.add(cur.left);
            if(cur.right!=null)nodeQueue.add(cur.right);
        }

    }



    //前序遍历非递归实现   利用栈实现
    public void preOrderNR(){
        //存放要节点元素
        Stack<Node> stack = new Stack<>();
        //将根节点元素放入栈
        stack.push(root);
        while (!stack.isEmpty()){
            //拿出栈中的元素
            Node cur = stack.pop();
            //访问一下这个节点元素
            System.out.println(cur.e);
            //后进先出  先放入右子树  再放入左子树
            if(cur.right!=null) stack.push(cur.right);
            if(cur.left!=null)  stack.push(cur.left);
        }
    }


    //中序遍历(排序树遍历后就是从小到大的)
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        //非常清晰的2段式
        if(node==null)
            return;
        inOrder(node.left);
        //中间访问该节点
        System.out.println(node.e);
        inOrder(node.right);
    }
    //后续遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node) {
        //非常清晰的2段式
        if(node==null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        //中间访问该节点
        System.out.println(node.e);
    }

    //查找二分搜索树最小值  可以尝试写非递归的写法
    public E minimum(){
        if(size==0)throw new IllegalArgumentException("BST is empty!");
       return minimum(root).e;
    }

    private Node minimum(Node node) {
        //最小值肯定是最左边的 如果node左子树为空  那么node节点最小
        if(node.left==null)return node;
        //否则递归返回去找左子树的结果
        return minimum(node.left);
    }


    //查找二分搜索树最大值
    public E maximum(){
        if(size==0)throw new IllegalArgumentException("BST is empty!");
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        //最大值肯定是最右边的 如果node右子树为空  那么node节点最大
        if(node.right==null)return node;
        //否则递归返回去找右子树的结果
        return maximum(node.right);
    }

    //删除二分搜索树中的最小值
    public E removeMin(){
        E ret=minimum();
        root=removeMin(root);
        return ret;
    }

    //删除以node为根的二分搜索树中的最小节点  返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if(node.left==null){
            //如果要删除的节点的左子树没有  那么本节点最小  是要被删除的啊
            //先把把要删除的节点的右子树先存在Node nodeRight中
            //本节点最小删除后  那么本节点的右子树将作为删除后的二分搜索树的根返回就行
            Node nodeRight= node.right;
            //之前的右子树置为null  成为新主 根节点了返回即可
            node.right=null;
            size--;
            //返回新的根  以前的本节点<最小的(没有左子树)>被干掉  换成了如今的右子树了
            return nodeRight;
        }
        //如果左子树不为空  那么该节点左子树就要被删除的
        node.left=removeMin(node.left);
        //再返回当前的node  只不过当前node的左子树已经没有了 右子树还在
        return node;
    }

    //删除二分搜索树中的最大值
    public E removeMax(){
        E ret=maximum();
        root=removeMax(root);
        return ret;
    }

    //删除以node为根的二分搜索树中的最大节点  返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if(node.right==null){
            //如果要删除的节点的右子树没有  那么本节点最小  是要被删除的啊
            //先把把要删除的节点的左子树先存在Node nodeLeft中
            //本节点最小删除后  那么本节点的左子树将作为删除后的二分搜索树的根返回就行
            Node nodeLeft= node.left;
            //之前的左子树置为null  成为新主 根节点了返回即可
            node.left=null;
            size--;
            //返回新的根  以前的本节点<最小的(没有右子树)>被干掉  换成了如今的左子树了
            return nodeLeft;
        }
        //如果左子树不为空  那么该节点右子树就要被删除的
        node.right=removeMax(node.right);
        //再返回当前的node  只不过当前node的右子树已经没有了 左子树还在
        return node;
    }



    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    //从二分搜索树中删除任意一个指定的元素
    public void remove(E e){
        root=remove(root,e);
    }

    //删除以node为根的二分搜索树中值为e的节点  递归算法  返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if( node == null )
            return null;

        if( e.compareTo(node.e) < 0 ){
            node.left = remove(node.left , e);
            return node;
        }
        else if(e.compareTo(node.e) > 0 ){
            node.right = remove(node.right, e);
            return node;
        }
        else{   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            // 这个时候找的是后继 也可以找前驱试试 将2个树融合成一颗树

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
}
