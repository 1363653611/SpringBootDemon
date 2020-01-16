package com.zbcn.combootredis.config;

import com.zbcn.combootredis.redisson.RedissonDistributedLocker;
import com.zbcn.combootredis.utils.RedissonLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  @title RedissonAutoConfiguration
 *  @Description redis 自动配置
 *  @author zbcn8
 *  @Date 2020/1/16 14:14
 */
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {
	@Autowired
	private RedissonProperties redssionProperties;
	/**
	 * 哨兵模式自动装配
	 * @return
	 */
//	@Bean
//	@ConditionalOnProperty(name="redisson.master-name")
//	RedissonClient redissonSentinel() {
//		Config config = new Config();
//		SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redssionProperties.getSentinelAddresses())
//				.setMasterName(redssionProperties.getMasterName())
//				.setTimeout(redssionProperties.getTimeout())
//				.setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
//				.setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());
//
//		if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
//			serverConfig.setPassword(redssionProperties.getPassword());
//		}
//		return Redisson.create(config);
//	}

	/**
	 * 单机模式自动装配
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(name="redisson.address")
	RedissonClient redissonSingle() {
		Config config = new Config();
		SingleServerConfig serverConfig = config.useSingleServer()
				.setAddress(redssionProperties.getAddress())
				.setTimeout(redssionProperties.getTimeout())
				.setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
				.setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());

		if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
			serverConfig.setPassword(redssionProperties.getPassword());
		}

		return Redisson.create(config);
	}

	//装配locker类，并将实例注入到RedissLockUtil中
	@Bean
	public RedissonDistributedLocker redissonLocker(RedissonClient redissonClient) {
		RedissonDistributedLocker locker = new RedissonDistributedLocker(redissonClient);
		//设置LockUtil的锁处理对象
		RedissonLockUtil.setLocker(locker);
		return locker;
	}
}
