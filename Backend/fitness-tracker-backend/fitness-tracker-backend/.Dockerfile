FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/fitness-tracker-backend-0.0.1-SNAPSHOT.war app.war
CMD ["java", "-jar", "app.war"]
