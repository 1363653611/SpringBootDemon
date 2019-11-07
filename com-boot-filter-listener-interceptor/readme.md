### 监听器Listener  监听器 Interceptor  过滤器 Filter 

请求调用顺序: `Controller -> Aspect -> controllerAdvice -> Interceptor -> Filter`

#### Interceptor

1. 第一种方式(废弃):
    -  编写 自定义 filter 类 继承 `HandlerInterceptorAdapter`(过时), 实现 adapter 中的方法;  
        解决方案两种：   
          - ①  `implements WebMvcConfigurer` （官方推荐）
          -  ②  `extends WebMvcConfigurationSupport`  
        使用第二种方法相当于覆盖了@EnableAutoConfiguration里的所有方法，每个方法都需要重写，比如，若不实现方法addResourceHandlers()，则会导致静态资源无法访问，实现的方法如下：
        ```java_holder_method_tree
            @Override
            protected void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .addResourceLocations("classpath:/resources/")
                        .addResourceLocations("classpath:/static/")
                        .addResourceLocations("classpath:/public/");
                super.addResourceHandlers(registry);
           }
       ```
        > 1. preHandle在业务处理器处理请求之前被调用
        > 2. postHandle在业务处理器处理请求执行完成后,生成视图之前执行
        >  3. afterCompletion在DispatcherServlet完全处理完请求后被调用
    - 编写config 类, 继承 `WebMvcConfigurerAdapter`, 重写 `addInterceptors` 方法, 将编写的自定义filter 注册进 `addInterceptors`中.
    
##### Filter 
1. 第一种方式:
    1. 编写TimeFilter 类,实现Filter 接口..
    2. 注册 在spring 中容器中 注册 `FilterRegistrationBean` 类,将 `TimeFilter` 添加中 `FilterRegistrationBean`
    3 . 例子： `TimterFilter`
    
2. 使用 `@WebFilter` 注解：
    1. 编写自定义 Filter 类,实现Filter 接口..
    2. 添加 `@WebFilter` 注解
    3. 在启动类添加 `@ServletComponentScan` 注解
    
#### Listener
1. 使用 @WebListener 方式：
    1. 编写 自定义Listener 实现 对应的listener 接口 。
    2. 添加注解 `@WebListener`
    3. 启动类中添加 `@ServletComponentScan` 注解
    
2. 通过ServletListenerRegistrationBean代码注册（ 详见SessionListener写法）

   listener 接口，很多，依据用途选取；

#### 拦截器和过滤器之间的区别

1. 过滤器只能在servlet容器下使用
2. 而拦截器可以在Spring容器中调用。
3. 拦截器为请求提供了一个更细粒度的控制:(pre/post/afterCompletion)
4. 过滤器的话，只能在将响应返回给最终用户之前使用它们


##### 总结:
1. 使用 @ServletComponentScan注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
   