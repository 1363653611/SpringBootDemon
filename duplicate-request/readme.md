### 重复提交控制 ####

- 网站：https://blog.battcn.com/2018/06/13/springboot/v2-cache-redislock/

####  本地缓存 重复提交 （基于GUAVA）

- 使用到的技术：自定义注解，aop,Guava Cache
- 适用位置： 单体应用，不适合分布式
##### 具体实现：

- 添加maven 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>21.0</version>
</dependency>
```
- 添加 锁的注解 `com.zbcn.duplicaterequest.annotation.LocalLock`
- 添加拦截器： `com.zbcn.duplicaterequest.interceptor.LocalLockMethodInterceptor`
- 测试方法： `com.zbcn.duplicaterequest.controller.DemonController#localQuery`

####  redis 重复提交，支持分布式。
- 使用技术： 自定义注解，AOP， redis cache
- 适用地址： 分布式系统

##### 具体实现方式
- 添加maven 依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencies>
```

- `application.properties` 添加 redis 配置
- 添加 redis 锁的注解 `com.zbcn.duplicaterequest.annotation.CacheLock`
- 添加 锁的key参数获取的注解 `com.zbcn.duplicaterequest.annotation.CacheParam` 
- 锁的key值生成器： `com.zbcn.duplicaterequest.generator.CacheKeyGenerator`
- lock 拦截器：`com.zbcn.duplicaterequest.interceptor.LockMethodInterceptor` Redis是线程安全的，我们利用它的特性可以很轻松的实现一个分布式锁，如 opsForValue().setIfAbsent(key,value) 它的作用就是如果缓存中没有当前 Key 则进行缓存同时返回 true 反之亦然；当缓存后给 key 在设置个过期时间，防止因为系统崩溃而导致锁迟迟不释放形成死锁； 那么我们是不是可以这样认为当返回 true 我们认为它获取到锁了，在锁未释放的时候我们进行异常的抛出….
- redisApi 封装： `com.zbcn.duplicaterequest.utils.RedisLockHelper`

### 注意：
- @AutoConfigureAfter 在加载配置的类之后再加载当前类
- 它的value 是一个数组 一般配合着**@import** 注解使用 ，在使用import时必须要让这个类先被spring ioc 加载好
  所以@AutoConfigureAfter必不可少
  
```java
@Configuration
public class ClassA {	//在加载DemoConfig之前加载ClassA类
}
@Configuration
@AutoConfigureAfter(ClassA.class)
@Import(ClassA.class)
public class DemoConfig {
}
```


