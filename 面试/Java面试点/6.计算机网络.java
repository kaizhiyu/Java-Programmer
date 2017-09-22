1.OSI 七层模型,包括 TCP/IP 的一些基本知识
	// http://www.cnblogs.com/lemo-/p/6391095.html
	1.1.OSI(Open System Interconnection):称为开放式系统互联参考模型,针对广域网通信进行设计的,将整个网络通信
		份名为7个层次:物理层、数据链路层、网络层、传输层、会话层、表示层、应用层.
		(1).OSI 低四层(从物理层到传输层)定义了如何进行端到端的数据传输,也就是定义了如何通过网卡、物理电缆、
			交换机和路由器进行数据传输.
		(2).而高三层(从会话层到应用层)定义了终端系统的应用程序和用户如何彼此通信,即定义了如何重建从发送方
			到目的方的应用程序数据流
		1.1.1.物理层:是所有计算机网络体系结构的最底层,为所有网络/数据通信提供物理的通信线路.
			是用来构建计算机网络通信和数据传输的通道的.
			中继器,集线器、网线、HUB
		1.1.2.数据链路层:控制网络层与物理层之间的通信,提供点对点的数据传输通道,通过MAC地址寻址把数据传到
			目的节点.为了保证传输,从网络层接收到的数据被分割成特定的可被物理层传输的帧.
			(1).数据链路层在不可靠的物理介质上提供可靠的传输.
				该层的作用包括:物理地址寻址、数据的成帧、流量控制、数据的检错、重发等
			(2).据链路层协议的代表包括:SDLC、HDLC、PPP、STP、帧中继等;
			(3).数据链路可以分为逻辑链路和物理链路.
				网卡,网桥,交换机
		1.1.3.网络层:
			(1).其主要功能是将网络地址翻译成对应的物理地址,并决定如何将数据从发送方路由到接收方
			(2).在网络中,"路由"是基于编址方案、使用模式以及可达性来指引数据的发送.
			(3).网络层是可选的,它只用于当两个计算机系统处于不同的由路由器分割开的网段这种情况,或者当通信应用
				要求某种网络层或传输层提供的服务、特性或者能力时
		1.1.4.传输层:
			(1).以端到端方式建立数据传输连接和通信,屏蔽途径网络中所有低层服务上的差异
			(2).以数据段为基本格式提供流量控制,拥塞控制和差错控制
			(3).传输层的协议:TCP,UDP
		1.1.5.会话层
			(1).维护通信双方应用进程会话;管理双方数据交换进程
			(2).建立通信链接,保持会话过程通信链接的畅通,同步两个节点之间的对话,决定通信是否被中断以及通信
				中断时决定从何处重新发送
				SESSION认证、断点续传
		1.1.6.表示层:数据将按照网络能理解的方案进行格式化
			(1).数据格式转换,数据加密与解密,数据压缩与解压缩
		1.1.7.应用层:是最靠近用户的OSI层
			(1).负责接受用户的各种网络应用进程的调用;
			(2).协议:FTP,SMTP,HTTP
	1.2.TCP/IP 协议:TCP/IP 协议体系结构,是专门针对使用TCP/IP 协议簇的广域网计算机网络而开发的.其只划分了四层:
		应用层、传输层、网际互连层、网络访问层
		1.2.1.应用层:
			将OSI参考模型中的会话层和表示层的功能合并到应用层实现
		1.2.2.传输层:
			传输层的功能是使源端主机和目标端主机上的对等实体可以进行会话
		1.2.3.网际互连层:
			(1).网络互连层是整个 TCP/IP 协议栈的核心.它的功能是把分组发往目标网络或主机
			(2).定义了分组格式和协议,即IP协议(Internet Protocol)
		1.2.4.网络访问层
2.TCP 协议
	// http://www.cnblogs.com/BlueTzar/articles/811160.html
	// http://www.jellythink.com/archives/705
