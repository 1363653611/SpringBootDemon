## Spring Boot Shiro-redis
- 在Shiro中加入缓存可以使权限相关操作尽可能快，避免频繁访问数据库获取权限信息，因为对于一个用户来说，其权限在短时间内基本是不会变化的。Shiro提供了Cache的抽象，其并没有直接提供相应的实现，因为这已经超出了一个安全框架的范围。在Shiro中可以集成常用的缓存实现，这里介绍基于Redis和Ehcache缓存的实现。

### Redis
- 网络上已经有关于Shiro集成Redis的实现，我们引入即可：
``` xml
<!-- shiro-redis -->
<dependency>
    <groupId>org.crazycake</groupId>
    <artifactId>shiro-redis</artifactId>
    <version>3.2.2</version>
</dependency>
```
### 配置Redis
- 我们在application.yml配置文件中加入Redis配置：
```yaml
spring:
  redis:
    host: localhost
    port: 6379
    pool:
      max-active: 8
      max-wait: -1
      max-idle: 8
      min-idle: 0
    timeout: 0
```
- 接着在ShiroConfig中配置Redis：
```
public RedisManager redisManager() {
    RedisManager redisManager = new RedisManager();
    return redisManager;
}

public RedisCacheManager cacheManager() {
    RedisCacheManager redisCacheManager = new RedisCacheManager();
    redisCacheManager.setRedisManager(redisManager());
    return redisCacheManager;
}
```
### ShiroConfig配置Ehcache
- 接着在ShiroConfig中注入Ehcache缓存：
```java
@Bean
public EhCacheManager getEhCacheManager() {
    EhCacheManager em = new EhCacheManager();
    em.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
    return em;
}
```
- 将缓存对象注入到SecurityManager中：
```java
@Bean  
public SecurityManager securityManager(){  
    DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
    securityManager.setRealm(shiroRealm());
    securityManager.setRememberMeManager(rememberMeManager());
    securityManager.setCacheManager(getEhCacheManager());
    return securityManager;  
}
```
- 启动





