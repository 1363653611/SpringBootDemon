## springBoot web 项目 ##

 - thymeleaf 支持
 - web 相关配置
    - 自动配置viewResolver
        + contentNegotiatingViewResolver 优先级最高,自己不处理View,而是代理给不同的ViewResolver来处理
        + beanNameViewResolver @Controller 中一个方法返回字符串,会依据该字符串查找对应的view
        + InternalResourceViewResolver 极为常用,主要通过设置前后缀、以及控制器中方法来返回视图的字符串,来的到实际的页面
    - 自动配置的静态资源
    - 自动配置的Formatter和Converter
    - 自动配置的HttpMessageConverters
    - 静态首页的支持
    
 -  接管web的配置
    - `@Configuration` 和`@EnableWebMvc` 
    - `@Configuration` 和继承 `WebMvcConfiguraterAdapter` （推荐）
 - 注册 Servlet、Filter、Listener
 
 - tomcat 配置
    - 配置文件
    - 代码方式
 - tomcat 替换
    
 - SSL 协议支持

    * 网站: http://blog.heroine-x.com/2018/02/08/use-https-in-spring-boot/
    * .keystore 生成命令:`keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore D:\baseCode\SpringBootDemon\com-boot-web\src\main\resources\keystore.p12 -validity 3650` 
        - storetype 指定密钥仓库类型
        - keyalg 生证书的算法名称，RSA是一种非对称加密算法
        - keysize 证书大小
        - keystore 生成的证书文件的存储路径
        - validity 证书的有效期
        
    * http 自动转向 https
    
 - Favicon 配置
  
 - WebSocket
    
