FROM amazoncorretto:17

WORKDIR /app

# Copy the correct JAR from target folder
COPY ./target/seMethods-0.1-alpha-2-jar-with-dependencies.jar /app/app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
