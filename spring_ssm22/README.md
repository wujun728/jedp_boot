## Younger项目说明
### 前言：
	项目主要用于学习练手。技术都是在git和blog上上现学现卖，有很多不足。但是，前期准备把项目做成一个比较完善的权限管理系统。
	这样，在项目中不断提升自己，同时，也致力于得到一个自己的产品。
### 项目结构：
	--com.wuzy
	--core 包下主要集成JavaBean对应的系统逻辑
	--sys 暂定为后台权限管理
	--web 暂定为业务入口（blog、笔记等功能。）
	--resources 系统资源文件
		--freemarker.properties
		--velocity.properties 
		--jdbc.properties 数据库连接相关，包括mysql、redis
		--log4j.properties 日志
		--mybatis-config.xml mybatis配置文件（可用于配置 别名，驼峰等）
		--spring-mvc.xml 视图层。
			请求处理：
				1.项目使用 RESTful 风格请求；
				2.集成jsp、freemarker及velocity。默认velocity（.vm/.html）优先；然后jsp；最后.ftl。
				  这里，相当于一个规范：请求按照 类名/方法名 （如“/sys/login”）发起，然后controller处理后仍返回该请求。
				  这样spring-mvc.xml配置里就会先在velocity指定的"/WEB-INF/html/"下查找/sys/login.vm与/sys/login.html
				  查不到结果就继续jsp查找"/WEB-INF/view/"下的/sys/login.jsp...以此类推
				  这样做，项目优先处理html页面，同时，也支持jsp与ftl的开发需求。视图层完美解耦！
		--spring-mybatis.xml spring对mybatis的完美支持。
		--spring-shiro.xml spring对shiro的支持。权限控制处理、redis缓存session（这个主要用于集群会话问题处理）
			关于shiro：很容易上手，但是使用bean装配，及redis缓存配置，都是有一定的难度。
		--spring.xml properties与xml文件管理
	--webapp
		--WEB-INF
			--html velocity视图入口
			--view jsp视图入口
			--ftl freemarker视图入口
		--static 静态资源入口
			--plugins 组件
				--jqgrid jquery表格 插件
				--layer 弹出层插件
			--common_css 公用样式提取
			--common_js 公用js提取
			--css 页面对应css，注意命名规范 
			--js 页面对应js，注意命名规范
			--fonts 字体
			注：1.后台用的AdminLTE(基于bootstrap)的UI框架，样式很赞；
			   2.页面使用vue.js，MVVM（双向绑定）很轻量级，也是js架构的一个学习方向。这就是视图层html页面优先的原因。
			   另：视图层开发，建议使用IDEA，对页面支持比较好，特别是用到bootstrap的样式时。
### 项目进度：
----
#####201702规划#####
	1.spring+springMVC+Mybatis 基础架构(已完成)
	2.shiro 权限管理(30%)
	3.redis 缓存(shiro的session正在进行...)
	4.基于角色的权限管理、菜单配置（ztree）
	5.freemarker/velocity 模板引擎(模板待集成)
----
	


	
	