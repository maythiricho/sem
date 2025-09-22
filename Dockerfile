FROM openjdk:18
COPY target/DevOpsLab1-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
