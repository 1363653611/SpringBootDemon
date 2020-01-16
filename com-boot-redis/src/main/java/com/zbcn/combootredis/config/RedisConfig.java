package com.zbcn.combootredis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbcn.combootredis.entity.KryoRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	/**
	 *  redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
	 * @param redisConnectionFactory
	 * @return
	 */
	@Primary
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		KryoRedisSerializer<Object> kryo = new KryoRedisSerializer<Object>(Object.class);
		// 设置value的序列化规则和 key的序列化规则
		redisTemplate.setStringSerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		//GenericToStringSerializer、StringRedisSerializer将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
		//定义value的序列化方式 // 增加long 类型的参数 方法回乱码
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(kryo);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
