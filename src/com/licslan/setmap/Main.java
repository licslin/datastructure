package com.licslan.setmap;

import java.util.ArrayList;

/**
 * 集合set  &  映射 map  利用BST实现
 * */
public class Main {
    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

//        ArrayList<String> words1 = new ArrayList<>();
//        //String filePath="src/com/licslan/setmap/pride-and-prejudice.txt";
//        String filePath="src/com/licslan/setmap/test.txt";
//        if(FileOperation.readFile(filePath, words1)) {
//            System.out.println("Total words: " + words1.size());
//
//            BSTSet<String> set1 = new BSTSet<>();
//            for (String word : words1)
//                set1.add(word);
//            System.out.println("Total different words: " + set1.getSize());
//        }
//
//        System.out.println();


        ArrayList<String> words1 = new ArrayList<>();
        System.out.println("Pride and Prejudice"); //slow than BSTSet

        String filePath="src/com/licslan/setmap/test.txt";
        if(FileOperation.readFile(filePath, words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


//        System.out.println("A Tale of Two Cities");
//
//        ArrayList<String> words2 = new ArrayList<>();
//        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
//            System.out.println("Total words: " + words2.size());
//
//            BSTSet<String> set2 = new BSTSet<>();
//            for(String word: words2)
//                set2.add(word);
//            System.out.println("Total different words: " + set2.getSize());
//        }


    }
}
