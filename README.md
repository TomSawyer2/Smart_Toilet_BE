# Smart_Toilet_BE

## Intro

智能公厕系统的后端

**框架**： Springboot + Mybatis-Plus

## 开发

### 〇、环境配置

项目JAVA版本统一采用JDK17，IDE采用IDEA

### 一、依赖安装

打开根目录下的`pom.xml`文件，点击`Maven`标签，点击`Install`

### 二、MySQL配置

找到`application.yml`文件，修改数据库配置，主要修改`url`中的数据库名称和端口

推荐在本地安装`mysql`，并使用`Navicat`连接数据库

配置文件示例：

```yml
server:
  port: 9999

spring:
  application:
    name: SmartToilet_BE
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    name: smart_toilet
    url: jdbc:mysql://${ip}:${port}/${dbName}?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
    username: ${username}
    password: "${password}"

mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
  configuration:
    auto-mapping-behavior: partial
    map-underscore-to-camel-case: true
  type-enums-package: com.st

jwt:
  tokenHeader: Authorization
  secret: ${secret}
```

### 三、运行

找到`SmartToiletBeApplication.java`文件，点击`Run`即可
