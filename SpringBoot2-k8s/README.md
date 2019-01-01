k8s部署SpringBoot应用的例子，主要是关于ConfigMap和Secret的使用。

# 部署应用
部署该应用时需要先创建MySQL的Secret配置和MySQL url的ConfigMap，两个配置文件在k8s目录文件中,在创建之前需要先对
Secret中的字段进行编码.
```
echo -n 'admin' | base64
```
解码则使用如下命令：
```
echo 'MWYyZDFlMmU2N2Rm' | base64 --decode
```