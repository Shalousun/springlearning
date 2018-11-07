Spring Boot 2集成RocketMQ，Apache官方已经给出了rocketmq-spring-boot-starter，这样可以很方便的集成Spring Boot项目中。

# Starter地址

```
https://github.com/apache/rocketmq-externals/tree/master/rocketmq-spring-boot-starter
```
# 修改Starter的SpringBoot版本
需要根据自己使用SpringBoot版本的情况来修改Starter中的SpringBoot版本号
# 构建Starter

apache给的starter只是SNAPSHOT，并没有上传到私服，因此需要自己构建。
```
mvn package -Dmaven.test.skip=true
```