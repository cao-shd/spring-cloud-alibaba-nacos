# SpringCloudAlibabaNacos

## Introduction

本项目为 SpringCloudAlibabaNacos 的学习测试用的 Demo。主要包含如下功能：

+ provider 负责提供 HTTP 服务
+ consumer 负责调用 HTTP 服务
+ consumer 使用 feign 远程调用 provider 服务， 并且可以负载均衡
+ consumer 使用 sentinel 进行服务熔断
+ provider 和 consumer 通过 nacos 进行服务的发现与注册
+ provider 和 consumer 通过 nacos 进行统一的配置管理
+ 支持多环境配置

## Environment

+ Windows 10
+ Java Jdk 1.8.0
+ Maven 3.6.3
+ Nacos 1.4.1
+ Sentinel Console 1.8.1

## Project Structure

```
root
├─ consumer
│  └─ src
│    ├─ main
│    │  ├─ java
│    │  │  └─ spring.cloud.alibaba.nacos.consumer
│    │  │     ├─ config
│    │  │     │  └─ ConsumerConfiguration.java
│    │  │     ├─ controller
│    │  │     │  └─ ConsumeController.java
│    │  │     └─ service
│    │  │     │ └─ IProviderService.java
│    │  │     └─ ConsumerApplication.java
│    │  └─resources
│    │    ├─application.yml.bak
│    │    └─bootstrap.properties
│    └─ pom.xml
├─ dependencies
│  └─ pom.xml
├─ provider
│  └─ src
│     ├─ main
│     │  ├─ java
│     │  │  └─ spring.cloud.alibaba.nacos.provider
│     │  │     ├─ controller
│     │  │     │  └─ ProviderController.java
│     │  │     └─ ProviderApplication.java
│     │  └─ resources
│     │     ├─ application.yml.bak
│     │     └─ bootstrap.properties
│     └─ pom.xml
├─ pom.xml
└─ README.md
```

## Quick Start

1. 安装 [Nacos 安装](https://nacos.io/zh-cn/docs/quick-start.html) （Nacos的安装请查看官网，本文档不再详细介绍。）

2. 启动 Nacos （本实验使用单机启动的方式）

```shell
# 首先进入如下路径 $NACOS_HOME/bin
# 使用 cmd 控制台 执行如下命令 启动 nacos 单机版
startup.cmd -m standalone
```

3. 启动 Sentinel 控制台 Sentinel Console 安装及启动请参考官网 [Sentinel Dashboard](https://github.com/alibaba/Sentinel/wiki/Dashboard)，本示例将使用 8858 端口启动 Sentinel 控制台

4. clone 工程

```shell
git clone 
```

5. 进入工程

```shell
cd 
```

6. 导入 nacos 配置

```shell
# 在管理画面导入 nacos 的配置
# 配置管理 > 配置列表 > 导入配置
# 配置存放路径
$PROJECT_ROOT/nacos-config/nacos_config.zip
```

7. 执行打包命令

```shell
mvn clean package 
```

8. 启动 provider （两个）

```shell
java -jar provider/target/provider-1.0.0-SNAPSHOT.jar --server.port=10010
java -jar provider/target/provider-1.0.0-SNAPSHOT.jar --server.port=10011 --spring.profiles.active=test
```

9. 启动 consumer

```shell
java -jar consumer/target/consumer-1.0.0-SNAPSHOT.jar --server.port=10020
```

## Verify

浏览器访问如下路径

1. [http://localhost:10020/consumer/template/provider](http://localhost:10020/consumer/template/provider) （consumer 使用 restTemplate 负载均衡访问 provider 服务）
2. [http://localhost:10020/consumer/feign/provider](http://localhost:10020/consumer/feign/provider) （consumer 使用 feign 负载均衡访问 provider 服务）

显示如下现象证明已启动成功

1. 后台无报错，且多次访问上述地址时画面端口号轮询切换。
2. 停掉一个 provider, 服务依然可以正常响应，但是端口号不再改变。
3. 两个 provider 都停掉， 服务返回自定义错误信息， 而不是默认的错误画面。

## Other

+ Nacos 控制台： [http://localhost:8848/nacos/](http://localhost:8848/nacos/)
+ Sentinel 控制台： [http://localhost:8858/](http://localhost:8858/) 

