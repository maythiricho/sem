FROM eclipse-temurin:18-jdk

WORKDIR /tmp

# Make sure to include the .jar extension
COPY ./target/DevOpsLab1-0.1.0.2-jar-with-dependencies.jar /tmp

ENTRYPOINT ["java", "-jar", "DevOpsLab1-0.1.0.2-jar-with-dependencies.jar"]