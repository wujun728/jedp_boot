package org.springframework.data.mongodb.examples.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.baidu.bae.api.util.BaeEnv;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

@Configuration
public class AppConfig {

	public @Bean MongoOperations mongoTemplate(Mongo mongo) {
/*		String password = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_SK);
        String user = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_AK);*/

		String user = "admin";

		String password = "admin";
		String databaseName = "tyBGXypArPvzmXmDrPZU";
		UserCredentials userCredentials = new UserCredentials(user, password);
		MongoTemplate mongoTemplate = new MongoTemplate(mongo, databaseName, userCredentials);
		return mongoTemplate;
	}
	/*
	 * Factory bean that creates the Mongo instance
	 */
	public @Bean MongoFactoryBean mongo() {
		MongoFactoryBean mongoFactoryBean = new MongoFactoryBean();
		MongoOptions mongoOptions = new MongoOptions();
		/**
		 * CursorFinalizerEnabled 这个参数必须设置为fale，否则BAE不支持
		 */
		mongoOptions.setCursorFinalizerEnabled(false);
		mongoFactoryBean.setMongoOptions(mongoOptions);
	/*	String server = "mongo.duapp.com";
		String port = "8908";*/
        String server = "localhost";
		String port = "27017";
		String host = server + ":" + port;
		mongoFactoryBean.setHost(host);
		return mongoFactoryBean;
	}

	/*
	 * Use this post processor to translate any MongoExceptions thrown in @Repository annotated classes
	 */
	public @Bean PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
