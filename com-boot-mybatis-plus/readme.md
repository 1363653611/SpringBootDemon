## mybatis_plus ##

1. 引入相关 maven 依赖

2. 添加mapper 接口,继承BaseMapper

3. 在启动类上添加@MapperScanner


4. 遇到问题:
    1. `org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userMapper' defined in file [D:\baseCode\SpringBootDemon\com-boot-mybatis-plus\target\classes\com\zbcn\combootmybatisplus\mapper\UserMapper.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required
`
        - 问题原因:
           使用了错误的maven 依赖:
           ```
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatisplus-spring-boot-starter</artifactId>
                <version>1.0.5</version>
            </dependency>
           ```
           引发了很多问题....
         - 解决方案:
            更换maven依赖:
             ```
                <dependency>
                    <groupId>com.baomidou</groupId>
                    <artifactId>mybatis-plus-boot-starter</artifactId>
                    <version>3.2.0</version>
                </dependency>
              ```