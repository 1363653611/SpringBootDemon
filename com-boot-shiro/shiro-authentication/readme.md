## Spring Boot Shiro用户认证

### 说明
__在Spring Boot中集成Shiro进行用户的认证过程主要可以归纳为以下三点：__

1. 定义一个ShiroConfig，然后配置SecurityManager Bean，SecurityManager为Shiro的安全管理器，管理着所有Subject；
2. 在ShiroConfig中配置ShiroFilterFactoryBean，其为Shiro过滤器工厂类，依赖于SecurityManager；
3. 自定义Realm实现，Realm包含doGetAuthorizationInfo()和doGetAuthenticationInfo()方法，因为本文只涉及用户认证，所以只实现doGetAuthenticationInfo()方法。

### 需要的核心依赖:
```xml
<!-- MyBatis -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
</dependency>

<!-- thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- shiro-spring -->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.4.0</version>
</dependency>

<!-- oracle驱动 -->
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>6.0</version>
</dependency>

<!-- druid数据源驱动 -->
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>druid-spring-boot-starter</artifactId>
   <version>1.1.6</version>
</dependency>
```

### shiroConfig
- 详见 `com.zbcn.shiroauthentication.config.ShiroConfig`

- 需要注意的是filterChain基于短路机制，即最先匹配原则
```properties
/user/**=anon
/user/aa=authc 永远不会执行
```
- 其中anon、authc等为Shiro为我们实现的过滤器，具体如下表所示：

|Filter |Name	|Class	|Description|
|:-----|:-----|:-----|:------|
|anon	|org.apache.shiro.web.filter.authc.AnonymousFilter	|匿名拦截器，即不需要登录即可访问；一般用于静态资源过滤；示例/static/**=anon|
|authc	|org.apache.shiro.web.filter.authc.FormAuthenticationFilter|	基于表单的拦截器；如/**=authc，如果没有登录会跳到相应的登录页面登录|
|authcBasic	| org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter	| Basic HTTP身份验证拦截器|
|logout	| org.apache.shiro.web.filter.authc.LogoutFilter |	退出拦截器，主要属性：redirectUrl：退出成功后重定向的地址（/），示例/logout=logout|
|noSessionCreation |	org.apache.shiro.web.filter.session.NoSessionCreationFilter	| 不创建会话拦截器，调用subject.getSession(false)不会有什么问题，但是如果subject.getSession(true)将抛出DisabledSessionException异常 |
|perms | org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter |	权限授权拦截器，验证用户是否拥有所有权限；属性和roles一样；示例/user/**=perms["user:create"] |
|port  |org.apache.shiro.web.filter.authz.PortFilter|	端口拦截器，主要属性port(80)：可以通过的端口；示例/test= port[80]，如果用户访问该页面是非80，将自动将请求端口改为80并重定向到该80端口，其他路径/参数等都一样|
|rest  | org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter	|rest风格拦截器，自动根据请求方法构建权限字符串；示例/users=rest[user]，会自动拼出user:read,user:create,user:update,user:delete权限字符串进行权限匹配（所有都得匹配，isPermittedAll）|
|roles	|org.apache.shiro.web.filter.authz.RolesAuthorizationFilter	|角色授权拦截器，验证用户是否拥有所有角色；示例/admin/**=roles[admin]|
|ssl	|org.apache.shiro.web.filter.authz.SslFilter|	SSL拦截器，只有请求协议是https才能通过；否则自动跳转会https端口443；其他和port拦截器一样；|
|user	|org.apache.shiro.web.filter.authc.UserFilter	|用户拦截器，用户已经身份验证/记住我登录的都可；示例/**=user|

配置完ShiroConfig后，接下来对Realm进行实现，然后注入到SecurityManager中。

### Realm
- 自定义Realm实现只需继承AuthorizingRealm类，然后实现doGetAuthorizationInfo()和doGetAuthenticationInfo()方法即可。
- 这两个方法名乍看有点像，authorization发音[ˌɔ:θəraɪˈzeɪʃn]，为授权，批准的意思，即获取用户的角色和权限等信息
- authentication发音[ɔ:ˌθentɪ’keɪʃn]，认证，身份验证的意思，即登录时验证用户的合法性，比如验证用户名和密码。

- 详见 `com.zbcn.shiroauthentication.component.ShiroRealm`
- 登录异常处理：
- 其中UnknownAccountException等异常为Shiro自带异常，Shiro具有丰富的运行时AuthenticationException层次结构，可以准确指出尝试失败的原因。你可以包装在一个try/catch块，并捕捉任何你希望的异常，并作出相应的反应。例如：
```java_holder_method_tree
try {
    currentUser.login(token);
} catch ( UnknownAccountException uae ) { ...
} catch ( IncorrectCredentialsException ice ) { ...
} catch ( LockedAccountException lae ) { ...
} catch ( ExcessiveAttemptsException eae ) { ...
} ... catch your own ...
} catch ( AuthenticationException ae ) {
    //unexpected error?
}
```
> 虽然我们可以准确的获取异常信息，并根据这些信息给用户提示具体错误，但最安全的做法是在登录失败时仅向用户显示通用错误提示信息，例如“用户名或密码错误”。这样可以防止数据库被恶意扫描

### 数据层
```mysql
-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE `SCOTT`.`T_USER` (
   ID BIGINT NOT NULL primary key COMMENT '用户名',
   USERNAME VARCHAR(20) NOT NULL COMMENT '密码',
   PASSWD VARCHAR(128) NOT NULL COMMENT '密码',
   CREATE_TIME DATE NULL COMMENT '创建时间',
   STATUS CHAR NOT NULL COMMENT '是否有效 1：有效  0：锁定'
);

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO SCOTT.T_USER VALUES (2, 'test', '7a38c13ec5e9310aed731de58bbc4214', DATE('2017-11-19 17:20:21'), '0');
INSERT INTO SCOTT.T_USER VALUES (1, 'zbcn', '42ee25d1e43e9f57119a00d0a39e5250', DATE('2017-11-19 10:52:48'), '1');
```

### 启动报错：
1. securityManager 配置报错
```log
Caused by: org.springframework.beans.factory.BeanInitializationException: The security manager does not implement the WebSecurityManager interface.
	at org.apache.shiro.spring.web.ShiroFilterFactoryBean.createInstance(ShiroFilterFactoryBean.java:434) ~[shiro-spring-1.4.0.jar:1.4.0]
	at org.apache.shiro.spring.web.ShiroFilterFactoryBean.getObject(ShiroFilterFactoryBean.java:343) ~[shiro-spring-1.4.0.jar:1.4.0]
	at org.springframework.beans.factory.support.FactoryBeanRegistrySupport.doGetObjectFromFactoryBean(FactoryBeanRegistrySupport.java:171) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	... 50 common frames omitted
```
原因：在 shiroConfig 中 配置的 websecurity 为 DefaultSecurityManager，实际为 `DefaultWebSecurityManager`
```java_holder_method_tree
    @Bean
	public SecurityManager securityManager(){
		// 配置SecurityManager，并注入shiroRealm
		// 报错：org.springframework.beans.factory.BeanInitializationException: The security manager does not implement the WebSecurityManager interface.
		//DefaultSecurityManager securityManager = new DefaultSecurityManager();
		//正确
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}
```
2. jdbc 驱动找不到
`java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver`
    解决方案：添加驱动
    ```xml
       <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.19</version>
        </dependency>
    ```
   
## 启动 访问地址
````http request
http://localhost:8080/web/

http://localhost:8080/web/index

http://localhost:8080/web/aaaaaaa

http://localhost:8080/web
````