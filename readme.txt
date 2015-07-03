zframees 基于springmvc hibernate shiro freemarker easyui 的开发基础框架，
包含较为完善的权限管理基础部分，代码生成部分
可在此基础上直接进行进一步的开发
核心部分包括：

一、代码生成器
1.采用Freemarker模板，生成model类的基本的增删改查逻辑，采用在线编程方式，创建实体类之后可运行代码生成，生成代码手动配置菜单之后可直接测试。
2.生成前端界面基于Easyui框架生成数据模型的JSP页面片段，页面数据校验、页面提交回调处理；
3.生成实体结构基于Hibernate，支持ManyToone、OneToOne等关联类型；
4.生成后端控制层部分，包括异常处理、跳转处理、权限控制、数据接口、jsr303 validator校验；
5.生成后端逻辑部分：包括唯一性校验、可空校验、树形结构父子节点校验、ManyToone空逻辑校验；
6.生成后端DAO部分：包括数据源切换、分布式多数据源事务控制。

二、权限控制
权限控制基于Apache Shiro框架，提供灵活配置，提供模式包括：
1.Role Based Aaccess Control：基于角色的访问控制（适用于常见应用系统）
2.Resource Based Aaccess Control：基于资源的访问控制（类似ACL，适用于对于权限拦截请求最高的系统）
3.Url Based Aaccess Control：基于访问路径和目录的控制（适用于开放门户目录）实现部分提供两种实现方式(5,6)
4.基于Echcacahe 或者Memory的Session 与Auth 缓存机制（适用于单机应用环境）
5.基于Redis的分布式或集群环境的Session 与Auth 管理机制（适用于分布式或集群环境）此模式下提供session的Redis统一管理机制 ，类似Spring Session 或Spring token 无需再集群环境中配置session同步。
6.实际应用系统中提供role-position-department形式的数据模型

三、数据访问
1.DAO：默认采用Hibernate完成Dao层功能，（未采用JPA原因是过分的ORM影响性能），提供灵活接口可直接运行HQL 或SQL；
2.主键生成策略提供基于Identity形式的自增形式和基于Base58编码的UUID形式（InnoDB引擎中不建议使用），
3.连接池：提供淘宝的Druid连接池，以及C3p0连接池；
4.分布式环境中提供Atomikos进行事物控制；
5.扩展Spring Session机制，采用动态数据源以及动态sessionFactory方式，事物控制模式下换数据源
6.Redis：提供基于commons-pool-2.6的Jedis Spring封装，序列化工具提供基于JDK以及protostuff两种序列化方式；
7.Mongodb：提供基于Spring的Template形式简单配置，后期待扩展；
8.文件：提供部分基于Nio的文件操作接口，Apache Ant的文件压缩工具；

四、异步任务
 基于spring异步事件机制、spring异步接口、spring task等
 
五、日志
 日志采用log4j以及logback两种选择，并提供是否存储数据库的灵活配置选择
 
六、文件上传
 提供基于百度Uploader的分片式多线程文件上传组件，后端采用基于NIO的Spring 文件传输接口
 
七、邮件
 提供基于Spring JavaMail 的邮件发送封装，提供简单格式和Html格式的邮件发送模板；

所需jar吧包下载地址：http://pan.baidu.com/s/1gdpMun5
