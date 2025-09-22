FROM openjdk:18
COPY target/DevOpsLab1-0.1.0.2-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
