## redis 数据类型
### string 
- string 类型是二进制安全的
- string 类型的值最大存储 512M
- redis 的 string 可以包含任何数据。比如jpg图片或者序列化的对象

### List
- Redis 列表是简单的字符串列表，按照插入顺序排序。 你可以添加一个元素到列表的头部（左边）或者尾部（右边）

### Set
- Redis 的 Set 是 string 类型的无序集合。 集合是 __通过哈希表实现__ 的，所以添加，删除，查找的复杂度都是 O(1)。 

### Zset(sorted set：有序集合)
- Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
- zset的成员是唯一的,但分数(score)却可以重复。

### Hash
- Redis hash 是一个键值(key=>value)对集合。 Redis hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象

### 配置文件  
1.  daemonize no
    - Redis 默认不是以守护进程的方式运行，可以通过该配置项修改，使用 yes 启用守护进程（Windows 不支持守护线程的配置为 no ）
2. pidfile /var/run/redis.pid
    - 当 Redis 以守护进程方式运行时，Redis 默认会把 pid 写入 /var/run/redis.pid 文件，可以通过 pidfile 指定
3. port 6379
    - 指定 Redis 监听端口，默认端口为 6379，作者在自己的一篇博文中解释了为什么选用 6379 作为默认端口，因为 6379 在手机按键上 MERZ 对应的号码，而 MERZ 取自意大利歌女 Alessia Merz 的名字
4. bind 127.0.0.1
    - 绑定的主机地址
5. timeout 300
    - 当客户端闲置多长时间后关闭连接，如果指定为 0，表示关闭该功能
6. loglevel notice
    - 指定日志记录级别，Redis 总共支持四个级别：debug、verbose、notice、warning，默认为 notice
7. logfile stdout
    - 日志记录方式，默认为标准输出，如果配置 Redis 为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给 /dev/null
8. databases 16
    - 设置数据库的数量，默认数据库为0，可以使用SELECT 命令在连接上指定数据库id
9. save <seconds> <changes>
    - Redis 默认配置文件中提供了三个条件：
        - save 900 1 :900 秒（15 分钟）内有 1 个更改
        - save 300 10: 300 秒（5 分钟）内有 10 个更改
        - save 60 10000: 以及 60 秒内有 10000 个更改
    - 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合

10. rdbcompression yes
    - 指定存储至本地数据库时是否压缩数据，默认为 yes，Redis 采用 LZF 压缩，如果为了节省 CPU 时间，可以关闭该选项，但会导致数据库文件变的巨大
11. dbfilename dump.rdb
    - 指定本地数据库文件名，默认值为 dump.rdb
12. ir ./
    - 指定本地数据库存放目录
13. slaveof <masterip> <masterport>
    - 设置当本机为 slav 服务时，设置 master 服务的 IP 地址及端口，在 Redis 启动时，它会自动从 master 进行数据同步
14. masterauth <master-password>
    - 当 master 服务设置了密码保护时，slav 服务连接 master 的密码
15. requirepass foobared
    - 设置 Redis 连接密码，如果配置了连接密码，客户端在连接 Redis 时需要通过 AUTH <password> 命令提供密码，默认关闭
16. maxclients 128
    - 设置同一时间最大客户端连接数，默认无限制，Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis 会关闭新的连接并向客户端返回 max number of clients reached 错误信息
17. maxmemory <bytes>
    - 指定 Redis 最大内存限制，Redis 在启动时会把数据加载到内存中，达到最大内存后，Redis 会先尝试清除已到期或即将到期的 Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis 新的 vm 机制，会把 Key 存放内存，Value 会存放在 swap 区
18. appendonly no
    - 指定是否在每次更新操作后进行日志记录，Redis 在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis 本身同步数据文件是按上面 save 条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为 no
19. appendfilename appendonly.aof
    - 指定更新日志文件名，默认为 appendonly.aof
20. appendfsync everysec
    - 指定更新日志条件，共有 3 个可选值：
        - no：表示等操作系统进行数据缓存同步到磁盘（快）  
        - always：表示每次更新操作后手动调用 fsync() 将数据写到磁盘（慢，安全）
        - everysec：表示每秒同步一次（折中，默认值）
21. vm-enabled no
    - 指定是否启用虚拟内存机制，默认值为 no，简单的介绍一下，VM 机制将数据分页存放，由 Redis 将访问量较少的页即冷数据 swap 到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析 Redis 的 VM 机制）
22. vm-swap-file /tmp/redis.swap
    - 虚拟内存文件路径，默认值为 /tmp/redis.swap，不可多个 Redis 实例共享
23. vm-max-memory 0
    - 将所有大于 vm-max-memory 的数据存入虚拟内存，无论 vm-max-memory 设置多小，所有索引数据都是内存存储的(Redis 的索引数据 就是 keys)，也就是说，当 vm-max-memory 设置为 0 的时候，其实是所有 value 都存在于磁盘。默认值为 0
24. vm-page-size 32
    - Redis swap 文件分成了很多的 page，一个对象可以保存在多个 page 上面，但一个 page 上不能被多个对象共享，vm-page-size 是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page 大小最好设置为 32 或者 64bytes；如果存储很大大对象，则可以使用更大的 page，如果不确定，就使用默认值
25. vm-pages 134217728
    - 设置 swap 文件中的 page 数量，由于页表（一种表示页面空闲或使用的 bitmap）是在放在内存中的，，在磁盘上每 8 个 pages 将消耗 1byte 的内存。
26. vm-max-threads 4
    - 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4
27. glueoutputbuf yes
    - 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启

28. hash-max-zipmap-entries 64
hash-max-zipmap-value 512
    - 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法
29. activerehashing yes
    - 指定是否激活重置哈希，默认为开启（后面在介绍 Redis 的哈希算法时具体介绍）
30. include /path/to/local.conf
    -指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件
    
    
    
## 集成问题:
### REDIS
- redisUtil.incr("11", 2) 方法调用,会抛出:ERR value is not an integer or out of range

解决方案:重新配置 `com.zbcn.combootredis.config.RedisConfig` 中的 redisTemplate 的 `valueSerializer`
```java
//GenericToStringSerializer、StringRedisSerializer将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
//定义value的序列化方式 // redisTemplate.opsForValue().increment(key, delta) 方法回乱码
Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
ObjectMapper om = new ObjectMapper();
om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
jackson2JsonRedisSerializer.setObjectMapper(om);

redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

```

### 集成功能
- redis 工具
- redisson 权限校验

- ZSet 实现 `LoaderBords` 排行榜功能
    - `com.zbcn.combootredis.service.LeaderBoardService`
    
- hash 实现 Redis缓存 +写入数据库实现高性能点赞功能
    `com.zbcn.combootredis.service.LikedService`
    
### swagger api
点赞接口-url: http://localhost:8080/swagger-ui.html