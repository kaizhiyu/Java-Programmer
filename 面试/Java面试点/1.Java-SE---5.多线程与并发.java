1.创建多线程的方式? 创建线程几种方式的不同之处? 线程的生命周期? 线程的状态是如何的?
	1.1.创建线程的方式:
		(1).继承 Thread
		(2).实现 Runnable 接口
		(3).Callable:实现 Callable 接口,该接口中的call方法可以在线程执行结束时产生一个返回值
		(4).线程池创建
	1.2.简述线程的生命周期:
		(1).新建态(New):通过线程的创建方式创建线程后,进入新建态态;
		(2).就绪(Runnable):调用 Tread 的start 方法,就会为线程分配私有的方法栈,程序计数器资源,如果得到CPU资源,线程就转为运行状态.
		(3).运行(Running):就绪态得到CPU资源后转为运行态,执行run方法.在调用 yield 方法后,线程由运行转为就绪
		(4).阻塞(Bolcking):线程因为某种原因放弃CPU使用权,暂时停止运行.直到线程进入就绪状态,才有机会转到运行状态.阻塞的情况分三种:
			A.等待阻塞 -- 通过调用线程的wait()方法，让线程等待某工作的完成。
		    B.同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。
		    C.其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态.
		    			当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态
		(5).死亡状态(Dead):线程执行完了或者因异常退出了run()方法,该线程结束生命周期

2.sleep() wait() yield() join()用法与区别:wait方法为什么会定义在 Object 中?
	(1).sleep:在指定时间内让当前正在执行的线程暂停执行，但不会释放"锁标志"
	(2).wait:在其他线程调用对象的notify或notifyAll方法前,导致当前线程等待.线程会释放掉它所占有的"锁标志".
		从而使别的线程有机会抢占该锁,wait,notify,notifyAll必须在同步代码块中.否则抛:IllegalMonitorStateException
	(3).yield:暂停当前正在执行的线程对象,使当前线程重新回到可执行状态,只能使同优先级或更高优先级的线程有执行的机会
	(4).join:等待该线程终止,等待调用join方法的线程结束,再继续执行
	(5).为什么要放在 Object 中?
		JAVA 提供的锁是对象级的而不是线程级的,每个对象都有锁,通过线程获得,
		如果线程需要等待某些锁那么调用对象中的 wait()方法就有意义了,
		如果 wait ()方法定义在 Thread 类中,线程正在等待的是哪个锁就不明显了;
		wait,notify和notifyAll 都是锁级别的操作,所以把他们定义在 Object 类中因为锁属于对象

3.多线程同步的原理
	(1).Java 会为每个Object对象分配一个 monitor(监视器锁),当某个对象的同步方法被多个线程调用时,该对象的 monitor 
	将负责处理这些访问的并发独占要求.
	(2).当一个线程调用一个对象的同步方法时,JVM 会检查该对象的monitor.如果monitor没有被占用,那么该线程就得到了 monitor
	的占有权,可以继续执行该对象的同步方法.如果monitor被其他线程占用,那么该线程被挂起,直到monitor释放.
	(3).当线程退出同步方法调用时,该线程会释放 monitor.其他等待线程可以获得monitor执行同步方法.

4.线程同步的实现方式?为什么会出现线程安全问题?
	4.1.多线程同步的实现方式:
		(1).synchronized 同步方法或者 synchronized 代码块;
		(2).volatile 变量,保证可见性,不保证原子性
		(3).显示锁.Lock
		(4).原子变量
	4.2.线程安全问题出现原因:

5.如何停止一个线程?Thread.setDeamon()的含义
	5.1.停止线程的方法:
		(1).自然终止,run方法执行结束后,线程自动终止;
		(2).stop 方法,已被弃用.stop 是立即终止,会导致一些数据被到处理一部分就会被终止;
		(3).使用volatile 标志位,在run()方法中使用标志位控制程序运行
		(4).使用interrupt()方法中断运行态和阻塞态线程.
			注意使用interrupt()方法中断正在运行中的线程只会修改中断状态位,可以通过isInterrupted()判断

