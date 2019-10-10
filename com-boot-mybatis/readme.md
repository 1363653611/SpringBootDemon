### mybatis ###



- __原生：注解方式__
    
    - 一种带@Select注解的是Mybatis3.x提供的新特性，同理它还有@Update、@Delete、@Insert等等一系列注解

- __原生：xml 配置文件方式__

- __框架：__
    - *通用Mapper插件*: 文档地址：https://gitee.com/free/Mapper/wikis/Home
    - *分页插件* 文档地址：https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md


- __问题解决__
    1. No beans of 'UserMapper' type found 问题:虽然不影响运行结果 ，但是本人有些强迫症看着不舒服.
        - 解决方案1: 在 UserMapper 接口上添加 `@Component(value = “UserMapper”);`
        - 解决方案2: 用@Resource注解替换@Autowired注解，错误消失
        