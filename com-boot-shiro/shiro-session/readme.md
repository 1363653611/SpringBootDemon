## Spring Boot shiro-session 在线会话管理
- 在Shiro中我们可以通过org.apache.shiro.session.mgt.eis.SessionDAO对象的getActiveSessions()方法方便的获取到当前所有有效的Session对象。
- 通过这些Session对象，我们可以实现一些比较有趣的功能，比如查看当前系统的在线人数，查看这些在线用户的一些基本信息，强制让某个用户下线等。

## 更改ShiroConfig
- 如果是 ehcache作为缓存，为了能够在Spring Boot中使用SessionDao，我们在ShiroConfig中配置该Bean：
```java
@Bean
public SessionDAO sessionDAO() {
    MemorySessionDAO sessionDAO = new MemorySessionDAO();
    return sessionDAO;
}
```
- 如果使用的是Redis作为缓存实现，那么SessionDAO则为RedisSessionDAO：
```java
@Bean
public RedisSessionDAO sessionDAO() {
    RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    redisSessionDAO.setRedisManager(redisManager());
    return redisSessionDAO;
}
```
- 在Shiro中，SessionDao通过org.apache.shiro.session.mgt.SessionManager进行管理，所以继续在ShiroConfig中配置SessionManager：
```java
@Bean
public SessionManager sessionManager() {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    Collection<SessionListener> listeners = new ArrayList<SessionListener>();
    listeners.add(new ShiroSessionListener());
    sessionManager.setSessionListeners(listeners);
    sessionManager.setSessionDAO(sessionDAO());
    return sessionManager;
}
```
- 其中ShiroSessionListener为org.apache.shiro.session.SessionListener接口的手动实现，所以接下来定义一个该接口的实现：
```java
public class ShiroSessionListener implements SessionListener{
    private final AtomicInteger sessionCount = new AtomicInteger(0);
    
    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }
    
    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
    }
    
    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
    }
}
```
其维护着一个原子类型的Integer对象，用于统计在线Session的数量。
- 定义完SessionManager后，还需将其注入到SecurityManager中：
```java
@Bean  
public SecurityManager securityManager(){  
    DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
    securityManager.setRealm(shiroRealm());
    ...
    securityManager.setSessionManager(sessionManager());
    return securityManager;  
}
```

### UserOnline
- 配置完ShiroConfig后，我们可以创建一个UserOnline实体类，用于描述每个在线用户的基本信息：
  
### Service
- 创建一个Service接口，包含查看所有在线用户和根据SessionId踢出用户抽象方法：
```java
public interface SessionService {
    List<UserOnline> list();
    boolean forceLogout(String sessionId);
}
```

- 通过SessionDao的getActiveSessions()方法，我们可以获取所有有效的Session，通过该Session，我们还可以获取到当前用户的Principal信息。

- 如果使用的Redis作为缓存实现，那么，forceLogout()方法：
```java
@Override
public boolean forceLogout(String sessionId) {
    Session session = sessionDAO.readSession(sessionId);
    sessionDAO.delete(session);
    return true;
}
```
- 如果使用的ehcache作为缓存实现，那么，forceLogout()方法：
```java
@Override
public boolean forceLogout(String sessionId) {
    Session session = sessionDAO.readSession(sessionId);
    session.setTimeout(0);
    return true;
}
```
值得说明的是，当某个用户被踢出后（Session Time置为0），该Session并不会立刻从ActiveSessions中剔除，所以我们可以通过其timeout信息来判断该用户在线与否。

### Controller
定义一个SessionContoller，用于处理Session的相关操作：

### 编写 online.html

### 修改 index.html
```html
<body>
    <p>你好！[[${user.userName}]]</p>
    <p shiro:hasRole="admin">你的角色为超级管理员</p>
    <p shiro:hasRole="test">你的角色为测试账户</p>
    <div>
        <a shiro:hasPermission="user:user" th:href="@{/user/list}">获取用户信息</a>
        <a shiro:hasPermission="user:add" th:href="@{/user/add}">新增用户</a>
        <a shiro:hasPermission="user:delete" th:href="@{/user/delete}">删除用户</a>
    </div>
    <a shiro:hasRole="admin" th:href="@{/online/index}">在线用户管理</a>
    <a th:href="@{/logout}">注销</a>
</body>
```
- 启动


