# sping-cloud整合
## 整合配置中心注意事项
整合配置中心一般的执行流程是指定配置中心地址，然后从配置中心读取启动配置信息。获取到响应的配置信息后，根据配置信息启动服务。第一步的执行逻辑是在`bootstrap`系列配置文件中。所以需要引入响应的依赖
### bootstrap依赖引入
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```
