### `jun_spring` 项目

#### 项目说明
多个Java常用的lib的组件项目，主要是详尽的了解项目里面的每个lib组件及功能，为个人及项目实际开发提供助力，帮组项目快速开发，本项目是maven项目，每个组件均可以独立运行且不依赖第三方框架，具体见项目明细。

#### `笔者其他项目` 功能实现&使用
- jun_springboot：[点击查看](https://github.com/wujun728/jun_springboot) 
- jun_springcloud：[点击查看](https://github.com/wujun728/jun_springcloud) 
- jedp：[点击查看](https://github.com/wujun728/jedp) 

#### 基础篇(组件)：企业级应用开发组件功能列表（jun_spring）
- 【spring_activity】[工作流：通过Spring集成activity工作流完成流程发起、待办会签等操作](https://github.com/wujun728/jun_spring)
- 【spring_cas_sso】[单点登录：通过Spring集成Cas sso单点登录操作](https://github.com/wujun728/jun_spring)
- 【spring_core】[核心Bean：SpringBean各类操作](https://github.com/wujun728/jun_spring)
- 【spring_data_jpa】[JPA数据层操作、完成单表、多表CRUD功能](https://github.com/wujun728/jun_spring)
- 【spring_doubbo】[dubbo RPC调用框架](https://github.com/wujun728/jun_spring)
- 【spring_email】[使用Spring集成邮件操作，便捷操作邮件、文本邮件、Html邮件、附件邮件等](https://github.com/wujun728/jun_spring)
- 【spring_fastdfs】[分布式文件管理、完成文件的上传下载、读取等操作](https://github.com/wujun728/jun_spring)
- 【spring_generator】[Spring代码生成器](https://github.com/wujun728/jun_spring)
- 【spring_hibernate】[Hibernate数据层框架集成Spring，完成数据层单表、多表的增删改查等](https://github.com/wujun728/jun_spring)
- 【spring_jsonp】[JSONP跨域解决方案、前后端骚操作](https://github.com/wujun728/jun_spring)
- 【spring_quartz 】[定时任务Quartz集成Spring框架，完成任务的调度、执行处理、分布式任务调度等](https://github.com/wujun728/jun_spring)
- 【spring_rabbitmq】[MQ消息的部署、发送、订阅、TOPIC处理等操作](https://github.com/wujun728/jun_spring)
- 【spring_redis】[Redis缓存集成Spring的操作、通过注解即开即用](https://github.com/wujun728/jun_spring)
- 【spring_rest】[完成RESTful接口的Spring操作，发送PUT、GET、POST、DELETE请求并处理请求](https://github.com/wujun728/jun_spring)
- 【spring_security】[Spring安全框架，集成常用的几个接口、完成安全验证过滤等](https://github.com/wujun728/jun_spring)
- 【spring_shiro】[Spring集成的Apache的shiro安全框架，集成几个接口就可以搞定](https://github.com/wujun728/jun_spring)
- 【spring_ssh】[Spring集成的Struts及Hibernate的框架的CURD的demo，即开即用](https://github.com/wujun728/jun_spring)
- 【spring_ssh2】[Spring集成的Struts2及Hibernate的框架的CURD的demo，即开即用](https://github.com/wujun728/jun_spring)
- 【spring_test 】[Spring集成的JUNIT及TestNG框架，编辑的Spring测试集成](https://github.com/wujun728/jun_spring)
- 【spring_websocket】[Spring集成的Websocket长链接，完成长链接会话](https://github.com/wujun728/jun_spring)

#### 已办&待办列表
1. 完善Spring集成Activity
1. 梳理当前的代码及功能


 

jun_springmvc   --> jun_mybatisplus_generator
jun_springbootcodegenerator   ---在线代码生成器
jun_spring_cloud_codegen  


https://github.com/dcloudio/casecode


https://github.com/wayss000/WebDemo
https://github.com/yuchenggroup/rapid-generator
https://github.com/cncounter

https://github.com/shenkunlin/code-template

https://github.com/9499574/layui-form-create

https://github.com/9499574/layui-transfer
https://github.com/9499574/transferTable
https://github.com/9499574/layui-transfer-ajax
https://github.com/hnzzmsf/layui-formSelects
https://github.com/sentsin/layui
https://github.com/hnzzmsf/blog 

https://github.com/hnzzmsf/jquery.runCode.js

https://github.com/huang303513/NodejsVuePractice
https://github.com/yuzhiping/jeeplus
https://gitee.com/lemur/easypoi
https://www.jianshu.com/p/0bfe2318814f

https://github.com/u014427391/jeeplatform
https://github.com/white-cat/jeeweb

TODO
jun_plugin
	1、@author  注解删掉；
	2、jun_utils提出出来；
	3、jun_lucene lib .gitignore 没提交
	4、jun_httpclient  --> HttpClientTest  挪到测试文件夹下面
		HttpGetTest
	5、jun_apache_commons  没有指定JDK类型
	6、修改所有的package及类描述
	7、所有添加maven-compiler-plugin
	7、04-ApacheCamel-CXF-WebService 修改项目名称
	book01-ch00-basic
	dbtracer_core
	fastdfs_core
	jun_springboot_server
	netty.http.server
	redis-proxy-monitor
	tcc-transaction-bom
	8、所有共享jar包版本
	9、删除全部项目重新导入；
	10、补充每个项目的测试用例及readme.md

TODO
Jun_fontend
	https://github.com/Soliman/jqGrid.bootstrap
	treegrid


TODO
jun_spring
        
jun_spring\spring_dbutil\README.md  优化步骤文档
com.jun.plugin
 	    
		
		
spring_springjdbc 干掉，干掉代码，
spring_springjdbc_aop_ioc 调整，干掉，功能合并到ioc aop  jdbc
spring_springjdbc_case  干掉
spring_springjdbc_multipledb  调整，合并到spring_distributed_multidb
spring_springjdbc11   干掉  
spring_springjdbc22   合并到spring_springjdbc
spring_springjdbc44  合并到spring_springjdbc
spring_springjdbc55  重命名   spring_springjdbc
spring_springmq  代码合并spring_activemq
spring_springmultidatasource  调整
spring_springmvc  调整
spring_springmvc (2) 调整
spring_springmvc_test  合并到spring_springmvc
spring_springmvc2   合并到spring_springmvc
spring_springmvc22 代码合并spring_springmvc
spring_springmvcblzedskjsjian_jb51  干掉
spring_springmvcdemo   代码合并spring_springmvc
spring_springmvcliuyanban  代码合并spring_springmvc
spring_springmybatislianbiaoshengchengdaima   合并到jun_code_generator
spring_springpermission  调试
spring_springrain   ***** 挪到jedp_framework,重命名jedp_spring4all_layui
spring_springside.github.com  挪到jun_frontend
spring_springside4  迁移到jun_springboot
spring_springside42  干掉
spring_springtianxiadiyi  干掉
spring_springtime  挪到jun_srpingboot
spring_springwind2  挪到jedp_framework
spring_ssh  调整 spring_S2SH
spring_ssh (4) 调整 spring_ssh
spring_ssh 2  合并到 spring_S2SH
spring_ssh 3 干掉，合并到mvn_template
spring_ssh_demo  合并到 spring_ssh
spring_ssh_maven 合并到 spring_S2SH
spring_ssh_showcase 干掉
spring_ssh2  合并到 spring_S2SH
spring_ssh15  合并到 spring_ssh
spring_sshdemo  合并到 spring_ssh
spring_sshe  n挪到 jedp_framework
spring_ssm  重命名 spring_swagger
spring_ssm 2  挪到 jun_springboot
spring_ssm_bbs  调整
spring_ssm_cluster  调整
spring_ssm_demo  合并到 spring_ssm
spring_ssm_dubbo  调整
spring_ssm_dubbo2  干掉
spring_ssm_micro_service   干掉
spring_ssm_sms   调整
spring_ssm_update  调整，合并到ssm
spring_ssm2   调整，spring_ssm_article
spring_ssm4  迁移到jun_framework
spring_ssm5   合并到ssm
spring_ssm6  合并到ssm
spring_ssm22  干掉
spring_ssm23  干掉
spring_ssm44   迁移到jun_framework
spring_ssm77   *****spring_ssm
spring_ssm111   合并到ssm
spring_ssm2666  干掉
spring_ssmdemo  调试
spring_ssmzhengheactiviti_qingjiajibangongyongpinshenpiliucheng  迁移到spring_activti
Spring_sso  kisso补充
spring_store   跑起来
spring_struts 2   补充
spring_template_jqgrid  重命名spring_jqgrid 
spring_test 合并到spring_test
spring_test 2   重命名spring_test
spring_test_dbunit       干掉
spring_threads  调试
spring_thymeleaf  调试
spring_tiles   合并到spring_quartz 里面
spring_ueditor_with_spring  重命名，spring_ueditor
spring_velocity   调整
spring_web_ssh   干掉
spring_web_ssm  干掉
spring_webapp   干掉
Spring_task   新增
spring_webim  调试
spring_websocket  调试
spring_websocket 2  干掉
spring_websocket 3  *****合并到 spring_websocket
spring_websocket2    干掉
spring_webssh  干掉
spring_wind  迁移到jun_framework
spring_wind 2     干掉
spring_xsd_canal   干掉
spring_xxpay_master   干掉
spring_xxpay_master2   干掉


mybatis+spring+springmvc+vue+easyui实现
来自 <https://github.com/Ftine/Ajie-Forever-God_snack> 

