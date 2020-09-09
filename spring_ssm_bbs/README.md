# BBS_SSm

#### 项目介绍
{**项目简介**<br>
本项目后端整合了现在主流的ORM框架体系，Mybatis,hibernate，jdbcTemplate等，hibernate可以用于
一般的单表操作，Mybatis可用于复杂的业务逻辑SQL操作，级联查询等。必要时，在代码方便直接操作SQL，
特地整合了原生的JDBC服务，不过这里使用的是Spring的整合版，在后端开发上更加容易上手。
另外为了后期的业务扩展需要，特地整合目前主流的安全框架Shiro。扩展性非常好，非常适合新手练手的项目。
前端采用的layui的Ui界面框架。如果不适合个人，可以自由切换到其他主流的JS框架如VUE，angular等。
论坛包含的功能模块主要有【用户模块】【帖子管理模块】【广告板块】，主要拥有的功能：发帖，回帖，点赞，删除，采纳，帖子加精，帖子置顶
等大部分常有的功能都有；项目目前还在开发阶段，上诉的功能大部分实现
}

#### 软件架构
1：采用Spring核心容器架构<br>
2：采用Spring MVC模式<br>
3：采用Mybatis作为持久层框架<br>
4：前端采用layuiUi框架<br>
5：引入JQuery核心标签库<br>
6：后端整合主流的ORM框架，hibernate，jdbcTemplate等。<br>
7：为方便扩展权限体系，整合了Shiro安全框架。<br>



#### 安装教程

1. 数据库mysql5.7，创建库my_bbs,导入数据库文件
2. 使用idea导入本项目，会自动下载maven依赖
3. 创建tomcat直接运行
4. 数据库的表结构在附件区，可以在附件区下载，不过项目整合了hibernate，只要开启项目启动自动更新表结构即可。

#### 使用说明

1. 本项目只供个人使用<br>
2.如果对项目有什么疑问，可在下方回复，我会尽可能的为大家解答项目中的疑问

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)