# 使用官方的 OpenJDK 8 镜像作为基础镜像
FROM openjdk:8-jdk-slim

# 设置环境变量
# 例如：设置 JAVA_OPTS 或自定义环境变量
ENV JAVA_OPTS="dev"

# 设定时区
ENV TZ=Asia/Shanghai

# 设置工作目录
WORKDIR /app

# 复制 Maven 构建的项目包（通常是 `*.jar` 文件）到容器中
# 假设你的项目构建生成的 JAR 文件名为 `crazy-test.jar`
# 打包文件地址，由于手动代码打包则直接复制
#COPY target/crazy-test.jar /app/crazy-test.jar
# COPY crazy-test-0.0.1-SNAPSHOT.jar /app/crazy-test.jar

COPY ${JAR_FILE} /app/crazy-test.jar




# 暴露端口（根据你的 Java 应用配置的端口，通常是 8080）
EXPOSE 8080

# 定义容器启动时执行的命令
# 动态传递 JAVA_OPTS
CMD ["sh", "-c", "java $JAVA_OPTS -jar crazy-test.jar"]

# 构建命令
#docker build -t crazy-test-image --build-arg JAR_FILE=target/crazy-test-0.0.1-SNAPSHOT.jar .
