# Use Amazon Corretto 18 (supported and up-to-date)
FROM amazoncorretto:18

WORKDIR /tmp

# Copy your JAR into the container
COPY ./target/DevOpsLab1-0.1.0.2-jar-with-dependencies.jar /tmp

# Run your JAR
ENTRYPOINT ["java", "-jar", "DevOpsLab1-0.1.0.2-jar-with-dependencies.jar"]
