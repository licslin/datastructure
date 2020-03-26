package com.licslan.stack;

public class ArrayStack<E> implements StackE {
    //[[[利用数组实现栈]]]
    //LICSLAN  day2 栈学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>
    //栈的理解
    //1.栈也是一种线性结构
    //2.相比数组栈对应的操作也是数组的子集
    //3.只能从一端添加元素  从另一端取出元素   栈顶   入栈  1234    出栈  4321   last in first out

    //栈的应用
    //1.无处在的[undo撤销]
    //2.程序调用的系统栈  子过程 子逻辑的调用  比如对递归的理解
    //3.括号匹配  栈  检查括号匹配

    //stack<E>
    //void push(E) O(1)     E pop()  O(1)   E peek()  O(1) int getSize()  O(1) boolean isEmpty()  O(1)
    //用户来看不关心你底层怎么实现栈的功能(上述的5个方法)  底层实现有多种方法
    //Interface ArrayStack<E>  <-----implement-------ArrayStack<E>   利用动态数组来实现栈

    //动态数组
    ArrayDynamic<E> arrayDynamic;
    public ArrayStack(int capaticy){
        arrayDynamic = new ArrayDynamic<>(capaticy);
    }

    //无参构造方法 不知道stack的初始大小
    public ArrayStack(){
        arrayDynamic = new ArrayDynamic<>();
    }

    public int getCapacity(){
        return arrayDynamic.getcapacity();
    }

    @Override
    public int getSize() {
        return arrayDynamic.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arrayDynamic.isEmpty();
    }

    @Override
    public void push( Object e) {
        E n = (E) e;
        arrayDynamic.addLast(n);
    }

    @Override
    public Object pop() {
        return arrayDynamic.removeLast();
    }

    @Override
    public Object peek() {
        return arrayDynamic.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("ArrayStack: ");
        res.append("[");
        for(int i=0;i<arrayDynamic.getSize();i++){
            res.append(arrayDynamic.getIndex(i));
            if(i!=arrayDynamic.getSize()-1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {
        ArrayStack<Integer> integerArrayStack = new ArrayStack<>();
        for(int i=0;i<5;i++){
            integerArrayStack.push(i);
            System.out.println("元素是  "+ integerArrayStack);
        }

        integerArrayStack.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStack);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        ArrayStack<Integer> integerArrayStackNew = new ArrayStack<>(10);
        for(int i=0;i<15;i++){
            integerArrayStackNew.push(i);
            System.out.println("元素是  "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        }

        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());
        System.out.println("再操作就报错啦~~~~^_^");
        //integerArrayStackNew.pop();
        System.out.println("出栈后的元素是： "+ integerArrayStackNew +"  and capacity is "+ integerArrayStackNew.getCapacity());




        //判断右边的字符串括号是否匹配 可以利用栈([{{{{}}}}])    ([{{{{}}}}]]  ([{{{{}}}}))  ([{{{{}})}])

        System.out.println("是匹配的吗？  "+isValid("([{{{{}}}}])")); //true
        System.out.println("是匹配的吗？  "+isValid("([{{{{}}}}]]")); //false


    }


    //LeetCode

    private static boolean isValid(String s){
        //java.util.ArrayStack<Character> arrayStack = new java.util.ArrayStack<>();  JDK实现的stack
        ArrayStack<Character> arrayStack = new ArrayStack<>(); //我们自己实现的栈  经过测试我们自己写的栈和JDK都能够实现相同的结构
        for(int i=0;i<s.length();i++){
            //获取元素
            char c = s.charAt(i);
            //判断是否是"("  "{"  "[" 是的话放入stack  左括号
            if(c=='('||c=='['||c=='{')
                //入栈
                arrayStack.push(c);
            else {
                //否则就考察的是右括号了
                //栈顶为空 就没有可以匹配的字符啦  返回false
                if(arrayStack.isEmpty())
                    return false;
                //拿出栈顶元素对比
                char topChar=(Character) arrayStack.pop();
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
        return arrayStack.isEmpty();
    }

}
