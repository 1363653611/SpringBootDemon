## 可手动配置的定时任务

### 利用的框架 quartz-scheduler

#### 主要控制类是 `QuartzManager`

#### `QuartzJobInitListener` 初始化定时任务 .
- 注意 `CommandLineRunner` 的作用

#### 定时任务 的配置 `QuartzConfiguration`

#### 新增任务 
- 将需要执行的任务 添加到 `com.zbcn.combootjob.quartz.job` 包中
- 在数据库中配置该任务

### 利用的框架 spring 自带的框架

#### 主要控制类是 `com.zbcn.combootjob.schedule.component.TaskConfiguration`

#### `ScheduleJobInitListener` 初始化定时任务 .
- 注意 `CommandLineRunner` 的作用

#### 新增任务 
- 将需要执行的任务 添加到 `com.zbcn.combootjob.quartz.job` 包中，实现接口 `Ijob`
- 在数据库中配置该任务