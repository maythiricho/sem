FROM eclipse-temurin:24

WORKDIR /app

# Copy the JAR built by Maven
COPY ./target/devops.jar app.jar

# Run Java app – db is the service name from docker-compose
ENTRYPOINT ["java", "-jar", "app.jar", "db:3306", "10000"]
