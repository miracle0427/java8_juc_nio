# 1.Lambda 表达式 

Java 8新特性简介：其中最为核心的为 Lambda 表达式与Stream API

- 速度更快

  hashMap数据结构解释:

  哈希表的默认大小为16，对hashcode方法运用hash算法，产生hash值，然后用hash值生成数组的索引值。

  如果该位置上没有元素存在，则存入。

  否则，则运用equals方法判断两个元素是否相同，若相同，则不必存入，

  否则，原来是使用单链表存储来解决冲突问题，后加的放在单链表前面，效率低，默认的加载因子是0.75，达到0.75后进行扩容，重新计算hash值。	JDK1.8以后使用 **数组-链表-红黑树** 来解决冲突，当单链表的长度大于8且总容量大于64，会将单链表转换成红黑树，除了添加外，效率都比链表高，扩容时不用重新计算hash值

- 代码更少（增加了新的语法 Lambda 表达式）

- 强大的 Stream API

- 便于并行 

- 最大化减少空指针异常 Optional

**为什么使用 Lambda 表达式**

Lambda 是一个匿名函数，我们可以把 Lambda 表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使 Java的语言表达能力得到了提升。

# 2.函数式接口 

Java 内置四大核心函数式接口

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210617233856.png" alt="image-20210617233855860" style="zoom:80%;" />

其他接口

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210617233939.png" alt="image-20210617233936091" style="zoom:80%;" />

# 3. 方法引用与构造器引用 

见代码

