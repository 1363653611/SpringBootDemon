- https://www.jianshu.com/p/4aa95905aad3
- https://github.com/Dylan-haiji/javayh-middleware
### tabbitMq 简介

### rabbitmq 集成
- 添加pom 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>

```
## 页面访问
- url:http://localhost:15672/
- 管理远:guest / guest

## 项目
- producer 中有失败重试机制, 定时任务扫表来执行消息发送失败的message
- consumer 中有消费重试机制,超过 3次后,将错误信息保存到数据库,手动处理