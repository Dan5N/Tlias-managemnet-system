# 使用 OpenJDK 17 镜像
FROM openjdk:17.0.2-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 jar 文件
COPY target/tlias.jar app.jar
COPY wait-for-it.sh /usr/local/bin/wait-for-it
RUN chmod +x /usr/local/bin/wait-for-it
# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]