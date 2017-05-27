package com.pxjy.elog.mongoDao;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractBaseMongoTemplete implements ApplicationContextAware {

	protected MongoTemplate mongoTemplate;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		MongoTemplate mongoTemplate = (MongoTemplate) context.getBean("mongoTemplate");
		setMongoTemplate(mongoTemplate);
	} 

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}