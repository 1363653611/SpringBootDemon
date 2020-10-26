package com.zbcn.common.constant;

public interface StaticNumber {
	/*************************Direct 方式****************************/
	/*Direct 方式*/
	//直接-交换机名称
	String DIRECT_EXCHANGE_NAME = "direct.zbcn.exchange";
	//保存用户-队列名称
	String DIRECT_QUEUE_NAME = "direct_zbcn";
	//保存用户-队列路由键
	String DIRECT_QUEUE_ROUTE_KEY = "direct.queue.route.key";

	/**************************Fanout 方式***************************/
	/*Fanout 方式*/
	/*队列名*/
	String FANOUT_QUEUE_NAME = "fanout.zbcn";
	String FANOUT_QUEUE_NAME_2 = "fanout.zbcn.2";
	String FANOUT_QUEUE_NAME_3 = "fanout.zbcn.3";
	/*交换机名*/
	String FANOUT_EXCHANGE_NAME = "fanout.zbcn.exchange";
	String FANOUT_QUEUE_ROUTE_KEY = "fanout.queue.route.key";

/****************************TOPIC (主题)模式*************************/
	/*topic (主题)模式*/
	/*以topic 开头的队列*/
	String ZBCN_TOPIC = "topic.zbcn";
	String YHJ_TOPIC = "topic.yhj";

	/*其他队列*/
	String OTHER_TOPIC = "other.topic";
	String OTHER_TOPIC_2 = "other.topic_2";

	/*交换机*/
	String TOPIC_EXCHANGE = "topic.zbcn.exchange";

	/*routing key*/
	String TOPIC = "topic.#";
	String TOPIC_KEY = "topic.zbcn.key";
	/*****************************************************/
	/*  生产者  */
	/*资源投递中*/
	String IN_DELIVERY = "0";
	/*投递成功*/
	String SUCCESSFUL_DELIVERY = "1";
	/*投递失败*/
	String FAILURE_DELIVERY = "2";

	/*   消费者   */
	/*消费成功*/
	String CONSUMER_SUCCESSFUL = "1";
	/*消费失败（为尝试）*/
	String CONSUMER_FAILURE = "2";
	/*消费失败（达到最大尝试次数）*/
	String CONSUMER_TRY_FAILURE = "3";

	String YMDHMS = "yyyy-MM-dd HH:mm:ss";

	String JAVAYOHO = "JavaYoHo";
	String PREFIXKEY = "zbcn";


	enum MessageType{
		DIRECT("1","direct", "直接方式"),
		TOPIC("2","topic","主题方式"),
		FANOUT("3","fanout","广播方式");

		private String key;
		private String value;
		private String cnName;

		MessageType(String key, String value, String cnName) {
			this.key = key;
			this.value = value;
			this.cnName = cnName;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getCnName() {
			return cnName;
		}

		public void setCnName(String cnName) {
			this.cnName = cnName;
		}
	}
}
