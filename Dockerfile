#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

ARG BUILD_ENVIRONMENT="development"

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dactive.profile=${BUILD_ENVIRONMENT}

#
# Package stage
#
FROM openjdk:11-jre-slim

ARG BUILD_ENVIRONMENT="development"

COPY --from=build /home/app/target/maricota-doces-0.0.1-SNAPSHOT.jar /usr/local/lib/maricota-doces.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar -Dspring.profiles.active={${BUILD_ENVIRONMENT}}","/usr/local/lib/maricota-doces.jar"]