<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE ibatorConfiguration PUBLIC "-//Apache Software Foundation//DTD Apache iBATIS Ibator Configuration 1.0//EN" 
   "http://ibatis.apache.org/dtd/ibator-config_1_0.dtd" >
<ibatorConfiguration>
	<#if driver='com.mysql.jdbc.Driver'><classPathEntry location="lib/mysql-connector.jar"/></#if>
	<#if driver='oracle.jdbc.driver.OracleDriver'><classPathEntry location="lib/ojdbc14.jar"/></#if>
	<ibatorContext id="contenxt" targetRuntime="Ibatis2Java5">
		<jdbcConnection driverClass="${driver}" connectionURL="${dburl}" userId="${username}" password="${password}" />
		<javaModelGenerator targetPackage="${packageName}.model" targetProject="${projectSrcDir}/main/java/">
			<property name="trimStrings" value="true" />
		</javaModelGenerator>
		<sqlMapGenerator targetPackage="${packageName}.map" targetProject="${projectSrcDir}/main/resources/" />
		<daoGenerator targetPackage="${packageName}.dao" targetProject="${projectSrcDir}/main/java/" type="SPRING" />
		<#list tables as table>
		<table tableName="${table}" domainObjectName="${domains[table]}" schema="00fly">
		   <#if driver='com.mysql.jdbc.Driver'><!--<generatedKey column="id" sqlStatement="select @@identity as id"/>--></#if> 
		   <#if driver='oracle.jdbc.driver.OracleDriver'><generatedKey column="id" sqlStatement="select seq_xxxx.nextval from dual"/></#if>
		</table>
		</#list>
	</ibatorContext>
</ibatorConfiguration>