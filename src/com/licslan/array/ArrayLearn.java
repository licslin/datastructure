package com.licslan.array;

public class ArrayLearn {


    public static void main(String[] args) {
        //LICSLAN  day1 数组学习  数据结构   这个时候开始学习  算法与数据结构!!!  <之前一直没有下决心学数据结构和算法>
        int[] scores = new int[]{
                100,99,89
        };
        for(int score :scores)
            System.out.println("ArrayLearn.main "+score);

        int[] arr = new int[3];
        for(int i=0;i<arr.length;i++)
            arr[i]=i;

        for(int score :arr)
            System.out.println("ArrayLearn.main "+score);


        //使用自己封装的数组
//        Array arrNew=new Array(20);
//        for(int i=0;i<15;i++)
//            //添加元素
//            arrNew.addLast(i);
//
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);
//        System.out.println(arrNew);
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);
//        arrNew.add(2,1000);

//        System.out.println(arrNew.getSize());
//        System.out.println(arrNew);
//        System.out.println(arrNew.getSize());
//        arrNew.addFirst(-1);
//        System.out.println(arrNew);
//        System.out.println(arrNew.getIndex(0));
//        arrNew.set(0,0);
//        System.out.println(arrNew);
//        System.out.println(arrNew.getIndex(0));
//        System.out.println(arrNew.contains(10000));
//        arrNew.delete(0);
//        System.out.println(arrNew);
//        System.out.println(arrNew.removeLast());
//        arrNew.remove(2);
//        System.out.println(arrNew);
//        arrNew.remove(2);
//        System.out.println(arrNew);

        //动态数组测试
        ArrayDynamic<Integer> arrayGeneric = new ArrayDynamic<>(10);
        //没有扩容能力的数组测试
        //ArrayGeneric<Integer> arrayGeneric = new ArrayGeneric<>(10);
        //----->Exception in thread "main" java.lang.IllegalArgumentException: array is full
        //使用自己封装的数组
        for(int i=0;i<15;i++){
            arrayGeneric.addLast(i);
        }
        System.out.println(arrayGeneric);
        ArrayGeneric<Person> personArrayGeneric = new ArrayGeneric<>();
        personArrayGeneric.addLast(new Person("licslan00",1));
        personArrayGeneric.addLast(new Person("licslan01",10));
        personArrayGeneric.addLast(new Person("licslan02",100));
        personArrayGeneric.addLast(new Person("licslan00",1));
        personArrayGeneric.addLast(new Person("licslan01",10));
        personArrayGeneric.addLast(new Person("licslan02",100));
        personArrayGeneric.addLast(new Person("licslan00",1));
        personArrayGeneric.addLast(new Person("licslan01",10));
        personArrayGeneric.addLast(new Person("licslan02",100));
        System.out.println(personArrayGeneric);


        //发生一次扩容
        ArrayDynamic<Person> personArrayDy = new ArrayDynamic<>();
        System.out.println("原始数组的容量  "+personArrayDy);
        personArrayDy.addLast(new Person("licslan00",1));
        personArrayDy.addLast(new Person("licslan01",2));
        personArrayDy.addLast(new Person("licslan02",3));
        personArrayDy.addLast(new Person("licslan03",4));
        personArrayDy.addLast(new Person("licslan04",5));
        personArrayDy.addLast(new Person("licslan05",6));
        personArrayDy.addLast(new Person("licslan06",7));
        personArrayDy.addLast(new Person("licslan07",8));
        personArrayDy.addLast(new Person("licslan08",9));
        personArrayDy.addLast(new Person("licslan09",10));
        personArrayDy.addLast(new Person("licslan10",11));
        personArrayDy.addLast(new Person("licslan11",12));
        System.out.println("扩容之后的容量  "+personArrayDy);
        //发生一次缩小容量  删除一定数量后
        personArrayDy.remove(0);
        personArrayDy.remove(0);
        personArrayDy.remove(0);
        personArrayDy.remove(0);
        System.out.println("缩小之后的容量  "+personArrayDy);





        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~^_^~~~~~~~~~~~~~~~~~~~~~~");


        //动态数组测试
        ArrayDynamic<Integer> integerArrayDynamic = new ArrayDynamic<>();
        System.out.println(integerArrayDynamic);





    }



}
