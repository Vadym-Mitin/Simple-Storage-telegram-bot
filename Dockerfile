# Spring project is prepared to run on https://render.com/ webservice
#
# Build stage
FROM gradle:8.12-jdk23-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

LABEL org.name="drib"
# Package stage
FROM eclipse-temurin:23-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/app-telegram-rss-service-1.0.5.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]