6.解释是一下什么是线程安全?举例说明一个线程不安全的例子
	6.1.线程安全性:当多个线程访问某个类时,这个类始终都表现出正确的行为,那么称这个类是线程安全的;
		由于线程访问无状态对象的行为并不会影响其他线程中操作的正确性,因此无状态对象是线程安全的.
		大都数 Servlet 都是无状态的.只有当 Servlet 在处理请求时需要保存一些信息时,线程安全才会称为问题

7.线程池?如果让你设计一个线程池,如何设计,应该有哪些方法?
	// http://www.cnblogs.com/skywang12345/p/3509903.html
	7.1.Java 线程池:
		(1).Executor:它是"执行者"接口,它是来执行任务的.准确的说:Executor 提供了execute()接口来执行已提交的 Runnable 任务的对象;
			Executor 存在的目的是提供一种将"任务提交"与"任务如何运行"分离开来的机制:
			void execute(Runnable command)
		(2).ExecutorService:继承于 Executor.它是"执行者服务"接口,它是为"执行者接口Executor"服务而存在的.准确的话:
			ExecutorService 提供了"将任务提交给执行者的接口(submit方法),让执行者执行任务(invokeAll, invokeAny方法)"的接口等等;
			// 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行。
			boolean awaitTermination(long timeout, TimeUnit unit)
		(3).AbstractExecutorService:是一个抽象类,，它实现了 ExecutorService 接口;
			AbstractExecutorService 存在的目的是为 ExecutorService 中的函数接口提供了默认实现;
		(4).ThreadPoolExecutor:线程池,它继承于 AbstractExecutorService 抽象类;
		(5).ScheduledExecutorService:是一个接口,它继承于于 ExecutorService,相当于提供了"延时"和"周期执行"功能的ExecutorService
			可以安排任务在给定的延迟后执行,也可以让任务周期的执行
		(6).ScheduledThreadPoolExecutor:
			继承于 ThreadPoolExecutor,并且实现了 ScheduledExecutorService 接口
			类似于Timer,但是在高并发程序中,ScheduledThreadPoolExecutor 的性能要优于 Timer
		(7).Executors:是个静态工厂类.
			它通过静态工厂方法返回 ExecutorService、ScheduledExecutorService、ThreadFactory 和 Callable 等类的对象
	7.2.线程池原理:
		预先启动一些线程,线程无限循环从任务队列中获取一个任务进行执行,直到线程池被关闭.
		如果某个线程因为执行某个任务发生异常而终止,那么重新创建一个新的线程而已.如此反复.
		线程池的实现类是 ThreadPoolExecutor 类:

	7.3.线程池的作用:在程序启动的时候就创建若干线程来响应处理,它们被称为线程池,里面的线程叫工作线程.
		(1).降低资源消耗.通过重复利用已创建的线程降低线程创建和销毁造成的消耗;
		(2).提高响应速度.当任务到达时,任务可以不需要等到线程创建就能立即执行;
		(3).提高线程的可管理性
	7.4.设计一个线程池:
		http://www.cnblogs.com/absfree/p/5357118.html

8.volatile 关键字如何保证内存可见性:
9.synchronized 与 Lock 的区别
10.synchronized 用在代码块和方法上有什么区别? 底层是如何实现的?[参考:../Java/JavaSE/多线程/Java并发与多线程.java]
	偏向锁、轻量级锁、自旋锁、重量级锁,锁的膨胀模型,以及锁的优化原理,为什么要这样设计
