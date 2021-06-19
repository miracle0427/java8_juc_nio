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

# 4. Stream API 

Java8中有两大最为重要的改变。第一个是 Lambda 表达式；另外一个则是 **Stream API(java.util.stream.*)**。 Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。 使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。也可以使用 Stream API 来并行执行操作。简而言之， Stream API 提供了一种高效且易于使用的处理数据的方式。

流(Stream) 到底是什么呢？ 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。 “集合讲的是数据，流讲的是计算！” 

注意： 

①Stream 自己不会存储元素。 

②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。

③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

详见代码

**并行流与串行流**

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。

Java 8 中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream API 可以声明性地通过 parallel() 与 sequential() 在并行流与顺序流之间进行切换。

**Fork/Join**框架

Fork/Join 框架：就是在必要的情况下，将一个大任务，进行拆分(fork)成若干个小任务（拆到不可再拆时），再将一个个的小任务运算的结果进行 join 汇总.

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210619210407.png" alt="image-20210619210406568"  />

**Fork/Join 框架与传统线程池的区别**

采用 “工作窃取”模式（work-stealing) : 当执行新的任务时它可以将其拆分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。 

相对于一般的线程池实现,fork/join框架的优势体现在对其中包含的任务的处理方式上.在一般的线程池中,如果一个线程正在执行的任务由于某些原因 无法继续运行,那么该线程会处于等待状态.而在fork/join框架实现中,如果某个子问题由于等待另外一个子问题的完成而无法继续运行.那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行.这种方式减少了线程的等待时间,提高了性能.

# 5. 接口中的默认方法与静态方法 

Java 8中允许接口中包含具有具体实现的方法，该方法称为 “默认方法”，默认方法使用 default 关键字修饰。

**接口默认方法的”类优先”原则**

若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时

- 选择父类中的方法。如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略。 
- 接口冲突。如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称和参数列表的方法（不管方法是否是默认方法），那么必须覆盖该方法来解决冲突

Java8 中，接口中允许添加静态方法。

详见代码

# 6. 新时间日期 API 

**使用 LocalDate、LocalTime、LocalDateTime**

LocalDate、LocalTime、LocalDateTime 类的实例是不可变的对象，分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210620012144.png" alt="image-20210620012144152" style="zoom:80%;" />

**Instant 时间戳**

用于“时间戳”的运算。它是以Unix元年(传统的设定为UTC时区1970年1月1日午夜时分)开始所经历的描述进行运算

**Duration 和 Period**

Duration:用于计算两个“时间”间隔 

Period:用于计算两个“日期”间隔

**日期的操纵**

- TemporalAdjuster : 时间校正器。有时我们可能需要获取例如：将日期调整到“下个周日”等操作。
- TemporalAdjusters : 该类通过静态方法提供了大量的常用 TemporalAdjuster 的实现。 例如获取下个周日：

**解析与格式化**

 java.time.format.DateTimeFormatter 类：该类提供了三种格式化方法：

- 预定义的标准格式 
- 语言环境相关的格式 
- 自定义的格式

**时区的处理**

 Java8 中加入了对时区的支持，带时区的时间为分别为 : ZonedDate、ZonedTime、ZonedDateTime 其中每个时区都对应着 ID，地区ID都为 “{区域}/{城市}”的格式 

例如 ：Asia/Shanghai 等 ZoneId：该类中包含了所有的时区信息 

getAvailableZoneIds() : 可以获取所有时区时区信息 

of(id) : 用指定的时区信息获取 ZoneId 对象

**与传统日期处理的转换**

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210620012853.png" alt="image-20210620012853794"  />

# 7. 其他新特性

**Optional 类 **

Optional 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在， 原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。并且可以避免空指针异常。

**可重复注解与类型注解**

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210620020645.png" alt="image-20210620020645411" style="zoom:80%;" />