3.TCP 三次握手和四次挥手
	// http://www.cnblogs.com/zmlctt/p/3690998.html
	3.1.三次握手:在TCP/IP 协议中,TCP 协议提供可靠的连接服务,连接是通过三次握手进行初始化的.
		三次握手的目的是同步连接双方的序列号和确认号并交换 TCP 窗口大小信息
		(1).第一次握手:建立连接
			客户端发送连接请求报文段,将 SYN 位置为1,Sequence Number 为 x; 然后,客户端进入 SYN_SEND 状态,等待服务器的确认;
		(2).第二次握手:服务器收到 SYN 报文段
			服务器收到客户端的 SYN 报文段,需要对这个 SYN 报文段进行确认,设置 Acknowledgment Number为 x+1(Sequence Number+1);
			同时,自己自己还要发送SYN请求信息,将 SYN 位置为1,Sequence Number为y;
			服务器端将上述所有信息放到一个报文段(即 SYN+ACK 报文段)中,一并发送给客户端,此时服务器进入 SYN_RECV 状态;
		(3).第三次握手:客户端收到服务器的 SYN+ACK 报文段
			然后将 Acknowledgment Number 设置为 y+1,向服务器发送 ACK 报文段,这个报文段发送完毕以后,
			客户端和服务器端都进入 ESTABLISHED 状态,完成TCP三次握手
	3.2.四次挥手:断开TCP连接
		(1).第一次挥手:
			主机1(可以使客户端，也可以是服务器端),设置 Sequence Number 和 Acknowledgment Number,向主机2发送一个 FIN 报文段;
			此时,主机1进入 FIN_WAIT_1 状态;这表示主机1没有数据要发送给主机2了;
		(2).第二次挥手:
			主机2收到了主机1发送的 FIN 报文段,向主机1回一个 ACK 报文段,Acknowledgment Number 为 Sequence Number 加1;
			主机1进入 FIN_WAIT_2 状态;主机2告诉主机1,我"同意"你的关闭请求;
		(3).第三次挥手:
			主机2向主机1发送 FIN 报文段,请求关闭连接,同时主机2进入 LAST_ACK 状态;
		(4).第四次挥手:
			主机1收到主机2发送的 FIN 报文段,向主机2发送 ACK 报文段,然后主机1进入 TIME_WAIT 状态;
			主机2收到主机1的ACK报文段以后,就关闭连接;
			此时,主机1等待2MSL后依然没有收到回复,则证明Server端已正常关闭,那好,主机1也可以关闭连接了
	3.3.为什么要三次握手:
		为了防止已失效的连接请求报文段突然又传送到了服务端,因而产生错误.防止了服务器端的一直等待而浪费资源
		如:client发出的第一个连接请求报文段并没有丢失,而是在某个网络结点长时间的滞留了,以致延误到连接释放以后的某个时间才到达server
	3.4.为什么要四次挥手:
		TCP 协议是一种面向连接的、可靠的、基于字节流的运输层通信协议.TCP 是全双工模式
	3.5.为什么建立连接协议是三次握手,而关闭连接却是四次挥手呢?
		(1).这是因为服务端的 LISTEN 状态下的 SOCKET 当收到 SYN 报文的连接请求后,它可以把ACK和SYN(ACK起应答作用,而SYN起同步作用)
			放在一个报文里来发送.
		(2).但关闭连接时,当收到对方的 FIN 报文通知时,它仅仅表示对方没有数据发送给你了;
			但未必你所有的数据都全部发送给对方了,所以你可能未必会马上会关闭SOCKET,也即你可能还需要发送一些数据给对方之后,
			再发送FIN报文给对方来表示你同意现在可以关闭连接了,所以它这里的ACK报文和FIN报文多数情况下都是分开发送的
4.http是无状态通信,http的请求方式有哪些,可以自己定义新的请求方式么:
	GET,POST,PUT,DELETE
5.长连接与短连接? 分包如何处? 连接异常.
	// http://www.cnblogs.com/0201zcr/p/4694945.html
	// http://www.cnblogs.com/cswuyg/p/3653263.html
