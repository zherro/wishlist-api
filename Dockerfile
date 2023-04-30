FROM openjdk:17-jdk-slim-buster

MAINTAINER zherro

COPY ./build/libs/*SNAPSHOT.jar  wishlist-api.jar

EXPOSE 8080

USER root

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "wishlist-api.jar"]



