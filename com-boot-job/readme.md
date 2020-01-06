## 可手动配置的定时任务

### 利用的框架 quartz-scheduler

### 主要控制类是 `QuartzManager`

### `ScheduleJobInitListener` 初始化定时任务 .
- 注意 `CommandLineRunner` 的作用

### 定时任务 的配置 `QuartzConfiguration`

### 新增任务 
- 将需要执行的任务 添加到 `com.zbcn.combootjob.job` 包中
- 在数据库中配置该任务