6.socket通信模型的使用，AIO 和 NIO。
7.socket框架netty的使用，以及NIO的实现原理，为什么是异步非阻塞。
8.同步和异步,阻塞和非阻塞。
9.http中,GET POST 的区别:
	// http://www.cnblogs.com/hyddd/archive/2009/03/31/1426026.html
	(1).一般而言,GET 用于获取/查询数据,而 POST 用于更新资源信息;
	(2).根据HTTP规范,GET 用于信息获取,而且应该是安全的和幂等的,安全的意味着该操作用于获取信息而非修改信息.
		幂等的意味着对同一URL的多个请求应该返回同样的结果
	(3).根据HTTP规范,POST 表示可能修改变服务器上的资源的请求.
	(4).GET 请求的数据会附在URL之后,就是把数据放置在HTTP协议头中,以?分割URL和传输数据,参数之间以&相连;
		POST 把提交的数据则放置在是HTTP包的包体中
	(5).GET 是通过URL提交数据,那么GET可提交的数据量就跟URL的长度有直接关系了.实际上,URL 不存在参数上限的问题,
		HTTP 协议规范没有对URL长度进行限制.这个限制是特定的浏览器及服务器对它的限制.
		IE 对URL长度的限制是2083字节(2K+35);
	(6).POST 是没有大小限制的,HTTP 协议规范也没有进行大小限制;
	(7).POST 的安全性要比GET的安全性高
10.说说 HTTP,UDP,TCP 之间关系和区别.
	(1).HTTP 是应用层协议,UDP 和 TCP 是传输层协议;
	(2).HTTP 利用 TCP 在两台电脑(通常是Web服务器和客户端)之间传输信息的协议
	(3).TCP 协议是有连接的,有连接的意思是开始传输实际数据之前TCP的客户端和服务器端必须通过三次握手建立连接,
		会话结束之后也要结束连接;而UDP是无连接的
	(4).TCP 协议保证数据按序发送,按序到达,提供超时重传来保证可靠性;
		UDP 不保证按序到达,甚至不保证到达,只是努力交付,即便是按序发送的序列,也不保证按序送到.
	(5).TCP 协议所需资源多,TCP 首部需20个字节(不算可选项);UDP 首部字段只需8个字节。
	(6).TCP 有流量控制和拥塞控制;UDP 没有，网络拥堵不会影响发送端的发送速率
	(7).TCP 是一对一的连接;而UDP则可以支持一对一、多对多、一对多的通信。
	(8).TCP 面向的是字节流的服务;UDP 面向的是报文的服务
11.说说浏览器访问www.taobao.com，经历了怎样的过程.

