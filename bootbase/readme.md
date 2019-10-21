### springBoot 基础 ###

1. config 的读取方式:
  - 位置问题:
    + 默认在 Spring Boot 中，一共有 4 个地方可以存放 application.properties 文件:(优先级从上到下依次降低)
        1. 当前项目根目录下的 config 目录下
        2. 当前项目的根目录下
        3. resources 目录下的 config 目录下
        4. resources 目录下
    + 可以在项目启动时指定配置文件位置
         > spring.config.location = classpath:/zbcn/application.propertie
  - 文件名问题
    + ` spring.config.name=` 指定文件名称
   
  - 配置文件内容读取:
    1. 使用 `@value` 来读取
        + 由于 Spring Boot 中，默认会自动加载 `application.properties` 文件，所以简单的属性注入可以直接在这个配置文件中写:
        ```java
       @Component
       public class Book {
           @Value("${book.id}")
           private Long id;
           @Value("${book.name}")
           private String name;
           @Value("${book.author}")
           private String author;
           //省略getter/setter
       }
        ```
        + 自定义配置文件 `book.properties ` ,则需要在指定文件去读取缓存:
           - 在spring 中的xml配置 `<context:property-placeholder location="classpath:book.properties"/>` 回将配置文件缓存到context中
           - springboot:
           
           ```java
           @Component
           @PropertySource("classpath:book.properties")
           public class Book {
               @Value("${book.id}")
               private Long id;
               @Value("${book.name}")
               private String name;
               @Value("${book.author}")
               private String author;
               //getter/setter
           }

            ``` 
          
    2. 类型安全的属性注入:
        ```java
       @Component
       @PropertySource("classpath:book.properties")
       @ConfigurationProperties(prefix = "book")
       public class Book {
           private Long id;
           private String name;
           private String author;
           //省略getter/setter
       }
       ```
    - __desc__: 
        1. 主要是引入 @ConfigurationProperties(prefix = “book”) 注解，并且配置了属性的前缀，此时会自动将 Spring 容器中对应的数据注入到对象对应的属性中 
        
2. banner 的集成
  
3. profiles 多环境配置
  
4. logback 日志的集成
  
5. @import 和 @importResource
 
  - @Import来导入配置类或者导入一个带有@Component等注解要放入Spring容器中的类
  - 用@ImportResource来导入一个传统的xml配置文件
  - 在启用很多组件时，我们会用到一个形如@EnableXXX的注解，比如@EnableAsync、@EnableHystrix、@EnableApollo等，点开这些注解往里追溯，你也会发现@Import的身影
    
   - __说明：__
   @importResource 类似于
     ```xml
        <import resource="cons-injecxml" />
     ```
     
   @Improt 类似于：
   ```xml
    <bean id="cdPlayer" class="com.jiaobuchong.soundsystem.CDPlayer">
           <property name="cd" ref="compactDisc" />
    </bean>
   ```