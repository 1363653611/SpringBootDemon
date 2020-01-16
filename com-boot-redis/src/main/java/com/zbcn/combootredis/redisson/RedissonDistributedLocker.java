package com.zbcn.combootredis.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *  @title RedissonDistributedLocker
 *  @Description 基于redisson 锁的实现
 *  @author zbcn8
 *  @Date 2020/1/16 14:23
 */
public class RedissonDistributedLocker implements DistributedRedisLocker{

	private RedissonClient redissonClient;

	public RedissonDistributedLocker(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	@Override
	public RLock lock(String lockKey) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock();
		return lock;
	}

	@Override
	public RLock lock(String lockKey, int timeout) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock(timeout, TimeUnit.SECONDS);
		return lock;
	}

	@Override
	public RLock lock(String lockKey, TimeUnit unit, int timeout) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock(timeout, unit);
		return lock;
	}

	@Override
	public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
		RLock lock = redissonClient.getLock(lockKey);
		try {
			return lock.tryLock(waitTime, leaseTime, unit);
		} catch (InterruptedException e) {
			return false;
		}
	}

	@Override
	public void unlock(String lockKey) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.unlock();
	}

	@Override
	public void unlock(RLock lock) {
		lock.unlock();
	}

	@Override
	public boolean trySetRate(String key, long perNum, long time) {
		boolean b = redissonClient.getRateLimiter(key).trySetRate(RateType.OVERALL, perNum, time, RateIntervalUnit.SECONDS);
		return b;
	}

	@Override
	public boolean tryAcquire(String key, long permits, long timeout) {
		boolean b = redissonClient.getRateLimiter(key).tryAcquire(permits, timeout, TimeUnit.SECONDS);
		return b;
	}
}