12.HTTP 协议,HTTPS 协议,SSL 协议及完整交互过程；
13.tcp的拥塞，快回传，ip的报文丢弃
14.https处理的一个过程，对称加密和非对称加密
15.head各个特点和区别
16.DNS 解析:主要是将域名解析成IP地址
	16.1.DNS 协议:
		是应用层协议,使用客户机/服务器模式在通信的端系统之间运行
	16.2.DNS 解析过程:[/Java知识点/Java/JavaEE/DNS解析过程.png]
		(1).浏览器会检查缓存中有没有这个域名对应的解析过的IP地址,如果缓存中存在,则这个解析过程结束.
			浏览器缓存域名也是有限制的,不仅浏览器的缓存大小有限制,而且缓存的时间也有限制.
		(2).如果用户浏览器缓存中没有数据,浏览器会查找操作系统缓存中是否有这个域名对应的DNS解析结果.操作系统也有一个域名解析的过程.
		 	windows是通过C:\Windows\System32\drivers\etc\hosts文件来设置,在Linux中可以通过/etc/hosts文件来设置,
		 	用户可以将任何域名解析到任何能够访问的IP地址;
		(3).前两个过程无法解析时,就需要用到DNS服务器地址了.操作系统会把这个域名发送给 LDNS(本地区的域名服务器).
		 	专门的域名解析服务器性能都会很好,它们一般都会缓存域名解析结果,当然缓存时间是受到域名的失效时间控制的.
		 	大约80%的域名解析到这里就结束了,所以LDNS主要承担了域名的解析工作;
		(4).如果 LDNS 仍未命中,就直接到 Root Server 域名服务器请求解析;
		(5).根域名服务器返回本地域名服务器一个所查询的主域名服务器(gTLD Server)的地址.
		 	gTLD是国际顶级域名服务器,如.com、.cn、.org等,全球只有13台左右
		(6).本地域名服务器 LDNS 再向上一步返回的 gTLD 服务器发送请求.
		(7).接受请求的gTLD服务器查找并返回此域名对应的Name Server 域名服务器的地址,这个Name Server 通常就是用户注册的
			域名服务器;
		(8).Name Server 域名服务器会查询存储的域名和IP的映射关系表,在正常情况下都根据域名得到目标IP地址,
			连同一个TTL值返回给 DNS Server 域名服务器;
		(9).返回该域名对应的IP和TTL值,LDNS 会缓存这个域名和IP的对应关系,缓存时间由TTL值控制;
		(10).把解析的结果返回给用户,用户根据TTL值缓存在本地系统中,域名解析过程结束.
	16.3.清除缓存的域名:
		缓存解析结果的位置:Local DNS Server 和 用户的本地机器
		(1).windows: ipconfig /flushdns
		(2).Linux 环境下可以通过/etc/init.d/nscd restart来清除缓存
		(3).Java 中 JVM 也会缓存 DNS 解析的结果,这个缓存是在 InetAddress 类中完成的,这个缓存时间比较特殊,
			有两种缓存策略:一种是正确的解析结果,一种是错误的解析结果;
			这两个缓存时间有两个配置项控制,配置项在 %JRE_HOME%/lib/security/java.security 文件中配置的.
			对应配置项分别为:networkaddress.cache.ttl,networkaddress.cache.negative.ttl,默认值分别为 -1(永不失效)
			和 10(缓存10秒).直接修改这两个值就可以了,也可以通过在Java启动参数中增加 -Dsun.net.inetaddr.ttl=xxx来
			修改默认值,也可以通过 InetAddress 类动态修改;
			==> 如果需要使用 InetAddress 类解析域名,必须是单例模式,不然会有验证的性能问题.
	16.4.域名解析方式:
		域名解析记录主要分为 A记录、MX记录、CNAME记录、NS记录 和 TXT记录
		(1).A记录:A 代表 Address,用来指定域名对应的IP地址,如将item.taobao.com指定到115.238.23.xxx,将switch.taobao.com
			指定到121.14.24.xxx. A 记录可以将多个域名解析到一个IP地址,但是不能将一个域名解析到多个IP地址;
		(2).MX记录:Mail Exchange,就是可以将某个域名下的邮件服务器指向自己的 Mail Server,如taobao.com域名的	A记录IP地址
			是115.238.25.xxx,如果将MX记录设置为115.238.25.xxx,即xxx@taobao.com的邮件路由,DNS 会将邮件发送到115.238.25.xxx
			所在的服务器,而正常通过Web请求的话仍然解析到A记录的IP地址;
		(3).CANME 记录:Canonical Name,即别名解析,所谓别名解析就是可以为一个域名设置一个或者多个别名;
		(4).NS记录:为某个域名指定 DNS 解析服务器,也就是这个域名由指定的IP地址的DNS服务器取解析;
		(5).TXT记录:为某个主机名或域名设置说明,如可以为ddd.net设置TXT记录为"这是XXX的博客"这样的说明
17.在不使用WebSocket情况下怎么实现服务器推送的一种方法:心跳检测

