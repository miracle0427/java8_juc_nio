# 1. Java NIO 简介 

 Java NIO（New IO）是从Java1.4版本开始引入的一个新的IO API，可以替代标准的Java IO API。 NIO与原来的IO有同样的作用和目的，但是使用的方式完全不同，NIO支持面向缓冲区的、基于通道的IO操作。NIO将以更加高效的方式进行文件的读写操作。

# 2. Java NIO 与 IO 的主要区别 

| IO                      | NIO                         |
| ----------------------- | --------------------------- |
| 面向流(Stream Oriented) | 面向缓冲区(Buffer Oriented) |
| 阻塞IO(Blocking IO)     | 非阻塞IO(Non Blocking IO)   |
| (无)                    | 选择器(Selectors)           |

# 3. 缓冲区(Buffer)和通道(Channel) 

Java NIO系统的核心在于：通道(Channel)和缓冲区 (Buffer)。通道表示打开到 IO 设备(例如：文件、套接字)的连接。若需要使用 NIO 系统，需要获取用于连接 IO 设备的通道以及用于容纳数据的缓冲区。然后操作缓冲区，对数据进行处理。

**简而言之，Channel 负责传输， Buffer 负责存储**

**直接与非直接缓冲区**

- 字节缓冲区要么是直接的，要么是非直接的。如果为直接字节缓冲区，则 Java 虚拟机会尽最大努力直接在此缓冲区上执行本机 I/O 操作。也就是说，在每次调用基础操作系统的一个本机 I/O 操作之前（或之后）， 虚拟机都会尽量避免将缓冲区的内容复制到中间缓冲区中（或从中间缓冲区中复制内容）。 
- 直接字节缓冲区可以通过调用此类的 allocateDirect() 工厂方法来创建。此方法返回的缓冲区进行分配和取消分配所需**成本通常高于非直接缓冲区**。直接缓冲区的内容可以驻留在常规的垃圾回收堆之外，因此，它们对应用程序的内存需求量造成的影响可能并不明显。所以，建议将直接缓冲区主要分配给那些易受基础系统的本机 I/O 操作影响的大型、持久的缓冲区。一般情况下，最好仅在直接缓冲区能在程序性能方面带来明显好处时分配它们。 
- 直接字节缓冲区还可以通过 FileChannel 的 map() 方法将文件区域直接映射到内存中来创建。该方法返回 MappedByteBuffer 。Java 平台的实现有助于通过 JNI 从本机代码创建直接字节缓冲区。如果以上这些缓冲区中的某个缓冲区实例指的是不可访问的内存区域，则试图访问该区域不会更改该缓冲区的内容，并且将会在访问期间或稍后的某个时间导致抛出不确定的异常。 
- 字节缓冲区是直接缓冲区还是非直接缓冲区可通过调用其 isDirect() 方法来确定。提供此方法是为了能够在 性能关键型代码中执行显式缓冲区管理。



<figure style="half">
    <img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210709004426.png" alt="image-20210709004426600" style="zoom:50%;" />&nbsp;&nbsp;
    <img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210709004456.png" alt="image-20210709004456332" style="zoom:50%;" />
</figure>

# 4. 文件通道(FileChannel) 

通道（Channel）：由 java.nio.channels 包定义的。Channel 表示 IO 源与目标打开的连接。 Channel 类似于传统的“流”。只不过 Channel 本身不能直接访问数据，Channel 只能与 Buffer 进行交互。

<figure style="half">
    <img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210710231401.png" alt="image-20210710231401572" style="zoom:35%;" />
    &nbsp;&nbsp;
    <img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210710231435.png" alt="image-20210710231434937" style="zoom:35%;" />
    <img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210710231455.png" alt="image-20210710231455230" style="zoom:35%;" />
</figure>

# 5. NIO 的非阻塞式网络通信 

阻塞与非阻塞

- 传统的 IO 流都是阻塞式的。也就是说，当一个线程调用 read() 或 write() 时，该线程被阻塞，直到有一些数据被读取或写入，该线程在此期间不能执行其他任务。因此，在完成网络通信进行 IO 操作时，由于线程会阻塞，所以服务器端必须为每个客户端都提供一个独立的线程进行处理， 当服务器端需要处理大量客户端时，性能急剧下降。 

- Java NIO 是非阻塞模式的。当线程从某通道进行读写数据时，若没有数据可用时，该线程可以进行其他任务。线程通常将非阻塞 IO 的空闲时间用于在其他通道上执行 IO 操作，所以单独的线程可以管理多个输入和输出通道。因此，NIO 可以让服务器端使用一个或有限几个线程来同时处理连接到服务器端的所有客户端。

## 选择器(Selector) 

选择器（Selector） 是 SelectableChannel 对象的多路复用器，Selector 可以同时监控多个 SelectableChannel 的 IO 状况，也就是说，利用 Selector 可使一个单独的线程管理多个 Channel。Selector 是非阻塞 IO 的核心

## SocketChannel、ServerSocketChannel、DatagramChannel 

Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道。

操作步骤：打开SocketChannel 、读写数据 、关闭 SocketChannel

Java NIO中的 ServerSocketChannel 是一个可以 监听新进来的TCP连接的通道，就像标准IO中 的ServerSocket一样。

Java NIO中的DatagramChannel是一个能收发 UDP包的通道。 操作步骤： 打开 DatagramChannel 、接收/发送数据

# 6. 管道(Pipe) 

Java NIO 管道是2个线程之间的单向数据连接。 Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

<img src="https://gitee.com/miracle0427/figurebed/raw/master/images/20210711101146.png" alt="image-20210711101146625" style="zoom:80%;" />

# 7. Java NIO2 (Path、Paths 与 Files )

随着 JDK 7 的发布，Java对NIO进行了极大的扩展，增强了对文件处理和文件系统特性的支持， 以至于我们称他们为 NIO.2。因为 NIO 提供的一些功能，NIO已经成为文件处理中越来越重要的部分。

Java 7 增加了一个新特性，该特性提供了另外一种管理资源的方式，这种方式能自动关闭文件。这个特性有时被称为自动资源管理 (Automatic Resource Management, ARM)， 该特性以 try 语句的扩展版为基础。自动资源管理主要用于，当不再需要文件（或其他资源）时， 可以防止无意中忘记释放它们。

当 try 代码块结束时，自动释放资源。因此不需要显示的调用 close() 方法。该形式也称为“带资源的 try 语句”。 注意： ①try 语句中声明的资源被隐式声明为 final ，资源的作用局限于带资源的 try 语句 ②可以在一条 try 语句中管理多个资源，每个资源以“;” 隔开即可。 ③需要关闭的资源，必须实现了 AutoCloseable 接口或其自接口 Closeable