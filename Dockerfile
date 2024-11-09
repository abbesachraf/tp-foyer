
FROM openjdk:17-jdk-slim

EXPOSE 8082
COPY target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar
COPY src/main/resources/templates /app/resources/templates
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.0.jar"]
