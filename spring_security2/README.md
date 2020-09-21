# Spring-security
  为了给MIS系统添加一套较为通用的权限控制功能，本项目基于Spring，整合Spring的security模块，实现用户管理和权限控制，主要内容如下：
  
  1.登录，包括“记住我”的功能；
  
  2.加密，存储的密码不采用明文,初始密码123；
  
  3.拦截器：对静态文件（HTML/JS/CSS等）进行权限控制，无权限则请求不到；
  
  4.后台接口权限控制：对后台接口启用权限控制，对应的接口若不满足权限或角色要求，则请求失败，使用@Secured实现；
  
  5.用户-角色-权限使用常规RBAC的模型，用户到角色，角色到权限均为多对多关系映射。
![输入图片说明](http://git.oschina.net/uploads/images/2017/0322/151628_4dd347da_1110335.jpeg "在这里输入图片标题")
  
  效果图：
 
![输入图片说明](http://git.oschina.net/uploads/images/2017/0323/114026_82d4a0b0_1110335.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0209/144409_b7aaea2f_1110335.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0209/144419_70c4b9f4_1110335.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0323/114134_1c478e5f_1110335.png "在这里输入图片标题")