package com.sprSecurity.spring.jms;

public enum QueueEnum {
	QUEUE_TEST("queue_TEST");

	private String queueName;

	private QueueEnum(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
