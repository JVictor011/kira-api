FROM eclipse-temurin:18-jdk AS BUILD

RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app

COPY . .

RUN mvn clean compile install -DskipTests=true

FROM eclipse-temurin:18-jdk

WORKDIR /app

COPY --from=BUILD /app/target/kira-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "app.jar"]