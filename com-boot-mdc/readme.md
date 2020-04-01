## MDC (Mapped Diagnostic Context) 线程安全的日志诊断日志的容器

在分布式应用系统中,需要同时处理很多的请求,在此过程中会产生大量的请求日志,如何来区分每个日志是由于哪个请求产生的的呢,就显得尤为重要.
- 方案1:
   -  可以为每个请求产生一个logger,但是这样会产生大量的资源浪费,随着请求的增多,这种请求方式会将资源耗尽.该方式并不可取.
    
- 方案2:
    - 一种更加轻量级的 MDC机制. 在处理请求前将将请求的唯一标识放到mdc 容器中,如: `requestId`,这个唯一标识可以随着日志一起输出.以此来区分每个日志是属于哪个请求的.并且在请求完成后,清除MDC 容器.
    - mdc 的简单示例: :http://logback.qos.ch/xref/chapters/mdc/SimpleMDC.html
    - test 包编写了测试 logback 的代码
    
### springBoot 中添加 traceId（MDC）
1. 编写 com.zbcn.combootmdc.util.TraceIdUtil 工具类
2. 编写 com.zbcn.combootmdc.filter.TraceFilter
3. 在 com.zbcn.combootmdc.ComBootMdcApplication 中添加 @ServletComponentScan ，使得Filter 生效
4. 配置 src/main/resources/logback-spring.xml