18.浏览器缓存机制[http://blog.csdn.net/longxibendi/article/details/41630389]
	18.1.浏览器缓存控制机制有两种:HTML Meta 标签 vs. HTTP 头信息
		(1).HTML Meta 标签控制缓存:
			浏览器缓存机制,其实主要就是HTTP协议定义的缓存机制,如:Expires, Cache-control等
			但是也有非HTTP协议定义的缓存机制,如使用HTML Meta 标签,Web 开发者可以在HTML页面的<head>节点中加入<meta>标签
			<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		(2).HTTP 头信息控制缓存:
			浏览器第一次请求:/Java知识点/Java/JavaEE/浏览器第一次请求流程图.png
			浏览器再次请求时:/Java知识点/Java/JavaEE/浏览器请求流程.png
	18.2.几个重要概念:
		(1).Expires 策略:
			Expires 是Web服务器响应消息头字段,在响应http请求时告诉浏览器在过期时间前浏览器可以直接从浏览器缓存取数据,而无需再次请求
			Expires 是HTTP 1.0的东西,现在默认浏览器均默认使用HTTP 1.1,所以它的作用基本忽略;
			Expires 的一个缺点就是:返回的到期时间是服务器端的时间;
			在HTTP 1.1版开始,使用 Cache-Control: max-age=秒替代;
		(2).Cache-control策略:
			Cache-Control与 Expires的作用一致,都是指明当前资源的有效期,控制浏览器是否直接从浏览器缓存取数据还是重新发
				请求到服务器取数据.如果同时设置的话,其优先级高于Expires;
			值可以是:public、private、no-cache、no- store、no-transform、must-revalidate、proxy-revalidate、max-age
			各个消息中的指令含义如下:
				A.public:指示响应可被任何缓存区缓存.
				B.private:指示对于单个用户的整个或部分响应消息.不能被共享缓存处理.
					这允许服务器仅仅描述当用户的部分响应消息.此响应消息对于其他用户的请求无效。
				C.no-cache:指示请求或响应消息不能缓存,该选项并不是说可以设置"不缓存".容易望文生义.
				D.no-store:用于防止重要的信息被无意的发布.在请求消息中发送将使得请求和响应消息都不使用缓存,完全不存下來.
				E.max-age:指示客户机可以接收生存期不大于指定时间(以秒为单位)的响应.
				F.min-fresh:指示客户机可以接收响应时间小于当前时间加上指定时间的响应.
				G.max-stale:指示客户机可以接收超出超时期间的响应消息.如果指定max-stale消息的值,
					那么客户机可以接收超出超时期指定值之内的响应消息
		(3).Last-Modified/If-Modified-Since:Last-Modified/If-Modified-Since 要配合 Cache-Control 使用.
			Last-Modified:标示这个响应资源的最后修改时间.web服务器在响应请求时,告诉浏览器资源的最后修改时间.
			If-Modified-Since:当资源过期时(使用Cache-Control标识的max-age)发现资源具有 Last-Modified声明,则再次向web服务器请求
				时带上头 If-Modified-Since,表示请求时间.web服务器收到请求后发现有头 If-Modified-Since 则与被请求资源的最后修改时
				间进行比对.若最后修改时间较新,说明资源又被改动过,则响应整片资源内容(写在响应消息包体内)HTTP 200;
				若最后修改时间较旧,说明资源无新修改,则响应HTTP 304 (无需包体，节省浏览),告知浏览器继续使用所保存的cache;
		(4).Etag(实体标识):使用 Last-Modified 已经足以让浏览器知道本地的缓存副本是否足够新,为什么还需要Etag?
			HTTP1.1中Etag的出现主要是为了解决几个 Last-Modified 比较难解决的问题:
				Last-Modified 标注的最后修改只能精确到秒级,如果某些文件在1秒钟以内,被修改多次的话,它将不能准确标注文件的修
				改时间如果某些文件会被定期生成,当有时内容并没有任何变化,但 Last-Modified 却改变了,导致文件没法使用缓存
				有可能存在服务器没有准确获取文件修改时间,或者与代理服务器时间不一致等情形
			Etag 是服务器自动生成或者由开发者生成的对应资源在服务器端的唯一标识符,能够更加准确的控制缓存.
			Last-Modified 与 ETag一起使用时,服务器会优先验证ETag
	18.3.几种状态码的区别:
		(1).200 状态:当浏览器本地没有缓存或者下一层失效时,或者用户点击了 CTRL+F5 时,浏览器直接去服务器下载最新数据;
		(2).304 状态:这一层由 Last-Modified/ETag 控制.当下一层失效时或用户点击refresh,F5时,浏览器就会发送请求给服务器,
			如果服务器端没有变化,则返回304给浏览器;
		(3).200 (form cache):这一层由 expire/cache-control 控制,expires(http1.0有效)是绝对时间,cache-control(http1.1)相对时间,
			两者都存在时,cache-control 覆盖 expires,只要没有失效,浏览器只访问自己的缓存.
	18.4.用户行为与缓存:
		用户行为 	|Expires/Cache-Control| Last-Modified/Etag	
		地址栏回车		有效					有效
		页面链接跳转	有效					有效
		新开窗口		有效					有效
		前进/后退		有效					有效
		F5刷新			无效(BR重置max-age=0)	有效
		Ctrl+F5刷新		无效(重置CC=no-cache)	无效
19.CDN 工作机制

20.负载均衡

21.HTTP code:
	(1).常见的状态码:
		200-->服务器成功返回, 404-->请求的网页不存在, 503-->服务不可用
	(2).HTTP: Status 1xx  (临时响应)
		->表示临时响应并需要请求者继续执行操作的状态代码.
		100-->请求者应当继续提出请求.服务器返回此代码表示已收到请求的第一部分.正在等待其余部分
		101-->请求者已要求服务器切换协议,服务器已确认并准备切换
	(3).HTTP Status 2xx  (成功)->表示成功处理了请求的状态代码;
		HTTP Status 200 (成功) -> 服务器已成功处理了请求. 通常这表示服务器提供了请求的网页.
		HTTP Status 201 (已创建)-> 请求成功并且服务器创建了新的资源
		HTTP Status 202 (已接受)-> 服务器已接受请求,但尚未处理.
		HTTP Status 203 (非授权信息)-> 服务器已成功处理了请求,但返回的信息可能来自另一来源.
		HTTP Status 204 (无内容)-> 服务器成功处理了请求,但没有返回任何内容.
		HTTP Status 205 (重置内容)-> 服务器成功处理了请求,但没有返回任何内容.
		HTTP Status 206 (部分内容)-> 服务器成功处理了部分 GET 请求
	(4).HTTP Status 3xx (重定向)->表示要完成请求,需要进一步操作.通常,这些状态代码用来重定向
	(5).HTTP Status 4xx (请求错误)-->这些状态代码表示请求可能出错,妨碍了服务器的处理
		HTTP Status 400 (错误请求) -->服务器不理解请求的语法.
		HTTP Status 401 (未授权) -->请求要求身份验证. 对于需要登录的网页,服务器可能返回此响应.
		HTTP Status 403 (禁止)	--> 服务器拒绝请求.
		HTTP Status 404 (未找到) -->服务器找不到请求的网页.
		HTTP Status 405 (方法禁用) -->禁用请求中指定的方法.
		HTTP Status 406 (不接受) -->无法使用请求的内容特性响应请求的网页.
		HTTP Status 407 (需要代理授权) -->此状态代码与 401(未授权)类似，但指定请求者应当授权使用代理.
		HTTP Status 408 (请求超时)-->服务器等候请求时发生超时.
		HTTP Status 409 (冲突)-->服务器在完成请求时发生冲突. 服务器必须在响应中包含有关冲突的信息.
		HTTP Status 410 (已删除)--> 如果请求的资源已永久删除，服务器就会返回此响应.
		HTTP Status 411 (需要有效长度) -->服务器不接受不含有效内容长度标头字段的请求.
		HTTP Status 412 (未满足前提条件) -->服务器未满足请求者在请求中设置的其中一个前提条件.
		HTTP Status 413 (请求实体过大) -->服务器无法处理请求，因为请求实体过大，超出服务器的处理能力.
		HTTP Status 414 (请求的 URI 过长) 请求的 URI(通常为网址)过长，服务器无法处理.
		HTTP Status 415 (不支持的媒体类型)-->请求的格式不受请求页面的支持.
		HTTP Status 416 (请求范围不符合要求) -->如果页面无法提供请求的范围，则服务器会返回此状态代码.
		HTTP Status 417 (未满足期望值)-->服务器未满足”期望”请求标头字段的要求
	(6).HTTP Status 5xx (服务器错误)-->这些状态代码表示服务器在尝试处理请求时发生内部错误.这些错误可能是服务器本身的错误,而不是请求出错.
		HTTP Status 500 (服务器内部错误) -->服务器遇到错误,无法完成请求.
		HTTP Status 501 (尚未实施) -->服务器不具备完成请求的功能. 例如,服务器无法识别请求方法时可能会返回此代码.
		HTTP Status 502 (错误网关) -->服务器作为网关或代理,从上游服务器收到无效响应.
		HTTP Status 503 (服务不可用)--> 服务器目前无法使用(由于超载或停机维护). 通常,这只是暂时状态.
		HTTP Status 504 (网关超时)	-->服务器作为网关或代理,但是没有及时从上游服务器收到请求.
		HTTP Status 505 (HTTP 版本不受支持)	--> 服务器不支持请求中所用的 HTTP 协议版本.
22.跨域问题:
	// https://segmentfault.com/a/1190000011145364
	22.1.什么是跨域:跨域是指一个域下的文档或脚本试图去请求另一个域下的资源
		是因为JavaScript同源策略的限制,a.com 域名下的js无法操作b.com或是c.a.com域名下的对象.
		当两个域具有相同的协议(如http), 相同的端口(如80),相同的host(如www.example.org),那么我们就可以认为它们是相同的域,
		比如 "http://www.example.org/index.html"和"http://www.example.org/sub/index.html"是同域,
		而 "http://www.example.org", "https://www.example.org", "http://www.example.org:8080", "http://sub.example.org"
		中的任何两个都将构成跨域;
	22.2.广义的跨域:
		(1).资源跳转:A 链接,重定向,表单提交
		(2).资源嵌入:<link>,<script>,<img>,<frame>等dom标签,还有样式中的background:url(),@font-face等文件外链;
		(3).脚本请求:js发起的ajax请求,dom和js的跨域操作;
		我们常说的跨域是狭义的,是由浏览器同源策略限制的一类请求场景
	22.3.什么是同源策略:(SOP-Same Origin Policy)
		(1).同源策略是一种约定,是浏览器最核心的也是最基本的安全功能,如果缺少同源策略很容易收到XSS,CSRF 攻击.
			所谓同源,即"协议+域名+端口"三者相同,即使是两个不同的域名指向同一个IP地址,也非同源
		(2).同源策略限制以下行为:
			cookie,localString 和indexDb无法读取;
			dom和js对象无法获得
			ajax请求不能发送
	22.4.常见跨域场景:
		URL                                      说明                    是否允许通信
		http://www.domain.com/a.js
		http://www.domain.com/b.js         同一域名，不同文件或路径           允许
		http://www.domain.com/lab/c.js

		http://www.domain.com:8000/a.js
		http://www.domain.com/b.js         同一域名，不同端口                不允许
		 
		http://www.domain.com/a.js
		https://www.domain.com/b.js        同一域名，不同协议                不允许
		 
		http://www.domain.com/a.js
		http://192.168.4.12/b.js           域名和域名对应相同ip              不允许
		 
		http://www.domain.com/a.js
		http://x.domain.com/b.js           主域相同，子域不同                不允许
		http://domain.com/c.js
		 
		http://www.domain1.com/a.js
		http://www.domain2.com/b.js        不同域名                         不允许
	22.5.常见跨域解决方案:
		22.5.1.通过jsonp跨域:
			(1).通常为了减轻web服务器的负载,我们把js、css、img等静态资源分离到另一台独立域名的服务器上,在html页面中再通过相应的标签从不同域名
				下加载静态资源,而被浏览器允许.基于此原理.我们可以通过动态创建script.再请求一个带参网址实现跨域通信.
			(2).原生实现:
				<script>
				    var script = document.createElement('script');
				    script.type = 'text/javascript';
				    // 传参并指定回调执行函数为onBack
				    script.src = 'http://www.domain2.com:8080/login?user=admin&callback=onBack';
				    document.head.appendChild(script);
				    // 回调执行函数
				    function onBack(res) {
				        alert(JSON.stringify(res));
				    }
				 </script>
				 服务端返回如下:
				 onBack({"status": true, "user": "admin"})
			(3).jQuery实现:
				$.ajax({
				    url: 'http://www.domain2.com:8080/login',
				    type: 'get',
				    dataType: 'jsonp',  // 请求方式为jsonp
				    jsonpCallback: "onBack",    // 自定义回调函数名
				    data: {}
				});
			(4).缺点:只能实现get一种请求
		22.5.2.document.domain + iframe跨域:此方案仅限主域相同,子域不同的跨域应用场景
			(1).实现原理:两个页面都通过js强制设置document.domain为基础主域,就实现了同域.
			(2).父窗口:(http://www.domain.com/a.html)
				<iframe id="iframe" src="http://child.domain.com/b.html"></iframe>
				<script>
				    document.domain = 'domain.com';
				    var user = 'admin';
				</script>
			(3)子窗口:(http://child.domain.com/b.html)
				<script>
				    document.domain = 'domain.com';
				    // 获取父窗口中变量
				    alert('get js data from parent ---> ' + window.parent.user);
				</script>
		22.5.3.location.hash + iframe:
			(1).实现原理:a欲与b跨域相互通信,通过中间页c来实现. 三个页面.不同域之间利用iframe的location.hash传值.相同域之间直接js访问来通信
			(2).具体实现:A域:a.html ---> B域:b.html ---> A域:c.html,a与b不同域只能通过hash值单向通信,b与c也不同域也只能单向通信,但c与a同域,
				所以c可通过parent.parent访问a页面所有对象
			(3).
		22.5.4.window.name + iframe跨域
		22.5.5.postMessage跨域
		22.5.6.跨域资源共享（CORS）
		22.5.7.nginx代理跨域
		22.5.8.nodejs中间件代理跨域
		22.5.9.WebSocket 协议跨域


