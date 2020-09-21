#spring-cloud-books
## **使用**

1.根目录执行mvn clean install后,将所有war包放在document/run文件夹下
2.点击run-all.bat批量按顺序执行war包
启动时请等待,直到启动完一个,再按回车继续启动下一个
3.也可以按照自己需求启动每个项目的bat文件

以下按照启动顺序依次介绍各项目

### **配置中心**

访问svn上配置文件所处位置，比如svn上某个地址
svn://xxx.xxx.xxx.xxx/project_name/docs
访问 http://localhost:8000/api/dev/docs
即显示docs目录下api-dev.properties文件中相关配置信息

### **服务发现**

访问http://localhost:8001/discovery/
界面如下：
![输入图片说明](http://git.oschina.net/uploads/images/2016/1121/181013_db44c0d2_43183.jpeg "在这里输入图片标题")

之后相应的服务启动后，刷新页面后可见已被发现的服务
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/094700_ac4d9ecb_43183.jpeg "在这里输入图片标题")

### **服务端**

访问http://localhost:8002/swagger-ui.html
可以查看swagger文档，如下图：
![输入图片说明](http://git.oschina.net/uploads/images/2016/1121/181042_2d280d46_43183.jpeg "在这里输入图片标题")
相应技术为springboot+mysql+mybatis+hikariCP

### **API网关**

访问http://localhost:8005/swagger-ui.html ，如下图：
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/101922_61f72a16_43183.jpeg "在这里输入图片标题")

可查看相关接口，目前这里只是利用json web token做了一个鉴定请求是否有权调用服务端的安全验证功能，还有就是路由功能

### **客户端**

访问http://localhost:8004/consumer/10

返回的json数据结果如下图：
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/102304_d351bed6_43183.jpeg "在这里输入图片标题")

这里是通过访问api网关，获取token放入请求的header中，然后请求服务端接口获取数据。
目前只做了查询bookID的功能，可自行扩展其它crud操作，如 http://localhost:8002/swagger-ui.html 中的各接口
这里还包括了负载均衡和熔断器功能，如果服务端访问不了，会访问相关故障信息,如下:
​    
   ```
    {
    "code": -99,
    "message": "无法访问服务，该服务可能由于某种未知原因被关闭。请重启服务！",
    "data": null
    }
```

swagger文档，如下图：

![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/101906_41ee44ef_43183.jpeg "在这里输入图片标题")

### **服务监控控制台**

访问http://localhost:8005/hystrix.steam
可以查看某服务在一个server节点或多个server节点上的实时运行情况
比如在搜索框输入 http://localhost:8004/hystrix.stream ,并在title输入框取名hystrix-8004
（注意在点击monitor stream按钮前，先运行 http://localhost:8004/consumer/10 ），结果如下图
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/094900_506a714c_43183.jpeg "在这里输入图片标题")

### **聚合服务节点**

可在 http://localhost:8005/hystrix.steam 搜索框输入 http://localhost:8006/turbine.stream ,
并在title输入框取名turbine-8006，看下列结果
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/094949_b4459472_43183.jpeg "在这里输入图片标题")


这里因为服务只在我本机上部署，因此上述两张图是一样的，如果服务还部署在另外一台或多台server上，第二张图会显示多个server运行服务情况。如果此时有很多访问 http://localhost:8004/consumer/10 的请求，我们可以看见实时运行情况，如下图
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/095115_0092d6cc_43183.jpeg "在这里输入图片标题")


### **Actuator**

每个项目的info信息都是直接从maven的pom文件中读取，具体可参考各个项目的resources目录下的application和application-test属性文件

```
#查看info信息配置
info.app.name=@project.name@
info.app.description=@project.description@
info.app.version=@project.version@
```

下列这些图是展示服务端，API网关，消费端的info信息

![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/102852_06e87b19_43183.jpeg "服务端")
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/102908_3eb87ba2_43183.jpeg "API网关")
![输入图片说明](http://git.oschina.net/uploads/images/2016/1123/103141_a10b809a_43183.jpeg "消费端")
