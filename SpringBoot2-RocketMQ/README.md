Spring Boot 2集成RocketMQ，Apache官方已经给出了rocketmq-spring-boot-starter，这样可以很方便的集成Spring Boot项目中。

# Starter地址

```
https://github.com/apache/rocketmq-externals/tree/master/rocketmq-spring-boot-starter
```
# 构建Starter

apache给的starter只是SNAPSHOT，并没有上传到私服，因此需要自己构建。
```
mvn package -Dmaven.test.skip=true
```