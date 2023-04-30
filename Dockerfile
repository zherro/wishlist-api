FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1

MAINTAINER zherro

COPY ./target/*SNAPSHOT.jar  nextgen-api.jar

EXPOSE 8080

RUN mkdir -p /src/main/resources
COPY ./checks/check.png  /src/main/resources/checks/check.png

USER root

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "nextgen-api.jar"]



