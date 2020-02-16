## Spring Boot Shiro-rememberMe 功能

- Shiro为我们提供了Remember Me的功能，用户的登录状态不会因为浏览器的关闭而失效，直到Cookie过期。

### 更改 ShiroConfig
- 继续编辑ShiroConfig，加入：
```java_holder_method_tree
/**
 * cookie对象
 * @return
 */
public SimpleCookie rememberMeCookie() {
    // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
    SimpleCookie cookie = new SimpleCookie("rememberMe");
    // 设置cookie的过期时间，单位为秒，这里为一天
    cookie.setMaxAge(86400);
    return cookie;
}

/**
 * cookie管理对象
 * @return
 */
public CookieRememberMeManager rememberMeManager() {
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(rememberMeCookie());
    // rememberMe cookie加密的密钥 
    cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
    return cookieRememberMeManager;
}
```

- 接下来将cookie管理对象设置到SecurityManager中：
```java_holder_method_tree
@Bean  
public SecurityManager securityManager(){  
    DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
    securityManager.setRealm(shiroRealm());
    securityManager.setRememberMeManager(rememberMeManager());
    return securityManager;  
}
```
- 最后修改权限配置，将ShiroFilterFactoryBean的filterChainDefinitionMap.put("/**", "authc");更改为filterChainDefinitionMap.put("/**", "user");。user指的是用户认证通过或者配置了Remember Me记住用户登录状态后可访问。

- 更改 login.html,在login.html中加入Remember Me checkbox：
```html
<html>
<div class="login-page">
        <div class="form">
            <input type="text" placeholder="用户名" name="username" required="required"/>
            <input type="password" placeholder="密码" name="password" required="required"/>
            <p><input type="checkbox" name="rememberMe" />记住我</p>
            <button onclick="login()">登录</button>
        </div>
    </div>
<script th:inline="javascript"> 
    var ctx = [[@{/}]];
    function login() {
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        var rememberMe = $("input[name='rememberMe']").is(':checked');
        $.ajax({
            type: "post",
            url: ctx + "login",
            data: {"username": username,"password": password,"rememberMe": rememberMe},
            dataType: "json",
            success: function (r) {
                if (r.code == 0) {
                    location.href = ctx + 'index';
                } else {
                    alert(r.msg);
                }
            }
        });
    }
}
</script>
</html>
```

### 更改 LoginController
- 在login 方法中添加 rememberMe 参数