package com.licslan.hashcode;

import com.licslan.setmap.FileOperation;
import com.licslan.tree.avltree.AVLTree;
import com.licslan.tree.avltree.BST;
import com.licslan.tree.rbtree.RBTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println();

        Student student = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student student2 = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student2.hashCode());

        //student  和  student2  使用java中的hashcode方法得到的值是不一样的  因为Java中是根据每个对象的内存地址来计算hash值的 计算得到整型
        //两个不同对象 所以内存地址也是不一样的  如果我们覆盖了默认的Ojbect的hashcode方法就会获得一样的hash值




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


            // Test RBTree
            startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }

            for(String word: words)
                rbt.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");


            // Test HashTable
            startTime = System.nanoTime();

            // HashTable<String, Integer> ht = new HashTable<>();
            HashTable<String, Integer> ht = new HashTable<>(131071);
            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }

            for(String word: words)
                ht.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }

        System.out.println();
    }
}
