package com.licslan.queue;


import java.util.*;
import java.util.PriorityQueue;

/**
 * 使用Minheap JDK  PriorityQueue(最小堆)  实现优先队列这种数据结构
 * */
public class PriorityQueueJDK {

    //优先队列的经典问题


    //在100000000个元素中选出前100名？
    // 1.在N个元素中选出前M个元素  排序  Nlogn
    // 2.优先队列  维护当前看到的前M个元素需要使用最小堆 NlogM

    //Leetcode No347 前K个高频元素   给定数组[1,1,1,2,2,3,4,5,6] k=2 返回[1,2]



    //可比较的内部类
    //有了下面的FreqComparator就不用下面的Freq去实现Comparable<Freq>
    /*private class Freq implements Comparable<Freq>{

        public int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another){
            if(this.freq < another.freq)
                return -1;
            else if(this.freq > another.freq)
                return 1;
            else
                return 0;
        }
    }*/

    private class Freq{
        public int e, freq;
        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }
    }

    private class FreqComparator implements Comparator<Freq>{
        @Override
        public int compare(Freq a,Freq b){
            return a.freq-b.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        //PriorityQueue<Freq> pq = new PriorityQueue<>();  JDK  PriorityQueue
        PriorityQueue<Freq> pq = new PriorityQueue<>(new FreqComparator());
        for(int key: map.keySet()){
            if(pq.size() < k)
                pq.add(new Freq(key, map.get(key)));
            else if(map.get(key) > pq.peek().freq){
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty())
            res.add(pq.remove().e);
        return res;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        printList((new PriorityQueueJDK()).topKFrequent(nums, k));
    }




    //索引堆？ 2叉堆  多叉堆  2项堆？  斐波那契堆？  广义队列普通队列 优先队列  栈
}
