### actuator
翻译：执行器  
springboot 遵循这“约定大于配置”的规则，许多功能使用默认配置即可。但是过于简单的配置，会让我们在了解各种配置\依赖的了解上存在困难。Actuator提供了一系列的RESTful API让我们可以更为细致的了解各种信息。

### Actuator 监控分成两类：原生端点和用户自定义端点；
- __自定义端点__ 主要是指扩展性，用户可以根据自己的实际应用，定义一些比较关心的指标，在运行期进行监控。

- __原生端点__ 是在应用程序里提供众多 Web 接口，通过它们了解应用程序运行时的内部状况。
    - 原生端点分为三类:
        - 应用配置类: 可以查看应用在运行期的静态信息：例如自动配置信息、加载的 `springbean` 信息、`yml` 文件配置信息、环境信息、请求映射信息；
        - 量度指标类: 主要是运行期的动态信息，例如堆栈、请求连、一些健康指标、metrics 信息等；
        - 操作控制类: 主要是指 shutdown,用户可以发送一个请求将应用的监控功能关闭。

### 原生 api

```
HTTP 方法	路径	描述
GET	/auditevents	显示应用暴露的审计事件 (比如认证进入、订单失败)
GET	/beans	描述应用程序上下文里全部的 Bean，以及它们的关系
GET	/conditions	就是 1.0 的 /autoconfig ，提供一份自动配置生效的条件情况，记录哪些自动配置条件通过了，哪些没通过
GET	/configprops	描述配置属性(包含默认值)如何注入Bean
GET	/env	获取全部环境属性
GET	/env/{name}	根据名称获取特定的环境属性值
GET	/flyway	提供一份 Flyway 数据库迁移信息
GET	/liquidbase	显示Liquibase 数据库迁移的纤细信息
GET	/health	报告应用程序的健康指标，这些值由 HealthIndicator 的实现类提供
GET	/heapdump	dump 一份应用的 JVM 堆信息
GET	/httptrace	显示HTTP足迹，最近100个HTTP request/repsponse
GET	/info	获取应用程序的定制信息，这些信息由info打头的属性提供
GET	/logfile	返回log file中的内容(如果 logging.file 或者 logging.path 被设置)
GET	/loggers	显示和修改配置的loggers
GET	/metrics	报告各种应用程序度量信息，比如内存用量和HTTP请求计数
GET	/metrics/{name}	报告指定名称的应用程序度量值
GET	/scheduledtasks	展示应用中的定时任务信息
GET	/sessions	如果我们使用了 Spring Session 展示应用中的 HTTP sessions 信息
POST	/shutdown	关闭应用程序，要求endpoints.shutdown.enabled设置为true
GET	/mappings	描述全部的 URI路径，以及它们和控制器(包含Actuator端点)的映射关系
GET	/threaddump	获取线程活动的快照
```


