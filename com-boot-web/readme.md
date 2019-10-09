## springBoot web 项目 ##
 
 - web 相关配置
    - 自动配置viewResolver
        + contentNegotiatingViewResolver 优先级最高,自己不处理View,而是代理给不同的ViewResolver来处理
        + beanNameViewResolver @Controller 中一个方法返回字符串,会依据该字符串查找对应的view
        + InternalResourceViewResolver 极为常用,主要通过设置前后缀、以及控制器中方法来返回视图的字符串,来的到实际的页面
    - 自动配置的静态资源
    - 自动配置的Formatter和Converter
    - 自动配置的HttpMessageConverters
    - 静态首页的支持
    
 - 静态资源位置( 源码:`WebMvcAutoConfiguration `)
    - 默认情况下，一共有5个位置可以放静态资源:(静态资源请求路径中不需要/static，因为在路径映射中已经自动的添加上了/static了)
       1. classpath:/META-INF/resources/
       2. classpath:/resources/
       3. classpath:/static/
       4. classpath:/public/
       5. /  (springBoot 默认时没有webapp目录的,所以/ 其实就是表示 webapp 目录中的静态资源也不被拦截)
    - 自定义配置:
        ```properties
        # 配置表示定义资源位置
        spring.resources.static-locations=classpath:/
        # 行配置表示定义请求 URL 规则
        spring.mvc.static-path-pattern=/**
        ```
 - thymeleaf 支持    
     - 默认配置:(`org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties `)
        - 从源码中可以看出来，Thymeleaf 模板的默认位置在 resources/templates 目录下，默认的后缀是 html
 
 - 异常页面
    1. 是使用 HTTP 响应码来命名页面，例如 404.html、405.html、500.html ….
    2. 直接定义一个 4xx.html，表示400-499 的状态都显示这个异常页面，5xx.html 表示 500-599 的状态显示这个异常页面
    3. 静态异常页面
        - 路径:默认是在 `classpath:/static/error/ ` 路径下定义相关页面
    4. 动态异常页面:
        动态页面模板，不需要开发者自己去定义控制器，直接定义异常页面即可 ，Spring Boot 中自带的异常处理器会自动查找到异常页面
    5. desc: 如果动态页面和静态页面同时定义了异常处理页面，例如 classpath:/static/error/404.html 和 classpath:/templates/error/404.html 同时存在时，默认使用动态页面
        完整的查找方式:发生了500错误–>查找动态 500.html 页面–>查找静态 500.html –> 查找动态 5xx.html–>查找静态 5xx.html
              
 - `@ControllerAdvice`
    1. 全局异常处理
        ```java
           @ControllerAdvice
           public class MyControllerAdvice {
               @ExceptionHandler(Exception.class)
               public ModelAndView customException(Exception e) {
                   ModelAndView mv = new ModelAndView();
                   mv.addObject("message", e.getMessage());
                   mv.setViewName("myerror");
                   return mv;
               }
           }
        ```
    2. 全局数据绑定
        ```java
           @ControllerAdvice
           public class MyControllerAdvice {
               @ModelAttribute(name = "md")
               public Map<String,Object> mydata() {
                   HashMap<String, Object> map = new HashMap<>();
                   map.put("age", 99);
                   map.put("gender", "男");
                   return map;
               }
           }
        ```
    3. 全局数据预处理
        ```java
          @ControllerAdvice
          public class MyControllerAdvice {
              @InitBinder("b")
              public void b(WebDataBinder binder) {
                  binder.setFieldDefaultPrefix("b.");
              }
              @InitBinder("a")
              public void a(WebDataBinder binder) {
                  binder.setFieldDefaultPrefix("a.");
              }
          }
        
        ```
    
    
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
   * 广播模式
   * 点对点模式
    
