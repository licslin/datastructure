package com.licslan.hashcode;
/**
 * 背后隐藏了哈希表的数据结构
 * int[26] freq 就是一个哈希表
 * 每一个字符都和一个索引相对应
 * a-0;
 * b-1
 * c-2
 * ....
 * z-25
 *
 * index = ch-'a'
 * 修改查找 O(1)
 * 将键转换为索引方式    设计一个合理的哈希函数  在哈希表上操作
 *
 * 哈希冲突：2个不同的键 通过哈希函数转换后产生一样的索引 这个就是哈希冲突  如何解决哈希冲突呢？ plz look at HashMap
 * 哈希表充分体现了算法领域的经典思想  --- 空间换时间   很多时候我们多存储一些东西 或者多预处理或者缓存一些东西  在执行
 * 我们的算法任务时候  得到结果的效率就会快很多  哈希表是空间和时间之间的平衡
 * "键"通过哈希函数后得到的"索引"越均匀也好  哈希冲突就越少
 *
 * 什么是哈希表这种数据结构呢？
 * 其实就是把我们关心的键通过哈希函数转换成一个索引  然后把我们的内容存在一个数组中   index --> array value
 * 哈希函数设计  ？  有专门的论文会探讨  这里主要讲解一些一般的哈希函数设计原则
 *
 * 大整数设计哈希函数
 * 一个简单的解决办法  取模一个素数会更好  取模一个合数不是更好的办法  背后的数学理论超出课程范畴  和数论有关
 * 那么这个素数如何选择也是和你这个大整数有关的 减少hash冲突 请参考 http://planetmath.org/goodhashtableprimes
 * 10 % 4 -->2   10 % 7 -->3
 * 20 % 4 -->0   20 % 7 -->6
 * 30 % 4 -->2   30 % 7 -->2
 * 40 % 4 -->0   40 % 7 -->4
 * 50 % 4 -->2   50 % 7 -->1
 *
 * 浮点型设计哈希函数  在计算机中都是32位或者64位的二进制位表示 只不过计算机解析成了浮点数
 * sign  exponent(8-bit)                fraction(23-bit)   ---->  32位
 * 0     0 1 1 1 1 1 1 1    0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 31                       23                                          0    <----index
 * sign  exponent(11-bit)                fraction(64-bit)   ---->  64位
 * 0     0 1 1 1 1 1 1 1 1 1 1     0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 63                        52                                                                                                          0  <----index
 * 将浮点型所占用的空间转换成整形  再转换成索引就可以了 就可以按照上面的方式计算了
 * 通过上面我们发现通过一个素数取模  得到的结构分布更加均匀
 *
 * 字符串设计哈希函数
 * 字符串也转换成整型处理
 * 166 = 1*10^2 + 6*10^1 + 6*10^0      <---10进制
 * code  = c * 26^3 + o * 26^2 + d * 26^1 + e * 26^0   <---26进制
 * code  = c * X^3 + o * X^2 + d * X^1 + e * X^0   <---X进制
 * hash(code) = (c * X^3 + o * X^2 + d * X^1 + e * X^0) % M
 * hash(code) = ((((c * X)+ o) * X + d) * X + e) % M
 * hash(code) = ((((c%M) * X+ o)%M  * X + d)%M * X + e) % M
 *
 *
 * 转成整形并不是唯一的方法
 * 原则：
 * 1.一致性  若 a==b hash(a)==hash(b)
 * 2.高效性 计算高效简便
 * 3.均匀性 哈希值均匀分布
 *
 *
 *
 * 哈希冲突如何解决？
 * 开放地址法  ：就是不在同一个位置进行链表是存储 而是发生冲突后 会向该元素地址后面其他元素位置为空的地址存储  后面又发生冲突了 继续向后找并存储
 *             每一个位置都是开放的  只要满足哈希函数规则  发生哈希冲突就可以往后找到空余地方并插入
 *             首先是 [线性探测法] 遇到哈希冲突+1 性能比较低
 *             改进型 [平方探测法] 遇到哈希冲突 +1 +4 +9 +16 ......会跳着选择一些地方 步长序列是一个平方的序列
 *             [二次哈希法] 遇到哈希冲突 +hash2(key)  对之前hash函数一次后的值再进行一次hash计算
 *             上面都是开发地址法  只不过是计算步长不一样  判断负载率  进行扩缩容  O(1)
 * 再哈希法 Rehashing
 * Coalesced Hashing法  综合了 Seperate Chaining （链地址法）+ open Addressing （开发地址法）
 * 链地址法  Seperate Chaining  hashMap hashSet  （封闭地址 具有排他性）
 * 源码  (hashcode(k1) & 0x7fffffff)%M 哈希函数处理后  按位与 再取模     16进制  0x7fffffff  --> 代表 31 个 1  二进制
 * 最高是1 代表一个负数   是0代表一个整数  这样按位与后就会去掉符号了  没有负数了
 *
 * (hashcode(k1) & 0x7fffffff)%M 就会计算一个索引值 a  k1为键的内容就会存储在数组索引为a的位置上面  内容：北京  array[a]-->北京
 * (hashcode(k2) & 0x7fffffff)%M 就会计算一个索引值 b  k2为键的内容就会存储在数组索引为b的位置上面  内容：上海  array[b]-->上海
 * (hashcode(k3) & 0x7fffffff)%M 就会计算一个索引值 b  k3为键的内容就会存储在数组索引为b的位置上面  内容：武汉  array[b]-->上海--->武汉
 * 那么上面的k2  &  k3  为键计算出来的索引值都为b 就会出现hash冲突了  怎么办  这个时候就会形成一个链表的形式  array[b]-->上海--->武汉
 *
 * 查找表  不一定是一个链表结构   此时每个数组里面每一个位置都是一个链表结构  分开的链表 Seperate Chaining
 * 查找表也可以使用平很树结构  此时数组里面每一个位置就不是一个链表  可以是TreeMap(底层红黑树) ....
 * 至于底层的数据查找表是什么数据结构 可以自己合理选择  适合查找就行了  能放入元素 又适合查找
 * Java8之前每一个位置对应一个链表
 * Java8开始当哈希冲突达到一定程度  初始时  每个位置依然是一个链表 平均来讲 每一个位置所存储的元素数大于某一个程度 某一个值了
 * Java8哈希表会自动将每一个位置之前用链表形式存放的元素转化为红黑树的形式来存放元素 (TreeMap) 当数据量比较的小的时候 查询操作链表时比较快的
 * 整体来说 红黑树时间复杂度要低于链表的 当时当哈希冲突较小的时 存储数据规模比较小的时候 比如存2-7左右的元素冲突  此时用链表来添加删除是更加合适的
 * 此时使用红黑树对数据进行添加删除  此时可能会有多次数据翻转 那么性能就不是那么好了  此时有可能就比不上链表了
 *
 *
 * 时间复杂度分析
 * 总共有M个地址
 * 如果放入哈希表的元素为N
 * 如果每个地址是链表 ：O(N/M)
 * 如果每个地址是平衡树：O(log(N/M))
 * 所以如何改进上面说的时间复杂度  让其趋近于O(1)呢？   和静态数组一样  固定地址空间是不合理的 需要resize
 * 平均每个地址承载的元素多过一定程度 即扩容  N/M>=upperTol(容忍度)
 * 平均每个地址承载的元素少过一定程度 即缩容  N/M< lowerTol(容忍度)
 * 这就是哈希表动态扩容机制  和之前讲的动添数组很像
 *
 * 信息安全领域就有一个关于哈希碰撞攻击的案例  知道你的哈希函数怎么设计的 就可以设计一套与之匹配的数据  造成过多的哈希冲突  从而导致拖慢系统
 *
 * */
public class Student {

    //数字
    int grade;
    int cls;
    //字符串
    String firstName;
    String lastName;

    Student(int grade, int cls, String firstName, String lastName){
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    //覆盖默认的hashcode  hashCode() Object类型默认有的
    @Override
    public int hashCode(){

        int B = 31;
        int hash = 0;
        hash = hash * B + ((Integer)grade).hashCode();
        hash = hash * B + ((Integer)cls).hashCode();
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();
        return hash;
    }

    //判断两个对象是否相对 equals() Object类型默认有的
    @Override
    public boolean equals(Object o){

        //判断当前这个对象的和传进来的o是不是同一个引用
        if(this == o)
            return true;

        //是不是空
        if(o == null)
            return false;

        //判断当前这个对象的Class和传进来的是不是相等
        if(getClass() != o.getClass())
            return false;

        //强制类型转换 o-->Student  强制转换后 再来和当前这个对象的每个属性进行比较
        Student another = (Student)o;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }
}
