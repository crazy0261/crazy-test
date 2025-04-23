# Crazy Test 平台

## 简介

Crazy Test 是一个开源的测试平台，可以快速搭建测试平台支持接口用例、场景用例执行并生成测试报告。

![系统架构图](src/photo/架构图.png)

## 代码地址

* [前端,点击查看](https://github.com/crazy0261/crazy-test-ui.git)
* [后端，点击查看](https://github.com/crazy0261/crazy-test.git)

## 环境

- 前端：React + Antd + Antd Pro + AntV
- 后端：Spring Boot + MyBatis

## 部署

- Mysql 数据库
- XXl-Job 分布式任务调度框架

## 步骤

1. 下载源码

2. 打包

3. 安装docker

4. Dockerfile (其他方式也可实现)

- 构建 docker build -t crazy:1.0 .

5. 启动及运行容器

- docker run -d --name crazy-test -p 8080:8080 crazy-test:latest

## 页面

1. 登录
   ![img_1.png](src/photo/登录.png)
2. 接口用例
   ![img.png](src/photo/首页.png)
3. 接口用例执行
   ![img.png](src/photo/接口用例执行.png)
4. 场景用例
   ![img.png](src/photo/场景用例.png)
5. 场景用例执行
   ![img.png](src/photo/场景用例执行.png)
6. 定时任务
   ![img.png](src/photo/定时任务.png)
7. 定时任务详情
   ![img.png](src/photo/定时任务详情.png)
   8.应用管理
   ![img.png](src/photo/应用管理.png)
9. 接口管理
   ![img.png](src/photo/接口管理.png)
10. 系统管理-数据源管理
    ![img.png](src/photo/数据源.png)
11. 系统管理-环境设置
    ![img.png](src/photo/环境设置.png)
12. 系统管理-域名设置
    ![img.png](src/photo/域名设置.png)
13. 系统管理-账号设置
    ![img.png](src/photo/测试账号设置.png)
14. 系统管理-加密设置
    ![img.png](src/photo/加密设置.png)
15. 系统管理-项目设置
    ![img.png](src/photo/项目设置.png)
    16.用户管理
    ![img.png](src/photo/账号设置.png)
17. 数据大盘
    ![img.png](src/photo/数据大盘1.png)
    ![img.png](src/photo/数据大盘2.png)
    ![img.png](src/photo/数据大盘3.png)
