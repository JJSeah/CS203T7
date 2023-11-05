# First stage: build the application
FROM maven:3.8.3-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests

# Second stage: create a slim image
FROM openjdk:17-jdk-slim
LABEL author="Jeremy"
WORKDIR /application
COPY --from=build /app/target/Electric-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java", "-jar", "Electric-0.0.1-SNAPSHOT.jar"]

#docker build -f ./src/main/dockerBase/Dockerfile -t car .
#FROM openjdk:17-jdk-slim