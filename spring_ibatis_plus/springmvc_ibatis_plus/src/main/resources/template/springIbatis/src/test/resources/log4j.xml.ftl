<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

	<!--
		========================== 自定义输出格式说明================================
	-->
	<!-- %p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL -->
	<!-- %r 输出自应用启动到输出该log信息耗费的毫秒数  -->
	<!-- %c 输出所属的类目，通常就是所在类的全名 -->
	<!-- %t 输出产生该日志事件的线程名 -->
	<!-- %n 输出一个回车换行符，Windows平台为“/r/n”，Unix平台为“/n” -->
	<!--
		%d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd
		HH:mm:ss,SSS}，输出类似：2002年10月18日 22：10：28，921
	-->
	<!--
		%l
		输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
	-->

	<!--
		========================== 输出方式说明================================
		Log4j提供的appender有以下几种:
	-->
	<!-- org.apache.log4j.ConsoleAppender(控制台),  -->
	<!-- org.apache.log4j.FileAppender(文件),  -->
	<!-- org.apache.log4j.DailyRollingFileAppender(每天产生一个日志文件), -->
	<!-- org.apache.log4j.RollingFileAppender(文件大小到达指定尺寸的时候产生一个新的文件),  -->
	<!-- org.apache.log4j.WriterAppender(将日志信息以流格式发送到任意指定的地方)   -->
	<!--
		==========================================================================
	-->

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

	<!-- 输出到控制台  -->
	<appender name="myConsole" class="org.apache.log4j.ConsoleAppender">
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss} |%-5p|%t|%m|%n" />
		</layout>
		<filter class="org.apache.log4j.varia.LevelRangeFilter">
			<param name="levelMin" value="DEBUG" />
			<param name="levelMax" value="ERROR" />
			<param name="AcceptOnMatch" value="true" />
		</filter>
	</appender>

	<!-- 输出到日志文件  -->
	<appender name="log4jTestDebug" class="org.apache.log4j.RollingFileAppender">
		<param name="File" value="logs/log4j_debug.log" />
		<param name="Append" value="true" />
		<param name="MaxFileSize" value="256KB" />
		<param name="MaxBackupIndex" value="4" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss} [%-5p] %c {%F:%L} - %m%n" />
		</layout>
		<filter class="org.apache.log4j.varia.LevelRangeFilter">
			<param name="LevelMin" value="DEBUG" />
			<param name="LevelMax" value="DEBUG" />
		</filter>
	</appender>

	<appender name="LOG.DEBUG" class="org.apache.log4j.DailyRollingFileAppender">
		<param name="File" value="logs/debug/debug.log" />
		<param name="DatePattern" value="yyyy-MM-dd" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss} [%-5p] %c {%F:%L} - %m%n" />
		</layout>
		<filter class="org.apache.log4j.varia.LevelRangeFilter">
			<param name="LevelMin" value="DEBUG" />
			<param name="LevelMax" value="DEBUG" />
		</filter>
	</appender>

	<appender name="LOG.INFO" class="org.apache.log4j.DailyRollingFileAppender">
		<param name="File" value="logs/info/info.log" />
		<param name="DatePattern" value="yyyy-MM-dd" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss} [%-5p] %c {%F:%L} - %m%n" />
		</layout>
		<filter class="org.apache.log4j.varia.LevelRangeFilter">
			<param name="LevelMin" value="INFO" />
			<param name="LevelMax" value="INFO" />
		</filter>
	</appender>

	<appender name="LOG.WARN" class="org.apache.log4j.DailyRollingFileAppender">
		<param name="File" value="logs/warn/warn.log" />
		<param name="DatePattern" value="yyyy-MM-dd" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss} [%-5p] %c {%F:%L} - %m%n" />
		</layout>
		<filter class="org.apache.log4j.varia.LevelRangeFilter">
			<param name="LevelMin" value="WARN" />
			<param name="LevelMax" value="WARN" />
		</filter>
	</appender>

	<appender name="LOG.ERROR" class="org.apache.log4j.DailyRollingFileAppender">
		<param name="File" value="logs/error/error.log" />
		<param name="DatePattern" value="yyyy-MM-dd" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss} [%-5p] %c {%F:%L} - %m%n" />
		</layout>
		<filter class="org.apache.log4j.varia.LevelRangeFilter">
			<param name="LevelMin" value="ERROR" />
			<param name="LevelMax" value="ERROR" />
		</filter>
	</appender>

	<!-- 指定logger的设置，additivity指示是否遵循缺省的继承机制-->
	<logger name="java.sql.PreparedStatement" additivity="true">
		<level value="INFO" />
		<appender-ref ref="myConsole" />
	</logger>

	<root>
		<priority value="INFO" />
		<appender-ref ref="myConsole" />
		<!-- 
		<appender-ref ref="log4jTestDebug" />
		<appender-ref ref="LOG.DEBUG" />
		<appender-ref ref="LOG.INFO" />
		<appender-ref ref="LOG.WARN" />
		<appender-ref ref="LOG.ERROR" />
		 -->
	</root>

</log4j:configuration>