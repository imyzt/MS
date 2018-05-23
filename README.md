# Management-System
Management-System是使用SpringBoot + MybatisPlus实现的后台管理系统脚手架程序.

[![AppVeyor](https://img.shields.io/appveyor/ci/gruntjs/grunt.svg)
[![Bitbucket Pipelines](https://img.shields.io/badge/JAVA-1.8.0__161-brightgreen.svg)](#)
[![Bitbucket Pipelines](https://img.shields.io/badge/maven-3.5.3-green.svg)](http://maven.apache.org/)
[![Bitbucket Pipelines](https://img.shields.io/badge/SpringBoot-2.0.1.RELEASE-brightgreen.svg)](https://projects.spring.io/spring-boot/)
[![Bitbucket Pipelines](https://img.shields.io/badge/Mybatis--Plus-2.2.0-blue.svg)](http://mp.baomidou.com/#/https://projects.spring.io/spring-boot/)

## Quick Start
1. <a href="#deploy">如何部署MS</a>
2. 运行MS
3. 在Tomcat环境下部署MS
4. 



## 使用到的技术有
* Spring Boot
* Mybatis
* Mybatis Plus
* Druid
* Swagger2
* 持续更新中...

***
## 实现的功能有
1. Druid连接池集成进入项目
2. 自定义分页查询,Mybatis plus实现通用CRUD
3. 全局异常处理及自定义异常
4. 使用Swagger2在线接口文档
5. 封装请求响应消息内容,自定义消息转换器
6. 修改了Mybatis-plus代码生成器模板,实现了Model,Controller,Mapper,Service代码的一键生成
7. 实现了方法级别的多数据源
8. 持续更新中...




### <a id="deploy">部署</a>
1. 环境要求
  MySQL5.7+
  IDEA
2. clone代码到本地
  git clone https://github.com/imyzt/MS.git
3. 使用 IDEA导入项目
  ![项目截图](http://wx4.sinaimg.cn/mw690/0060lm7Tly1frld2t8tdzj31hc0t4wiq.jpg)
