一.基础类型
1.Java 的基础类型和字节大小:
	(1).byte:是8位、有符号的,以二进制补码表示的整数:(256个数字),占1字节
		最小值是-128(-2^7) ~ 最大值是127(2^7-1).	
		默认值是0;
		byte类型用在大型数组中节约空间,主要代替整数,因为byte变量占用的空间只有int类型的四分之一;
		例子:byte a = 100,byte b = -50.
	(2).short:是16位、有符号的以二进制补码表示的整数,占2字节
		最小值是-32768(-2^15) ~ 最大值是32767(2^15 - 1);
		默认值是0;
		short 数据类型也可以像byte那样节省空间.一个short变量是int型变量所占空间的二分之一;
		例子:short s = 1000,short r = -20000
	(3).int:是32位、有符号的以二进制补码表示的整数;占4字节
		最小值是-2,147,483,648(-2^31) ~ 最大值是2,147,485,647(2^31 - 1)
		一般地整型变量默认为int类型;	默认值是0.
		例子:int a = 100000, int b = -200000
	(4).long:是64位、有符号的以二进制补码表示的整数;占8字节
		最小值是-9,223,372,036,854,775,808(-2^63) ~ 最大值是9,223,372,036,854,775,807(2^63 -1);
		这种类型主要使用在需要比较大整数的系统上; 默认值是0L；
		例子:long a = 100000L，int b = -200000L。
		long a=111111111111111111111111(错误，整数型变量默认是int型)
		long a=111111111111111111111111L(正确，强制转换)
	(5).float:是单精度、32位、符合IEEE 754标准的浮点数;占4字节,-3.4*E38- 3.4*E38...浮点数是有舍入误差的
		float 在储存大型浮点数组的时候可节省内存空间;
		默认值是0.0f；
		浮点数不能用来表示精确的值,如货币；
		float f1 = 234.5f.
		float f=6.26(错误  浮点数默认类型是double类型)
		float f=6.26F（转换正确，强制）
		double d=4.55(正确)
	(6).double:是双精度、64位、符合IEEE 754标准的浮点数;
		浮点数的默认类型为double类型;double 类型同样不能表示精确的值,如货币；
		默认值是0.0d;
		例子:double d1 = 123.4。
	(7).boolean:表示一位的信息;
		只有两个取值：true 和 false;
		这种类型只作为一种标志来记录 true/false 情况;
		默认值是 false;
		例子:boolean one = true
	(8).char:是一个单一的16位Unicode字符;用 ''表示一个字符.
		java 内部使用Unicode字符集.他有一些转义字符,占2字节
		最小值是'\u0000'(即为0)
		最大值是'\uffff'(即为65,535),可以当整数来用,它的每一个字符都对应一个数字
2.char 型变量中能不能存贮一个中文汉字?为什么?
	(1).char 型变量可以存储一个汉字
	(2).char 是用来存储 Unicode 编码的字符, 范围在0-65536,Unicode 编码字符集中包含了汉字;
		如果某个特殊的汉字没有被包含在unicode编码字符集中,那么,这个char型变量中就不能存储这个特殊汉字
	(3).不同的看编码占据字节数也不同:utf-32中文是4字节;
		utf-8码的中文都是3字节的,字母是1字节,因为utf-8是变长编码;
    	而 gbk/gbk18030 中文是2字节的,英文是1个字节
3.int 在 32 位和 64 位系统中有什么区别
4.Java 常用命令:
	javac 编译Java源文件为class文件
	java 命令的使用,带package 的Java类如何在命令中启动;
	java程序涉及的各个路径(classpath,java library path)
5.基本类型之间的相互转换:
	http://www.cnblogs.com/ggjucheng/archive/2012/11/20/2779081.html
	5.1.自动类型转换:从存储范围小的类型到存储范围大的类型
		byte → short(char) → int → long → float → double
		将整数类型,特别是比较大的整数类型转换成小数类型时,由于存储方式不同,有可能存在数据精度的损失
	5.2.强制类型转换:是指必须书写代码才能完成的类型转换,该类类型转换很可能存在精度的损失
		转换规则:从存储范围大的类型到存储范围小的类型.
		具体规则为：double → float → long → int → short(char) → byte

二.泛型:
1.泛型的优缺点
	8.1.优点:
		(1).类型安全;
		(2).消除强制类型转换;
		(3).潜在的性能收益

三.异常:
1.Exception 与 Error 的区别:父类都是throwable类
	(1).Exception:表示程序可以处理的异常,可以捕获且可能恢复.遇到这类异常,应该尽可能处理异常,使程序恢复运行,
		而不应该随意终止异常.
		Exception 类又分为运行时异常(Runtime Exception)和受检查的异常(Checked Exception)
	(2).Error:一般是指与虚拟机相关的问题,如系统崩溃,虚拟机错误,内存空间不足,方法调用栈溢等.
		对于这类错误的导致的应用程序中断,仅靠程序本身无法恢复和和预防,遇到这样的错误,建议让程序终止
2.try catch  块, try 里有 return , finally 也有 return ,如何执行这类型的笔试题
	(1).如果 finally 中有 return 语句,是以 finally 中 return 返回值为最终返回值	

其他:
1.Securitymanager:Java安全
2.JDK 各个版本的新特性
	2.1.JDK5:
		(1).引入泛型;
		(2).增强循环,可以使用迭代方式;
		(3).自动装箱与自动拆箱;
		(4).类型安全的枚举,
		(5).可变参数;
		(6).静态引入,
		(7).元数据(注解),
		(8).引入Instrumentation
	2.2.JDK6:
		(1).支持脚本语言
		(2).引入JDBC 4.0 API
		(3).引入Java Compiler API;
		(4).可插拔注解;
		(5).增加对Native PKI、Java GSS、Kerberos 和 LDAP 的支持
		(6).继承Web Services
	2.3.JDK7:
		(1).switch语句块中允许以字符串作为分支条件;
		(2).在创建泛型对象时应用类型推断;钻石语法:Map<String, List<String>> data = new HashMap();
		(3).在一个语句块中捕获多种异常;
		(4).支持动态语言;
		(5)支持 try-with-resources;
		(6).引入Java NIO.2开发包;
		(7).数值类型可以用2进制字符串表示,并且可以在字符串表示中添加下划线;
		(8).null 值的自动处理;
	2.4.JDK8:[http://www.open-open.com/lib/view/open1403232177575.html]
		(1).函数式接口 FunctionalInterface 
		(2).Lambda表达式
		(3).接口的增强.接口中的默认方法.默认方法的继承.单接口实现情况下,默认方法可以直接用,
			多接口实现情况下一旦出现同方法签名的默认方法,那么必须显式覆盖,否则编译不通过.
		(4).Stream 迭代
		(5).新增时间 API
		(6).JVM 的PermGen空间被移除,取代它的是Metaspace(JEP 122)元空间
		(7).数组并行(parallel)操作
3.JDK 中的标记接口:
	3.1.Serializable 序列化

	3.2.RandomAccess:List 实现所使用的标记接口,用来表明其支持快速(通常是固定时间)随机访问.
		此接口的主要目的是允许一般的算法更改其行为,从而在将其应用到随机或连续访问列表时能提供良好的性能

	3.3.Cloneable 克隆

	3.4.EventListener 事件监听
4.for(;;)和while(true)的区别
	(1).大多数时候开启优化两者之间性能没有什么区别。
	(2).while(true)比 for(;;)编译后的指令多。
	(3).while(true)每次编译器都要判断一下 true
	(4).for(;;)比 while(true)敲的字符数更少















	