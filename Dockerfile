# Build stage
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/delcons-api-0.0.1.jar
COPY ${JAR_FILE} app_delcons.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_delcons.jar"]


