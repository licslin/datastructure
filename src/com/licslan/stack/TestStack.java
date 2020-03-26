package com.licslan.stack;

import java.util.Random;

public class TestStack {
    // 测试使用q运行opCount个push和pop操作所需要的时间，单位：秒
    // 运行多次取平均值
    private static double testStack(StackE<Integer> q,int optCount){
        long startTime=System.nanoTime();

        Random random = new Random();
        //入栈操作
        for(int i=0;i<optCount;i++)
            q.push(random.nextInt(Integer.MAX_VALUE));
        //出栈操作
        for(int i=0;i<optCount;i++)
            q.pop();

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        //int optCount = 100000;    // arrayStack cost time is 0.0164159 秒     linkedListStack cost time is 0.011901599 秒
        int optCount = 1000000;     // arrayStack cost time is 0.0559767 秒      linkedListStack cost time is 0.246334201 秒
        //没有巨大差异  时间复杂度一致基本上
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double v = testStack(arrayStack, optCount);
        System.out.println("arrayStack cost time is "+v+" 秒");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double r = testStack(linkedListStack,optCount );
        System.out.println("linkedListStack cost time is "+r+" 秒");

    }
}
