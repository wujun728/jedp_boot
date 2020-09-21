<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
       	http://www.springframework.org/schema/context 
       	http://www.springframework.org/schema/context/spring-context-4.3.xsd
       	http://www.springframework.org/schema/mvc
       	http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd"
	default-lazy-init="true">

	<!-- 对web包中的所有类进行扫描，以完成Bean创建和自动依赖注入的功能 -->
	<context:component-scan base-package="${packageName}.controller">
		<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Service" />
	</context:component-scan>

	<mvc:annotation-driven />
	
	<!-- 静态资源配置 -->
	<mvc:resources location="/css/" mapping="/css/**" />
	<mvc:resources location="/pic/" mapping="/pic/**" />
	<mvc:resources location="/script/" mapping="/script/**" />

	<!-- 对模型视图名称的解析，即在模型视图名称添加前后缀 id="JSPviewResolver" -->
	<bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="cache" value="false" />
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jsp" />
		<property name="contentType" value="text/html;charset=UTF-8" />
		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
		<property name="order" value="0" />
	</bean>
	 
</beans>