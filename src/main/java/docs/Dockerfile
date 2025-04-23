# 使用官方的 OpenJDK 8 镜像作为基础镜像
FROM java:8

# 设定时区
ENV TZ=Asia/Shanghai

# 设置工作目录
WORKDIR /app

#COPY target/crazy-test.jar /app/crazy-test.jar
COPY crazy-test-0.0.1-SNAPSHOT.jar /app/app.jar

#COPY ${JAR_FILE} /app/crazy-test.jar

# 暴露端口（根据你的 Java 应用配置的端口，通常是 8080）
EXPOSE 8080

# 定义容器启动时执行的命令
# 动态传递 JAVA_OPTS
#CMD ["sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILE} -jar crazy-test.jar"]

ENTRYPOINT ["java", "-jar", "/app/app.jar"]