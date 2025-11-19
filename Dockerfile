FROM eclipse-temurin:24
WORKDIR /tmp
COPY ./target/devops.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "db:3306", "10000"]
