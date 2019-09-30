package com.zbcn.duplicaterequest.interceptor;

import com.zbcn.duplicaterequest.annotation.CacheLock;
import com.zbcn.duplicaterequest.generator.CacheKeyGenerator;
import com.zbcn.duplicaterequest.utils.RedisLockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * redis 解决方案
 */
@Aspect
@Configuration
public class LockMethodInterceptor {

	private final RedisLockHelper redisLockHelper;
	private final CacheKeyGenerator cacheKeyGenerator;

	@Autowired
	public LockMethodInterceptor(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
		this.redisLockHelper = redisLockHelper;
		this.cacheKeyGenerator = cacheKeyGenerator;
	}

	@Around("execution(public * *(..)) && @annotation(com.zbcn.duplicaterequest.annotation.CacheLock)")
	public Object interceptor(ProceedingJoinPoint pjp){
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		CacheLock lock = method.getAnnotation(CacheLock.class);
		if (StringUtils.isEmpty(lock.prefix())) {
			throw new RuntimeException("lock key don't null...");
		}
		String lockKey = cacheKeyGenerator.getLockKey(pjp);
		String value = UUID.randomUUID().toString();
		try {
			final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
			if(!success){
				throw new RuntimeException("重复提交");
			}
			try {
				return pjp.proceed();
			} catch (Throwable throwable) {
				throw new RuntimeException("系统异常");
			}
		}finally {
			// TODO 如果演示的话需要注释该代码;实际应该放开
			redisLockHelper.unlock(lockKey, value);
		}

	}
}
