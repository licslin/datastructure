package com.licslan.queue;

import java.util.Random;

public class MainCount {
    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    // 运行多次取平均值
    private static double testQueue(QueueQ<Integer> q,int optCount){
        long startTime=System.nanoTime();

        Random random = new Random();
        //入队操作
        for(int i=0;i<optCount;i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        //出队操作
        for(int i=0;i<optCount;i++)
            q.dequeue();

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int optCount = 100000;
        Queue<Integer> queue = new Queue<>();
        double v = testQueue(queue, optCount);    //出队  dequeue O(n)   testQueue()方法就是O(n2)
        System.out.println("Queue cost time is "+v+" 秒");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double r = testQueue(loopQueue,optCount ); //出队  dequeue O(1)   testQueue()方法就是O(n)
        System.out.println("LoopQueue cost time is "+r+" 秒");

    }
}
