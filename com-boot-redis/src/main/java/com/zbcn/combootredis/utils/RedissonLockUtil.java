package com.zbcn.combootredis.utils;

import com.zbcn.combootredis.redisson.DistributedRedisLocker;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 *  @title RedissonLockUtil
 *  @Description redis锁 工具类
 *  @author zbcn8
 *  @Date 2020/1/16 14:39
 */
public class RedissonLockUtil {

	private static DistributedRedisLocker redissLock;

	public static void setLocker(DistributedRedisLocker locker) {
		redissLock = locker;
	}

	/**
	 * 加锁
	 * @param lockKey
	 * @return
	 */
	public static RLock lock(String lockKey) {
		return redissLock.lock(lockKey);
	}

	/**
	 * 释放锁
	 * @param lockKey
	 */
	public static void unlock(String lockKey) {
		redissLock.unlock(lockKey);
	}

	/**
	 * 释放锁
	 * @param lock
	 */
	public static void unlock(RLock lock) {
		redissLock.unlock(lock);
	}

	/**
	 * 带超时的锁
	 * @param lockKey
	 * @param timeout 超时时间   单位：秒
	 */
	public static RLock lock(String lockKey, int timeout) {
		return redissLock.lock(lockKey, timeout);
	}

	/**
	 * 带超时的锁
	 * @param lockKey
	 * @param unit 时间单位
	 * @param timeout 超时时间
	 */
	public static RLock lock(String lockKey, TimeUnit unit , int timeout) {
		return redissLock.lock(lockKey, unit, timeout);
	}

	/**
	 * 尝试获取锁
	 * @param lockKey
	 * @param waitTime 最多等待时间
	 * @param leaseTime 上锁后自动释放锁时间
	 * @return
	 */
	public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
		return redissLock.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
	}

	/**
	 * 尝试获取锁
	 * @param lockKey
	 * @param unit 时间单位
	 * @param waitTime 最多等待时间
	 * @param leaseTime 上锁后自动释放锁时间
	 * @return
	 */
	public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
		return redissLock.tryLock(lockKey, unit, waitTime, leaseTime);
	}

	/**
	 * 限流器
	 * @param key       限流key
	 * @param perNum    生成个数
	 * @param time      所需要时间
	 * @return
	 */
	public static boolean trySetRate(String key,long perNum, long time) {
		return redissLock.trySetRate(key,perNum, time);
	}

	/**
	 * 限流器
	 * @param key       限流key
	 * @param permits   等待时间
	 * @param timeout   超时时间
	 */
	public static boolean tryAcquire(String key, long permits, long timeout) {
		boolean b = redissLock.tryAcquire(key,permits, timeout);
		return b;
	}
}