11.线程间如何通信?
12.生产者消费者模式的几种实现
13.阻塞队列实现
14.ThreadLocal 的设计理念与作用,ThreadPool 用法与优势,ThreadLocal 在什么情况下会发生内存泄露(线程池)
	参考:Java知识点/Java/JavaSE/多线程/Java并发与多线程.java
	14.1.设计理念和作用:
		(1).ThreadLocal 的作用是提供线程内的局部变量,这种变量在线程的生命周期内起作,减少同一个线程内多个函数或者组件之间一些公共变量
		的传递的复杂度.但是如果滥用 ThreadLocal,就可能会导致内存泄漏
	14.2.ThreadPool 用法与优势

	14.3.ThreadLocal 在什么情况下会发生内存泄露:
		// http://blog.jobbole.com/104364/
		14.3.1.ThreadLocal 的实现:
			每个 Thread 维护一个 ThreadLocalMap 映射表,这个映射表的 key 是 ThreadLocal 实例本身,value 是真正需要存储的 Object.
			就是说 ThreadLocal 本身并不存储值,它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value.
			ThreadLocalMap 是使用 ThreadLocal 的弱引用作为 Key 的,弱引用的对象在 GC 时会被回收.
		14.3.2.为什么会内存泄漏:
			(1).ThreadLocalMap 使用 ThreadLoca l的弱引用作为key,如果一个 ThreadLocal 没有外部强引用来引用它,那么系统 GC 的时候,
				这个 ThreadLocal 势必会被回收,这样一来,ThreadLocalMap 中就会出现key为 null 的 Entry,就没有办法访问这些key为null的
				Entry 的value.如果当前线程再迟迟不结束的话,这些key为null的Entry的value就会一直存在一条强引用链:
				Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value永远无法回收,造成内存泄漏
			(2).ThreadLocalMap 的设计中已经考虑到这种情况,加上了一些防护措施:在ThreadLocal的get(),set(),remove()的时候都会清除线程
				ThreadLocalMap 里所有key为null的value
			(3).但是这些被动的预防措施并不能保证不会内存泄漏:
				==> 使用线程池的时候,这个线程执行任务结束,ThreadLocal 对象被回收了,线程放回线程池中不销毁,这个线程一直不被使用,导致内存泄漏;
				==> 分配使用了ThreadLocal又不再调用get(),set(),remove()方法,那么这个期间就会发生内存泄漏
		14.3.3.为什么使用弱引用:
			(1).key 使用强引用:
				引用的 ThreadLocal 的对象被回收了,但是 ThreadLocalMap 还持有 ThreadLocal 的强引用,
				如果没有手动删除,ThreadLocal 不会被回收,导致 Entry 内存泄漏
			(2).key 使用弱引用:
				引用的 ThreadLocal 的对象被回收了,由于 ThreadLocalMap 持有 ThreadLocal 的弱引用,
				即使没有手动删除,ThreadLocal 也会被回收.value在下一次ThreadLocalMap调用set,get的时候会被清除
			==> 对比上述情况可以发现:
				由于 ThreadLocalMap 的生命周期跟 Thread 一样长,如果都没有手动删除对应key,都会导致内存泄漏,但是使用弱引用可以多一层保障:
				弱引用ThreadLocal不会内存泄漏,对应的value在下一次ThreadLocalMap调用set,get,remove的时候会被清除
			==> ThreadLocal内存泄漏的根源是:
				由于ThreadLocalMap的生命周期跟Thread一样长,如果没有手动删除对应key就会导致内存泄漏,而不是因为弱引用
		14.3.4.ThreadLocal 最佳实践:如何避免内存泄漏:
			每次使用完 ThreadLocal,都调用它的remove()方法,清除数据.
			在使用线程池的情况下,没有及时清理 ThreadLocal,不仅是内存泄漏的问题,更严重的是可能导致业务逻辑出现问题.
