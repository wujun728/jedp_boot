UEditor with spring项目介绍

UEditor官方只提供jsp版本的上传实现，功能比较简单，不太适合流行的ssh架构类的项目。
所以本项目使用spring来实现了相关服务器端功能，并扩展了一些功能，如大小判断、用户限制、文件类型可配置、提示信息可配置等等，希望能够给需要的同学带来一些方便。
项目用maven进行构建，导入Eclipse后输入命令tomcat7:run启动，浏览器输入http://localhost:8080即可访问。
源码：http://git.oschina.net/pinhuba/ueditor-with-spring
交流：http://www.pinhuba.com/bbs.jhtml

项目所用类库版本

ueditor 1.3.6 utf-8
spirng 3.2.6
springmvc 3.2.6
commons-lang 2.6
commons-io 2.4
commons-fileupload 1.2.1
log4j 1.2.17
org.json 20090211
httpclient 4.0.3

UEditor配置修改

配置项全部位于文件ueditor.config.js中
保存路径
savePath 设置为[upload]
修正地址
如imagePath、scrawlPath、filePath等等，以前值为 URL + "jsp/"，目前全部设置为空，共修改九处，对应九类需要请求服务端的操作。
压缩图片
maxImageSideLength 默认为900，当图片宽度超过900时将会被压缩，修改为3000，不需要图片压缩。

服务端操作

1、图片上传
2、涂鸦图片（还没实现）
3、附件上传
4、远程抓取
5、图片在线管理
6、屏幕截图（还没实现）
7、word转存
8、获取视频
9、视频上传