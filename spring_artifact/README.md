# SpringMvc-Artifact

`SpringMvc-Artifact`项目结合Mybatis创建的框架（SSM），创建于2017-11-1，正在慢慢完善，目的是打造简单通俗易懂的架构模式供开发测试使用。
本项目支持**开发环境和正式环境隔离**、**非关系型流行数据库**、**消息队列**、**阿里数据源监控**等功能。总之，`SpringMvc-Artifact`是一个麻雀虽小，五脏俱全的案例型项目，
可作为Demo进行项目部署开发测试使用，由于为参考型项目，所以未采用Maven多模块去实现，使用java package取而代之。

#### 项目目录结构：
```
+---src
|   +---main
|   |   +---java
|   |   |   \---cn
|   |   |       \---kiwipeach
|   |   |           +---core  项目核心包
|   |   |           |   +---enum  枚举包
|   |   |           |   +---exception 异常包
|   |   |           |   +---filter 过滤器
|   |   |           |   +---interceptor 拦截器
|   |   |           |   +---page 分页
|   |   |           |   +---plugin 插件
|   |   |           |   +---annotation 注解
|   |   |           |   +---security 安全认证
|   |   |           |   \---utils 工具类
|   |   |           +---mapper mybatis的Mapper接口
|   |   |           +---modal  mybatis的模型数据
|   |   |           +---service  服务接口
|   |   |           |   +---bhind 后台服务接口适配
|   |   |           |   |   \---impl 后台接口服务实现
|   |   |           |   \---front 前台接口实现适配
|   |   |           |       \---impl 前台接口实现适配
|   |   |           \---web  web层
|   |   |               +---bhind 后台控制模块
|   |   |               |   +---controller 后台控制器
|   |   |               |   \---dto 数据交互DTO
|   |   |               \---front
|   |   |                   +---controller 前台控制器
|   |   |                   \---dto 数据交互DTO
|   |   +---resources
|   |   |   +---envs  多环境目录
|   |   |   |   +---development  开发环境
|   |   |   |   \---production   生产环境
|   |   |   \---mappings  mybatis映射XML文件
|   |   \---webapp web应用路径
|   |       \---WEB-INF 
|   |           \---jsp  SpringMvc页面映射路径
|   |               \---error 错误显示
|   \---test  测试模块
|       +---java
|       |   \---cn
|       |       \---kiwipeach
|       |           \---mapper  接口测试
|       \---resource 测试资源
```
#### 完成情况：
- 项目的Maven骨架搭建，依赖、插件、仓库、环境配置（√）
- 对数据源密码进行加密，对阿里数据源进行监控（√）
- Spring集成Mybatis，并且使用测试模块进行测试（√）
- SpringMVC集成配置，容器加载配置规则顺顺序（√）‘
- 采用了logback日志系统对业务日期进行管理，包括文件与数据库（mongodb、oracle）实现 （ING...）
- 添加Mybats PageHelper分页插件功能（ING...）
- 添加Redis缓存支持（ING...）
- 添加Ehcache缓存支持（ING...）
- 添加MongoDB非关系ing型数据库支持（ING...）
- 添加ActiveMQ消息队列支持（ING...）
- 服务端控制器层制层制定统一数据返回Response标准（ING...）
- 服务端控制器层制添加hibernate validator服务端校验（ING...）
- 添加基于配置的前台提示消息功能（ING...）
- 添加页面国际化功能（ING...）
- 业务逻辑代码编写规范，异常不捕捉，由全局异常统一处理（ING...）
- 使用@Annotation注解开发（ING...）



#### 考虑实现:
- Duboo服务提供消费模式集成，添加Zookeeper服务治理工具（WILL...）
- 使用Mybatis Plus(WILL...)
- 添加用户登录，验证码模块（WILL...）
- 使用添加security安全模块,使用Shrio实现（WILL...）
- 使用jsper实现报表供能（WILL...）


#### 开发环境:
- Oracle11g: 数据库（可切换）
- Tomcat: 开发服务器（可切换jetty）
- Tomcat: 应用服务器(可切换Weblogic)
- Git: 版本管理（可选择SVN）
- IntelliJ IDEA 2017.3: 开发IDE（可选择Eclipse）
- PLSQL: 数据库客户端（可切换Navicat）
- Jdk1.8(可切换)
- Redis
- Ehcache
- MongDB
- ActiveMQ
- Dubbo-admin
- Dubbo-monitor
- Zookeeper



