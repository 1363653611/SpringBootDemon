### spring 自定义starter #####

  - 遇见问题：
  
    1. spring.factories 文件写错，导致该start无法识别
    2. spring.factories 文件中的前置key 写错。
    3. 每一个key 都有自己特殊的含义，请注意
    
  - 心得：
    
    1. 配置springBoot 的debug 模式：debug: true，可以看到系统级的一些starter 的启动信息
    2. 仿照springBoot 现有的starter 写自定义starter 
    3. starter 的开启和关闭都可以通过日志看见