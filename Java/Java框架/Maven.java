一.Maven



二.Maven常用命令
1.mvn -v:查看maven版本

2.mvn compile 编译命令

3.mvn test 测试命令

4.mvn package 打包命令

5.mvn clean 删除target

6.mvn install 安装jar包到本地仓库, 在本地Repository中安装ja

三.自动创建目录骨架:
1.mvn archetype:generate 按照相关提示进行操作

2.mvn archetype:generate -DgroupId=组织名,一般为公司网址的反写+项目名称
						 -DartifactId=项目名次-模块名称
						 -Dversion=版本号
						 -Dpackage=代码所存在的包
						 
四.maven中坐标和仓库
1.坐标:即构件,下面三者构成了构件的唯一标识
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.10</version>

2.仓库:本地仓库和远程仓库
	一般先从本地仓库获取,如果没有再到远程仓库获取,在没有,则报错
	
3.镜像仓库:一般是针对无法访问国外网站
	配置镜像仓库:修改settings.xml文件的<mirrors>
	<mirror>
      <id>maven.net.cn</id>
      <mirrorOf>central</mirrorOf>
      <name>Central mirror in China</name>
      <url>http://maven.net.cn/content/groups/public</url>
    </mirror>
	
五.maven在 eclipse 中的安装和使用:
1.安装:如果是高版本的 eclipse 不需要安装, eclipse已经自带

2.配置:
(1).配置installated JRE: 因为eclipse默认为jre环境,而maven需要使用jdk中的jar包,所以需添加jdk环境
(2).添加 maven 的installations,添加maven的目录
(3).配置 UserSettings 

3.使用:
	直接在pom.xml直接运行,输入相关命令即可

4.新建maven项目时,其jre为1.5版本,如果需要修改版本,修改 settings.xml文件的<profiles>
<profile>
      <id>jdk-1.7</id>

      <activation>
		<activeByDefault>true</activeByDefault>
        <jdk>1.7</jdk>
      </activation>
	  
	  <properties>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
		<maven.compiler.compilerVersion>1.7</maven.compiler.compilerVersion>
	  </properties>
</profile>

六.maven的生命周期和插件
1.完整的项目构建过程:
	清理、编译、测试、打包、集成测试、验证、部署	

2.maven的生命周期:分为三个周期,周期之间相互不影响
	2.1.clean:清理项目,分为三个阶段:
		(1).pre-clean:执行清理前的工作
		(2).clean:清理上一次构建生成的项目
		(3).post-clean:执行清理后的文件
	
	2.2.default:构建项目(最核心)
		compile,test,package,install
			
	2.3.site:生成项目站点
		(1).pre-site:在生成项目站点前要完成的工作
		(2).site:生成项目的站点文档
		(3).post-site:在生成项目站点后要完成的工作
		(4).site-deploy:发布生成的站点到服务器上

七.pom.xml介绍
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  //<!-- 指定当前pom的版本 -->
  <modelVersion>4.0.0</modelVersion>

  <groupId>反写公司的网址+项目名</groupId>
  <artifactId>项目名+模块名</artifactId>
  /*
  <!--
	第一个0表示大版本号,第二个0表示分支版本号,第三个0表示小版本号
	snapshot:快照
	alpha:内部测试版本
	beta:公测版本
	release:稳定版本
	GA:正式发布
  -->
  */
  <version>0.0.1-SNAPSHOT</version>
  // maven打包格式,默认是jar,war,zip
  <packaging>jar</packaging>
  // 项目描述名
  <name>demo</name>
  // 项目的地址
  <url>http://maven.apache.org</url>
  // 项目描述信息
  <description></description>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
	  <type></type>
      <scope>test</scope> // 依赖范围
	  <optional></optional> //设置依赖是否可选,true or false
	  <exclusions> // 排除依赖传递列表
      	<exclusion></exclusion>
      </exclusions>
    </dependency>
  </dependencies>
  
  <dependencyManagement> // 依赖管理
  	<dependencies>
  		<dependency></dependency>
  	</dependencies>
	
  <build> // 构建项目
  	<plugins>
  		<plugin>
  			<groupId>org.apache.maven.plugins</groupId>
  			<artifactId>maven-source-plugin</artifactId>
  			<version>2.4</version>
  			<executions>
  				<execution>
  					<phase>package</phase>
  					<goals>
  						<goal>jar-no-fork</goal>
  					</goals>
  				</execution>
  			</executions>
  		</plugin>
  	</plugins>
  </build>
</project>

八.依赖范围:<scope>,6个值
1.compile(默认值)

2.provided:编译和测试时有效,j2ee的servlet

3.runtime:测试和运行时有效,如JDBC

4.test:测试时有效

5.system:与本机系统相关联,可移植性差

6.import:导入的范围,只使用在dependencyManagement中,表示从其他的pom中导入dependency配置

九.依赖传递:


十.依赖冲突:
1.短路优先:优先解析路径短的版本
2.先声明先优先:路径长度相同的情况下,谁先声明,,谁优先

十一.聚合和继承:





























































































