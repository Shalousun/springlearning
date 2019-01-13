dubbo在k8s中注册服务的外部连通说明

# 准备
- 注册的zookeeper需要在集群外部

# 外部consumer访问集群中provider

## 第一种方案：
在启动是将宿主机的ip地址作为dubbo的注册ip,这个主要通过provider端部署时来指定,在k8s部署文件中指定
```
 env:
     - name: DUBBO_IP_TO_REGISTRY
       value: 192.168.237.130
```
这种配置需要将dubbo的注册端口改为30000+加的端口号，然后使用nodePort方式暴露该端口，主要端口必须一致，这样
就能保证访问了。一般用于dubbo服务多时研发部署自测使用。当然这种方式在部署时需要给应用打上标签，否则部署是没法指定注册ip