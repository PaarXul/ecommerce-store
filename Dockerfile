FROM maven:3.8.5-openjdk-17 AS builder

COPY pom.xml /app/
COPY src /app/src/

WORKDIR /app

RUN mvn clean package -Dmaven.test.skip=true
#RUN mvn clean package -Dmaven.test.skip=true -X || exit 0

FROM openjdk:17-jdk-alpine
COPY --from=builder /app/target/ecomerce-project.jar ecomerce-project.jar
## COPY target/ecomerce-project.jar ecomerce-project.jar

ENTRYPOINT ["java","-jar","/ecomerce-project.jar"]