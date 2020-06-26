## 全局异常处理
- 返回值封装
- 全局异常处理

## 接口说明
### 自定义 Assert 接口:
- 默认 default 实现方法,用来处理断言错误:
- 接口方法, 创建 异常类的方法
### IResponseEnum 接口
- 定义枚举类获取 code 和 message 方法的抽象框架

### CommonExceptionAssert 和 BusinessExceptionAssert 接口
- 实现 Assert 接口定义的 创建异常方法
- *Enum 类实现 该类接口

## *Enum 接口
- 定义枚举异常,实现 IResponseEnum中的 获取 code 和message 方法
- 实现 *Assert 接口, 以便于使用 期异常处理方法和断言方法

#### ServletResponseEnum 请求到业务代码前的异常,系统级别的错误

#### ResponseEnum,CommonResponseEnum,ArgumentResponseEnum 自定义业务异常枚举类

### UnifiedExceptionHandler 异常处理的核心入口
- `@ControllerAdvice`
- `@ExceptionHandler`

## 返回值封装
### BaseResponse : 返回值公共信息定义
### CommonResponse :请求成功通用返回结果
### ErrorResponse :请求错误通用返回结果
### QueryDataResponse 带分页结果返回值

## UnifiedMessageSource 国际化的支持

## *Exception 异常封装
### BaseException 通用异常
### ArgumentException, BusinessException,ValidationException 特定业务异常

### 404 请求后,spring mvc 默认跳转 到 错误页面,如果要以json 的方式返回,需要如下修改:
- 实际上，当出现404的时候，默认是不抛异常的，而是 forward跳转到/error控制器，spring也提供了默认的error控制器- `BasicErrorController`
- 只需在yml文件中加入如下配置即可:
```yaml
spring:
  mvc:
    throw-exception-if-no-handler-found: true
  resources:
    add-mappings: false
```

### 可以使用 `@RestControllerAdvice` 来代替 `UnifiedExceptionHandler` 中的  `@ControllerAdvice` 和 `@Response`

