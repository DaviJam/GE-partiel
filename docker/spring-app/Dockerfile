FROM maven:3.8.4-openjdk-11-slim as build
RUN mkdir gestionEcole
WORKDIR /gestionEcole
COPY ./ ./
RUN mvn clean package spring-boot:repackage -Dmaven.test.skip=true
RUN ls
RUN ls ./target

FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build /gestionEcole/target/*.jar /jar-files/gestionEcole.jar
WORKDIR /jar-files

EXPOSE 8080
CMD [ "java", "-jar", "-Dspring.profiles.active=prod", "gestionEcole.jar" ]
