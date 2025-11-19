FROM eclipse-temurin:24
COPY ./target/sem.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem.jar", "db:3306", "10000"]