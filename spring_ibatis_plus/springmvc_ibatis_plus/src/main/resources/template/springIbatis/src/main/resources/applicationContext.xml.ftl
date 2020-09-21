<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context" xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-4.3.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
	http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
	
	<!-- 使用annotation 自动注册bean, 并保证@Required、@Autowired的属性被注入 -->
	<context:component-scan base-package="${packageName}">
		<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
		<context:exclude-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
	</context:component-scan>
	
	<!-- 资源文件 -->
	<bean id="propertyPlaceholderConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="location" value="classpath:jdbc.properties" />
	</bean>
	
	<!-- 数据源 -->
	<bean id="dataSource" class="org.apache.tomcat.jdbc.pool.DataSource" destroy-method="close">
		<property name="poolProperties">
				<bean class="org.apache.tomcat.jdbc.pool.PoolProperties">
				<property name="driverClassName" value="$\{jdbc.driver}" />
				<property name="url" value="$\{jdbc.url}" />
				<property name="username" value="$\{jdbc.username}" />
				<property name="password" value="$\{jdbc.password}" />
				<property name="jmxEnabled" value="true" />
				<property name="testWhileIdle" value="false" />
				<property name="testOnBorrow" value="true" />
				<property name="validationInterval" value="30000" />
				<property name="testOnReturn" value="false" />
				<property name="validationQuery" value="SELECT 1 from dual" />
				<property name="timeBetweenEvictionRunsMillis" value="30000" />
				<property name="maxIdle" value="2" />
				<property name="maxActive" value="10" />
				<property name="initialSize" value="2" />
				<property name="maxWait" value="10000" />
				<property name="removeAbandonedTimeout" value="60000" />
				<property name="minEvictableIdleTimeMillis" value="30000" />
				<property name="minIdle" value="1" />
				<property name="logAbandoned" value="true" />
				<property name="removeAbandoned" value="true" />
				<property name="jdbcInterceptors" value="org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer" />
			</bean>
		</property>
	</bean>
	
	<!-- 异常处理 -->
        <bean id="exceptionHandler" class="${packageName}.common.ExceptionHandler"/>
	   
	<!-- Ibatis 配置 -->
	<bean id="sqlMapclient" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
		<property name="configLocation" value="classpath:SqlMapConfig.xml" />
		<property name="dataSource" ref="dataSource" />
	</bean>

	<!-- 事务管理 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>

	<!-- 配置 Annotation 驱动，扫描@Transactional注解的类定义事务 -->
	<tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true" />

	<!-- sqlMapClient注入模板 -->
	<bean id="sqlMapClientTemplate" class="org.springframework.orm.ibatis.SqlMapClientTemplate">
		<property name="sqlMapClient" ref="sqlMapclient" />
	</bean>

</beans>

