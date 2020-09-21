
#### pom.xml
``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>name.luoyong.spring</groupId>
	<artifactId>spring-jpa-hibernate-annotationTx</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<junit.version>4.11</junit.version>
		<spring.version>4.0.3.RELEASE</spring.version>
		<hibernate.version>4.3.6.Final</hibernate.version>
		<mysql.version>5.1.6</mysql.version>
		<druid.version>1.0.7</druid.version>
	</properties>

	<dependencies>

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>${hibernate.version}</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>${mysql.version}</version>
		</dependency>

		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>${druid.version}</version>
		</dependency>

	</dependencies>
</project>
```

##### applicationContext.xml
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context" xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:tx="http://www.springframework.org/schema/tx" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop 
	http://www.springframework.org/schema/aop/spring-aop-3.0.xsd 
	http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans-3.0.xsd 
	http://www.springframework.org/schema/context 
	http://www.springframework.org/schema/context/spring-context-3.0.xsd 
	http://www.springframework.org/schema/mvc 
	http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd 
	http://www.springframework.org/schema/tx 
	http://www.springframework.org/schema/tx/spring-tx-3.0.xsd">
	
	<context:component-scan base-package="name.luoyong.*" />
	
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
		<property name="url" value="jdbc:mysql://localhost:3306/hibernate"></property>
		<property name="username" value="root"></property>
		<property name="password" value="****"></property>
		
		<property name="initialSize" value="8"></property>
		<property name="minIdle" value="8"></property>
		<property name="maxActive" value="8"></property>
		
	</bean>

	<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="persistenceUnitName" value="unitName" />
	</bean>

	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory"></property>
	</bean>

	<tx:annotation-driven/>
	
</beans>
```

#### META-INF/persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
	version="2.0">

	<persistence-unit name="unitName">

		<provider>org.hibernate.ejb.HibernatePersistence</provider>

		<properties>
		
			<!--  If there is no dataSource in applicationContext.xml, need these jdbc config.-->
			<!-- 
			<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/hibernate" />
			<property name="javax.persistence.jdbc.user" value="root" />
			<property name="javax.persistence.jdbc.password" value="****" />
			-->
			
			<property name="hibernate.hbm2ddl.auto" value="update" />
			
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.format_sql" value="true" />

		</properties>

	</persistence-unit>

</persistence>
```

#### Stone.java
``` java
package name.luoyong.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stone {
	
	private Long id;
	private String name;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
```

#### StoneDao.java
``` java
package name.luoyong.spring.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.luoyong.spring.jpa.entity.Stone;

import org.springframework.stereotype.Repository;

@Repository
public class StoneDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Stone stone) {
		em.persist(stone);
		System.out.println("persist stone");
	}
	
	public void delete(Long id) {
		em.createQuery("delete from Stone where id=:id").setParameter("id", id).executeUpdate();
	}

}
```

#### StoneService.java
``` java
package name.luoyong.spring.jpa.service;

import name.luoyong.spring.jpa.dao.StoneDao;
import name.luoyong.spring.jpa.entity.Stone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoneService {
	
	@Autowired
	private StoneDao stoneDao;
	
	@Transactional
	public void save(Stone stone) {
		stoneDao.save(stone);
	}
	
	@Transactional
	public void delete(Long id) {
		stoneDao.delete(id);
	}

}
```

#### Main test
``` java
package name.luoyong.spring.jpa;

import name.luoyong.spring.jpa.entity.Stone;
import name.luoyong.spring.jpa.service.StoneService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");

		StoneService stoneService = (StoneService) ctx.getBean(StoneService.class);

		Stone stone = new Stone();
		stone.setName("Big Stone");
		stoneService.save(stone);
		
		//stoneService.delete(1L);
	}

}
```