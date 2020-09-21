#spring-dynamic-job

项目用Maven管理


使用的技术与版本号
<ol>
 <li>Spring (3.2.2.RELEASE)</li>
 <li>Quartz (2.2.1)</li>
</ol>
<hr/>
<strong>如何使用</strong>
<ol>
    <li>
        Check-out代码, 使用IDE(如IDEA) 打开, 配置应用服务器(如TOMCAT)
    </li>
    <li>
        修改<code>spring-dynamic-job.properties</code>文件,并创建数据库(默认数据为名sdj),并运行<code>others/quartz_mysql_innodb.sql</code>文件初始化数据库
    </li>
    <li>
        启用TOMCAT, 访问
    </li>
</ol>

<p>
    项目的核心对象为: <code>DynamicSchedulerFactory</code>
    <br/>
    该项目已在 <a href="http://git.oschina.net/mkk/HeartBeat">HeartBeat</a> 项目中实际使用,
    更多运用案例可查看该项目.
</p>



<hr/>
<strong>帮助与改进</strong>
<ol>
<li>
<p>
 与该项目相关的博客请访问 <a target="_blank" href="http://blog.csdn.net/monkeyking1987/article/details/42173277">http://blog.csdn.net/monkeyking1987/article/details/42173277</a>
</p>
</li>
<li>
<p>
 若没有找到解决办法的,
 欢迎发邮件到<a href="mailto:shengzhao@shengzhaoli.com">shengzhao@shengzhaoli.com</a>一起讨论.
</p>
</li>

<li>
<p>
 如果在使用项目的过程中发现任何的BUG或者更好的提议, 建议将其提交到项目的 <a href="http://git.oschina.net/mkk/spring-dynamic-job/issues">Issues</a> 中,
 我会一直关注并不断改进项目.
</p>
</li>
</ol>

<hr/>
<p>
 关注更多我的开源项目请访问 <a href="http://andaily.com/my_projects.html">http://andaily.com/my_projects.html</a>
</p>
