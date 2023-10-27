
FROM openjdk:17-jdk-slim
LABEL authors="Jeremy"
WORKDIR application
COPY ../../../target/Electric-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java", "-jar", "Electric-0.0.1-SNAPSHOT.jar"]

#docker build -f ./src/main/dockerBase/Dockerfile -t car .
#FROM openjdk:17-jdk-slim