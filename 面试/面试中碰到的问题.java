1.NIO 与 IO 的区别:
	1.1.
		在传统的 IO 中,要为每个连接创建一个线程,当并发的连接数量非常巨大时，线程所占用的栈内存和CPU线程切换的开销将非常巨大;
		使用NIO，不再需要为每个线程创建单独的线程，可以用一个含有限数量线程的线程池，甚至一个线程来为任意数量的连接服务;
		由于线程数量小于连接数量,所以每个线程进行IO操作时就不能阻塞,如果阻塞的话,有些连接就得不到处理,
		NIO 提供了这种非阻塞的能力;
	1.2.主要区别:
		(1).Java NIO 和 IO 之间第一个最大的区别是:IO 是面向流的,NIO 是面向缓冲区的:
			Java IO面向流意味着每次从流中读一个或多个字节,直至读取所有字节,它们没有被缓存在任何地方,它不能前后移动流中的数据
			Java NIO数据读取到一个它稍后处理的缓冲区,需要时可在缓冲区中前后移动
		(2).Java IO 的各种流是阻塞的,意味着当一个线程调用read()或write()时,该线程被阻塞,直到有一些数据被读取,或数据完全写入,
			该线程在此期间不能再干任何事情了;
			Java NIO的非阻塞模式,使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时,
			就什么都不会获取,不是保持线程阻塞,所以直至数据变的可以读取之前，该线程可以继续做其他的事
		(3).Java NIO的选择器允许一个单独的线程来监视多个输入通道,你可以注册多个通道使用一个选择器,
			然后使用一个单独的线程来“选择”通道,这些通道里已经有可以处理的输入，或者选择已准备写入的通道。
			这种选择机制，使得一个单独的线程很容易来管理多个通道。
2.JVM 什么时候触发 GC?手动触发 GC 的方式?
	2.1.堆内存划分为 Eden, Survivor, Tenure/Old
		从新生代(Eden, Survivor)回收内存成为 Minor GC,对老年代GC称为 Major GC,而 Full GC 是对整个堆来说,
		都默认包括了对永生代的回收(JDK8 不存在永生代了),出现 Full GC 时经常伴随一次 Minor 对老年代GC称为,但非绝对的,
		Major GC 的速度一般会比 Minor GC 慢10倍以上
	2.2.触发 JVM 的 Full GC 的条件:
		(1).System.gc() 方法的调用:
			①.此方法调用是建议 JVM 进行 Full GC,很多情况下它会触发 Full GC,增加 Full GC 的概率,即增加了间歇性停顿的次数;
			②.强烈影响系建议能不使用此方法就别使用,让虚拟机自己去管理它的内存,
			可通过通过 -XX:+DisableExplicitGC 来禁止 RMI 调用 System.gc
		(2).老年代空间不足:
			①.老年代空间只有在新生代对象转入及创建为大对象、大数组时才会出现不足的现象,当执行 Full GC 后空间仍然不足,
			则抛出如下错误:java.lang.OutOfMemoryError: Java heap space;
			②.为避免以上两种状况引起的 Full GC,调优时应尽量做到让对象在 Minor GC 阶段被回收、让对象在新生代多存活一段
			时间及不要创建过大的对象及数组
		(3).永生区空间不足:
			Permanet Generation 中存放的为一些class的信息、常量、静态变量等数据,当系统中要加载的类、反射的类和调用的
			方法较多时 Permanet Generation 可能会被占满，在未配置为采用 CMS GC 的情况下也会执行 Full GC;
			JVM 会抛出如下错误信息:java.lang.OutOfMemoryError: PermGen space
			为避免 Perm Gen 占满造成 Full GC 现象，可采用的方法为增大 Perm Gen 空间或转为使用 CMS GC
		(4).CMS GC 时出现 promotion failed 和 concurrent mode failure:
			对于采用CMS进行老年代GC的程序而言,尤其要注意GC日志中是否有promotion failed和concurrent mode failure两种状况,
			当这两种状况出现时可能会触发 Full GC
			①.promotion failed是在进行 Minor GC 时,survivor space放不下、对象只能放入老年代,而此时老年代也放不下造成的;
			②.oncurrent mode failure是在执行 CMS GC 的过程中同时有对象要放入老年代,而此时老年代空间不足造成的
		(5).堆中分配很大的对象:
			所谓大对象是指需要大量连续内存空间的java对象,例如很长的数组,此种对象会直接进入老年代,而老年代虽然有很大的剩余空间,
			但是无法找到足够大的连续空间来分配给当前对象，此种情况就会触发JVM进行 Full GC
			为了解决这个问题，CMS 垃圾收集器提供了一个可配置的参数，即 -XX:+UseCMSCompactAtFullCollection 开关参数
			提供了另外一个参数 -XX:CMSFullGCsBeforeCompaction,这个参数用于设置在执行多少次不压缩的Full GC后,跟着来一次带压缩的
		(6).
3.JVM 如何创建一个对象如何分配内存?

4.JVM 堆的结构

5.统计一个字符串中出现次数最多的字母?假设该字符中都是小写字母

6.HTTP协议? Http 是如何区分前台传入的是表单,还是json,还是xml数据






































