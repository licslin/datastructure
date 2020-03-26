package com.licslan.stack;

public class LinkedListStack<E> implements StackE {


    //利用链表实现栈的功能
    
    private LinkedListNew list;
    public LinkedListStack(){
        list = new LinkedListNew();
    }
    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(Object o) {
        list.addFirst(o);

    }

    @Override
    public Object pop() {
        return list.removeFirst();
    }

    @Override
    public Object peek() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> integerLinkedListStack = new LinkedListStack<>();
        for(int i=0;i<5;i++){
            integerLinkedListStack.push(i);
            System.out.println("元素是  "+ integerLinkedListStack);
        }

        integerLinkedListStack.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStack);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        LinkedListStack<Integer> integerLinkedListStackNew = new LinkedListStack<>();
        for(int i=0;i<15;i++){
            integerLinkedListStackNew.push(i);
            System.out.println("元素是  "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        }

        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());
        System.out.println("再操作就报错啦~~~~^_^");
        //integerLinkedListStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerLinkedListStackNew +"  and capacity is "+ integerLinkedListStackNew.getSize());




        //判断右边的字符串括号是否匹配 可以利用栈([{{{{}}}}])    ([{{{{}}}}]]  ([{{{{}}}}))  ([{{{{}})}])

        System.out.println("是匹配的吗？  "+isValid("([{{{{}}}}])")); //true
        System.out.println("是匹配的吗？  "+isValid("([{{{{}}}}]]")); //false


    }


    //LeetCode

    private static boolean isValid(String s){
        //java.util.LinkedListStack<Character> LinkedListStack = new java.util.LinkedListStack<>();  JDK实现的stack
        LinkedListStack<Character> LinkedListStack = new LinkedListStack<>(); //我们自己实现的栈  经过测试我们自己写的栈和JDK都能够实现相同的结构
        for(int i=0;i<s.length();i++){
            //获取元素
            char c = s.charAt(i);
            //判断是否是"("  "{"  "[" 是的话放入stack  左括号
            if(c=='('||c=='['||c=='{')
                //入栈
                LinkedListStack.push(c);
            else {
                //否则就考察的是右括号了
                //栈顶为空 就没有可以匹配的字符啦  返回false
                if(LinkedListStack.isEmpty())
                    return false;
                //拿出栈顶元素对比
                char topChar=(Character) LinkedListStack.pop();
                //将接着遍历的当前元素和栈顶元素进行匹配  不匹配返回false
                if(c==')'&&topChar!='(')
                    return false;
                if(c==']'&&topChar!='[')
                    return false;
                if(c=='}'&&topChar!='{')
                    return false;
            }
        }
        //刚好一一匹配完成  栈数据都拿完了
        return LinkedListStack.isEmpty();
    }

}
