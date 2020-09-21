#springmvc_ibatis_plus **

### 针对ibatis框架的代码生成器项目。。。
**   

说明：如果数据库表中oracle无blob,clob字段，mysql中无text，blob字段请使用 noSupportBCLOB 分支代码

================================================================================================

不啰嗦了，直接上图！  附件为Windows 64位系统eclipse swt—jface运行jar包，如果为32位系统，请从eclipse\plugins目录下找到swt-jface包替换原来的64位系统包后打包。

swt—jface位置如下图

![swt-jface](https://git.oschina.net/uploads/images/2017/0504/163524_e6950195_722815.png "swt-jface")

打包步骤：
   项目右键-》Export-》 Runnable JAR file
   ![jar1](https://git.oschina.net/uploads/images/2017/0509/220011_fe753283_722815.png "打包1")
   ![jar2](https://git.oschina.net/uploads/images/2017/0509/220033_3dad4661_722815.png "打包2")


![打包注意](https://git.oschina.net/uploads/images/2017/0925/150741_a72ed6be_722815.png "打包注意")

打成可运行jar时， **请注意一定要选第一项**，
选择第二项的话，CodeMaker.class.getProtectionDomain().getCodeSource().getLocation() 返回值会是 rsrc:./ 
会导致取不到jar物理路径，运行出错！！！


运行界面
![运行界面](https://git.oschina.net/uploads/images/2017/0504/171154_95193a87_722815.png "运行界面")

生成的代码，新建web工程结构

![生成的代码](https://git.oschina.net/uploads/images/2017/0504/171214_0d0178b5_722815.png "生成的代码")


欢迎点击链接加入技术讨论群【Java 爱码少年】：https://jq.qq.com/?_wv=1027&k=4AuWuZu