15.Executors 创建的三种(JAVA8增加了一种，共四种)线程池的特点及适用范围
	15.1.newFixedThreadPool():
		该方法返回一个固定线程数量的线程池,该线程池中的线程数量始终不变,即不会再创建新的线程,也不会销毁已经创建好的线程,
		自始自终都是那几个固定的线程在工作,所以该线程池可以控制线程的最大并发数;
		==> 适用场景:
	15.2.newCachedThreadPool():
		方法返回一个可以根据实际情况调整线程池中线程的数量的线程池.即该线程池中的线程数量不确定,是根据实际情况动态调整的;
		==> 适用场景:耗时较短的任务;任务处理速度 > 任务提交速度
			这样能避免不断的创建新的线程,使得内存被占满.
	15.3.newSingleThreadExecutor():
		方法返回一个只有一个线程的线程池,即每次只能执行一个线程任务,多余的任务会保存到一个任务队列中,等待这一个线程空闲,
		当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务;
		==> 适用场景:
	15.4.newScheduledThreadPool():
		该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
		==> 适用场景:
	15.5.newSingleThreadScheduledExecutor():
		方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
		==> 适用场景:
16.Concurrent 包,java.util.concurrent 包下用过哪些类
17.乐观锁与悲观锁?乐观锁的设计要点和使用方法
	// http://www.cnblogs.com/ygj0930/p/6561376.html
	17.1.悲观锁:管是否发生多线程冲突,只要存在这种可能,就每次访问都加锁,加锁就会导致锁之间的争夺,有争夺就会有输赢,输者等待;
		synchronized 是一种悲观锁
	17.2.乐观锁:每次去拿数据的时候都认为别人不会修改.
		在更新的时候会判断一下在此期间别人有没有去更新这个数据,可以使用版本号等机制.
		乐观锁适用于多读的应用类型.
		偏向锁、轻量级锁(CAS轮询)、自旋锁 就是基于上述思路的乐观锁
	17.3.乐观锁的设计:
		使用数据版本(Version)记录机制实现,这是乐观锁最常用的一种实现方式.
		即为数据增加一个版本标识
18.锁的等级:方法锁、对象锁、类锁	
19.如果想实现所有的线程一起等待某个事件的发生,当某个事件发生时,所有线程一起开始往下执行的话,
	有什么好的办法吗?
	CyclicBarrier 实现原理
20.CAS、AQS:
	ABA 问题
21.锁的优化
	21.1.锁的优化策略
		① 读写分离
		② 分段加锁
		③ 减少锁持有的时间
		④ 多个线程尽量以相同的顺序去获取资源 
	21.2.锁优化是在JDK的那个版本
		为什么要提出自旋锁?
		自旋锁的原理?
		自旋的缺点?
		什么是自适应自旋?
		锁消除
		锁粗化
		轻量级锁
		偏向锁
22.Java 常见的锁有哪些?
	synchronized对象同步锁
	Lock 同步锁
	
	公平锁与非公平锁
	ReentrantReadWriteLock读写锁
	乐观锁与悲观锁
	死锁

23.可重入锁:ReentrantLock 与 synchronized 都是可重入锁

24.公平锁与非公平锁

25.ReentrantReadWriteLock 读写锁

26.死锁:
	26.1.锁嵌套:
		如果线程A持有锁L并且想获得锁M,线程B持有锁M并且想获得锁L,那么这两个线程将永远等待下去;
	26.2.检查死锁:
		jps -- 获取当前Java虚拟机进程的pid
		jstack pid-打印堆栈,jstack打印内容的最后其实已经报告发现了一个死锁
	26.3.避免死锁:
		(1).让程序每次至多只能获得一个锁.当然,在多线程环境下,这种情况通常并不现实;
		(2).设计时考虑清楚锁的顺序,尽量减少嵌在的加锁交互数量
		(3).既然死锁的产生是两个线程无限等待对方持有的锁,可以设置超时时间.synchronized 不具备这个功能
			使用Lock类中的tryLock方法去尝试获取锁,这个方法可以指定一个超时时
27.分布式锁?

28.何为幂等性控制? 举例说明如何实现幂等性
	28.1.幂等性(Idempotence):HTTP 协议中的一个重要性质.
		HTTP 方法的幂等性是指一次和多次请求某一个资源应该具有同样的结果
29.Doug Lea 在写JUC的时候为什么喜欢使用 for(;;)表示死循环而不是用 while(true)?
30.消息队列有哪些用途?
