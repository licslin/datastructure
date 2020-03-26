package com.licslan.queue;

import java.util.Random;

public class TestQueue {
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
        int optCount = 100000;    // ArrayQueue cost time is 4.74253 秒     LoopQueue cost time is 0.0106495 秒
        //int optCount = 1000000; // ArrayQueue cost time is 701.1282601 秒 LoopQueue cost time is 0.0934843 秒
        //有巨大差异  时间复杂度差异巨大  循环队列性能要好很多

        //数组队列:时间复杂度[O(n^2)]
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double v = testQueue(arrayQueue, optCount);    //出队  dequeue O(n)   testQueue()方法就是O(n2)
        System.out.println("ArrayQueue cost time is "+v+" 秒");

        //循环队列:时间复杂度[O(n)]
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double r = testQueue(loopQueue,optCount ); //出队  dequeue O(1)   testQueue()方法就是O(n)
        System.out.println("LoopQueue cost time is "+r+" 秒");

        //链表队列:时间复杂度[O(n)]
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double x = testQueue(linkedListQueue,optCount ); //出队  dequeue O(1)   testQueue()方法就是O(n)
        System.out.println("linkedListQueue cost time is "+x+" 秒");

    }
}
