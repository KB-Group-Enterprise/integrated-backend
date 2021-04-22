FROM maven:3.6.0-jdk-11-slim AS build
COPY src /mybackend/src
COPY pom.xml /mybackend
WORKDIR /mybackend
RUN mvn clean install

FROM openjdk:11.0-slim
EXPOSE 8080
COPY --from=build /mybackend/target/integrated-backend-0.0.1-SNAPSHOT.jar integrated-backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","integrated-backend-0.0.1-SNAPSHOT.jar"]
