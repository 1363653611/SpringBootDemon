package com.zbcn.config.annotation;

import com.zbcn.config.config.CustomRedisConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 一种是主动生效，在starter组件集成入Spring Boot应用时需要你主动声明启用该starter才生效，即使你配置完全。
 * 还有一种方式是:被动生效，在starter组件集成入Spring Boot应用时就已经被应用捕捉到。这里会用到类似java的SPI机制。在autoconfigure资源包下新建META-INF/spring.factories写入
 * 配置信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomRedisConfig.class)
public @interface EnableCustomRedis {
}
