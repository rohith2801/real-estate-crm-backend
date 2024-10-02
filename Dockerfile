FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY target/classes/application-docker.properties application.properties
ENTRYPOINT ["java", "-jar", "/app.jar"]
